//Carpeta en la que guardamos los archivos
package com.company;
//Librerias que necesitamos
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;



//Clase que realiza el parseo
class Main{
    public static void main(String[] args) throws ParserConfigurationException,SAXException, IOException {

        //Document builder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        //Cargamos el documento y lo parseamos
        Document document = builder.parse(new File("parking.xml"));

        List<plaza> parking = new ArrayList<plaza>();
        NodeList nodeList = document.getDocumentElement().getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) node;

                // Obtenemos el valor del ID
                String ID = node.getAttributes().getNamedItem("id").getNodeValue();

                //Obtenemos el valor de los subelementos que son la matricula, propietario,telefono y vencimiento
                //Matricula
                String matricula = elem.getElementsByTagName("matricula").item(0).getChildNodes().item(0).getNodeValue();
                //Porpietario
                String propietario = elem.getElementsByTagName("propietario").item(0).getChildNodes().item(0).getNodeValue();
                //Telefono
                String telefono = elem.getElementsByTagName("telefono").item(0).getChildNodes().item(0).getNodeValue();
                //Vencimiento
                String vencimiento = elem.getElementsByTagName("vencimiento").item(0).getChildNodes().item(0).getNodeValue();
                //Creamos el objeto plaza para cada plaza
                parking.add(new plaza(ID, matricula, propietario, telefono, vencimiento));
            }
        }

        //Imprimimos
        for (plaza pla: parking)
            System.out.println(pla.toString());
    }
}
