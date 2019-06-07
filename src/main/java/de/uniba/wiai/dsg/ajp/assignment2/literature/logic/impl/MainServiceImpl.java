package de.uniba.wiai.dsg.ajp.assignment2.literature.logic.impl;

import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.DatabaseService;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.LiteratureDatabaseException;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.MainService;
import de.uniba.wiai.dsg.ajp.assignment2.literature.logic.model.Database;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class MainServiceImpl implements MainService {

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
			Schema schema = factory.newSchema(new File("schema1.xsd"));
			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(new File(path)));
			System.out.println("The XML Document: " + path + " is valid \n" );

		} catch (SAXException e) {
			e.printStackTrace();

			System.out.println("Exception: "+e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Exception: "+e.getMessage());
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
		File xmlFile = new File(path);
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(DatabaseServiceImpl.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			DatabaseServiceImpl db = (DatabaseServiceImpl) jaxbUnmarshaller.unmarshal(xmlFile);

			System.out.println(db);

			return db;

		}catch (JAXBException e){
			System.out.println("Exception: "+e.getMessage());
			return null;
		}
	}


	/**
	 * Creates a new and empty literature database
	 *
	 * @return a service handle (<code>DatabasService</code>) for manipulating the literature database
	 * @throws LiteratureDatabaseException
	 */
	@Override
	public DatabaseService create() throws LiteratureDatabaseException {
		// TODO!!!!!!!!!!!!!!!!!!!!!!!!!
		DatabaseServiceImpl dbI = new DatabaseServiceImpl();

		return null;
	}

}
