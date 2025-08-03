package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.xwpf.usermodel.*;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

//        ObjectMapper mapper = new ObjectMapper();
//        Car renault = new Car("yellow","renault");
//        mapper.writeValue(new File("car.json"), renault);
//        String carAsString = mapper.writeValueAsString(renault);
//        System.out.println(carAsString);
//        Car ford = mapper.readValue(new File("car2.json"), Car.class);
//        System.out.println(ford);

//        FileInputStream in = new FileInputStream("template.docx");
//        XWPFDocument document = new XWPFDocument(in);
//
//        Map<String, String> placeholders = new HashMap<>();
//        placeholders.put("${name}", "Mohamed");
//        placeholders.put("${role}", "Software Engineer");
//        placeholders.put("${skills}", "JavaEE/SE");
//        placeholders.put("${names}", "shaheen w a3wanoo");
//
//        for (XWPFParagraph para : document.getParagraphs()) {
//            for (XWPFRun run : para.getRuns()) {
//                String text = run.getText(0);
//                System.out.println(text);
//                if (text != null) {
//                    // For each placeholder, check if it's in the run's text.
//                    for (Map.Entry<String, String> entry : placeholders.entrySet()) {
//                        if (text.contains(entry.getKey())) {
//                            // Replace the placeholder with the actual value.
//                            text = text.replace(entry.getKey(), entry.getValue());
//                            // Update the run text.
//                            run.setText(text, 0);
//                        }
//                    }
//                }
//            }
//        }
//
//        FileOutputStream out = new FileOutputStream("output.docx");
//        document.write(out);
//        out.close();
//        document.close();
//
//        System.out.println("Template filled and saved as output.docx");
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> data = objectMapper.readValue(new File("json_example.json"), HashMap.class);

        FileInputStream fis = new FileInputStream("json_example.docx");
        XWPFDocument document = new XWPFDocument(fis);

        for (XWPFParagraph paragraphs : document.getParagraphs()) {
            replaceInParagraph(paragraphs, data);
        }

        for (XWPFTable table : document.getTables()) {
            if(handleDynamicTable(table, data)) continue;

            for (XWPFTableRow rows : table.getRows()) {
                for (XWPFTableCell cell : rows.getTableCells()) {
                    for (XWPFParagraph paragraph : cell.getParagraphs()) {
                        replaceInParagraph(paragraph, data);
                    }
                }
            }
        }

        /*
        Old implementation before brilliant nour's idea :D
        XWPFTable table = document.getTables().get(document.getTables().size() - 1);

        // Get the rows array from JSON
        List<Map<String, String>> rows = (List<Map<String, String>>) data.get("teams");

        // Add a new row for each JSON object
        for (Map<String, String> rowData : rows) {
            XWPFTableRow row = table.createRow(); // creates and adds new row

            row.getCell(0).setText(rowData.getOrDefault("Team", ""));
            row.getCell(1).setText(rowData.getOrDefault("Screen", ""));
            row.getCell(2).setText(rowData.getOrDefault("Maker-Checker", ""));
        }
        */

        FileOutputStream fos = new FileOutputStream("json_output.docx");
        document.write(fos);

        fos.close();
        document.close();
        fis.close();

        System.out.println("Document Printed Successfully!");

    }

    private static void replaceInParagraph(XWPFParagraph paragraph, Map<String, Object> data) {
        for (XWPFRun runs : paragraph.getRuns()) {
            String text = runs.getText(0);
            System.out.println(text);
            if (text != null) {
                for (Map.Entry<String, Object> entry : data.entrySet()) {
                    if (text.contains("${" + entry.getKey() + "}")) {
                        text = text.replace("${" + entry.getKey() + "}", entry.getValue().toString());
                    }
                }
                runs.setText(text, 0);
            }
        }
    }

    private static boolean handleDynamicTable(XWPFTable table, Map<String, Object> data){
        XWPFTableRow headerRow = table.getRow(1);
        if (headerRow == null) return false;

        List<String> keys = new ArrayList<>();
        boolean flag = true;
//        System.out.println("hena");
        for (XWPFTableCell cell : headerRow.getTableCells()){
            String text = cell.getText();
            System.out.println(text);
            if (text != null && text.startsWith("TR_")){
                keys.add(text.substring(3));
            }else {
                flag = false;
                break;
            }
        }
//        System.out.println("hena1");

        if(!flag || keys.isEmpty()) return false;
//        System.out.println("hena2");

        String rootKey = keys.get(0).split("\\.")[0];
//        System.out.println("root key = " + rootKey);
        Object rawList = data.get(rootKey);
//        System.out.println("raw list " + rawList);
        if(!(rawList instanceof List)) return false;

        List<Map<String, Object>> rows = (List<Map<String, Object>>) rawList;
//        System.out.println(rows);
        for (Map<String, Object> rowData: rows){
            XWPFTableRow newRow = table.createRow();
            for (int i = 0; i < keys.size(); i++) {
                String[] parts = keys.get(i).split("\\.");
//                System.out.println(parts[0]);
                String keyInRow = parts.length >= 1 ? parts[1] : parts[0];
//                System.out.println("keyInRow " + keyInRow);
                String value = rowData.getOrDefault(keyInRow, "").toString();
//                System.out.println(value);
                newRow.getCell(i).setText(value);
            }
        }

        table.removeRow(1);

        return true;
    }


}