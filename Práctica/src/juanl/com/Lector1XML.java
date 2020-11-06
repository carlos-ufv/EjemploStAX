//Creamos el paquete juanl.com donde estara el archivo java
package juanl.com;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
//Crearemos una clase llamada Practica1, no la heremos publica debido a que me provoca un error
class Practica1 {
    //Inicializamos variables
    private static final String NOM = "nombre";
    private static final String SALR = "salario";
//declaramos la funcion main


    public static void main(String[] args) throws FileNotFoundException, XMLStreamException, FactoryConfigurationError {
        XMLInputFactory xmlinput = XMLInputFactory.newInstance();
        //Leemos el archicvo con los datos
        XMLStreamReader xmlreader = xmlinput.createXMLStreamReader(new FileReader("src/empleados.xml"));
        //Inicializamos  las variables
        ArrayList<String> names = new ArrayList<>();
        int eventType;
        String salr;
        String tag;
        String name = null;


        System.out.println("Leyendo documento");

        while (xmlreader.hasNext()){
            //El cursor leera el documento y con eventType entrará en el if
            eventType = xmlreader.next();
                //VAmos leyendo los tags y en el caso de que su sueldo supere los 3000 lo guardamos
                if (eventType==XMLEvent.START_ELEMENT) {
                    tag = xmlreader.getLocalName();
                    if (tag.equals(NOM)) {
                        name = xmlreader.getElementText();

                    } else if (tag.equals(SALR)) {
                        salr = xmlreader.getElementText();
                        if (Integer.parseInt(salr) >= 30000)
                            names.add(name);
                    }
                }
                else if (eventType==XMLEvent.END_DOCUMENT)
                    System.out.println("Fin del documento");

        }
        System.out.println("Empleados con salario mayor a 30000: "+names);
    }
}
