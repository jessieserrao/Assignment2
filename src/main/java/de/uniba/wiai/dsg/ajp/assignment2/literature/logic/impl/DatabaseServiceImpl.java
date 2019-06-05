package de.uniba.wiai.dsg.ajp.assignment2.literature.logic.impl;


import java.io.File;
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

public class DatabaseServiceImpl implements DatabaseService {


	/**
	 * Adds a new publication.
	 *
	 * @param title         the title of the publication. Must not be empty or null.
	 * @param yearPublished the year the publication was first published. Must not be
	 *                      negative.
	 * @param type          the type of the publication. Must not be null.
	 * @param authors       Must not be null; must at least contain one author; must not
	 *                      contain duplicates of authors
	 * @param id            the id of the publication. Must not be null or empty. Must be
	 *                      a valid id and unique within the current database.
	 * @throws LiteratureDatabaseException if any of the above preconditions are not met
	 */
	@Override
	public void addPublication(String title, int yearPublished,
							   PublicationType type, List<Author> authors, String id)
			throws LiteratureDatabaseException {

		Publication publication = new Publication();

		if (title != null) {
			publication.setTitle(title);
		}
		else if (yearPublished <= 0) {
			publication.setYearPublished(yearPublished);
		}
		else if (type != null) {
			publication.setType(type);
		}
		else if (authors != null && !authors.isEmpty() && DoNothasDuplicate(authors)) {
			publication.setAuthors(authors);
		}
		else if (ValidationHelper.isId(id) && id != null && !id.isEmpty() && !publication.getId().contains(id)) {
			publication.setId(id);
		}
	}

	/**
	 * Removes an existing publication identified by its ID
	 *
	 * @param id the ID of the publication to be removed. Must not be empty or
	 *           null. Must be a valid ID.
	 * @throws LiteratureDatabaseException if any of the above preconditions are not met
	 */

	@Override
	public void removePublicationByID(String id)
			throws LiteratureDatabaseException {
		// TODO Auto-generated method stub
		Author db = new Author();

		if ( id != null && !id.isEmpty() && ValidationHelper.isId(id) ) {
			db.getPublications().contains(id);
			db.getPublications().remove(id);
		}
	}

	/**
	 * Removes an existing author by his/her ID.
	 * Removing of an author is only possible if there is no publication assigned
	 * to the author.
	 *
	 * @param id the ID of the author to be removed. Must not be empty or null.
	 *           Must be a valid ID.
	 * @throws LiteratureDatabaseException if any of the above preconditions are not met
	 */
	@Override
	public void removeAuthorByID(String id)
			throws LiteratureDatabaseException {
		// TODO Auto-generated method stub

		Publication db = new Publication();

		if ( id != null && !id.isEmpty() && ValidationHelper.isId(id) ) {
			db.getAuthors().contains(id);
			db.getAuthors().remove(id);
		}
	}

	/**
	 * Adds a new author
	 *
	 * @param name  the name of the author. Must not be null or empty.
	 * @param email the email address of the author. Must not be null or empty.
	 *              Must be a valid mail address.
	 * @param id    the id of the author. Must not be null or empty. Must be a
	 *              valid and unique id.
	 * @throws LiteratureDatabaseException if any of the above preconditions are not met
	 */
	@Override
	public void addAuthor(String name, String email, String id)
			throws LiteratureDatabaseException {
		Author newAuthor = new Author();

		if ( name != null && !name.isEmpty() ) {
			newAuthor.setName(name);
		}
		else if ( email != null && !email.isEmpty() && ValidationHelper.isEmail(email) ) {
			newAuthor.setEmail(email);
		}
		else if ( id != null && id.isEmpty() && ValidationHelper.isId(id) ) {
			newAuthor.setId(id);
		}
	}

	/**
	 * Gets a <code>List</code> of publications stored in the database
	 *
	 * @return a <code>List</code> of publications
	 */
	@Override
	public List<Publication> getPublications() {
		Database getPublicationsFromDataBase = new Database();
		return getPublicationsFromDataBase.getPublications();
	}

	/**
	 * Gets a <code>List</code> of authors stored in the database
	 *
	 * @return a <code>List</code> of authors
	 */
	@Override
	public List<Author> getAuthors() {
		Database getAuthorsFromDataBase = new Database();
		return getAuthorsFromDataBase.getAuthors();
	}

	/**
	 * Removes all authors and books.
	 */
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
		// TODO Auto-generated method stub

	}

	/**
	 * Saves the current database to the given file by marshalling it to XML
	 *
	 * @param path the path of the file. Must not be null or empty.
	 * @throws LiteratureDatabaseException if path is null or empty or there are errors during
	 *                                     marshalling the current database
	 */
	@Override
	public void saveXMLToFile(String path) throws LiteratureDatabaseException {


		if (path != null && Files.exists(Paths.get(path)) && !path.isEmpty()) {
			try {
				JAXBContext jaxbContext = JAXBContext.newInstance(Database.class);

				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML

				//Print XML String to Console
				jaxbMarshaller.marshal(path, new File("database.xml"));

			} catch (JAXBException e) {
				e.printStackTrace();
			}
		}


	}


	public boolean DoNothasDuplicate(List<Author> authors) {
		List<Author> authorsList = new ArrayList<>();

		for (Author element : authors) {
			if (!authorsList.add(element)) {
				return false;
			}
		}
		return true;

	}



}

