package io.slifer.automation.reporter;

import io.slifer.automation.reporter.model.JsonReport;
import io.slifer.automation.reporter.model.JsonTest;
import io.slifer.automation.reporter.model.JsonTestLog;
import org.apache.commons.io.FileUtils;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

/**
 * Constructs and writes a pre-designed HTML visualization of the Suite configuration, logging events for each test, and
 * embedded screenshots for failed and stopped tests. This report was built in the spirit of TestNG's emailable-report,
 * which provides a simple, single-file view of relevant test result data. This report adds a few UI conveniences such
 * as expanding and collapsing results sections, distinction between failed and stopped tests (assertion fails vs other
 * exceptions), as well as embedded screenshots on all non-passing tests.
 *
 * @author Tim Slifer
 */
public class HtmlReporter {
    
    private JsonReport json;
    private Document document;
    
    public HtmlReporter(JsonReport json) {
        this.json = json;
        this.document = Document.createShell("");
    }
    
    public void generateReport() {
        writeHead();
        writeSummary();
        if (json.getFailedTests().size() > 0) {
            writeResultsSection(json.getFailedTests(), HtmlResult.FAILED);
        }
        if (json.getStoppedTests().size() > 0) {
            writeResultsSection(json.getStoppedTests(), HtmlResult.STOPPED);
        }
        if (json.getSkippedTests().size() > 0) {
            writeResultsSection(json.getSkippedTests(), HtmlResult.SKIPPED);
        }
        if (json.getPassedTests().size() > 0) {
            writeResultsSection(json.getPassedTests(), HtmlResult.PASSED);
        }
        writeScript();
        
        writeHtmlFile();
    }
    
    private void writeHead() {
        Element head = document.head();
        head.appendElement("meta").attr("charset", "UTF-8");
        head.appendElement("title").text("Test Report");
        head.appendElement("link")
            .attr("href", "https://fonts.googleapis.com/css2?family=Source+Sans+Pro:wght@600&display=swap")
            .attr("rel", "stylesheet");
        head.appendElement("style").text(loadAndMinifyCss());
    }
    
    private String loadAndMinifyCss() {
        String contents = null;
        try {
            Path path = Paths.get(ClassLoader.getSystemResource("html/report.css").toURI());
            contents = Files.readString(path, StandardCharsets.UTF_8);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        
        return contents.replace("    ", "").replace(" {", "{").replace(": ", ":").replaceAll("\n", "");
    }
    
    private void writeSummary() {
        document.body().appendElement("div").addClass("suite-summary bordered");
        
        Element summary = document.body().getElementsByClass("suite-summary bordered").get(0);
        summary.appendElement("div").addClass("suite-name bordered").text(json.getSuiteName());
        
        int passed = json.getPassedTests().size();
        int failed = json.getFailedTests().size();
        int stopped = json.getStoppedTests().size();
        int skipped = json.getSkippedTests().size();
        int total = passed + failed + stopped + skipped;
        
        writeSummaryItem(summary, true, "Launched", "", json.getSuiteStartDate());
        writeSummaryItem(summary, false, "Total Tests", "", String.valueOf(total));
        writeSummaryItem(summary, HtmlResult.PASSED, String.valueOf(passed));
        writeSummaryItem(summary, HtmlResult.FAILED, String.valueOf(failed));
        writeSummaryItem(summary, HtmlResult.STOPPED, String.valueOf(stopped));
        writeSummaryItem(summary, HtmlResult.SKIPPED, String.valueOf(skipped));
        writeSummaryItem(summary, true, "Execution Time", "", json.getSuiteExecutionTime());
        
        document.body().appendElement("br");
    }
    
    private void writeSummaryItem(Element summary, HtmlResult result, String value) {
        writeSummaryItem(summary, false, result.summaryItemLabel, result.summaryItemValueStyle, value);
    }
    
    private void writeSummaryItem(Element summary, boolean wide, String label, String style, String value) {
        String attribute = (wide) ? "summary-item bordered wide" : "summary-item bordered";
        summary.appendElement("div").addClass(attribute);
        Element totalTestsItem = summary.getElementsByClass(attribute).last();
        totalTestsItem.appendElement("div").addClass("summary-item-label").text(label);
        totalTestsItem.appendElement("div").addClass("summary-item-value " + style).text(value);
    }
    
    private void writeResultsSection(List<JsonTest> jsonTests, HtmlResult result) {
        // write main section structure
        document.body().appendElement("div").addClass("results-section bordered");
        Element section = document.body().getElementsByClass("results-section bordered").last();
        section.appendElement("div").addClass("section-name bordered " + result.resultsSectionStyle)
               .text(result.resultsSectionLabel);
        section.appendElement("div").addClass("test-classes bordered");
        Element classes = section.getElementsByClass("test-classes bordered").last();
        
        Comparator<JsonTest> comparator = Comparator.comparing(JsonTest::getClassName)
                                                    .thenComparing(JsonTest::getTestName);
        jsonTests.sort(comparator);
        
        for (JsonTest jsonTest : jsonTests) {
            Element testClass;
            // if the class name has not yet been written, write it... otherwise, append to the existing class name
            if (classes.getElementsContainingText(jsonTest.getClassName()).size() == 0) {
                classes.appendElement("div").addClass("test-class");
                testClass = classes.getElementsByClass("test-class").last();
                testClass.appendElement("div").addClass("class-name accordion").text(jsonTest.getClassName());
                testClass.appendElement("div").addClass("test-methods panel hide");
            }
            else {
                testClass = classes.getElementsByClass("test-class").last();
            }
            // write a new test method entry
            Element testMethods = testClass.getElementsByClass("test-methods panel hide").last();
            testMethods.appendElement("div").addClass("test-method");
            Element method = testMethods.getElementsByClass("test-method").last();
            method.appendElement("div").addClass("method-name accordion").text(jsonTest.getTestName());
            method.appendElement("div").addClass("method-details panel hide");
            
            // write the method details
            Element methodDetails = method.getElementsByClass("method-details panel hide").last();
            writeMethodDetailItem(methodDetails, "Start Time: ", jsonTest.getTestStartTime());
            writeMethodDetailItem(methodDetails, "Duration: ", jsonTest.getTestExecutionTime());
            
            // write the logging output for the test method
            methodDetails.appendElement("div").addClass("method-logs");
            Element methodLogs = method.getElementsByClass("method-logs").last();
            for (JsonTestLog log : jsonTest.getLogs()) {
                writeMethodLogs(log, methodLogs);
            }
            if (jsonTest.getScreenshot() != null) {
                methodDetails.appendElement("div").addClass("screenshot");
                Element screenshot = methodDetails.getElementsByClass("screenshot").last();
                screenshot.appendElement("img").attr("src", "data:image/png;base64, " + jsonTest.getScreenshot());
            }
        }
        
        document.body().appendElement("br");
    }
    
    private void writeMethodDetailItem(Element methodDetails, String label, String value) {
        methodDetails.appendElement("span").addClass("method-detail");
        Element duration = methodDetails.getElementsByClass("method-detail").last();
        duration.appendElement("span").addClass("method-detail-label").text(label);
        duration.appendElement("span").addClass("method-detail-value").text(value);
    }
    
    private void writeMethodLogs(JsonTestLog log, Element methodLogs) {
        String event = log.getTimestamp().split(" ")[1] +
                " | " + log.getLevel() +
                " | " + log.getLogger().substring(log.getLogger().lastIndexOf(".") + 1) +
                " | " + log.getMessage();
        
        methodLogs.appendElement("div").addClass("log-entry")
                  .text(event);
    }
    
    private void writeScript() {
        String js = "<script>var acc = document.getElementsByClassName(\"accordion\");" +
                "for (var i = 0; i < acc.length; i++) {" +
                "acc[i].addEventListener(\"click\", function () {" +
                "this.classList.toggle(\"active\");" +
                "var panel = this.nextElementSibling;" +
                "if (panel.style.display === \"block\") {" +
                "panel.style.display = \"none\";}" +
                "else {panel.style.display = \"block\";}});}</script>";
        DataNode script = new DataNode(js);
        document.body().appendChild(script);
    }
    
    private void writeHtmlFile() {
        try {
            File file = new File("Automation-Report.html");
            FileUtils.writeStringToFile(file, document.outerHtml(), StandardCharsets.UTF_8);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    
    private enum HtmlResult {
        FAILED("txt-failed", "Tests Failed", "bg-failed", "Failed Tests"),
        PASSED("txt-passed", "Tests Passed", "bg-passed", "Passed Tests"),
        SKIPPED("txt-skipped", "Tests Skipped", "bg-skipped", "Skipped Tests"),
        STOPPED("txt-stopped", "Tests Stopped", "bg-stopped", "Stopped Tests");
        
        private String summaryItemValueStyle;
        private String summaryItemLabel;
        private String resultsSectionStyle;
        private String resultsSectionLabel;
        
        HtmlResult(String summaryItemValueStyle, String summaryItemLabel, String resultsSectionStyle,
                String resultsSectionLabel) {
            this.summaryItemValueStyle = summaryItemValueStyle;
            this.summaryItemLabel = summaryItemLabel;
            this.resultsSectionStyle = resultsSectionStyle;
            this.resultsSectionLabel = resultsSectionLabel;
        }
        
        public String getSummaryItemValueStyle() {
            return summaryItemValueStyle;
        }
        
        public String getSummaryItemLabel() {
            return summaryItemLabel;
        }
        
        public String getResultsSectionStyle() {
            return resultsSectionStyle;
        }
        
        public String getResultsSectionLabel() {
            return resultsSectionLabel;
        }
    }
}
