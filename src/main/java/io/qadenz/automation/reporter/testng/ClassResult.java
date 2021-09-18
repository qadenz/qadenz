package io.qadenz.automation.reporter.testng;

import java.util.List;

/**
 * Copied from {@link org.testng.reporters.EmailableReporter2} due to being protected and static within the TestNG
 * project.
 *
 * @author TestNG
 */
public class ClassResult {
    
    private final String className;
    private final List<MethodResult> methodResults;
    
    /**
     * @param className the class name
     * @param methodResults the non-null, non-empty {@link MethodResult} list
     */
    public ClassResult(String className, List<MethodResult> methodResults) {
        this.className = className;
        this.methodResults = methodResults;
    }
    
    public String getClassName() {
        return className;
    }
    
    /**
     * @return the non-null, non-empty {@link MethodResult} list
     */
    public List<MethodResult> getMethodResults() {
        return methodResults;
    }
}
