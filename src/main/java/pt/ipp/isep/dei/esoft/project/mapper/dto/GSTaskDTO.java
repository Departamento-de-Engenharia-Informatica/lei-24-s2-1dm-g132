package pt.ipp.isep.dei.esoft.project.mapper.dto;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;

import java.util.Calendar;

public class GSTaskDTO {

    private String title;

    private String description;

    private String degreeOfUrgency;

    private int expectedDuration;

    private GreenSpace greenSpace;

    private Calendar startingDate;

    public GSTaskDTO(String title, String description, String degreeOfUrgency, int expectedDuration) {
        this.title = title;
        this.description = description;
        this.degreeOfUrgency = degreeOfUrgency;
        this.expectedDuration = expectedDuration;
    }

    public GSTaskDTO(String title, String description, String degreeOfUrgency, int expectedDuration, GreenSpace greenSpace) {
        this.title = title;
        this.description = description;
        this.degreeOfUrgency = degreeOfUrgency;
        this.expectedDuration = expectedDuration;
        this.greenSpace = greenSpace;
    }

    public GSTaskDTO(String title, String description, String degreeOfUrgency, int expectedDuration, GreenSpace greenSpace, Calendar startingDate) {
        this.title = title;
        this.description = description;
        this.degreeOfUrgency = degreeOfUrgency;
        this.expectedDuration = expectedDuration;
        this.greenSpace = greenSpace;
        this.startingDate = startingDate;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDegreeOfUrgency() {
        return degreeOfUrgency;
    }

    public int getExpectedDuration() {
        return expectedDuration;
    }

    public GreenSpace getGreenSpace(){ return greenSpace; }

    public Calendar getStartingDate() {
        return startingDate;
    }
}
