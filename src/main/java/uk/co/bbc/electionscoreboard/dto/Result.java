package uk.co.bbc.electionscoreboard.dto;

import javax.xml.bind.annotation.*;

/**
 * Created by Chris on 13-Aug-17.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "partyCode",
        "votes",
        "share"
})
public class Result implements Comparable<Result> {
    @XmlElement(required = true)
    private String partyCode; //TODO: This should really be an enum
    @XmlElement(required = true)
    private Long votes;
    @XmlElement(required = true)
    private Float share;

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
