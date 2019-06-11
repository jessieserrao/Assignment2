package de.uniba.wiai.dsg.ajp.assignment2.literature;


import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.DatabaseService;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.LiteratureDatabaseException;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.impl.ConsoleMenu;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.model.Author;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.model.Database;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.impl.DatabaseServiceImpl;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.impl.MainServiceImpl;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.model.Publication;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import static de.uniba.wiai.dsg.ajp.assignment2.literature.logic.model.PublicationType.BOOK;

public class Main {

	public static void main(String[] args) {
		// TODO startet eure Anwendung ueber diese main-Methode

		/*JAXBContext context;

		try{
			//call the XmlRootElement to create the object to be manipulated
			context = JAXBContext.newInstance(Database.class);


			// automatic XML doc -->Java Code   // Unmarshalling
			Unmarshaller um = context.createUnmarshaller();
			Database dataB = (Database) um.unmarshal(new File("database.xml"));
			dataB.getAuthors();
			dataB.getPublications();


			// automatic Java Code -->  XML Document  //Marshalling
			Marshaller ms = context.createMarshaller();
			ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			ms.marshal(dataB, new File("database.xml"));

		}catch (JAXBException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/

			//ConsoleMenu.Menu();

			try{
			//DatabaseServiceImpl dbI =new DatabaseServiceImpl();
			MainServiceImpl dbs =new MainServiceImpl();
			//dbI.clear();
			//dbI.printXMLToConsole();


			//dbs.validate("database.xml");
			dbs.load("database.xml");


			//DatabaseServiceImpl dbI =new DatabaseServiceImpl();
			//dbI.addAuthor("Jessie", "jessie@hotmail.com", "10" );
			//dbI.addPublication("titulo", 1919, BOOK, author, "33" );

			//dbI.printXMLToConsole();


			}catch (LiteratureDatabaseException e){
				System.out.println(MessageFormat.format("the following Error occurred: {0}", e.getMessage()));
			}





	}

}


