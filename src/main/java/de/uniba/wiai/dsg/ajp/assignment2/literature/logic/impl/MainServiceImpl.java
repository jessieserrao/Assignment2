package de.uniba.wiai.dsg.ajp.assignment2.literature.logic.impl;

import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.DatabaseService;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.LiteratureDatabaseException;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.MainService;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.model.Database;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.*;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class MainServiceImpl extends DatabaseServiceImpl implements MainService {

	/**
	 * Default constructor required for grading.
	 */
	public MainServiceImpl() {
		/*
		 * DO NOT REMOVE - REQUIRED FOR GRADING
		 *
		 * YOU CAN EXTEND IT BELOW THIS COMMENT
		 */
	}


	/**
	 * Validates the XML file identified by <code>path</code> with an XML Schema file
	 *
	 * @param path the path to the XML file to be validated
	 * @throws LiteratureDatabaseException if the file identified by <code>path</code> is not valid
	 */
	@Override
	public void validate(String path) throws LiteratureDatabaseException {

		try {
			SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = factory.newSchema(new File("schema.xsd"));
			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(new File(path)));
			System.out.println("The XML Document: " + path + " is valid \n" );

		} catch (SAXException e) {
			System.out.println("the following Error occurred: " + e.getMessage());
			throw new LiteratureDatabaseException();
		} catch (IOException e) {
			System.out.println("the following Error occurred: " + e.getMessage());
			throw new LiteratureDatabaseException();
		}
	}


	/**
	 * Loads an XML file by unmarshalling it into memory
	 *
	 * @param path the path of the XML file to be unmarshalled
	 * @return a service handle (<code>DatabasService</code>) for manipulating the literature database
	 * @throws LiteratureDatabaseException
	 */
	@Override
	public DatabaseService load(String path) throws LiteratureDatabaseException {

		JAXBContext jaxbContext;
		try {DatabaseServiceImpl dsI = new DatabaseServiceImpl();
			jaxbContext = JAXBContext.newInstance(Database.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			dsI.db = (Database) jaxbUnmarshaller.unmarshal(new File(path));
			return dsI;

		}catch (JAXBException e){
			System.out.println("Exception: "+e.getMessage() + e.getErrorCode());
			throw new LiteratureDatabaseException();
		}
	}

	/**
	 * Creates a new and empty literature database
	 *
	 * @return a service handle (<code>DatabasService</code>) for manipulating the literature database
	 * @throws LiteratureDatabaseException
	 */
	@Override
	public DatabaseService create() throws LiteratureDatabaseException{
			DatabaseService dbI = new DatabaseServiceImpl();
			return dbI;
	}

}
