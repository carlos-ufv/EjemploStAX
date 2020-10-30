import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.nio.file.FileSystemNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class EntregaXML {

    private static final String DOCUMENTO_XML = "//Volumes//DATA MAC OSX//andres//Documents//aaUni//Tercer Curso//Primer Cuatrimestre//DIS//GitProyects//EjemploStAx_forked//Práctica//empleados.xml";
    private static final String Elemento_empleado = "empleado";
    private static final String Elemento_salario = "salario";
    private static final String Elemento_nombre = "nombre";

    public static List<String> salarioMayorQue(int numero) throws IOException {
        List<String> result = new ArrayList<String>();
        String tempName = "";

        XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
        InputStream inputStream = null;
        XMLStreamReader xmlStreamReader = null;

        try {
            inputStream = new FileInputStream(DOCUMENTO_XML);
            xmlStreamReader = xmlInputFactory.createXMLStreamReader(inputStream);

            while (xmlStreamReader.hasNext()) {
                xmlStreamReader.next();
                if (xmlStreamReader.isStartElement() && Elemento_nombre.equals(xmlStreamReader.getLocalName())) {
                    tempName = xmlStreamReader.getElementText();

                    do {
                        xmlStreamReader.next();
                    }while(!(xmlStreamReader.isStartElement() && Elemento_salario.equals(xmlStreamReader.getLocalName())) && xmlStreamReader.hasNext());

                        int tempSalario = Integer.parseInt(xmlStreamReader.getElementText());

                        if(tempSalario >= 30000){
                            result.add(tempName);
                        }


                }
            }
        } catch (XMLStreamException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (xmlStreamReader != null) xmlStreamReader.close();
                if (inputStream != null) inputStream.close();
            } catch (XMLStreamException ex) {
                ex.printStackTrace();
            }

            return result;
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println("Empleados con salario mayor o igual a 30000: " + salarioMayorQue(3));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

