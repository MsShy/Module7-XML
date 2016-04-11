package homework.task1.dom;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;


public class ReadXML {
    private ReadXML() {
    }

    public static void main(String[] args) throws IOException {
        Map<String, ArrayList<String>> parse = Dom.parse();
        printStat(parse);
        System.out.println("-------------------");
        parse = Sax.parse();
        printStat(parse);


        // String url = "http://www.ibiblio.org/xml/examples/shakespeare/as_you.xml";


        /*    final NodeList childNodes = entities.item(0).getChildNodes();
            final Node textNode = getChild(childNodes, "SPEAKER");
            final Node line = getChild(childNodes, "LINE");
            System.out.println("Number of lines: " + entities.getLength());*/





          /*  NodeList items = document.getElementsByTagName("SPEECH");
            for (int i = 0; i < items.getLength(); i++) {
                Node n = items.item(i);
                if (n.getNodeType() != Node.ELEMENT_NODE)
                    continue;
                Element e = (Element) n;


                NodeList titleList =
                        e.getElementsByTagName("SPEAKER");
                Element titleElem = (Element) titleList.item(0);

                NodeList line =
                        e.getElementsByTagName("LINE");
                Element lineElem = (Element) titleList.item(0);


                Node titleNode = titleElem.getChildNodes().item(0);
                System.out.println(titleNode.getNodeValue());
                System.out.println("Total of elements : " + line.getLength());

                //System.out.println("Total of elements : " + titleList.getLength());
         /*   }*//*
        } catch (Exception e) {
            System.out.println(e);
        }*/
    }

    private static void printStat(Map<String, ArrayList<String>> parse) {
        Set<String> keySet = parse.keySet();
        for (String s : keySet) {
            String person = s;
            int count = Calc.getCount(person, parse);
            int average = Calc.gatAverage(person, parse);
            System.out.println(person);
            System.out.println(count);
            System.out.println(average);
        }
    }
}


