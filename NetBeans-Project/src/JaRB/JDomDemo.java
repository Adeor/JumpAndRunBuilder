package JaRB;
/*
 * JDomDemo.java - Beispiel fuer den Einsatz von JDOM : gibt Wurzel und direkten Kinder der Wurzel aus
 *
 */

import org.jdom2.*;
import org.jdom2.input.*;
import org.jdom2.output.*;
import java.io.*; 
import java.util.Scanner;

public class JDomDemo {
   @SuppressWarnings("rawtypes")
public static void main(String[] args) {
	  Scanner scan = new Scanner(System.in);
	  System.out.println("Bitte geben sie den Namen der Eingabedatei ein:");
      String input0 = scan.nextLine();
	  System.out.println("Bitte geben sie den Namen der Ausgabedatei ein:");
      String input1 = scan.nextLine();

      try {
        // eine Parser-Klasse anlegen
        SAXBuilder dom = new SAXBuilder();

        // das Dokument einlesen
        Document doc = dom.build(new File("res/xml/"+input0+".xml"));

        // Die Wurzel und ihre direkten Kinder ausgeben 
        Element wurzel = doc.getRootElement();
        System.out.println(" Wurzel: %s\n" + wurzel.getName());

        java.util.List kinder = wurzel.getChildren();	
        System.out.println(" Anzahl direkte Kinder: %d\n" + kinder.size());
        java.util.Iterator it = kinder.iterator();

        Element tmp;

        while(it.hasNext()) {
          tmp = (Element) it.next();  
          System.out.println("\n");
          System.out.println("   Kind: %s, Text: %s \n" +
                                  tmp.getName() + tmp.getText());
        }

        // zur Wurzel ein neues Element am Ende hinzufuegen
        Element neuesKind = new Element("Sektion");

        Text derText = new Text("Java-Anwendungen"); 
        neuesKind.addContent(derText);
        neuesKind.setAttribute("nummer","II");

        wurzel.addContent(neuesKind); 

        // in eine XML-Datei ausgeben
        FileWriter fw = new FileWriter("res/xml/"+input1+".xml");
        XMLOutputter ausgabe = new XMLOutputter();
        Format format = ausgabe.getFormat();
        format.setEncoding("iso-8859-1");
        ausgabe.setFormat(format);
        ausgabe.output(doc, fw); 
      } catch(Exception e) {
        System.out.println(" Fehler! Exception %s\n" + e);
      }
  }
}
