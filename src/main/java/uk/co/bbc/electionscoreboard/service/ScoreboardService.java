package uk.co.bbc.electionscoreboard.service;

import uk.co.bbc.electionscoreboard.dto.ConstituencyResults;
import uk.co.bbc.electionscoreboard.dto.Scoreboard;

/**
 * Created by Chris on 13-Aug-17.
 */
public interface ScoreboardService {
    public Scoreboard getScoreboard();

    public void addConstituencyResults(ConstituencyResults constituencyResults);
}
