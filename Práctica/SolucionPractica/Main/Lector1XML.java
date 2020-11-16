package Main;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Lector1XML {
    //Buscamos el archivo xml
    public static final String DOCUMENTO_XML = "C:\\Users\\dava9\\GitProjects\\EjemploStAX_forked\\Práctica\\empleados.xml";
    public static final String ELEMENTO_SALARIO= "salario";
    public static final String ELEMENTO_NOMBRE= "nombre";

    public static List<String> numeroDePlazas() throws FileNotFoundException, IOException {
        System.out.println("Iniciando el documento");
        List<String> Listas = new ArrayList<String>();
        String nombre = null;
        String salario = null;

        XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
        InputStream inputStream = null;
        XMLStreamReader xmlStreamReader = null;

        try {
            //Recorremos el archivo xml
            inputStream = new FileInputStream(DOCUMENTO_XML);
            xmlStreamReader = xmlInputFactory.createXMLStreamReader(inputStream);

            while (xmlStreamReader.hasNext()) {
                xmlStreamReader.next();

                if (xmlStreamReader.isStartElement() && ELEMENTO_NOMBRE.equals(xmlStreamReader.getLocalName())) {
                   //Guardamos el nombre
                    nombre = xmlStreamReader.getElementText();
                }
                if (xmlStreamReader.isStartElement() && ELEMENTO_SALARIO.equals(xmlStreamReader.getLocalName())){
                    //Guardamos el salario
                    salario = xmlStreamReader.getElementText();

                    if (Integer.parseInt(salario)>= 30000){
                        Listas.add(nombre);
                        //Comprobamps si el salario es mayor o igual que 30000 y lo añadimos a la lista
                    }
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
        System.out.println("Fin del documento");
        return Listas;
    }

    public static void main(String[] args) {

        try {
            System.out.println("Empleados con salario mayor a 30000: " + numeroDePlazas());
            //Imprimimos el resultado obtenido, es decir, la lista de nombres que hemos hallado antes
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
