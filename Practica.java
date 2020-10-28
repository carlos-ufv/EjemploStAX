package jaxp.stax;
// Declaras los paquetes y cada uno de los import que vayamos a utilizar para un ejercicio de
//lectura de fichero en java
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
 
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
 //La clase va a ser simplemente una clase, ni publica ni privada para que no haya ningun problema
 // con los permisos
class Practica {
 	//Declaramos las variables que se van a usar en el programa
 	private static final String NOMBRE = "nombre";
	private static final String SALARIO = "salario";
	
	public static <E> void main(String[] args) throws FileNotFoundException, XMLStreamException, FactoryConfigurationError {  
		//Creamos el flujo necesario para la resolucion e iniciamos la lectura del documento
		//que estara situado en su correspondiente ruta
		XMLInputFactory xmlinput = XMLInputFactory.newInstance();
		
		XMLStreamReader xmlreader = xmlinput.createXMLStreamReader(new FileReader("src/empleados.xml"));
		ArrayList<String> nombres = new ArrayList<String>();
		//Inicializamos cada una de las variables
		String tag = null;
		String nombre = null;
		String salario = null;
		int eventType;
		//En el momento en el que el documento se empiece a leer saltara el mensaje de la siguiente
		//linea
		System.out.println("Iniciando el documento");
		
		while (xmlreader.hasNext()){
			//iteramos con el cursor a lo largo del documento y mas tarde obtenemos el evento
			//con el que entrará al switch
			eventType = xmlreader.next();
			
			switch(eventType){
 			//Tendremos dos opciones, una que recorre el fichero hasta qeu este termine, con
			// la condicion de que saque las personas que pide en el enunciado
			//Y otra que indica el fin de la lectura del documento.
			case XMLEvent.START_ELEMENT:
				tag = xmlreader.getLocalName();
					
				if(tag.equals(NOMBRE)){
					nombre = xmlreader.getElementText();

				}else if(tag.equals(SALARIO)){
					salario = xmlreader.getElementText();
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