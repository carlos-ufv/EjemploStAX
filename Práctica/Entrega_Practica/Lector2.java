package jaxp.stax;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;

class StaxExample {
    private static final String TAG_SALARIO = "salario";
    private static final String TAG_NOMBRE = "nombre";
    public static <E> void main(String[] args) throws FileNotFoundException, XMLStreamException, FactoryConfigurationError {
//Creamos el flujo
        XMLInputFactory xmlif = XMLInputFactory.newInstance();
//Abro el XLM y creo una Lista donde meto todos los nombres obtenidos
        XMLStreamReader xmlsr = xmlif.createXMLStreamReader(new FileReader("/Users/sergiolg99/Documents/UFV/DIS/GitProjects/EjemploStAX_forked/Práctica/empleados.xml"));
        ArrayList<String> nombres = new ArrayList<String>();
        String tag = null;
        String nombre = null;
        String salario = null;
        int eventType;
        System.out.println("Iniciando el documento");
//iVamos accediendo campo a campo en el documento
        while (xmlsr.hasNext()){
//obtenemos el tipo de evento
            eventType = xmlsr.next();
//Al tener que manejar varios eventos el bloque switch resulta una solución elegante
//para ir añadiendo case de eventos
            switch(eventType){
                case XMLEvent.START_ELEMENT:
//obtenemos la etiqueta NOMBRE y SALARIO necesarias para resolver el problema
                    tag = xmlsr.getLocalName();
                    if(tag.equals(TAG_NOMBRE)){
                        nombre = xmlsr.getElementText();
                    }else if(tag.equals(TAG_SALARIO)){
                        salario = xmlsr.getElementText();
                        if(Integer.parseInt(salario) >= 30000)
                            nombres.add(nombre);
                    }
                    break;
                case XMLEvent.END_DOCUMENT:
                    System.out.println("Fin del documento");
                    break;
            }
        }
        System.out.println("Empleados con salario mayor a 30000: "+nombres);
    }
}