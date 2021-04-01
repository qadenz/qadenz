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
