package org.example;

public class Main {
    public static void main(String[] args) throws Exception {
        String json = "{\"name\":\"Mohamed Hamdy\",\"role\":\"Backend Developer\",\"skills\":[\"Java\",\"Spring\"]}";
        String qrPath = "qr_code.png";
        String pdfPath = "output.pdf";

        QRGenerator.generateQRCode(json, qrPath);
        QRToPdf.readQRCodeAndGeneratePDF(qrPath, pdfPath);
    }
}