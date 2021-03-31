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
        
        summary.appendElement("div").attr("class", "summary-item bordered");
        Element totalTestsItem = summary.getElementsByAttributeValue("class", "summary-item bordered").get(0);
        totalTestsItem.appendElement("div").attr("class", "summary-item-label").text("Total Tests");
        totalTestsItem.appendElement("div").attr("class", "summary-item-value").text("9999");
        
        summary.appendElement("div").attr("class", "summary-item bordered");
        Element testsPassedItem = summary.getElementsByAttributeValue("class", "summary-item bordered").get(1);
        testsPassedItem.appendElement("div").attr("class", "summary-item-label").text("Tests Passed");
        testsPassedItem.appendElement("div").attr("class", "summary-item-value txt-passed").text("9999");
        
        summary.appendElement("div").attr("class", "summary-item bordered");
        Element testsFailedItem = summary.getElementsByAttributeValue("class", "summary-item bordered").get(2);
        testsFailedItem.appendElement("div").attr("class", "summary-item-label").text("Tests Failed");
        testsFailedItem.appendElement("div").attr("class", "summary-item-value txt-failed").text("9999");
        
        summary.appendElement("div").attr("class", "summary-item bordered");
        Element testsStoppedItem = summary.getElementsByAttributeValue("class", "summary-item bordered").get(3);
        testsStoppedItem.appendElement("div").attr("class", "summary-item-label").text("Tests Stopped");
        testsStoppedItem.appendElement("div").attr("class", "summary-item-value txt-stopped").text("9999");
        
        summary.appendElement("div").attr("class", "summary-item bordered");
        Element testsSkippedItem = summary.getElementsByAttributeValue("class", "summary-item bordered").get(4);
        testsSkippedItem.appendElement("div").attr("class", "summary-item-label").text("Tests Skipped");
        testsSkippedItem.appendElement("div").attr("class", "summary-item-value txt-skipped").text("9999");
        
        summary.appendElement("div").attr("class", "summary-item bordered wide");
        Element executionTimeItem = summary.getElementsByAttributeValue("class", "summary-item bordered wide").get(0);
        executionTimeItem.appendElement("div").attr("class", "summary-item-label").text("Execution Time");
        executionTimeItem.appendElement("div").attr("class", "summary-item-value").text("99:99:99.999");
        
        body.appendElement("br");
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
    
    public static void main(String[] args) {
        HtmlReporter r = new HtmlReporter(null);
        r.generateReport();
        
        // System.out.println(r.loadAndMinifyCss());
    }
}
