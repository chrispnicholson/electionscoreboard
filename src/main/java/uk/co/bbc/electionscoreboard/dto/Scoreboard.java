package uk.co.bbc.electionscoreboard.dto;

import java.util.*;

/**
 * Created by Chris on 13-Aug-17.
 */
public class Scoreboard {
    private SortedMap<PoliticalPartyCode, NationalPoliticalParty> nationalParties;

    public Scoreboard(SortedMap<PoliticalPartyCode, NationalPoliticalParty> nationalParties) {
        this.nationalParties = nationalParties;
    }

    public SortedMap<PoliticalPartyCode, NationalPoliticalParty> getNationalParties() {
        return nationalParties;
    }

    public List<NationalPoliticalParty> getTruncatedNationalParties() {
        List<NationalPoliticalParty> nationalPoliticalPartyList = new ArrayList<NationalPoliticalParty>();
        nationalPoliticalPartyList.addAll(nationalParties.values());
        List<NationalPoliticalParty> removalList = new ArrayList<NationalPoliticalParty>();
        Collections.sort(nationalPoliticalPartyList, new PoliticalPartyComparator());

        // leave 0,1,2 alone
        // navigate from 3 onward and add them all to OTH, removing parties as you go
        for (int i = 0; i < nationalPoliticalPartyList.size(); i++) {
            if (i > 2 && i != (nationalPoliticalPartyList.size()-1)) {
                // get current national party
                NationalPoliticalParty currentPoliticalParty = nationalPoliticalPartyList.get(i);
                // get other national party
                NationalPoliticalParty others = nationalPoliticalPartyList.get(nationalPoliticalPartyList.size() - 1);
                // add seats and share from current national party to other national party
                others.addSeats(currentPoliticalParty.getSeats());
                others.addOverallShare(currentPoliticalParty.getOverallShare());
                // add this party to removal list
                removalList.add(currentPoliticalParty);
            }
        }

        nationalPoliticalPartyList.removeAll(removalList);

        return nationalPoliticalPartyList;
    }

    public int seatsCalled() {
        int seatsCalled = 0;

        Collection parties = nationalParties.values();
        Iterator<NationalPoliticalParty> nationalPoliticalPartyIterator = parties.iterator();
        while(nationalPoliticalPartyIterator.hasNext()) {
            seatsCalled += nationalPoliticalPartyIterator.next().getSeats();
        }

        return seatsCalled;
    }
    private class PoliticalPartyComparator implements Comparator<NationalPoliticalParty> {

        @Override
        public int compare(NationalPoliticalParty o1, NationalPoliticalParty o2) {
            return o2.getSeats().compareTo(o1.getSeats());
        }
    }
}
