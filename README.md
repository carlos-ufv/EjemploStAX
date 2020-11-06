# Ejemplo StAX
Ejemplos para parsear documento XML de matrículas.

StAX (Streaming API for XML) es el API más reciente de JAXP (Java API for XML Processing, conformado por SAX, DOM, StAX y TrAX). Es una alternativa a SAX y DOM para el procesamiento de documentos XML. La principal meta de StAX es otorgarle el control del parseo al programador exponiendo una API basada en un iterador simple. StAX  nos permite lograr un alto rendimiento en la iteración, procesado y modificación de documentos XML sobretodo en entornos donde se disponga de poca cantidad de memoria y limitada capacidad de extensibilidad.<br>

StAX consta realmente de 2 APIs distintas :<br>
<ul>
   <li><b>Cursor API (XMLStreamReader|Writer - The Cursor API)</b>: Representa un cursor con el cual se puede ir hacia adelante en un documento XML desde el principio hasta el final.        Este cursor puede apuntar un elemento a la vez y siempre se mueve hacia adelante, nunca hacia atrás. </li>
   <li><b>Iterator API (XMLEventReader|Writer - The Iterator API)</b>: Representa un flujo  de un documento XML como un conjunto de objetos de eventos discretos. Estos eventos son         sacados por la aplicación y provistos por el parseador en el orden en el cual son leídos en el documento XML.Esta solución es la que utilizaremos en los ejemplos</li>
</ul> 
The Cursor API es más eficiente en cuanto a memorias.
<br>  
Puedes consultar la documentación oficial en este enlace: https://docs.oracle.com/javase/tutorial/jaxp/stax/index.html 
<br>
En esta página puedes ver un buen ejemplo de cómo generar documentos XML válidos: https://www.tutorialspoint.com/java_xml/java_stax_modify_document.htm
holaaa
