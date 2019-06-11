package de.uniba.wiai.dsg.ajp.assignment2.literature.logic.impl;


import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.DatabaseService;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.LiteratureDatabaseException;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.ValidationHelper;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.model.Author;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.model.Database;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.model.Publication;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.model.PublicationType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class DatabaseServiceImpl extends Database implements DatabaseService{

	public Database db = new Database();

	@Override
	public void addPublication(String title, int yearPublished,
							   PublicationType type, List<Author> authors, String id)
			throws LiteratureDatabaseException {

		Publication publication = new Publication();

		if (title != null) {
			publication.setTitle(title);
			if (yearPublished <= 0) publication.setYearPublished(yearPublished);
			if (type != null) publication.setType(type);
			if (authors != null && !authors.isEmpty() && DoNothasDuplicate(authors)) publication.setAuthors(authors);
			if (id != null && !id.isEmpty()) publication.setId(id);
		}
		getPublications().add(publication);
	}


	@Override
	public void removePublicationByID(String id) throws LiteratureDatabaseException {


		if (id != null && !id.isEmpty()) {
			for(int i= 0; i<getPublications().size();i++) {
				if(getPublications().get(i).getId().matches(id)) {
					getPublications().remove(i);
					break;
				}
			}
		}
	}

	@Override
	public void removeAuthorByID(String id) throws LiteratureDatabaseException {

		if (id != null && !id.isEmpty()) {
			for(int i= 0; i<getAuthors().size();i++) {
				if(getAuthors().get(i).getId().matches(id)) {
					getAuthors().remove(i);
					break;
				}
			}
		}else { throw new LiteratureDatabaseException();}
	}

	@Override
	public void addAuthor(String name, String email, String id) throws LiteratureDatabaseException {
		Author newAuthor = new Author();

		if ( name != null && !name.isEmpty() ) {
			newAuthor.setName(name);
			if ( ValidationHelper.isEmail(email) && email != null && !email.isEmpty() ) {
				newAuthor.setEmail(email);
				if ( id != null && !id.isEmpty() && ValidationHelper.isId(id) ) {
					newAuthor.setId(id);

					getAuthors().add(newAuthor);
				}
			}
		} else { throw new LiteratureDatabaseException();}
	}

	@Override
	public List<Publication> getPublications() {
		return db.getPublications();
	}

	@Override
	public List<Author> getAuthors() {
		return db.getAuthors();
	}

	@Override
	public void clear() {
		Database removeAllAuthorsAndBooks = new Database();
		removeAllAuthorsAndBooks.getAuthors().clear();
		removeAllAuthorsAndBooks.getPublications().clear();
	}

	/**
	 * Prints the current database to the console by marshalling it to XML
	 *
	 * @throws LiteratureDatabaseException if there are errors while marshalling the current database
	 */
	@Override
	public void printXMLToConsole() throws LiteratureDatabaseException {

		JAXBContext context;
		try{
			//call the XmlRootElement to create the object to be manipulated
			context = JAXBContext.newInstance(Database.class);
			db.getAuthors();
			db.getPublications();
			 /*
			// XML doc -->Java Code   // Unmarshalling (TO CHECK IF IS WORKING)
			Unmarshaller um = context.createUnmarshaller();
			Database db = (Database) um.unmarshal(new File("database.xml"));
			db.getAuthors();
			db.getPublications();
			  */
			//automatic Java Code -->  XML Document  //Create Marshaller
			Marshaller ms = context.createMarshaller();
			//Required formatting??
			ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			ms.marshal(db, new PrintWriter(System.out));
		}catch (JAXBException e){
			System.out.println("Exception: "+e.getMessage() + e.getErrorCode());
			throw new LiteratureDatabaseException();
		}
	}

	/**
	 * Saves the current database to the given file by marshalling it to XML
	 *
	 * @param path the path of the file. Must not be null or empty.
	 * @throws LiteratureDatabaseException if path is null or empty or there are errors during
	 *                                     marshalling the current database
	 */
	@Override
	public void saveXMLToFile(String path) throws LiteratureDatabaseException{
		JAXBContext context;
		try{
			//call the XmlRootElement to create the object to be manipulated
			context = JAXBContext.newInstance(Database.class);
			db.getAuthors();
			db.getPublications();
			 /*
			// XML doc -->Java Code   // Unmarshalling (TO CHECK IF IS WORKING)
			Unmarshaller um = context.createUnmarshaller();
			Database db = (Database) um.unmarshal(new File("database.xml"));
			db.getAuthors();
			db.getPublications();
			  */
			//automatic Java Code -->  XML Document  //Create Marshaller
			Marshaller ms = context.createMarshaller();
			ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			ms.marshal(db, new File("database_Output.xml"));
		}catch (JAXBException e){
			System.out.println("Exception: "+e.getMessage() + e.getErrorCode());
			throw new LiteratureDatabaseException();
		}
	}

	public boolean DoNothasDuplicate(List<Author> authors) {
		List<Author> authorsList = new ArrayList<>();

		for (Author element : authors) {
			if (!authorsList.add(element)) return false;
		}
		return true;

	}


}

