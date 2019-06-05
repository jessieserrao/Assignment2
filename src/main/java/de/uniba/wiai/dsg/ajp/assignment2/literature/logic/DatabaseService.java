package de.uniba.wiai.dsg.ajp.assignment2.literature.logic;

import java.util.List;

import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.model.Author;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.model.Database;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.model.Publication;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.model.PublicationType;

public interface DatabaseService {

    /**
     * Adds a new publication.
     * 
     * @param title
     *            the title of the publication. Must not be empty or null.
     * @param yearPublished
     *            the year the publication was first published. Must not be
     *            negative.
     * @param type
     *            the type of the publication. Must not be null.
     * @param authors
     *            Must not be null; must at least contain one author; must not
     *            contain duplicates of authors
     * @param id
     *            the id of the publication. Must not be null or empty. Must be
     *            a valid id and unique within the current database.
     * @throws LiteratureDatabaseException
     *             if any of the above preconditions are not met
     */
    void addPublication(String title, int yearPublished, PublicationType type,
	    List<Author> authors, String id) throws LiteratureDatabaseException;

    /**
     * Removes an existing publication identified by its ID
     * 
     * @param id
     *            the ID of the publication to be removed. Must not be empty or
     *            null. Must be a valid ID.
     * @throws LiteratureDatabaseException
     *             if any of the above preconditions are not met
     */
    void removePublicationByID(String id) throws LiteratureDatabaseException;

    /**
     * Removes an existing author by his/her ID. 
     * Removing of an author is only possible if there is no publication assigned 
     * to the author.
     * 
     * @param id
     *            the ID of the author to be removed. Must not be empty or null.
     *            Must be a valid ID.
     * @throws LiteratureDatabaseException
     *             if any of the above preconditions are not met
     */
    void removeAuthorByID(String id) throws LiteratureDatabaseException;

    /**
     * Adds a new author
     * 
     * @param name
     *            the name of the author. Must not be null or empty.
     * @param email
     *            the email address of the author. Must not be null or empty.
     *            Must be a valid mail address.
     * @param id
     *            the id of the author. Must not be null or empty. Must be a
     *            valid and unique id.
     * 
     * @throws LiteratureDatabaseException
     *             if any of the above preconditions are not met
     */
    void addAuthor(String name, String email, String id)
	    throws LiteratureDatabaseException;

    /**
     * Gets a <code>List</code> of publications stored in the database
     * 
     * @return a <code>List</code> of publications
     */
    List<Publication> getPublications();

    /**
     * Gets a <code>List</code> of authors stored in the database
     * 
     * @return a <code>List</code> of authors
     */
    List<Author> getAuthors();

    /**
     * Removes all authors and books.
     */
    void clear();

    /**
     * Prints the current database to the console by marshalling it to XML
     * 
     * @throws LiteratureDatabaseException
     *             if there are errors while marshalling the current database
     */
    void printXMLToConsole() throws LiteratureDatabaseException;

    /**
     * Saves the current database to the given file by marshalling it to XML
     * 
     * @param path
     *            the path of the file. Must not be null or empty.
     * @throws LiteratureDatabaseException
     *             if path is null or empty or there are errors during
     *             marshalling the current database
     */
    void saveXMLToFile(String path) throws LiteratureDatabaseException;


}
