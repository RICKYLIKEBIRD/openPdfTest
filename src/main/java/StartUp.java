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
        System.out.println("Parse Nested HTML List");
        try {
            final String htmlText =
                    "<html>"
                            + "<body>"
                            + "<p>What should you say?</p>"
                            + "<ul>"
                            + "  <li>Hello</li>"
                            + "  <li>World</li>"
                            + "</ul>"
                            + "<ol>"
                            + "  <li>Element-1"
                            + "    <ol>"
                            + "      <li>Element-1-1</li>"
                            + "      <li>Element-1-2</li>"
                            + "    </ol>"
                            + "  </li>"
                            + "  <li>Element-2"
                            + "    <ol>"
                            + "      <li>Element-2-1"
                            + "        <ol>"
                            + "          <li>Element-2-1-1"
                            + "            <ol>"
                            + "              <li>Element-2-1-1-1</li>"
                            + "              <li>Element-2-1-1-2</li>"
                            + "            </ol>"
                            + "          </li>"
                            + "          <li>Element-2-1-2"
                            + "            <ol>"
                            + "              <li>Element-2-1-2-1</li>"
                            + "              <li>Element-2-1-2-2</li>"
                            + "            </ol>"
                            + "          </li>"
                            + "        </ol>"
                            + "      </li>"
                            + "      <li>Element-2-2</li>"
                            + "    </ol>"
                            + "  </li>"
                            + "</ol>"
                            + "</body>"
                            + "</html>";

            final StringReader reader = new StringReader(htmlText);
            final StyleSheet styleSheet = new StyleSheet();
            final Map<String, Object> interfaceProps = new HashMap<>();

            final List<Element> elements = HTMLWorker.parseToList(reader, styleSheet, interfaceProps);
            printElement("", elements);

            Document document = new Document();
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            PdfWriter writer = PdfWriter.getInstance(document,bao);
            document.open();

            //                writer.add(new Paragraph("test paragraph"));
            elements.forEach(document::add);

            document.close();
            writer.close();
            System.out.println(Arrays.toString(bao.toByteArray()));

        } catch (DocumentException | IOException de) {
            System.err.println(de.getMessage());
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
