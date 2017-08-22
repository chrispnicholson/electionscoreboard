package uk.co.bbc.electionscoreboard.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.io.Serializable;
import java.util.SortedSet;

/**
 * Created by Chris on 13-Aug-17.
 */
//@XmlAccessorType(XmlAccessType.FIELD)
//@XmlType(name = "", propOrder = {
//        "result"
//})
//@XmlRootElement(name = "results")
@JacksonXmlRootElement(localName = "results")
public class Results implements Serializable {

    @JacksonXmlElementWrapper(useWrapping = false)
    private SortedSet<Result> result;

    public SortedSet<Result> getResult() {
        if (result == null) {
            result = new java.util.TreeSet<Result>();
        }

        return this.result;
    }

    public void setResult(SortedSet<Result> result) {
        this.result = result;
    }
}
