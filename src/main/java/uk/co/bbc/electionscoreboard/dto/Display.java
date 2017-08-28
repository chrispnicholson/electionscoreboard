package uk.co.bbc.electionscoreboard.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.text.DecimalFormat;

@JacksonXmlRootElement(localName = "display")
public class Display {

    @JacksonXmlProperty(localName = "partyCode")
    private String partyCode;

    @JacksonXmlProperty(localName = "seats")
    private int seats;

    @JsonIgnore
    private float voteShareFloat;

    @JacksonXmlProperty(localName = "voteShare")
    private String voteShare;

    @JsonIgnore
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");

    public String getPartyCode() {
        return partyCode;
    }

    public void setPartyCode(String partyCode) {
        this.partyCode = partyCode;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public float getVoteShareFloat() {
        return voteShareFloat;
    }

    public void setVoteShareFloat(float voteShareFloat) {
        this.voteShareFloat = voteShareFloat;
        this.voteShare = decimalFormat.format(this.voteShareFloat);
    }

    public String getVoteShare() {
        return voteShare;
    }
}
