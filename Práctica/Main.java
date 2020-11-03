//Carpeta en la que guardamos los archivos
package com.company;
//Librerias que necesitamos
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.InputStream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;


//Clase que realiza el parseo
class Main{
//Guardamos estos strings con los que se realizan las comparaciones
    private static final String NOMBRE="nombre";
    private static final String SALARIO="salario";

    public static void main(String[] args) throws FileNotFoundException,XMLStreamException{

        //Creacion de streamreader
        XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
        InputStream inputStream = null;
        XMLStreamReader xmlStreamReader = null;
        inputStream = new FileInputStream("empleados.xml");
        xmlStreamReader = xmlInputFactory.createXMLStreamReader(inputStream);

        //Creacion array para guardar los nombres
        ArrayList<String> nombres= new ArrayList<String>();

        //Creacion de variables
        int evento;
        String tag=null,nombre=null,salario= null;

        //Imprime 'iniciando el documento' al inicio del documento
        System.out.println("Iniciando el documento");
        //Entrara al bucle siempre que existan elementos a parsear
        while (xmlStreamReader.hasNext()){
            evento=xmlStreamReader.next();

            if (evento== xmlStreamReader.START_ELEMENT){
            tag= xmlStreamReader.getLocalName();
                //En el caso de que se trate de un nombre se guarda por si se cumple la condicion
                if (tag==NOMBRE){
                    nombre=xmlStreamReader.getElementText();

                }else if(tag==SALARIO){
                    salario=xmlStreamReader.getElementText();
                    //En el caso de que el salario sea superior a 30000 se añadira el nombre al array de nombres
                    if (Integer.parseInt(salario)>=30000){
                        nombres.add(nombre);
                    }
                }

            }else if (evento==XMLStreamReader.END_DOCUMENT){
                //Imprime 'fin el documento' al final del documento
                System.out.println("Fin del documento");
            }
        }
        //Impresion de los empleados cuyo salario es superior a 30000
        System.out.println("Empleados con salario mayor a 30000: "+ nombres);
    }

}
