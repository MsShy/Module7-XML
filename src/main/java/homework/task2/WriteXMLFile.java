package homework.task2;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class WriteXMLFile {
    private WriteXMLFile() {
    }

    public static void main(String[] args) {
        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();


            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("project");
            add(doc, rootElement);



            // write into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("src\\main\\resources\\pom.xml"));



            transformer.transform(source, result);
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);
            System.out.println("File saved!");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

    private static void add(Document doc, Element rootElement) {
        Attr attr=  doc.createAttribute("xmlns");
        attr.setValue("http://maven.apache.org/POM/4.0.0");
        Attr attr1=  doc.createAttribute("xmlns:xsi");
        attr1.setValue("http://www.w3.org/2001/XMLSchema-instance");
        Attr attr2=  doc.createAttribute("xsi:schemaLocation");
        attr2.setValue("http://maven.apache.org/xsd/maven-4.0.0.xsd");
        rootElement.setAttributeNode(attr);
        rootElement.setAttributeNode(attr1);
        rootElement.setAttributeNode(attr2);
        doc.appendChild(rootElement);

        Element modelVersion = doc.createElement("modelVersion");
        modelVersion.appendChild(doc.createTextNode("4.0.0"));
        rootElement.appendChild(modelVersion);

        Element groupId = doc.createElement("groupId");
        groupId.appendChild(doc.createTextNode("javase07"));
        rootElement.appendChild(groupId);


        Element artifactId = doc.createElement("artifactId");
        artifactId.appendChild(doc.createTextNode("javase07"));
        rootElement.appendChild(artifactId);

        Element version = doc.createElement("version");
        version.appendChild(doc.createTextNode("1.0-SNAPSHOT"));
        rootElement.appendChild(version);

        Element packaging = doc.createElement("packaging");
        packaging.appendChild(doc.createTextNode("jar"));
        rootElement.appendChild(packaging);

        Element dependencies = doc.createElement("dependencies");
        rootElement.appendChild(dependencies);

        Element dependency= doc.createElement("dependency");
        dependencies.appendChild(dependency);


        Element groupId1 = doc.createElement("groupId");
        groupId1.appendChild(doc.createTextNode("junit"));
        dependency.appendChild(groupId1);


        Element artifactId1 = doc.createElement("artifactId");
        artifactId1.appendChild(doc.createTextNode("junit"));
        dependency.appendChild(artifactId1);


        Element version1 = doc.createElement("version");
        version1.appendChild(doc.createTextNode("4.12"));
        dependency.appendChild(version1);
    }
}


