package io.slifer.automation.reporter.testng;

import java.util.List;

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
