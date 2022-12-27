import Dto.PdfDTO;
import Factory.PdfFactory;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.html.simpleparser.StyleSheet;
import com.lowagie.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StartUp {
    public static void main(String[] args) {
        PdfFactory pdfFactory = new PdfFactory();
        PdfDTO pdfDto = pdfFactory.readerHtmlConvertToByteArr();
        pdfFactory.generatePdfFileByBase64(pdfDto);
    }
}
