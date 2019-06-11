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


	@Override
	public void validate(String path) throws LiteratureDatabaseException {

		try {
			SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = factory.newSchema(new File("schema.xsd"));
			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(new File(path)));
		} catch (SAXException e) {
			System.out.println("the following Error occurred: " + e.getMessage());
			throw new LiteratureDatabaseException();
		} catch (IOException e) {
			System.out.println("the following Error occurred: " + e.getMessage());
			throw new LiteratureDatabaseException();
		}
	}


	@Override
	public DatabaseService load(String path) throws LiteratureDatabaseException {

		JAXBContext jaxbContext;
		try {DatabaseServiceImpl dsI = new DatabaseServiceImpl();
			jaxbContext = JAXBContext.newInstance(Database.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			dsI.db = (Database) jaxbUnmarshaller.unmarshal(new File(path));
			System.out.println(dsI);
			return dsI;

		}catch (JAXBException e){
			System.out.println("Exception: "+e.getMessage() + e.getErrorCode());
			throw new LiteratureDatabaseException();
		}
	}

	@Override
	public DatabaseService create() throws LiteratureDatabaseException{
			DatabaseServiceImpl NewDatabase = new DatabaseServiceImpl();
			NewDatabase.db = new Database();

		if ( NewDatabase != null ){
			return NewDatabase;
		}else {
			throw new LiteratureDatabaseException();
		}
	}

}
