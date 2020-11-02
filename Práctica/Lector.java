
// Este ejemplo permite parsear y mostrar los empleados que figuran en el doc XML llamado empleados.xml cuyo salario
// es mayor o igual a 30000
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class Lector {
    public static final String DOCUMENTO_XML = "C:\\Users\\Miguel\\IdeaProjects\\EjemploStAX\\Práctica\\empleados.xml "; //Path absoluto al fic. XML
    public static final String ELEMENTO_NOMBRE = "nombre";
    public static final String ELEMENTO_SALARIO = "salario";

    public List<String> empleadoConSalario(int salario) throws FileNotFoundException {
        List<String> empleadoConSalarioList = new ArrayList<String>();

        XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
        InputStream inputStream = null;
        XMLStreamReader xmlStreamReader = null;

        try {
            inputStream = new FileInputStream(DOCUMENTO_XML);
            xmlStreamReader = xmlInputFactory.createXMLStreamReader(inputStream);

            StringBuilder nombre = null;
            System.out.println("Iniciando el documento");
            while (xmlStreamReader.hasNext()) {
                xmlStreamReader.next();

                if (xmlStreamReader.isStartElement() && ELEMENTO_NOMBRE.equals(xmlStreamReader.getLocalName())) {
                    // Obtenemos el texto contenido dentro del elemento (el nombre)
                    nombre = new StringBuilder(xmlStreamReader.getElementText());

                }

                if (xmlStreamReader.isStartElement() && ELEMENTO_SALARIO.equals(xmlStreamReader.getLocalName())) {
                    // Obtenemos el texto contenido dentro del elemento (el salrio) y lo comparamos con el salario dado
                    if (Integer.parseInt(xmlStreamReader.getElementText()) >= salario) { //si es mayor o igual, guardamos el nombre del empleado
                        empleadoConSalarioList.add(nombre.toString());
                    }

                    // Reseteamos la cadena temporal del nombre
                    nombre = null;
                }
            }
            System.out.println("Fin del documento");
        } catch (XMLStreamException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (xmlStreamReader != null) {
                    xmlStreamReader.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (XMLStreamException | IOException ex) {
                ex.printStackTrace();
            }
        }

        return empleadoConSalarioList;
    }

    public static void main(String[] args) {
        Lector lector = new Lector();
        try { //Aquí buscamos empleados con un salario superior o igual a 30000
            System.out.println("Empleados con salario mayor a 30000: " + lector.empleadoConSalario(30000));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
