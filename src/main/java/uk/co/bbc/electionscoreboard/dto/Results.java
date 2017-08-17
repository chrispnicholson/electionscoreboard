package uk.co.bbc.electionscoreboard.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.SortedSet;

/**
 * Created by Chris on 13-Aug-17.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "result"
})
@XmlRootElement(name = "results")
public class Results {

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
