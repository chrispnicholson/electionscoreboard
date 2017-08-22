package uk.co.bbc.electionscoreboard.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.io.Serializable;

/**
 * Created by Chris on 13-Aug-17.
 */

//@XmlAccessorType(XmlAccessType.FIELD)
//@XmlType(name = "", propOrder = {
//        "constituencyResult"
//})
//@XmlRootElement(name = "constituencyResults")
@JacksonXmlRootElement(localName = "constituencyResults")
public class ConstituencyResults implements Serializable {
    //@XmlElement(required = true)
    @JacksonXmlProperty
    private ConstituencyResult constituencyResult;

    public ConstituencyResult getConstituencyResult() {
        return constituencyResult;
    }

    public void setConstituencyResult(ConstituencyResult constituencyResult) {
        this.constituencyResult = constituencyResult;
    }
}
