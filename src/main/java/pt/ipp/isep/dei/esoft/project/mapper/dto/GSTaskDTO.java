package pt.ipp.isep.dei.esoft.project.mapper.dto;

public class GSTaskDTO {

    private String title;

    private String description;

    private String degreeOfUrgency;

    private int expectedDuration;

    public GSTaskDTO(String title, String description, String degreeOfUrgency, int expectedDuration) {
        this.title = title;
        this.description = description;
        this.degreeOfUrgency = degreeOfUrgency;
        this.expectedDuration = expectedDuration;
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
}
