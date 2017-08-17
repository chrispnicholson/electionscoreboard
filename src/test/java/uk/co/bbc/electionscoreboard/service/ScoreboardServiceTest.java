package uk.co.bbc.electionscoreboard.service;

import org.junit.Before;
import org.junit.Test;
import uk.co.bbc.electionscoreboard.dto.*;

import java.util.SortedSet;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Chris on 13-Aug-17.
 */
//@RunWith(SpringRunner.class)
public class ScoreboardServiceTest {

//    @Autowired
//    private ScoreboardService scoreboardService;

    private ScoreboardService scoreboardService = new ScoreboardServiceImpl();

    private ConstituencyResults constituencyResultsOne;
    private ConstituencyResults constituencyResultsTwo;

//    @TestConfiguration
//    static class ScoreboardServiceImplTestContextConfiguration {
//
//        @Bean
//        public ScoreboardService scoreboardService() {
//            return new ScoreboardServiceImpl();
//        }
//    }

    @Before
    public void setUp() {
        constituencyResultsOne = new ConstituencyResults();
        ConstituencyResult constituencyResultOne = new ConstituencyResult();
        Results resultsOne = new Results();
        SortedSet<Result> resultSetOne = new TreeSet<Result>();

        Result labResult = new Result();
        labResult.setPartyCode("LAB");
        labResult.setVotes(8994l);
        labResult.setShare(33.00f);
        resultSetOne.add(labResult);

        Result conResult = new Result();
        conResult.setPartyCode("CON");
        conResult.setVotes(7924l);
        conResult.setShare(29.10f);
        resultSetOne.add(conResult);

        Result ldResult = new Result();
        ldResult.setPartyCode("LD");
        ldResult.setVotes(5197l);
        ldResult.setShare(19.10f);
        resultSetOne.add(ldResult);

        Result pcResult = new Result();
        pcResult.setPartyCode("PC");
        pcResult.setVotes(3818l);
        pcResult.setShare(14.00f);
        resultSetOne.add(pcResult);

        Result othResult = new Result();
        othResult.setPartyCode("OTH");
        othResult.setVotes(517l);
        othResult.setShare(1.90f);
        resultSetOne.add(othResult);

        Result grnResult = new Result();
        grnResult.setPartyCode("GRN");
        grnResult.setVotes(512l);
        grnResult.setShare(1.90f);
        resultSetOne.add(grnResult);

        Result ukipResult = new Result();
        ukipResult.setPartyCode("UKIP");
        ukipResult.setVotes(296l);
        ukipResult.setShare(1.10f);
        resultSetOne.add(ukipResult);

        resultsOne.setResult(resultSetOne);
        constituencyResultOne.setResults(resultsOne);
        constituencyResultOne.setSeqNo(1);
        constituencyResultOne.setConstituencyId(2);
        constituencyResultOne.setConstituencyName("Aberconwy");
        constituencyResultsOne.setConstituencyResult(constituencyResultOne);

        constituencyResultsTwo = new ConstituencyResults();
        ConstituencyResult constituencyResultTwo = new ConstituencyResult();
        Results resultsTwo = new Results();
        SortedSet<Result> resultSetTwo = new TreeSet<Result>();

        labResult = new Result();
        labResult.setPartyCode("LAB");
        labResult.setVotes(8484l);
        labResult.setShare(33.90f);
        resultSetTwo.add(labResult);

        pcResult = new Result();
        pcResult.setPartyCode("PC");
        pcResult.setVotes(8028l);
        pcResult.setShare(32.10f);
        resultSetTwo.add(pcResult);

        conResult = new Result();
        conResult.setPartyCode("CON");
        conResult.setVotes(4106l);
        conResult.setShare(16.40f);
        resultSetTwo.add(conResult);

        ldResult = new Result();
        ldResult.setPartyCode("LD");
        ldResult.setVotes(3942l);
        ldResult.setShare(15.70f);
        resultSetTwo.add(ldResult);

        ukipResult = new Result();
        ukipResult.setPartyCode("UKIP");
        ukipResult.setVotes(482l);
        ukipResult.setShare(1.90f);
        resultSetTwo.add(ukipResult);

        othResult = new Result();
        othResult.setPartyCode("OTH");
        othResult.setVotes(0l);
        othResult.setShare(0.00f);
        resultSetTwo.add(othResult);

        resultsTwo.setResult(resultSetTwo);
        constituencyResultTwo.setResults(resultsTwo);
        constituencyResultTwo.setSeqNo(2);
        constituencyResultTwo.setConstituencyId(16);
        constituencyResultTwo.setConstituencyName("Arfon");
        constituencyResultsTwo.setConstituencyResult(constituencyResultTwo);
    }

    @Test
    public void getScoreboardForSinglePartyState() {

        scoreboardService.addConstituencyResults(constituencyResultsOne);
        Scoreboard scoreboard = scoreboardService.getScoreboard();

        assertEquals(2, scoreboard.getNationalParties().size());
        assertNotNull( scoreboard.getNationalParties().get("LAB"));
        assertNotNull( scoreboard.getNationalParties().get("OTHERS"));
        assertEquals("LAB", scoreboard.getNationalParties().firstKey());
        assertEquals("OTHERS", scoreboard.getNationalParties().lastKey());
        NationalParty labParty = scoreboard.getNationalParties().get("LAB");
        assertEquals(new Long(8994), labParty.getOverallVotes());
        assertEquals(new Integer(1), labParty.getSeats());
        NationalParty others = scoreboard.getNationalParties().get("OTHERS");
        assertEquals(new Long(0), others.getOverallVotes());
        assertEquals(new Integer(0), others.getSeats());
    }

    @Test
    public void getScoreboardForTwoConstituencies() {

        scoreboardService.addConstituencyResults(constituencyResultsOne);
        scoreboardService.addConstituencyResults(constituencyResultsTwo);
        Scoreboard scoreboard = scoreboardService.getScoreboard();

        assertEquals(2, scoreboard.getNationalParties().size());
        assertNotNull( scoreboard.getNationalParties().get("LAB"));
        assertNotNull( scoreboard.getNationalParties().get("OTHERS"));
        assertEquals("LAB", scoreboard.getNationalParties().firstKey());
        assertEquals("OTHERS", scoreboard.getNationalParties().lastKey());
        NationalParty labParty = scoreboard.getNationalParties().get("LAB");
        assertEquals(new Long(17478), labParty.getOverallVotes());
        assertEquals(new Integer(2), labParty.getSeats());
        assertEquals(new Float(33.45f), labParty.getOverallShare());
        NationalParty others = scoreboard.getNationalParties().get("OTHERS");
        assertEquals(new Long(0), others.getOverallVotes());
        assertEquals(new Integer(0), others.getSeats());
    }
}
