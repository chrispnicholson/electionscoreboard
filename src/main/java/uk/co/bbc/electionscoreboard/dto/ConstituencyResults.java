package uk.co.bbc.electionscoreboard.dto;

import javax.xml.bind.annotation.*;

/**
 * Created by Chris on 13-Aug-17.
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "constituencyResult"
})
@XmlRootElement(name = "constituencyResults")
public class ConstituencyResults {
    @XmlElement(required = true)
    private ConstituencyResult constituencyResult;

    public ConstituencyResult getConstituencyResult() {
        return constituencyResult;
    }

    public void setConstituencyResult(ConstituencyResult constituencyResult) {
        this.constituencyResult = constituencyResult;
    }
}
