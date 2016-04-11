package homework.task1.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import java.io.IOException;
import java.util.*;

public class Sax {
private static String key;
    private static String value;
    private static List<String> tempPhrase;

    public static Map<String, ArrayList<String>> parse() {
        String url = "http://www.ibiblio.org/xml/examples/shakespeare/as_you.xml";
      final Map  <String, ArrayList<String>> persons = new HashMap<>();
        try {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            DefaultHandler handler = new DefaultHandler() {

                boolean speech = false;


                @Override
                public void startElement(String uri, String localName, String qName,
                                         Attributes attributes) throws SAXException {
                    if (qName.equalsIgnoreCase("SPEECH")) {
                        speech = true;
                        tempPhrase = new ArrayList<>();
                    }
                }

                @Override
                public void endElement(String uri, String localName,
                                       String qName) throws SAXException {

                    if (qName.equalsIgnoreCase("SPEECH")) {
                        speech = false;
                        final StringBuilder builder = new StringBuilder();

                        for (String element:tempPhrase) {

                            builder.append(element).append(" ");
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
                        if (qName.equalsIgnoreCase("SPEAKER")) {
                            key = value;
                        }
                        if (qName.equalsIgnoreCase("LINE")) {
                            tempPhrase.add(value);
                        }
                    }
                }
                @Override
                public void characters(final char[] ch, final int start, final int length) throws SAXException {
                    value = new String(ch, start, length);
                }
            };
                saxParser.parse(url, handler);

            return persons;

        } catch (SAXException | ParserConfigurationException | IOException e) {
            System.out.println(e);
        }
        return Collections.emptyMap();
    }
}



