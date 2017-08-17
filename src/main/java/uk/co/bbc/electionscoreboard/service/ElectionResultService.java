package uk.co.bbc.electionscoreboard.service;

import uk.co.bbc.electionscoreboard.dto.ConstituencyResult;
import uk.co.bbc.electionscoreboard.dto.ConstituencyResults;

import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * Created by Chris on 13-Aug-17.
 */
public interface ElectionResultService {

    public ConstituencyResult getResult(String xml) throws IOException, JAXBException;

    public ConstituencyResults getConstituencyResults(String xml) throws IOException, JAXBException;
}
