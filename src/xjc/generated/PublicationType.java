//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 generiert 
// Siehe <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2019.06.07 um 12:13:45 AM CEST 
//


package generated;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für publicationType.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="publicationType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="article"/&gt;
 *     &lt;enumeration value="techreport"/&gt;
 *     &lt;enumeration value="book"/&gt;
 *     &lt;enumeration value="masterthesis"/&gt;
 *     &lt;enumeration value="phdthesis"/&gt;
 *     &lt;enumeration value="inproceeding"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "publicationType")
@XmlEnum
public enum PublicationType {

    @XmlEnumValue("article")
    ARTICLE("article"),
    @XmlEnumValue("techreport")
    TECHREPORT("techreport"),
    @XmlEnumValue("book")
    BOOK("book"),
    @XmlEnumValue("masterthesis")
    MASTERTHESIS("masterthesis"),
    @XmlEnumValue("phdthesis")
    PHDTHESIS("phdthesis"),
    @XmlEnumValue("inproceeding")
    INPROCEEDING("inproceeding");
    private final String value;

    PublicationType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PublicationType fromValue(String v) {
        for (PublicationType c: PublicationType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
