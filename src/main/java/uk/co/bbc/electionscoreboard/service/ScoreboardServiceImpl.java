package uk.co.bbc.electionscoreboard.service;

import org.springframework.stereotype.Service;
import uk.co.bbc.electionscoreboard.dto.*;

import java.util.EnumSet;
import java.util.SortedSet;
import java.util.TreeMap;

/**
 * Created by Chris on 13-Aug-17.
 */
@Service
public class ScoreboardServiceImpl implements ScoreboardService {
    private Scoreboard scoreboard;

    public ScoreboardServiceImpl() {
        TreeMap<PoliticalPartyCode, NationalPoliticalParty> nationalParties = new TreeMap<PoliticalPartyCode, NationalPoliticalParty>();

        EnumSet.allOf(PoliticalPartyCode.class).forEach(politicalPartyCode -> {
            NationalPoliticalParty nationalPoliticalParty = new NationalPoliticalParty();
            nationalPoliticalParty.setPartyCode(politicalPartyCode);
            nationalPoliticalParty.setSeats(new Integer(0));

            nationalParties.put(politicalPartyCode, nationalPoliticalParty);
        });

        scoreboard = new Scoreboard(nationalParties);
    }

    @Override
    public void addConstituencyResults(ConstituencyResults constituencyResults) {
        System.out.println("Results called for " + constituencyResults.getConstituencyResult().getSeqNo() + ": " + constituencyResults.getConstituencyResult().getConstituencyName());
        scoreboard.incrementConstituenciesCalled();
        SortedSet<Result> resultSortedSet = constituencyResults.getConstituencyResult().getResults().getResult();
        // top result wins the seat
        PoliticalPartyCode winner = PoliticalPartyCode.valueOf(resultSortedSet.first().getPartyCode().trim());
        System.out.println("Winner is " + winner.name());
        scoreboard.getNationalParties().get(winner).seatWon();

        for (Result result : resultSortedSet) {
            PoliticalPartyCode politicalPartyCode = PoliticalPartyCode.valueOf(result.getPartyCode().trim());
            System.out.println("-------------------------" + result.getPartyCode() + " has " + result.getVotes() + " votes and " + result.getShare() + "% share");

            if (scoreboard.getNationalParties().containsKey(politicalPartyCode)) {
                Long overallVotes = scoreboard.getNationalParties().get(politicalPartyCode).getOverallVotes();
                Long newOverallVotes = new Long(overallVotes.longValue() + result.getVotes());
                Float toBeAddedToAggregateShare = result.getShare();
                Float currentAggregateShare = scoreboard.getNationalParties().get(politicalPartyCode).getAggregateShare();
                scoreboard.getNationalParties().get(politicalPartyCode).setAggregateShare(toBeAddedToAggregateShare + currentAggregateShare);
                currentAggregateShare = scoreboard.getNationalParties().get(politicalPartyCode).getAggregateShare();
//                Float overallShare = scoreboard.getNationalParties().get(politicalPartyCode).getOverallShare();
//                Float newOverallShare = ((result.getShare() / 100.0f) + (overallShare / 100.0f)) /
//                   scoreboard.seatsCalled() * 100.0f;
                Float newOverallShare = currentAggregateShare / scoreboard.getConstituenciesCalled();


                scoreboard.getNationalParties().get(politicalPartyCode).setOverallVotes(newOverallVotes);
                scoreboard.getNationalParties().get(politicalPartyCode).setOverallShare(newOverallShare);

            }
        }
    }

    @Override
    public Scoreboard getScoreboard() {
        return scoreboard;
    }

}
