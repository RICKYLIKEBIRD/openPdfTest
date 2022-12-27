package Factory;

import Dto.PdfDTO;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.html.simpleparser.StyleSheet;
import com.lowagie.text.pdf.PdfWriter;
import lombok.Getter;
import lombok.Setter;
import org.jsoup.Jsoup;

import java.io.*;
import java.util.*;

@Getter
@Setter
public class PdfFactory {

    private byte[] content;

    private final static String fileName = "example";


    public PdfDTO readerHtmlConvertToByteArr(){
        try {
            String html = Jsoup.parse(new File("src/main/resources/template/"+fileName+".html"), "UTF-8").outerHtml();
            final StringReader reader = new StringReader(html);
            final StyleSheet styleSheet = new StyleSheet();
            final Map<String, Object> interfaceProps = new HashMap<>();

            final List<Element> elements = HTMLWorker.parseToList(reader, styleSheet, interfaceProps);

            Document document = new Document();
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            PdfWriter writer = PdfWriter.getInstance(document,bao);
            document.open();

            elements.forEach(document::add);

            document.close();
            writer.close();
            return PdfDTO.valueOf(bao.toByteArray());

        } catch (DocumentException | IOException de) {
            System.err.println(de.getMessage());
        }
        return null;
    }

    public void generatePdfFileByBase64(PdfDTO pdfDTO){
        File file = new File("./test.pdf");
        try (FileOutputStream fos = new FileOutputStream(file); ) {
            fos.write(pdfDTO.getPdfDateBytes());
            System.out.println("PDF File Saved");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printElement(String depth, List<Element> elements) {
        for (Element element : elements) {
            System.out.println(depth + "- element.getClass() = " + element.getClass());
            if (element instanceof com.lowagie.text.List) {
                com.lowagie.text.List elementList = (com.lowagie.text.List) element;
                printElement(depth + "    ", elementList.getItems());
            } else {
                System.out.println(depth + "  element = " + element.getChunks().get(0).toString());
            }
        }
    }
}
