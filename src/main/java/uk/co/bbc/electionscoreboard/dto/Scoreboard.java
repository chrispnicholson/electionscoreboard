package uk.co.bbc.electionscoreboard.dto;

import java.util.SortedMap;

/**
 * Created by Chris on 13-Aug-17.
 */
public class Scoreboard {
    private SortedMap<String, NationalParty> nationalParties;

    public Scoreboard(SortedMap<String, NationalParty> nationalParties) {
        this.nationalParties = nationalParties;
    }

    public SortedMap<String, NationalParty> getNationalParties() {
        return nationalParties;
    }
}
