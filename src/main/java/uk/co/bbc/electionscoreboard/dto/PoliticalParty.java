package uk.co.bbc.electionscoreboard.dto;

/**
 * Created by Chris on 16-Aug-17.
 */
public enum PoliticalParty {
    LABOUR("LAB"),
    CONSERVATIVE("CON"),
    LIBERAL_DEMOCRAT("LD"),
    GREEN("GRN"),
    SCOTTISH_NATIONAL_PARTY("SNP"),
    PLAID_CYMRU("PC"),
    UNITED_KINGDOM_INDEPENDENCE_PARTY("UKIP"),
    OTHER("OTH");

    private final String partyKey;

    PoliticalParty(String partyKey) {
        this.partyKey = partyKey;
    }

    public String partyKey() {
        return partyKey;
    }
}
