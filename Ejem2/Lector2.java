// Este ejemplo permite parsear y mostrar las dos primeras etiquetas del doc XML llamado parking.xml
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class Lector2 {
    public static final String DOCUMENTO_XML = "//Volumes//DATA MAC OSX//andres//Documents//aaUni//Tercer Curso//Primer Cuatrimestre//DIS//GitProyects//EjemploStAx_forked//parking.xml"; //Path absoluto al fic. XML
    public static final String ELEMENTO_PLAZA = "plaza";
    public static final String ELEMENTO_MATRICULA = "matricula";
    public static final String ATRIBUTO_ID = "id";

    public List<String> matriculasConId() throws FileNotFoundException {
        List<String> matriculasConIdList = new ArrayList<String>();

        XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
        InputStream inputStream = null;
        XMLStreamReader xmlStreamReader = null;

        try {
            inputStream = new FileInputStream(DOCUMENTO_XML);
            xmlStreamReader = xmlInputFactory.createXMLStreamReader(inputStream);

            StringBuilder tmpMatriculaId = null;
            while (xmlStreamReader.hasNext()) {
                xmlStreamReader.next();

                if (xmlStreamReader.isStartElement() && ELEMENTO_PLAZA.equals(xmlStreamReader.getLocalName())) {
                    // Obtenemos el valor del primer atributo del elemento (id) (numeración basada en indice-cero)
                    tmpMatriculaId = new StringBuilder(xmlStreamReader.getAttributeValue(0));

                    // En esta iteración es imposible que la siguiente condición se cumpla, por lo que saltamos a la siguiente
                    continue;
                }

                if (xmlStreamReader.isStartElement() && ELEMENTO_MATRICULA.equals(xmlStreamReader.getLocalName())) {
                    // Obtenemos el texto contenido dentro del elemento (entre las etiquetas de apertura y cierre)
                    if (tmpMatriculaId != null) {
                        tmpMatriculaId.append(":").append(xmlStreamReader.getElementText());
                    }
                    if (tmpMatriculaId != null) {
                        matriculasConIdList.add(tmpMatriculaId.toString());
                    }

                    // Reseteamos la cadena temporal de id:matricula
                    tmpMatriculaId = null;
                }
            }
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

        return matriculasConIdList;
    }

    public static void main(String[] args) {
        Lector2 lector = new Lector2();
        try {
            System.out.println("Matrículas con id: " + lector.matriculasConId());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
