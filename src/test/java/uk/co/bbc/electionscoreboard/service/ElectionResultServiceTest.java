package uk.co.bbc.electionscoreboard.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.bbc.electionscoreboard.dto.ConstituencyResult;
import uk.co.bbc.electionscoreboard.dto.ConstituencyResults;

import javax.xml.bind.JAXBException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Chris on 13-Aug-17.
 */
@RunWith(SpringRunner.class)
public class ElectionResultServiceTest {

    @Autowired
    private ElectionResultService electionResultService;

    private ObjectMapper objectMapper;

    @TestConfiguration
    static class ElectionResultServiceImplTestContextConfiguration {

        @Bean
        public ElectionResultService electionResultService() {
            return new ElectionResultServiceImpl();
        }
    }

    @Before
    public void setUp() {
        objectMapper = new ObjectMapper();
    }
    @Test
    public void testElectionResult() throws IOException, JAXBException {
        String xml = "<constituencyResult seqNo=\"1\"><consituencyId>2</consituencyId><constituencyName>Aberconwy</constituencyName><results><result><partyCode>LAB </partyCode><votes>8994</votes><share>33.00</share></result><result><partyCode>CON </partyCode><votes>7924</votes><share>29.10</share></result><result><partyCode>LD  </partyCode><votes>5197</votes><share>19.10</share></result><result><partyCode>PC  </partyCode><votes>3818</votes><share>14.00</share></result><result><partyCode>OTH</partyCode><votes>517</votes><share>1.90</share></result><result><partyCode>GRN </partyCode><votes>512</votes><share>1.90</share></result><result><partyCode>UKIP</partyCode><votes>296</votes><share>1.10</share></result></results></constituencyResult>";
        ConstituencyResult constituencyResult = electionResultService.getResult(xml);
        assertNotNull(constituencyResult);
        assertEquals(1, constituencyResult.getSeqNo());
        assertEquals(2, constituencyResult.getConstituencyId());
        assertEquals("Aberconwy", constituencyResult.getConstituencyName());
        assertEquals(7, constituencyResult.getResults().getResult().size());
        assertEquals("LAB ", constituencyResult.getResults().getResult().first().getPartyCode());

    }

    @Test
    public void testConstituencyResults() throws IOException, JAXBException {
        String xml = "<constituencyResults><constituencyResult seqNo=\"1\"><consituencyId>2</consituencyId><constituencyName>Aberconwy</constituencyName><results><result><partyCode>LAB </partyCode><votes>8994</votes><share>33.00</share></result><result><partyCode>CON </partyCode><votes>7924</votes><share>29.10</share></result><result><partyCode>LD  </partyCode><votes>5197</votes><share>19.10</share></result><result><partyCode>PC  </partyCode><votes>3818</votes><share>14.00</share></result><result><partyCode>OTH</partyCode><votes>517</votes><share>1.90</share></result><result><partyCode>GRN </partyCode><votes>512</votes><share>1.90</share></result><result><partyCode>UKIP</partyCode><votes>296</votes><share>1.10</share></result></results></constituencyResult></constituencyResults>";
        ConstituencyResults constituencyResults = electionResultService.getConstituencyResults(xml);
        assertNotNull(constituencyResults);

    }
}
