package uk.co.bbc.electionscoreboard.dto;

/**
 * Created by Chris on 13-Aug-17.
 */
public class NationalParty implements Comparable<NationalParty> {
    private String partyCode;
    private Integer seats = new Integer(0);
    private Long overallVotes = new Long(0l);
    private Float overallShare = new Float(0.0f);

    public void setPartyCode(String partyCode) {
        this.partyCode = partyCode;
    }

    @Override
    public int compareTo(NationalParty o) {
        return this.seats.compareTo(o.seats);
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public String getPartyCode() {
        return partyCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NationalParty that = (NationalParty) o;

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
}
