package com.carlos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class Lector1 {
    public static final String DOCUMENTO_XML = "D:\\Cloud\\Mega\\UFV\\DIS\\2-JSON _ XML\\Projects\\XML\\EjemploXML\\src\\com\\carlos\\parking.xml";
    public static final String ELEMENTO_PLAZA = "plaza";

    public int numeroDePlazas() throws FileNotFoundException, IOException {
        int numeroPlazas = 0;

        XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
        InputStream inputStream = null;
        XMLStreamReader xmlStreamReader = null;

        try {
            inputStream = new FileInputStream(DOCUMENTO_XML);
            xmlStreamReader = xmlInputFactory.createXMLStreamReader(inputStream);

            while (xmlStreamReader.hasNext()) {
                xmlStreamReader.next();

                if (xmlStreamReader.isStartElement() && ELEMENTO_PLAZA.equals(xmlStreamReader.getLocalName())) {
                    numeroPlazas++;
                }
            }
        } catch (XMLStreamException ex) {
            ex.printStackTrace();
        } finally {try {
            if (xmlStreamReader != null) xmlStreamReader.close();
            if (inputStream != null) inputStream.close();
        } catch (XMLStreamException ex) {
            ex.printStackTrace();
        }
        }

        return numeroPlazas;
    }

    public static void main(String[] args) {
        Lector lector = new Lector();
        try {
            System.out.printf("Número de plazas: %d", lector.numeroDePlazas());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}