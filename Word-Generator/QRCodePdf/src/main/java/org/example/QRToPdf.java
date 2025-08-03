package org.example;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.Result;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;


public class QRToPdf {

    public static void readQRCodeAndGeneratePDF(String qrImagePath, String pdfPath) throws Exception {
        BufferedImage bufferedImage = ImageIO.read(new File(qrImagePath));
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(bufferedImage)));

        Result result = new MultiFormatReader().decode(bitmap);
        String jsonData = result.getText();

        // Create PDF
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.setFont(PDType1Font.HELVETICA, 12);
        contentStream.beginText();
        contentStream.newLineAtOffset(50, 700);

        // Split JSON lines for readability
        for (String line : jsonData.split(",")) {
            contentStream.showText(line.trim());
            contentStream.newLineAtOffset(0, -15);
        }

        contentStream.endText();
        contentStream.close();

        document.save(pdfPath);
        document.close();

        System.out.println("PDF created at: " + pdfPath);
    }
}
