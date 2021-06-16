package io.slifer.automation.reporter.testng;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.collections.Lists;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Copied from {@link org.testng.reporters.EmailableReporter2} due to being protected and static within the TestNG
 * project. Edited to introduce the concept of "Stopped" tests as a new category of results.
 *
 * @author TestNG & Tim Slifer
 */
public class TestResult {
    
    protected static final Comparator<ITestResult> RESULT_COMPARATOR =
            Comparator.comparing((ITestResult o) -> o.getTestClass().getName())
                      .thenComparing(o -> o.getMethod().getMethodName());
    
    private final String testName;
    private final List<ClassResult> failedConfigurationResults;
    private final List<ClassResult> failedTestResults;
    private final List<ClassResult> stoppedTestResults;
    private final List<ClassResult> skippedConfigurationResults;
    private final List<ClassResult> skippedTestResults;
    private final List<ClassResult> retriedTestResults;
    private final List<ClassResult> passedTestResults;
    private final int failedTestCount;
    private final int retriedTestCount;
    private final int skippedTestCount;
    private final int stoppedTestCount;
    private final int passedTestCount;
    private final long duration;
    private final String includedGroups;
    private final String excludedGroups;
    
    public TestResult(ITestContext context) {
        testName = context.getName();
        
        Set<ITestResult> failedConfigurations = context.getFailedConfigurations().getAllResults();
        Set<ITestResult> failedTests = getFailuresWithAssertionFailures(context.getFailedTests().getAllResults());
        Set<ITestResult> stoppedTests = getFailuresWithOtherExceptions(context.getFailedTests().getAllResults());
        Set<ITestResult> skippedConfigurations = context.getSkippedConfigurations().getAllResults();
        Set<ITestResult> rawSkipped = context.getSkippedTests().getAllResults();
        Set<ITestResult> skippedTests = pruneSkipped(rawSkipped);
        Set<ITestResult> retriedTests = pruneRetried(rawSkipped);
        
        Set<ITestResult> passedTests = context.getPassedTests().getAllResults();
        
        failedConfigurationResults = groupResults(failedConfigurations);
        failedTestResults = groupResults(failedTests);
        stoppedTestResults = groupResults(stoppedTests);
        skippedConfigurationResults = groupResults(skippedConfigurations);
        skippedTestResults = groupResults(skippedTests);
        retriedTestResults = groupResults(retriedTests);
        passedTestResults = groupResults(passedTests);
        
        failedTestCount = failedTests.size();
        stoppedTestCount = stoppedTests.size();
        retriedTestCount = retriedTests.size();
        skippedTestCount = skippedTests.size();
        passedTestCount = passedTests.size();
        
        duration = context.getEndDate().getTime() - context.getStartDate().getTime();
        
        includedGroups = formatGroups(context.getIncludedGroups());
        excludedGroups = formatGroups(context.getExcludedGroups());
    }
    
    private static Set<ITestResult> getFailuresWithAssertionFailures(Set<ITestResult> results) {
        return results.stream()
                      .filter(result -> result.getThrowable() instanceof AssertionError).collect(Collectors.toSet());
    }
    
    private static Set<ITestResult> getFailuresWithOtherExceptions(Set<ITestResult> results) {
        return results.stream()
                      .filter(result -> !(result.getThrowable() instanceof AssertionError)).collect(Collectors.toSet());
    }
    
    private static Set<ITestResult> pruneSkipped(Set<ITestResult> results) {
        return results.stream().filter(result -> !result.wasRetried()).collect(Collectors.toSet());
    }
    
    private static Set<ITestResult> pruneRetried(Set<ITestResult> results) {
        return results.stream().filter(ITestResult::wasRetried).collect(Collectors.toSet());
    }
    
    /**
     * Groups test results by method and then by class.
     */
    protected List<ClassResult> groupResults(Set<ITestResult> results) {
        List<ClassResult> classResults = Lists.newArrayList();
        if (!results.isEmpty()) {
            List<MethodResult> resultsPerClass = Lists.newArrayList();
            List<ITestResult> resultsPerMethod = Lists.newArrayList();
            
            List<ITestResult> resultsList = Lists.newArrayList(results);
            resultsList.sort(RESULT_COMPARATOR);
            Iterator<ITestResult> resultsIterator = resultsList.iterator();
            assert resultsIterator.hasNext();
            
            ITestResult result = resultsIterator.next();
            resultsPerMethod.add(result);
            
            String previousClassName = result.getTestClass().getName();
            String previousMethodName = result.getMethod().getMethodName();
            while (resultsIterator.hasNext()) {
                result = resultsIterator.next();
                
                String className = result.getTestClass().getName();
                if (!previousClassName.equals(className)) {
                    // Different class implies different method
                    assert !resultsPerMethod.isEmpty();
                    resultsPerClass.add(new MethodResult(resultsPerMethod));
                    resultsPerMethod = Lists.newArrayList();
                    
                    assert !resultsPerClass.isEmpty();
                    classResults.add(new ClassResult(previousClassName, resultsPerClass));
                    resultsPerClass = Lists.newArrayList();
                    
                    previousClassName = className;
                    previousMethodName = result.getMethod().getMethodName();
                }
                else {
                    String methodName = result.getMethod().getMethodName();
                    if (!previousMethodName.equals(methodName)) {
                        assert !resultsPerMethod.isEmpty();
                        resultsPerClass.add(new MethodResult(resultsPerMethod));
                        resultsPerMethod = Lists.newArrayList();
                        
                        previousMethodName = methodName;
                    }
                }
                resultsPerMethod.add(result);
            }
            assert !resultsPerMethod.isEmpty();
            resultsPerClass.add(new MethodResult(resultsPerMethod));
            assert !resultsPerClass.isEmpty();
            classResults.add(new ClassResult(previousClassName, resultsPerClass));
        }
        return classResults;
    }
    
    public String getTestName() {
        return testName;
    }
    
    /**
     * @return the results for failed configurations (possibly empty)
     */
    public List<ClassResult> getFailedConfigurationResults() {
        return failedConfigurationResults;
    }
    
    /**
     * @return the results for failed tests (possibly empty)
     */
    public List<ClassResult> getFailedTestResults() {
        return failedTestResults;
    }
    
    /**
     * @return the results for stopped tests (possibly empty)
     */
    public List<ClassResult> getStoppedTestResults() {
        return stoppedTestResults;
    }
    
    /**
     * @return the results for skipped configurations (possibly empty)
     */
    public List<ClassResult> getSkippedConfigurationResults() {
        return skippedConfigurationResults;
    }
    
    /**
     * @return the results for skipped tests (possibly empty)
     */
    public List<ClassResult> getSkippedTestResults() {
        return skippedTestResults;
    }
    
    public List<ClassResult> getRetriedTestResults() {
        return retriedTestResults;
    }
    
    /**
     * @return the results for passed tests (possibly empty)
     */
    public List<ClassResult> getPassedTestResults() {
        return passedTestResults;
    }
    
    public int getFailedTestCount() {
        return failedTestCount;
    }
    
    public int getStoppedTestCount() {
        return stoppedTestCount;
    }
    
    public int getSkippedTestCount() {
        return skippedTestCount;
    }
    
    public int getRetriedTestCount() {
        return retriedTestCount;
    }
    
    public int getPassedTestCount() {
        return passedTestCount;
    }
    
    public long getDuration() {
        return duration;
    }
    
    public String getIncludedGroups() {
        return includedGroups;
    }
    
    public String getExcludedGroups() {
        return excludedGroups;
    }
    
    /**
     * Formats an array of groups for display.
     */
    protected String formatGroups(String[] groups) {
        if (groups.length == 0) {
            return "";
        }
        
        StringBuilder builder = new StringBuilder();
        builder.append(groups[0]);
        for (int i = 1; i < groups.length; i++) {
            builder.append(", ").append(groups[i]);
        }
        return builder.toString();
    }
}
