package sample;

import com.sun.org.apache.xerces.internal.impl.io.MalformedByteSequenceException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class Controller implements ControllerInterface {
    View view;
    List<Planet> model;

    Controller(View view){
        this.view = view;
        this.model = new ArrayList<>();
    }

    @Override
    public void open(String path) {
        model.clear();
        File xmlFile = new File(path);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName("planet");

            for (int i = 0; i < nodeList.getLength(); i++) {
                model.add(setPlanet(nodeList.item(i)));
            }

            view.showTable(model);
            view.showInfo("Number of planets", model.size()+"");
        }
        catch (Exception e) {
            view.showError(e.getMessage());
        }
    }

    private static Planet setPlanet(Node node) {
        Planet planet;
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            String name = element.getAttribute("name");
            double mass = Double.parseDouble(getTagValue("mass", element));
            double diameter = Double.parseDouble(getTagValue("diameter", element));
            int count = Integer.parseInt(getTagValue("count", element));
            String ans = getTagValue("ans", element);
            planet = new Planet(name, mass, diameter, count, ans);

            return planet;
        }
        else {
            return null;
        }
    }

    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }

    @Override
    public void write(String path) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            Element rootElement = doc.createElement("planets");
            doc.appendChild(rootElement);

            for(Planet planet:model){
                rootElement.appendChild(setPlanet(doc, planet));
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult file = new StreamResult(new File(path));
            transformer.transform(source, file);

            view.showInfo("You're lucky!", "File saved!");
        } catch (ParserConfigurationException | TransformerException pce) {
            view.showError(pce.getMessage());
        }
    }

    private static Node setPlanet(Document doc, Planet planet) {
        Element language = doc.createElement("planet");

        language.setAttribute("name", planet.getName());
        language.appendChild(getPlanetElements(doc, "mass", planet.getMass()+""));
        language.appendChild(getPlanetElements(doc, "diameter", planet.getDiameter()+""));
        language.appendChild(getPlanetElements(doc, "count", planet.getCount()+""));
        language.appendChild(getPlanetElements(doc, "ans", planet.getSystem()));
        return language;
    }

    private static Node getPlanetElements(Document doc, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }
    @Override
    public void SAXCounting(String fileName, String find){
        double minDiameter = 1000000000000.0;
        double diameter = 1;
        int maxCount = 0;
        int count = 0;
        String maxName = "";
        String minName = "";
        String name = "";
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(fileName));
            while (reader.hasNext()) {

                XMLEvent xmlEvent = reader.nextEvent();
                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    switch (startElement.getName().getLocalPart()) {
                        case "planet":
                            Attribute namePlanet = startElement.getAttributeByName(new QName("name"));
                            name = namePlanet.getValue();
                            break;
                        case "diameter":
                            xmlEvent = reader.nextEvent();
                            diameter = Double.parseDouble(xmlEvent.asCharacters().getData());

                            break;
                        case "count":
                            xmlEvent = reader.nextEvent();
                            count = Integer.parseInt(xmlEvent.asCharacters().getData());
                            break;
                    }
                }
                if (xmlEvent.isEndElement()) {
                    EndElement endElement = xmlEvent.asEndElement();
                    if (endElement.getName().getLocalPart().equals("planet")) {
                        if(diameter < minDiameter){
                            minDiameter = diameter;
                            minName = name;
                        }
                        if(count > maxCount){
                            maxCount = count;
                            maxName = name;
                        }
                    }
                }
            }
            if(find.equals("max")){
                view.showInfo(maxName+ " has the most satellites.\n", "Value: " + maxCount);
            }
            else{
                view.showInfo(minName + " has the smallest diameter.\n" , "Value: " + minDiameter);
            }

        } catch (FileNotFoundException | XMLStreamException exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public void openDAT(String path){
        try{
            model.clear();
            FileInputStream fileInputStream = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fileInputStream);
            model = (ArrayList<Planet>)ois.readObject();
            view.showTable(model);
            view.showInfo("Number of planets", model.size()+"");
        }
        catch (IOException | ClassCastException | ClassNotFoundException ex){
            view.showError(ex.getMessage());
        }
    }

    @Override
    public void writeDAT(String path) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(model);
            view.showInfo("You're lucky!", "File saved!");
        }
        catch (Exception ex){
            view.showError(ex.getMessage());
        }
    }

    @Override
    public void updateList(Planet planet, String action){
        switch (action){
            case "add":
                model.add(planet);
                view.showTable(model);
                break;
            case "remove":
                model.remove(planet);
                view.showTable(model);
                break;
        }
    }
}
