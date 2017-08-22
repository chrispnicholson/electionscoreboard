package uk.co.bbc.electionscoreboard.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.io.Serializable;

/**
 * Created by Chris on 13-Aug-17.
 */
//@XmlAccessorType(XmlAccessType.FIELD)
//@XmlType(name = "", propOrder = {
//        "partyCode",
//        "votes",
//        "share"
//})
//@XmlRootElement(name = "result")
@JacksonXmlRootElement(localName = "result")
public class Result implements Serializable, Comparable<Result> {
    //@XmlElement(required = true)
    @JacksonXmlProperty(localName = "partyCode")
    private String partyCode; //TODO: This should really be an enum
    //@XmlElement(required = true)
    @JacksonXmlProperty(localName = "votes")
    private Long votes = new Long(0l);
    //@XmlElement(required = true)
    @JacksonXmlProperty(localName = "share")
    private Float share = new Float(0.0f);

    public Result() {

    }

    public void setPartyCode(String partyCode) {
        this.partyCode = partyCode;
    }

    public void setVotes(Long votes) {
        this.votes = votes;
    }

    public void setShare(Float share) {
        this.share = share;
    }

    public Float getShare() {
        return share;
    }

    public String getPartyCode() {
        return partyCode;
    }

    public Long getVotes() {
        return votes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Result result = (Result) o;

        return partyCode.equals(result.partyCode);
    }

    @Override
    public int hashCode() {
        return partyCode.hashCode();
    }

    @Override
    public int compareTo(Result other) {
        int i = other.share.compareTo(this.share);

        if (i != 0) return i;

        i = other.votes.compareTo(this.votes);

        return i;
    }
}
