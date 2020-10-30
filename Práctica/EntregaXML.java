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
        List<String> result = new ArrayList<String>(); // Lista de resultados finales
        String tempName = ""; // variable auxiliar para guardar el nombre
        int tempSalario = 0; // variable auxiliar para guardar el salario

        XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory(); //creamos el objeto xmlInputFactory que usaremos para crear el xmlStreamReader
        InputStream inputStream = null; //creamos un objeto inputstream y lo inicializamos a null por ahora
        XMLStreamReader xmlStreamReader = null; //creamos un objeto xmlStreamReader y lo inicializamos a null por ahora

        try {
            inputStream = new FileInputStream(DOCUMENTO_XML); //intentamos crear la conexion con el fichero
            xmlStreamReader = xmlInputFactory.createXMLStreamReader(inputStream); //intentamos parsear el fichero xml

            while (xmlStreamReader.hasNext()) { //mientras el objeto xmlstreamreader tenga elementos
                xmlStreamReader.next(); // pasamos al siguiente elemento
                if (xmlStreamReader.isStartElement() && Elemento_nombre.equals(xmlStreamReader.getLocalName())) { //si el cursor se encuenta en el inicio de la etiqueta y si la etiqueta es igual al nombre del empleado
                    tempName = xmlStreamReader.getElementText(); //guarda el nombre en un variable auxiliar temporal

                    do {
                        xmlStreamReader.next();//haz mientras el cursor no este en el principio de la etiqueta y la etiqueta no sea salario y mientras el objeto tenga mas etiquetas a recorrer
                    }while(!(xmlStreamReader.isStartElement() && Elemento_salario.equals(xmlStreamReader.getLocalName())) && xmlStreamReader.hasNext());

                    tempSalario = Integer.parseInt(xmlStreamReader.getElementText()); //guardamos el salario y lo parseamos a int para poder comparar

                    if(tempSalario >= 30000){ //si el salario es mayor o igual que 30000
                        result.add(tempName); //añade el nombre a la lista de empleados con salarios iguales o superiores a 30000
                    }
                    tempSalario = 0; //reseteamos valor temporal de salario


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
            System.out.println("Empleados con salario mayor o igual a 30000: " + salarioMayorQue(3)); //imprimimos el resultado
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}