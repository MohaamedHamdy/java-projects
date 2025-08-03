package org.example;
import org.apache.poi.xwpf.usermodel.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.Iterator;
import java.util.Map;

public class Main {
   public static void main(String[] args) throws IOException {
       String output = "test.docx";
       XWPFDocument document = new XWPFDocument();

       XWPFParagraph title = document.createParagraph();
       title.setAlignment(ParagraphAlignment.CENTER);
       XWPFRun titler = title.createRun();
       titler.setText("Generate Word");
       titler.setColor("080301");
       titler.setBold(true);
       titler.setFontFamily("Courier");
       titler.setFontSize(20);

        FileOutputStream out = new FileOutputStream(output);
        document.write(out);
        out.close();
        document.close();


   }
}



/*

 public static void main(String[] args) throws IOException {
        File jsonFile = new File("src/main/java/org/example/data.json");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(jsonFile);

        // Create Word document
        XWPFDocument document = new XWPFDocument();

        // Iterate through JSON fields
        Iterator<Map.Entry<String, JsonNode>> fields = root.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> entry = fields.next();

            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setBold(true);
            run.setText(entry.getKey() + ": ");

            run = paragraph.createRun();
            if (entry.getValue().isArray()) {
                run.setText(entry.getValue().toString());
            } else {
                run.setText(entry.getValue().asText());
            }
        }

        // Write to Word file
        try (FileOutputStream out = new FileOutputStream("json_output.docx")) {
            document.write(out);
            System.out.println("Word document created successfully.");
        }
    }
 */