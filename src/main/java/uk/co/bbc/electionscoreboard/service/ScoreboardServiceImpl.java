package uk.co.bbc.electionscoreboard.service;

import org.springframework.stereotype.Service;
import uk.co.bbc.electionscoreboard.dto.ConstituencyResults;
import uk.co.bbc.electionscoreboard.dto.NationalParty;
import uk.co.bbc.electionscoreboard.dto.Result;
import uk.co.bbc.electionscoreboard.dto.Scoreboard;

import java.util.*;

/**
 * Created by Chris on 13-Aug-17.
 */
@Service
public class ScoreboardServiceImpl implements ScoreboardService {
    private Scoreboard scoreboard;

    public ScoreboardServiceImpl() {
        NationalParty labourParty = new NationalParty();
        labourParty.setPartyCode("LAB");
        labourParty.setSeats(new Integer(0));
        NationalParty others = new NationalParty();
        others.setPartyCode("OTHERS");
        others.setSeats(new Integer(0));
        TreeMap<String, NationalParty> nationalParties = new TreeMap<String, NationalParty>();
        nationalParties.put(labourParty.getPartyCode(), labourParty);
        nationalParties.put(others.getPartyCode(), others);

        scoreboard = new Scoreboard(nationalParties);
    }

    @Override
    public void addConstituencyResults(ConstituencyResults constituencyResults) {
        SortedSet<Result> resultSortedSet = constituencyResults.getConstituencyResult().getResults().getResult();
        // top result wins the seat
        String winner = resultSortedSet.first().getPartyCode();
        scoreboard.getNationalParties().get(winner).seatWon();

        for (Result result : resultSortedSet) {
            if (scoreboard.getNationalParties().containsKey(result.getPartyCode())) {
                Long overallVotes = scoreboard.getNationalParties().get(result.getPartyCode()).getOverallVotes();
                Long newOverallVotes = new Long(overallVotes.longValue() + result.getVotes());
                Float overallShare = scoreboard.getNationalParties().get(result.getPartyCode()).getOverallShare();
                Float newOverallShare = new Float(overallShare.floatValue() + result.getShare() / 2.0f);

                scoreboard.getNationalParties().get(result.getPartyCode()).setOverallVotes(newOverallVotes);
                scoreboard.getNationalParties().get(result.getPartyCode()).setOverallShare(newOverallShare);

            }
        }
    }

    @Override
    public Scoreboard getScoreboard() {
        return scoreboard;
    }

}
