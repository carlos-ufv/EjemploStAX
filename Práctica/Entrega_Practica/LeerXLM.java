package com.sergioluna;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


class CrearXml {

    public static void main(String args[]) {
        try {
            System.out.println("Iniciando el documento...");
            File archivo = new File("/Users/sergiolg99/Documents/UFV/DIS/GitProjects/EjemploStAX_forked/Práctica/empleados.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
            Document document = documentBuilder.parse(archivo);

            document.getDocumentElement().normalize();
            NodeList listaEmpleados = document.getElementsByTagName("empleado");
            System.out.println("Empleados con salario mayor a 30000: ");
            for (int temp = 0; temp < listaEmpleados.getLength(); temp++) {
                Node nodo = listaEmpleados.item(temp);

                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;
                    int mayor = Integer.parseInt(element.getElementsByTagName("salario").item(0).getTextContent());
                    if (mayor >= 30000){
                        System.out.println( element.getElementsByTagName("nombre").item(0).getTextContent() + ",");
                    }
                    ;
                    
                }
            } System.out.println("Fin del documento...");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}