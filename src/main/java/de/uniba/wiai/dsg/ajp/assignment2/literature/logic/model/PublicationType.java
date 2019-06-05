package de.uniba.wiai.dsg.ajp.assignment2.literature.logic.model;


import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum(String.class)
public enum PublicationType {

	@XmlEnumValue(value = "article") ARTICLE,
	@XmlEnumValue(value ="techreport")TECHREPORT,
	@XmlEnumValue(value ="book")BOOK,
	@XmlEnumValue(value ="masterthesis")MASTERSTHESIS,
	@XmlEnumValue(value ="phdthesis")PHDTHESIS,
	@XmlEnumValue(value ="inproceeding")INPROCEEDINGS;

}
