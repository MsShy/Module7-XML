package homework.task1.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Dom {
    public static Map<String, ArrayList<String>> parse() {
        String url = "http://www.ibiblio.org/xml/examples/shakespeare/as_you.xml";
        Map<String, ArrayList<String>> persons = new HashMap<>();
        try {

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(url);
            document.getDocumentElement().normalize();

            final NodeList speechList = document.getElementsByTagName("SPEECH");

            for (int index = 0; index < speechList.getLength(); index++) {
                final Node node = speechList.item(index);
                final String key = ((Element) node).getElementsByTagName("SPEAKER").item(0).getTextContent();
                final NodeList lines = ((Element) node).getElementsByTagName("LINE");
                final StringBuilder builder = new StringBuilder();
                for (int lineIndex = 0; lineIndex < lines.getLength(); lineIndex++) {
                    Node nodelINE = lines.item(lineIndex);
                    final String textContent = nodelINE.getTextContent();
                    builder.append(textContent).append(" ");
                }
                final String value = builder.toString();

                if (persons.containsKey(key)) {
                    final ArrayList<String> oldValue = persons.get(key);
                    oldValue.add(value);
                    persons.put(key, oldValue);
                } else {
                    final ArrayList<String> newValue = new ArrayList<>();
                    newValue.add(value);
                    persons.put(key, newValue);
                }
            }
            return persons;

        } catch (SAXException | ParserConfigurationException | IOException e) {
            System.out.println(e);
        }
        return Collections.emptyMap();
    }
}

