package uk.co.bbc.electionscoreboard.dto;

import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by Chris on 13-Aug-17.
 */
public class Scoreboard {
    private float constituenciesCalled = 0.0f;
    private SortedMap<PoliticalPartyCode, NationalPoliticalParty> nationalParties;

    public Scoreboard(SortedMap<PoliticalPartyCode, NationalPoliticalParty> nationalParties) {
        this.nationalParties = nationalParties;
    }

    public SortedMap<PoliticalPartyCode, NationalPoliticalParty> getNationalParties() {
        return nationalParties;
    }

    public List<Display> getTruncatedNationalParties() {
        List<NationalPoliticalParty> nationalPoliticalPartyList = new ArrayList<NationalPoliticalParty>();
        nationalPoliticalPartyList.addAll(new ArrayList<NationalPoliticalParty>(nationalParties.values()));
        List<NationalPoliticalParty> removalList = new ArrayList<NationalPoliticalParty>();
        Collections.sort(nationalPoliticalPartyList, new PoliticalPartyComparator());
        List<Display> displayList = new ArrayList<Display>();
        Display others = new Display();
        others.setPartyCode("OTH");
        others.setSeats(0);

        // leave 0,1,2 alone
        // navigate from 3 onward and add them all to OTH, removing parties as you go
        for (int i = 0; i < nationalPoliticalPartyList.size(); i++) {
            if (i > 2) {
                // lower end of list, but not others

                int seatsForOthers = others.getSeats();
                int seatsForCurrentParty = nationalPoliticalPartyList.get(i).getSeats();
                others.setSeats(seatsForOthers + seatsForCurrentParty);
                float shareForOthers = others.getVoteShareFloat();
                float shareForCurrentParty = nationalPoliticalPartyList.get(i).getOverallShare();
                others.setVoteShareFloat(shareForOthers + shareForCurrentParty);
            } else {
                Display display = new Display();
                display.setPartyCode(nationalPoliticalPartyList.get(i).getPartyCode().name());
                display.setSeats(nationalPoliticalPartyList.get(i).getSeats());
                display.setVoteShareFloat(nationalPoliticalPartyList.get(i).getOverallShare());
                displayList.add(display);
            }
        }

        //nationalPoliticalPartyList.removeAll(removalList);
        displayList.add(others);

        return displayList;
    }

    public float getConstituenciesCalled() {
        return constituenciesCalled;
    }

    public void incrementConstituenciesCalled() {
        constituenciesCalled += 1.0f;
    }

    private class PoliticalPartyComparator implements Comparator<NationalPoliticalParty> {

        @Override
        public int compare(NationalPoliticalParty o1, NationalPoliticalParty o2) {
            return o2.getSeats().compareTo(o1.getSeats());
        }
    }
}
