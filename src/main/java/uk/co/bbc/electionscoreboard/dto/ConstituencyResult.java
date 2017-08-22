package uk.co.bbc.electionscoreboard.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.io.Serializable;

/**
 * Created by Chris on 13-Aug-17.
 */
//@XmlAccessorType(XmlAccessType.FIELD)
//@XmlType(name = "", propOrder = {
//        "consituencyId",
//        "constituencyName",
//        "results"
//})
//@XmlRootElement(name = "constituencyResult")
@JacksonXmlRootElement(localName = "constituencyResult" )
public class ConstituencyResult implements Serializable {
    //@XmlAttribute(name = "seqNo", required = true)
    @JacksonXmlProperty(localName = "seqNo", isAttribute = true)
    private int seqNo;
    //@XmlElement(required = true)
    @JacksonXmlProperty(localName = "consituencyId")
    private int consituencyId;
    //@XmlElement(required = true)
    @JacksonXmlProperty(localName = "constituencyName")
    private String constituencyName;
    //@XmlElement(required = true)
    @JacksonXmlProperty(localName = "results")
    private Results results;

    public int getSeqNo() {
        return seqNo;
    }

    public int getConstituencyId() {
        return consituencyId;
    }

    public String getConstituencyName() {
        return constituencyName;
    }

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }

    public void setSeqNo(int seqNo) {
        this.seqNo = seqNo;
    }

    public void setConstituencyId(int constituencyId) {
        this.consituencyId = constituencyId;
    }

    public void setConstituencyName(String constituencyName) {
        this.constituencyName = constituencyName;
    }
}
