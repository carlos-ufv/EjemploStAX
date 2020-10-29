/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
 
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
/**
 *
 * @author Daniel Ojeda Velasco 28/10/2020
 */
class dis {
    //Declaro dos variables, una para el salario y otra para el nombre
    private static final String TAG_SALARIO = "salario";
    private static final String TAG_NOMBRE = "nombre";
    
    public static void main(String args[]) throws FileNotFoundException, XMLStreamException {
        //Creamos el flujo
		XMLInputFactory xmlif = XMLInputFactory.newInstance();
		
		//Vamos a crear un flujo, para leer nuestro documento XML y le pasamos la
                //ruta del fichero
		XMLStreamReader xmlsr = xmlif.createXMLStreamReader(new FileReader("C:\\Users\\Daniel Ojeda\\Documents\\NetBeansProjects\\mavenproject1\\src\\main\\java\\com\\mycompany\\mavenproject1\\empleados.xml"));
		ArrayList<String> nombres = new ArrayList<String>();
                //Ponemos nuestras variables a NULL, es lo recomendable
		String tag = null;
		String nombre = null;
		String salario = null;
		int eventType;
		//Sacamos un mensaje para saber por dónde va el programa
		System.out.println("Iniciando el documento");
		
		//iteramos con el cursor a lo largo del documento
		while (xmlsr.hasNext()){  
			
			//obtenemos el tipo de evento
			eventType = xmlsr.next();  
			
			//Al tener que manejar varios eventos el bloque switch resulta una solución elegante
			//para ir añadiendo case de eventos
			switch(eventType){
 
			case XMLEvent.START_ELEMENT:
				
				//obtenemos la etiqueta
				tag = xmlsr.getLocalName();	
                                //obtenemos el nombre del empleado en cuestión
				if(tag.equals(TAG_NOMBRE)){
					nombre = xmlsr.getElementText();
                                //comprobamos el salario que tiene
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
		//Sacamos por pantalla los empleados que cumplen el requisito
		System.out.println("Empleados con salario mayor a 30000: "+nombres);
 
	}
}
