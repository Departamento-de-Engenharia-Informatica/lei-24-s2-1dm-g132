package pt.ipp.isep.dei.esoft.project.mapper.dto;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;

public class GSTaskDTO {

    private String title;

    private String description;

    private String degreeOfUrgency;

    private int expectedDuration;

    private GreenSpace greenSpace;

    public GSTaskDTO(String title, String description, String degreeOfUrgency, int expectedDuration, GreenSpace greenSpace) {
        this.title = title;
        this.description = description;
        this.degreeOfUrgency = degreeOfUrgency;
        this.expectedDuration = expectedDuration;
        this.greenSpace = greenSpace;
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
}
