package uk.co.bbc.electionscoreboard.service;

import org.junit.Before;
import org.junit.Test;
import uk.co.bbc.electionscoreboard.dto.*;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static uk.co.bbc.electionscoreboard.dto.PoliticalPartyCode.*;
import static uk.co.bbc.electionscoreboard.dto.PoliticalPartyCode.LAB;
import static uk.co.bbc.electionscoreboard.dto.PoliticalPartyCode.OTH;

/**
 * Created by Chris on 13-Aug-17.
 */
public class ScoreboardServiceTest {

    private ScoreboardService scoreboardService = new ScoreboardServiceImpl();

    private ConstituencyResults constituencyResultsOne;
    private ConstituencyResults constituencyResultsTwo;
    private ConstituencyResults constituencyResultsThree;

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

        constituencyResultsThree = new ConstituencyResults();
        ConstituencyResult constituencyResultThree = new ConstituencyResult();
        Results resultsThree = new Results();
        SortedSet<Result> resultSetThree = new TreeSet<Result>();

        labResult = new Result();
        labResult.setPartyCode("LAB");
        labResult.setVotes(17195l);
        labResult.setShare(40.70f);
        resultSetThree.add(labResult);

        conResult = new Result();
        conResult.setPartyCode("CON");
        conResult.setVotes(16290l);
        conResult.setShare(38.50f);
        resultSetThree.add(conResult);

        ldResult = new Result();
        ldResult.setPartyCode("LD");
        ldResult.setVotes(4469l);
        ldResult.setShare(10.60f);
        resultSetThree.add(ldResult);

        othResult = new Result();
        othResult.setPartyCode("OTH");
        othResult.setVotes(2565l);
        othResult.setShare(6.10f);
        resultSetThree.add(othResult);

        ukipResult = new Result();
        ukipResult.setPartyCode("UKIP");
        ukipResult.setVotes(1109l);
        ukipResult.setShare(2.60f);
        resultSetThree.add(ukipResult);

        grnResult = new Result();
        grnResult.setPartyCode("GRN");
        grnResult.setVotes(662l);
        grnResult.setShare(1.60f);
        resultSetThree.add(grnResult);

        resultsThree.setResult(resultSetThree);
        constituencyResultThree.setResults(resultsThree);
        constituencyResultThree.setSeqNo(3);
        constituencyResultThree.setConstituencyId(33);
        constituencyResultThree.setConstituencyName("Basildon South &amp; East Thurrock");
        constituencyResultsThree.setConstituencyResult(constituencyResultThree);
    }

    @Test
    public void getScoreboardForSinglePartyState() {

        scoreboardService.addConstituencyResults(constituencyResultsOne);
        Scoreboard scoreboard = scoreboardService.getScoreboard();

        assertEquals(values().length, scoreboard.getNationalParties().size());
        assertNotNull( scoreboard.getNationalParties().get(LAB));
        assertNotNull( scoreboard.getNationalParties().get(OTH));
        assertEquals(LAB, scoreboard.getNationalParties().firstKey());
        assertEquals(OTH, scoreboard.getNationalParties().lastKey());
        NationalPoliticalParty labParty = scoreboard.getNationalParties().get(LAB);
        assertEquals(new Long(8994), labParty.getOverallVotes());
        assertEquals(new Integer(1), labParty.getSeats());
        assertEquals(new Float(33.0f), labParty.getOverallShare());
        NationalPoliticalParty others = scoreboard.getNationalParties().get(OTH);
        assertEquals(new Long(517), others.getOverallVotes());
        assertEquals(new Integer(0), others.getSeats());
        assertEquals(new Float(1.9f), others.getOverallShare());
    }

    @Test
    public void getScoreboardForTwoConstituencies() {

        scoreboardService.addConstituencyResults(constituencyResultsOne);
        scoreboardService.addConstituencyResults(constituencyResultsTwo);
        Scoreboard scoreboard = scoreboardService.getScoreboard();

        assertEquals(values().length, scoreboard.getNationalParties().size());
        assertNotNull( scoreboard.getNationalParties().get(LAB));
        assertNotNull( scoreboard.getNationalParties().get(OTH));

        assertEquals(LAB, scoreboard.getNationalParties().firstKey());
        assertEquals(OTH, scoreboard.getNationalParties().lastKey());

        NationalPoliticalParty labParty = scoreboard.getNationalParties().get(LAB);
        assertEquals(new Long(17478), labParty.getOverallVotes());
        assertEquals(new Integer(2), labParty.getSeats());
        assertEquals(new Float(33.45f), labParty.getOverallShare());

        NationalPoliticalParty others = scoreboard.getNationalParties().get(OTH);
        assertEquals(new Long(517), others.getOverallVotes());
        assertEquals(new Integer(0), others.getSeats());
        assertEquals(new Float(0.95f), others.getOverallShare());
    }

    @Test
    public void getScoreboardForThreeConstituencies() {

        scoreboardService.addConstituencyResults(constituencyResultsOne);
        scoreboardService.addConstituencyResults(constituencyResultsTwo);
        scoreboardService.addConstituencyResults(constituencyResultsThree);
        Scoreboard scoreboard = scoreboardService.getScoreboard();

        assertEquals(values().length, scoreboard.getNationalParties().size());
        assertNotNull( scoreboard.getNationalParties().get(LAB));
        assertNotNull( scoreboard.getNationalParties().get(OTH));

        assertEquals(LAB, scoreboard.getNationalParties().firstKey());
        assertEquals(OTH, scoreboard.getNationalParties().lastKey());

        NationalPoliticalParty labParty = scoreboard.getNationalParties().get(LAB);
        //assertEquals(new Long(17478), labParty.getOverallVotes());
        //assertEquals(new Integer(2), labParty.getSeats());
        assertEquals(new Float(35.86667f), labParty.getOverallShare());

        NationalPoliticalParty others = scoreboard.getNationalParties().get(OTH);
        //assertEquals(new Long(517), others.getOverallVotes());
        //assertEquals(new Integer(0), others.getSeats());
        //assertEquals(new Float(0.95f), others.getOverallShare());
    }

    @Test
    public void getScoreboardWithTruncatedParties() {
        NationalPoliticalParty con = new NationalPoliticalParty();
        NationalPoliticalParty lab = new NationalPoliticalParty();
        NationalPoliticalParty snp = new NationalPoliticalParty();
        NationalPoliticalParty ld = new NationalPoliticalParty();
        NationalPoliticalParty dup = new NationalPoliticalParty();
        NationalPoliticalParty sf = new NationalPoliticalParty();
        NationalPoliticalParty pd = new NationalPoliticalParty();
        NationalPoliticalParty sdlp = new NationalPoliticalParty();
        NationalPoliticalParty uu = new NationalPoliticalParty();
        NationalPoliticalParty grn = new NationalPoliticalParty();
        NationalPoliticalParty ukip = new NationalPoliticalParty();
        NationalPoliticalParty others = new NationalPoliticalParty();

        con.setPartyCode(PoliticalPartyCode.CON);
        con.setSeats(331);
        con.setOverallShare(36.9f);
        lab.setPartyCode(PoliticalPartyCode.LAB);
        lab.setSeats(232);
        lab.setOverallShare(30.4f);
        snp.setPartyCode(PoliticalPartyCode.SNP);
        snp.setSeats(56);
        snp.setOverallShare(4.7f);
        ld.setPartyCode(PoliticalPartyCode.LD);
        ld.setSeats(8);
        ld.setOverallShare(7.9f);
        dup.setPartyCode(PoliticalPartyCode.DUP);
        dup.setSeats(8);
        dup.setOverallShare(0.6f);
        sf.setPartyCode(PoliticalPartyCode.SF);
        sf.setSeats(4);
        sf.setOverallShare(0.6f);
        pd.setPartyCode(PD);
        pd.setSeats(3);
        pd.setOverallShare(0.6f);
        sdlp.setPartyCode(SDLP);
        sdlp.setSeats(3);
        sdlp.setOverallShare(0.3f);
        uu.setPartyCode(UU);
        uu.setSeats(2);
        uu.setOverallShare(0.4f);
        ukip.setPartyCode(UKIP);
        ukip.setSeats(1);
        ukip.setOverallShare(12.6f);
        grn.setPartyCode(GRN);
        grn.setSeats(1);
        grn.setOverallShare(3.8f);
        others.setPartyCode(OTH);
        others.setSeats(1);
        others.setOverallShare(0.5f);

        SortedMap<PoliticalPartyCode, NationalPoliticalParty> nationalPoliticalPartySortedMap = new TreeMap<PoliticalPartyCode, NationalPoliticalParty>();
        nationalPoliticalPartySortedMap.put(CON, con);
        nationalPoliticalPartySortedMap.put(LAB, lab);
        nationalPoliticalPartySortedMap.put(SNP, snp);
        nationalPoliticalPartySortedMap.put(LD, ld);
        nationalPoliticalPartySortedMap.put(DUP, dup);
        nationalPoliticalPartySortedMap.put(SF, sf);
        nationalPoliticalPartySortedMap.put(PD, pd);
        nationalPoliticalPartySortedMap.put(SDLP, sdlp);
        nationalPoliticalPartySortedMap.put(UU, uu);
        nationalPoliticalPartySortedMap.put(UKIP, ukip);
        nationalPoliticalPartySortedMap.put(GRN, grn);
        nationalPoliticalPartySortedMap.put(OTH, others);

        Scoreboard scoreboard = new Scoreboard(nationalPoliticalPartySortedMap);
        List<Display> truncatedScores = scoreboard.getTruncatedNationalParties();

        assertEquals(4, truncatedScores.size());
        assertEquals(CON.name(), truncatedScores.get(0).getPartyCode());
        assertEquals(LAB.name(), truncatedScores.get(1).getPartyCode());
        assertEquals(SNP.name(), truncatedScores.get(2).getPartyCode());
        assertEquals(OTH.name(), truncatedScores.get(3).getPartyCode());

        assertEquals(331, truncatedScores.get(0).getSeats());
        assertEquals("36.90", truncatedScores.get(0).getVoteShare());
        assertEquals(232, truncatedScores.get(1).getSeats());
        assertEquals("30.40", truncatedScores.get(1).getVoteShare());
        assertEquals(56, truncatedScores.get(2).getSeats());
        assertEquals("4.70", truncatedScores.get(2).getVoteShare());
        assertEquals(31, truncatedScores.get(3).getSeats());
        assertEquals("27.30", truncatedScores.get(3).getVoteShare());


    }
}
