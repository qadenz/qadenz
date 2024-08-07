/*
Copyright Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.reporter;

import dev.qadenz.automation.logs.Loggers;
import dev.qadenz.automation.reporter.model.JsonClass;
import dev.qadenz.automation.reporter.model.JsonLogEvent;
import dev.qadenz.automation.reporter.model.JsonMethod;
import dev.qadenz.automation.reporter.model.JsonReport;
import dev.qadenz.automation.reporter.model.JsonTest;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;

import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
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
    
    private static final Logger LOG = Loggers.getReporterLogger();
    
    private JsonReport jsonReport;
    private Document document;
    
    public HtmlReporter(JsonReport jsonReport) {
        this.jsonReport = jsonReport;
        this.document = Document.createShell("");
    }
    
    public void generateReport(String outputPath, String fileName) {
        writeComment();
        writeHead();
        writeSummary();
        for (JsonTest jsonTest : jsonReport.getTests()) {
            writeTestSection(jsonTest);
        }
        
        writeScreenshotModal();
        writeAccordionScript();
        writeModalScript();
        
        writeHtmlFile(outputPath, fileName);
    }
    
    private void writeHead() {
        Element head = document.head();
        head.appendElement("meta").attr("charset", "UTF-8");
        head.appendElement("title").text("Test Report");
        head.appendElement("link")
            .attr("href", "https://fonts.googleapis.com/css2?family=Source+Sans+Pro:wght@600&display=swap")
            .attr("rel", "stylesheet");
        head.appendElement("link")
            .attr("href", "https://fonts.googleapis.com/css2?family=Source+Sans+Pro:wght@900&display=swap")
            .attr("rel", "stylesheet");
        head.appendElement("style").text(loadAndCompressCss());
    }
    
    private String loadAndCompressCss() {
        String contents = null;
        try {
            InputStream inputStream = getClass().getResourceAsStream("/html/report.css");
            contents = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        }
        catch (Exception exception) {
            LOG.error("Error loading CSS file: {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
        }
        
        // Yeah, it's hacky but it works. I'll revisit this another time.
        return contents.replace("    ", "").replace(" {", "{").replace(": ", ":").replaceAll("\n", "");
    }
    
    private void writeTestSection(JsonTest jsonTest) {
        String testName = jsonTest.getTestName() + " Results";
        
        document.body().appendElement("div").addClass("test-section bordered");
        Element section = document.body().getElementsByClass("test-section bordered").last();
        section.appendElement("div").addClass("test-name bordered").text(testName);
        
        if (!jsonTest.getFailedConfigurations().isEmpty()) {
            writeResultsSection(section, jsonTest.getFailedConfigurations(), HtmlResult.FAILED_CONFIGS);
        }
        if (!jsonTest.getSkippedConfigurations().isEmpty()) {
            writeResultsSection(section, jsonTest.getSkippedConfigurations(), HtmlResult.SKIPPED_CONFIGS);
        }
        if (!jsonTest.getFailedTests().isEmpty()) {
            writeResultsSection(section, jsonTest.getFailedTests(), HtmlResult.FAILED_TESTS);
        }
        if (!jsonTest.getStoppedTests().isEmpty()) {
            writeResultsSection(section, jsonTest.getStoppedTests(), HtmlResult.STOPPED_TESTS);
        }
        if (!jsonTest.getSkippedTests().isEmpty()) {
            writeResultsSection(section, jsonTest.getSkippedTests(), HtmlResult.SKIPPED_TESTS);
        }
        if (!jsonTest.getPassedTests().isEmpty()) {
            writeResultsSection(section, jsonTest.getPassedTests(), HtmlResult.PASSED_TESTS);
        }
        
        document.body().appendElement("br");
    }
    
    private void writeSummary() {
        document.body().appendElement("div").addClass("suite-summary bordered");
        
        Element summary = document.body().getElementsByClass("suite-summary bordered").get(0);
        summary.appendElement("div").addClass("suite-name bordered").text(jsonReport.getSuiteName());
        
        int failed = 0;
        for (JsonTest jsonTest : jsonReport.getTests()) {
            failed += jsonTest.getTotalFailedTests();
        }
        
        int stopped = 0;
        for (JsonTest jsonTest : jsonReport.getTests()) {
            stopped += jsonTest.getTotalStoppedTests();
        }
        
        int skipped = 0;
        for (JsonTest jsonTest : jsonReport.getTests()) {
            skipped += jsonTest.getTotalSkippedTests();
        }
        
        int passed = 0;
        for (JsonTest jsonTest : jsonReport.getTests()) {
            passed += jsonTest.getTotalPassedTests();
        }
        
        int total = failed + stopped + skipped + passed;
        
        writeSummaryItem(summary, true, "Launched", "", jsonReport.getSuiteStartDate());
        writeSummaryItem(summary, false, "Total Tests", "", String.valueOf(total));
        writeSummaryItem(summary, HtmlResult.PASSED_TESTS, String.valueOf(passed));
        writeSummaryItem(summary, HtmlResult.FAILED_TESTS, String.valueOf(failed));
        writeSummaryItem(summary, HtmlResult.STOPPED_TESTS, String.valueOf(stopped));
        writeSummaryItem(summary, HtmlResult.SKIPPED_TESTS, String.valueOf(skipped));
        writeSummaryItem(summary, true, "Execution Time", "", jsonReport.getSuiteExecutionTime());
        
        document.body().appendElement("br");
    }
    
    private void writeSummaryItem(Element summary, HtmlResult result, String value) {
        writeSummaryItem(summary, false, result.getSummaryItemLabel(), result.getSummaryItemValueStyle(), value);
    }
    
    private void writeSummaryItem(Element summary, boolean wide, String label, String style, String value) {
        String attribute = (wide) ? "summary-item bordered wide" : "summary-item bordered";
        summary.appendElement("div").addClass(attribute);
        Element totalTestsItem = summary.getElementsByClass(attribute).last();
        totalTestsItem.appendElement("div").addClass("summary-item-label").text(label);
        totalTestsItem.appendElement("div").addClass("summary-item-value " + style).text(value);
    }
    
    private void writeResultsSection(Element testSection, List<JsonClass> jsonClasses, HtmlResult result) {
        // write main section structure
        testSection.appendElement("div").addClass("results-section");
        Element section = document.body().getElementsByClass("results-section").last();
        
        int methodCount = 0;
        for (JsonClass jsonClass : jsonClasses) {
            methodCount += jsonClass.getMethods().size();
        }
        String header = methodCount + " " + result.getResultsSectionLabel();
        
        section.appendElement("div").addClass("section-name bordered " + result.getResultsSectionStyle()).text(header);
        section.appendElement("div").addClass("test-classes bordered");
        Element classes = section.getElementsByClass("test-classes bordered").last();
        
        for (JsonClass jsonClass : jsonClasses) {
            // write a new class entry
            Element testClass;
            classes.appendElement("div").addClass("test-class");
            testClass = classes.getElementsByClass("test-class").last();
            testClass.appendElement("div").addClass("class-name accordion").text(jsonClass.getClassName());
            testClass.appendElement("div").addClass("test-methods panel hide");
            
            for (JsonMethod jsonMethod : jsonClass.getMethods()) {
                // write a new test method entry
                Element testMethods = testClass.getElementsByClass("test-methods panel hide").last();
                testMethods.appendElement("div").addClass("test-method");
                Element method = testMethods.getElementsByClass("test-method").last();
                String methodName = (jsonMethod.getParameters() == null) ? jsonMethod.getMethodName() :
                        jsonMethod.getMethodName() + " | " + jsonMethod.getParameters();
                method.appendElement("div").addClass("method-name accordion").text(methodName);
                method.appendElement("div").addClass("method-details panel hide");
                
                // write the method details
                Element methodDetails = method.getElementsByClass("method-details panel hide").last();
                writeMethodDetailItem(methodDetails, "Start Time: ", jsonMethod.getTestStartTime());
                writeMethodDetailItem(methodDetails, "Duration: ", jsonMethod.getTestExecutionTime());
                
                // write the logging output for the test method
                methodDetails.appendElement("div").addClass("method-logs");
                Element methodLogs = method.getElementsByClass("method-logs").last();
                for (JsonLogEvent jsonLogEvent : jsonMethod.getLogEvents()) {
                    writeMethodLogs(jsonLogEvent, methodLogs);
                }
            }
        }
    }
    
    private void writeMethodDetailItem(Element methodDetails, String label, String value) {
        methodDetails.appendElement("span").addClass("method-detail");
        Element duration = methodDetails.getElementsByClass("method-detail").last();
        duration.appendElement("span").addClass("method-detail-label").text(label);
        duration.appendElement("span").addClass("method-detail-value").text(value);
    }
    
    private void writeMethodLogs(JsonLogEvent jsonLogEvent, Element methodLogs) {
        String message = jsonLogEvent.getLogMessage().replace("\n", "<br>");
        if (jsonLogEvent.getLogMessage().contains("| WARN |")) {
            methodLogs.appendElement("div").addClass("log-entry bold").html(message);
        }
        else if (jsonLogEvent.getLogMessage().contains("| Result - PASS")) {
            methodLogs.appendElement("div").addClass("log-entry log-pass").html(message);
        }
        else if (jsonLogEvent.getLogMessage().contains("| Result - FAIL")) {
            methodLogs.appendElement("div").addClass("log-entry log-fail").html(message);
        }
        else {
            methodLogs.appendElement("div").addClass("log-entry").html(message);
        }
        
        if (jsonLogEvent.getScreenshot() != null) {
            methodLogs.appendElement("div").addClass("log-entry screenshot").text("View Screenshot");
            Element screenshot = methodLogs.getElementsMatchingText("View Screenshot").last();
            screenshot.appendElement("img").addClass("image")
                      .attr("style", "display: none")
                      .attr("src", "data:image/png;base64, " + jsonLogEvent.getScreenshot());
        }
    }
    
    private void writeScreenshotModal() {
        document.body().appendElement("div").attr("id", "modal");
        Element modal = document.getElementById("modal");
        modal.appendElement("span").addClass("close").html("&times;");
        modal.appendElement("img").addClass("modal-content").attr("id", "modal-img");
    }
    
    private void writeAccordionScript() {
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
    
    private void writeModalScript() {
        String js = "<script>var modal = document.getElementById(\"modal\");" +
                "var scr = document.getElementsByClassName(\"screenshot\");" +
                "for (var i = 0; i < scr.length; i++) {" +
                "scr[i].addEventListener(\"click\", function () {" +
                "modal.style.display = \"block\";" +
                "var modalImg = document.getElementById(\"modal-img\");" +
                "var imgSrc = this.getElementsByClassName(\"image\")[0];" +
                "modalImg.src = imgSrc.src;});}" +
                "var span = document.getElementsByClassName(\"close\")[0];" +
                "span.onclick = function () {" +
                "modal.style.display = \"none\";};</script>";
        DataNode script = new DataNode(js);
        document.body().appendChild(script);
    }
    
    private void writeComment() {
        String comment = "<!--\n" +
                "This report was generated by Qadenz.\n\n" +
                "Copyright Tim Slifer\n\n" +
                "Licensed under the PolyForm Internal Use License, Version 1.0.0 (the \"License\");\n" +
                "you may not use this file except in compliance with the License.\n" +
                "A copy of the License may be obtained at\n\n" +
                "https://polyformproject.org/licenses/internal-use/1.0.0/\n" +
                "-->";
        
        document.select("html").before(comment);
    }
    
    private void writeHtmlFile(String outputPath, String fileName) {
        try {
            File file = new File(outputPath, fileName + ".html");
            FileUtils.writeStringToFile(file, document.outerHtml(), StandardCharsets.UTF_8);
        }
        catch (Exception exception) {
            LOG.error("Error writing HTML file :: {}: {}", exception.getClass().getSimpleName(),
                    exception.getMessage());
        }
    }
    
    private enum HtmlResult {
        FAILED_CONFIGS("txt-failed", "Tests Failed", "bg-failed", "Failed Configurations"),
        SKIPPED_CONFIGS("txt-skipped", "Tests Skipped", "bg-skipped", "Skipped Configurations"),
        FAILED_TESTS("txt-failed", "Tests Failed", "bg-failed", "Failed Tests"),
        STOPPED_TESTS("txt-stopped", "Tests Stopped", "bg-stopped", "Stopped Tests"),
        SKIPPED_TESTS("txt-skipped", "Tests Skipped", "bg-skipped", "Skipped Tests"),
        PASSED_TESTS("txt-passed", "Tests Passed", "bg-passed", "Passed Tests");
        
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
