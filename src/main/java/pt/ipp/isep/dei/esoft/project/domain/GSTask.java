package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;

/**
 * Represents a Green Space Task in the system.
 */
public class GSTask implements Serializable {

    private String title;

    private String description;

    private String degreeOfUrgency;

    private int expectedDuration;

    private GreenSpace greenSpace;

    public GSTask(String title, String description, String degreeOfUrgency, int expectedDuration, GreenSpace selectedGreenSpace)
    {
        this.title = title;
        this.description = description;
        this.degreeOfUrgency = degreeOfUrgency;
        this.expectedDuration = expectedDuration;
        this.greenSpace = selectedGreenSpace;
    }



}
