package uk.co.bbc.electionscoreboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uk.co.bbc.electionscoreboard.dto.ConstituencyResults;
import uk.co.bbc.electionscoreboard.dto.NationalPoliticalParty;
import uk.co.bbc.electionscoreboard.dto.Scoreboard;
import uk.co.bbc.electionscoreboard.service.ScoreboardService;

import java.util.List;

/**
 * Created by Chris on 19-Aug-17.
 */

@RestController
@RequestMapping("/scores")
public class ScoreboardController {

    @Autowired
    public ScoreboardService scoreboardService;

    @RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE})
    public List<NationalPoliticalParty> getNationalPoliticalPartyResults() {
        Scoreboard scoreboard = scoreboardService.getScoreboard();
        return scoreboard.getTruncatedNationalParties();
    }

    @RequestMapping(method = RequestMethod.POST, produces = {MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> push(@RequestBody ConstituencyResults constituencyResults) {
        scoreboardService.addConstituencyResults(constituencyResults);
        return new ResponseEntity<>(constituencyResults.getConstituencyResult().getConstituencyName() + " added", HttpStatus.CREATED);
    }
}
