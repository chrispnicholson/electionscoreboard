package uk.co.bbc.electionscoreboard.dto;

import javax.xml.bind.annotation.*;

/**
 * Created by Chris on 13-Aug-17.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "consituencyId",
        "constituencyName",
        "results"
})
@XmlRootElement(name = "constituencyResult")
public class ConstituencyResult {
    @XmlAttribute(name = "seqNo", required = true)
    private int seqNo;
    @XmlElement(required = true)
    private int consituencyId;
    @XmlElement(required = true)
    private String constituencyName;
    @XmlElement(required = true)
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
