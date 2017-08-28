package uk.co.bbc.electionscoreboard.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Chris on 13-Aug-17.
 */
@XmlRootElement
public class NationalPoliticalParty {
    private PoliticalPartyCode partyCode;
    private Integer seats = new Integer(0);
    private Long overallVotes = new Long(0l);
    private Float overallShare = new Float(0.0f);

    // aggregate share - this needs divided by number of constituencies called
    private Float aggregateShare = new Float(0.0f);

    public void setPartyCode(PoliticalPartyCode partyCode) {
        this.partyCode = partyCode;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public PoliticalPartyCode getPartyCode() {
        return partyCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NationalPoliticalParty that = (NationalPoliticalParty) o;

        return partyCode.equals(that.partyCode);
    }

    @Override
    public int hashCode() {
        return partyCode.hashCode();
    }

    public Long getOverallVotes() {
        return overallVotes;
    }

    public void setOverallVotes(Long overallVotes) {
        this.overallVotes = overallVotes;
    }

    public void seatWon() {
        this.seats = new Integer(seats.intValue() + 1);
    }

    public Float getOverallShare() {
        return overallShare;
    }

    public void setOverallShare(Float overallShare) {
        this.overallShare = overallShare;
    }

    public void addSeats(Integer addedSeats) {
        this.seats = new Integer(this.seats.intValue() + addedSeats);
    }

    public void addOverallShare(Float addedOverallShare) {
        this.overallShare = new Float(this.getOverallShare() + addedOverallShare);
    }

    public Float getAggregateShare() {
        return aggregateShare;
    }

    public void setAggregateShare(Float aggregateShare) {
        this.aggregateShare = aggregateShare;
    }

}
