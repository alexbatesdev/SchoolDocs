import com.aspose.pdf.Document;
import com.aspose.pdf.HtmlLoadOptions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class HTMLtoPDF {
    public static void generatePDF(String inputHtmlPath)
    {
        System.out.println("hit");
        String outputPDFPath = inputHtmlPath.substring(0, inputHtmlPath.length()-5) + ".pdf";
        HtmlLoadOptions htmloptions = new HtmlLoadOptions();

        Document doc = new Document(inputHtmlPath, htmloptions);
        doc.save(outputPDFPath);
    }
}
