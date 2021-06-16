package io.slifer.automation.reporter.testng;

import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.collections.Lists;
import org.testng.xml.XmlSuite.ParallelMode;

import java.util.List;

/**
 * Copied from {@link org.testng.reporters.EmailableReporter2} due to being protected and static within the TestNG
 * project.
 *
 * @author TestNG
 */
public class SuiteResult {
    
    private final String suiteName;
    private final List<TestResult> testResults = Lists.newArrayList();
    private final ParallelMode mode;
    
    public SuiteResult(ISuite suite) {
        suiteName = suite.getName();
        mode = suite.getXmlSuite().getParallel();
        for (ISuiteResult suiteResult : suite.getResults().values()) {
            testResults.add(new TestResult(suiteResult.getTestContext()));
        }
    }
    
    public String getSuiteName() {
        return suiteName;
    }
    
    /**
     * @return the test results (possibly empty)
     */
    public List<TestResult> getTestResults() {
        return testResults;
    }
    
    public ParallelMode getParallelMode() {
        return mode;
    }
}
