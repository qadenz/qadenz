package io.slifer.automation.reporter;

import org.apache.commons.io.FileUtils;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
        writeResultsSection(HtmlResult.FAILED);
        writeResultsSection(HtmlResult.STOPPED);
        writeResultsSection(HtmlResult.SKIPPED);
        writeResultsSection(HtmlResult.PASSED);
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
        
        writeSummaryItem(summary, false, "", "Total Tests", String.valueOf(total));
        writeSummaryItem(summary, false, "txt-passed", "Tests Passed", String.valueOf(passed));
        writeSummaryItem(summary, false, "txt-failed", "Tests Failed", String.valueOf(failed));
        writeSummaryItem(summary, false, "txt-stopped", "Tests Stopped", String.valueOf(stopped));
        writeSummaryItem(summary, false, "txt-skipped", "Tests Skipped", String.valueOf(skipped));
        writeSummaryItem(summary, true, "", "Execution Time", "99:99:99.999");
        
        document.body().appendElement("br");
    }
    
    private void writeSummaryItem(Element summary, boolean wide, String valueAttribute, String label, String value) {
        String attribute = (wide) ? "summary-item bordered wide" : "summary-item bordered";
        summary.appendElement("div").addClass(attribute);
        Element totalTestsItem = summary.getElementsByClass(attribute).last();
        totalTestsItem.appendElement("div").addClass("summary-item-label").text(label);
        totalTestsItem.appendElement("div").addClass("summary-item-value " + valueAttribute).text(value);
    }
    
    private void writeResultsSection(HtmlResult result) {
        document.body().appendElement("div").addClass("results-section bordered");
        Element section = document.body().getElementsByClass("results-section bordered").last();
        section.appendElement("div").addClass("section-name bordered " + result.resultsSectionStyle)
               .text(result.resultsSectionLabel);
        section.appendElement("div").addClass("test-classes bordered");
        Element classes = section.getElementsByClass("test-classes bordered").last();
        
        writeTestClass(classes);
        
        document.body().appendElement("br");
    }
    
    private void writeTestClass(Element classes) {
        classes.appendElement("div").addClass("test-class");
        Element testClass = classes.getElementsByClass("test-class").last();
        testClass.appendElement("div").addClass("class-name accordion").text("io.slifer.test.cases.SampleTest");
        testClass.appendElement("div").addClass("test-methods panel hide");
        Element methods = testClass.getElementsByClass("test-methods panel hide").last();
        
        writeTestMethod(methods);
    }
    
    private void writeTestMethod(Element methods) {
        methods.appendElement("div").addClass("test-method");
        Element testMethod = methods.getElementsByClass("test-method").last();
        testMethod.appendElement("div").addClass("method-name accordion")
                  .text("verifySomethingHappensWhenActionIsCompleted");
        testMethod.appendElement("div").addClass("method-details panel hide");
        Element methodDetails = testMethod.getElementsByClass("method-details panel hide").last();
        
        writeMethodDetailItem(methodDetails, "Start Time: ", "99:99:99.999");
        writeMethodDetailItem(methodDetails, "Duration: ", "99:99:99.999");
        
        methodDetails.appendElement("div").addClass("method-logs");
        Element methodLogs = testMethod.getElementsByClass("method-logs").last();
        
        writeMethodLogs(methodLogs);
    }
    
    private void writeMethodDetailItem(Element methodDetails, String label, String value) {
        methodDetails.appendElement("span").addClass("method-detail");
        Element duration = methodDetails.getElementsByClass("method-detail").last();
        duration.appendElement("span").addClass("method-detail-label").text(label);
        duration.appendElement("span").addClass("method-detail-value").text(value);
    }
    
    private void writeMethodLogs(Element methodLogs) {
        methodLogs.appendElement("div").addClass("log-entry")
                  .text("99:99:99.999 | INFO | This is a log entry for a test step.");
    }
    
    private void writeScript() {
        String js = "<script>var acc = document.getElementsByClassName(\"accordion\");" +
                // "var i;" +
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
    
    public static void main(String[] args) {
        HtmlReporter r = new HtmlReporter(null);
        r.generateReport();
        
        // System.out.println(r.loadAndMinifyCss());
    }
}
