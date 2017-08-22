package uk.co.bbc.electionscoreboard.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import uk.co.bbc.electionscoreboard.dto.NationalPoliticalParty;
import uk.co.bbc.electionscoreboard.dto.PoliticalPartyCode;
import uk.co.bbc.electionscoreboard.dto.Scoreboard;
import uk.co.bbc.electionscoreboard.service.ScoreboardService;

import java.util.SortedMap;
import java.util.TreeMap;

import static uk.co.bbc.electionscoreboard.dto.PoliticalPartyCode.*;

/**
 * Created by Chris on 19-Aug-17.
 */

@RunWith(SpringRunner.class)
@WebMvcTest(ScoreboardController.class)
public class ScoreboardControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ScoreboardService scoreboardService;

    private Scoreboard scoreboard;

    @Before
    public void setUp() {
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

        scoreboard = new Scoreboard(nationalPoliticalPartySortedMap);
    }

    @Test
    public void getScoreboard() throws Exception {
        Mockito.when(scoreboardService.getScoreboard()).thenReturn(scoreboard);

        mvc.perform(MockMvcRequestBuilders.get("/scores")
            .contentType(MediaType.APPLICATION_XML))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.xpath("List").exists());

        System.out.println();
    }
}
