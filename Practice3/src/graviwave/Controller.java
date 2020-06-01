package graviwave;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;


public class Controller implements ControllerInterface {
    private List<Country> model;
    private ViewInterface view;
    private Map<String, String> capMap;
    Controller() {
        view = new View(this);
        model = new ArrayList<>();
        capMap = new HashMap<>();
    }
    @Override
    public boolean open(String path) {
        try {
            model.clear();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(path));
            NodeList countryElements = document.getDocumentElement().getElementsByTagName("country");
            for (int i = 0; i < countryElements.getLength(); i++) {
                Node country = countryElements.item(i);
                // Получение атрибутов каждого элемента
                NamedNodeMap attributes = country.getAttributes();
                model.add(new Country(attributes.getNamedItem("name").getNodeValue(),
                        attributes.getNamedItem("capital").getNodeValue()));
                capMap.clear();
                for (Country c : model) {
                    capMap.put(c.getName(), c.getCapital());
                }
            }
            return true;
        } catch (Exception e) {
            view.showError(e);
            return false;
        }
    }
    @Override
    public void showList() {
        List<String> sorted = new ArrayList<>(capMap.keySet());
        view.showAll(sorted);
    }

    @Override
    public List<Country> getModel(){
        return model;
    }
    @Override
    public Map<String, String> getCapMap() {
        return capMap;
    }
}