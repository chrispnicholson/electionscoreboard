package uk.co.bbc.electionscoreboard.service;

import org.springframework.stereotype.Service;
import uk.co.bbc.electionscoreboard.dto.ConstituencyResult;
import uk.co.bbc.electionscoreboard.dto.ConstituencyResults;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;

/**
 * Created by Chris on 13-Aug-17.
 */
@Service
public class ElectionResultServiceImpl implements ElectionResultService {

    @Override
    public ConstituencyResult getResult(String xml) throws IOException, JAXBException {

        StringReader sr = new StringReader(xml);
        JAXBContext jaxbContext = JAXBContext.newInstance(ConstituencyResult.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        ConstituencyResult constituencyResult = (ConstituencyResult) unmarshaller.unmarshal(sr);

        return constituencyResult;
    }

    @Override
    public ConstituencyResults getConstituencyResults(String xml) throws IOException, JAXBException {
        StringReader sr = new StringReader(xml);
        JAXBContext jaxbContext = JAXBContext.newInstance(ConstituencyResults.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        ConstituencyResults constituencyResults = (ConstituencyResults) unmarshaller.unmarshal(sr);

        return constituencyResults;
    }
}
