package io.slifer.automation.reporter;

import org.apache.commons.io.FileUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HtmlReporter {
    
    private JsonReport json;
    
    public HtmlReporter(JsonReport json) {
        this.json = json;
    }
    
    public void generateReport() {
        Document document = Document.createShell("");
        writeHead(document);
        writeSummary(document.body());
        writeResultsSection(document.body(), HtmlResult.FAILED);
        writeResultsSection(document.body(), HtmlResult.STOPPED);
        writeResultsSection(document.body(), HtmlResult.SKIPPED);
        writeResultsSection(document.body(), HtmlResult.PASSED);
        writeScript(document.body());
        
        writeHtmlFile(document);
    }
    
    private void writeHead(Document document) {
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
    
    private void writeSummary(Element body) {
        body.appendElement("div").attr("class", "suite-summary bordered");
        
        Element summary = body.getElementsByAttributeValue("class", "suite-summary bordered").get(0);
        summary.appendElement("div").attr("class", "suite-name bordered").text("Suite Name Goes Here");
        
        writeSummaryItem(summary, false, "", "Total Tests", "9999");
        writeSummaryItem(summary, false, "txt-passed", "Tests Passed", "9999");
        writeSummaryItem(summary, false, "txt-failed", "Tests Failed", "9999");
        writeSummaryItem(summary, false, "txt-stopped", "Tests Stopped", "9999");
        writeSummaryItem(summary, false, "txt-skipped", "Tests Skipped", "9999");
        writeSummaryItem(summary, true, "", "Execution Time", "99:99:99.999");
        
        body.appendElement("br");
    }
    
    private void writeSummaryItem(Element summary, boolean wide, String valueAttribute, String label, String value) {
        String attribute = (wide) ? "summary-item bordered wide" : "summary-item bordered";
        summary.appendElement("div").attr("class", attribute);
        Element totalTestsItem = summary.getElementsByAttributeValue("class", attribute).last();
        totalTestsItem.appendElement("div").attr("class", "summary-item-label").text(label);
        totalTestsItem.appendElement("div").attr("class", "summary-item-value " + valueAttribute).text(value);
    }
    
    private void writeResultsSection(Element body, HtmlResult result) {
        body.appendElement("div").attr("class", "results-section bordered");
        Element section = body.getElementsByClass("results-section bordered").last();
        section.appendElement("div").attr("class", "section-name bordered " + result.resultsSectionStyle)
               .text(result.resultsSectionLabel);
        section.appendElement("div").attr("class", "test-classes bordered");
        Element classes = section.getElementsByClass("test-classes bordered").last();
        
        writeTestClass(classes);
        
        body.appendElement("br");
    }
    
    private void writeTestClass(Element classes) {
        classes.appendElement("div").attr("class", "test-class accordion").text("io.slifer.test.cases.SampleTest");
        classes.appendElement("div").attr("class", "test-methods panel hide");
        Element methods = classes.getElementsByClass("test-methods panel hide").last();
        
        writeTestMethod(methods);
    }
    
    private void writeTestMethod(Element methods) {
        methods.appendElement("div").attr("class", "test-method");
        Element testMethod = methods.getElementsByClass("test-method").last();
        testMethod.appendElement("div").attr("class", "method-name accordion")
                  .text("verifySomethingHappensWhenActionIsCompleted");
        testMethod.appendElement("span").attr("class", "method-detail");
        Element startTime = testMethod.getElementsByClass("method-detail").last();
        startTime.appendElement("span").attr("class", "method-detail-label").text("Start Time");
        startTime.appendElement("span").attr("class", "method-detail-value").text("99:99:99.999");
        Element duration = testMethod.getElementsByClass("method-detail").last();
        duration.appendElement("span").attr("class", "method-detail-label").text("Start Time");
        duration.appendElement("span").attr("class", "method-detail-value").text("99:99:99.999");
        testMethod.appendElement("div").attr("class", "method-logs");
        Element methodLogs = testMethod.getElementsByClass("method-logs").last();
        
        writeMethodLogs(methodLogs);
    }
    
    private void writeMethodLogs(Element methodLogs) {
        methodLogs.appendElement("div").attr("class", "log-entry")
                  .text("99:99:99.999 | INFO | This is a log entry for a test step.");
    }
    
    private void writeScript(Element body) {
        /*
        "var acc = document.getElementsByClassName("accordion");"+
        "var i;""+
        "for (i = 0; i < acc.length; i++) {"+
            "acc[i].addEventListener("click", function () {"+
                "this.classList.toggle("active");"+
                "var panel = this.nextElementSibling;"+
                "if (panel.style.display === "block") {"+
                "panel.style.display = "none";"+
                "}"+
                "else {"+
                "panel.style.display = "block";"+
                "}"+
            "});"+
        "}"
         */
        String script = "var acc = document.getElementsByClassName(\"accordion\");" +
                "var i;" +
                "for (i = 0; i < acc.length; i++) {" +
                "acc[i].addEventListener(\"click\", function () {" +
                "this.classList.toggle(\"active\");" +
                "var panel = this.nextElementSibling;" +
                "if (panel.style.display === \"block\") {" +
                "panel.style.display = \"none\";}" +
                "else {panel.style.display = \"block\";}});}";
        body.appendElement("script").text(script);
    }
    
    private void writeHtmlFile(Document document) {
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
