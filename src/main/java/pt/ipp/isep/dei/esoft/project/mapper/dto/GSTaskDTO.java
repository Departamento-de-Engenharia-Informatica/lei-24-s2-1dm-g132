package pt.ipp.isep.dei.esoft.project.mapper.dto;

import java.util.Calendar;

/**
 * Data Transfer Object (DTO) for representing Green Space tasks.
 * This class holds information about a task, including its title, description, degree of urgency,
 * expected duration, associated Green Space DTO, and starting date.
 */
public class GSTaskDTO {

    /**
     * The title of the task.
     */
    private String title;

    /**
     * The description of the task.
     */
    private String description;

    /**
     * The degree of urgency of the task.
     */
    private String degreeOfUrgency;

    /**
     * The expected duration of the task in hours.
     */
    private int expectedDuration;

    /**
     * The DTO representing the associated Green Space.
     */
    private GreenSpaceDTO greenSpace;

    /**
     * The starting date of the task.
     */
    private Calendar startingDate;

    /**
     * Constructs a GSTaskDTO object with the specified parameters.
     *
     * @param title The title of the task.
     * @param description The description of the task.
     * @param degreeOfUrgency The degree of urgency of the task.
     * @param expectedDuration The expected duration of the task in hours.
     */
    public GSTaskDTO(String title, String description, String degreeOfUrgency, int expectedDuration) {
        this.title = title;
        this.description = description;
        this.degreeOfUrgency = degreeOfUrgency;
        this.expectedDuration = expectedDuration;
    }

    /**
     * Constructs a GSTaskDTO object with the specified parameters.
     *
     * @param title The title of the task.
     * @param description The description of the task.
     * @param degreeOfUrgency The degree of urgency of the task.
     * @param expectedDuration The expected duration of the task in hours.
     * @param greenSpace The DTO representing the associated Green Space.
     */
    public GSTaskDTO(String title, String description, String degreeOfUrgency, int expectedDuration, GreenSpaceDTO greenSpace) {
        this.title = title;
        this.description = description;
        this.degreeOfUrgency = degreeOfUrgency;
        this.expectedDuration = expectedDuration;
        this.greenSpace = greenSpace;
    }

    /**
     * Constructs a GSTaskDTO object with the specified parameters.
     *
     * @param title The title of the task.
     * @param description The description of the task.
     * @param degreeOfUrgency The degree of urgency of the task.
     * @param expectedDuration The expected duration of the task in hours.
     * @param greenSpace The DTO representing the associated Green Space.
     * @param startingDate The starting date of the task.
     */
    public GSTaskDTO(String title, String description, String degreeOfUrgency, int expectedDuration, GreenSpaceDTO greenSpace, Calendar startingDate) {
        this.title = title;
        this.description = description;
        this.degreeOfUrgency = degreeOfUrgency;
        this.expectedDuration = expectedDuration;
        this.greenSpace = greenSpace;
        this.startingDate = startingDate;
    }

    /**
     * Retrieves the title of the task.
     *
     * @return The title of the task.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Retrieves the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Retrieves the degree of urgency of the task.
     *
     * @return The degree of urgency of the task.
     */
    public String getDegreeOfUrgency() {
        return degreeOfUrgency;
    }

    /**
     * Retrieves the expected duration of the task.
     *
     * @return The expected duration of the task in hours.
     */
    public int getExpectedDuration() {
        return expectedDuration;
    }

    /**
     * Retrieves the DTO representing the associated Green Space.
     *
     * @return The GreenSpaceDTO object representing the associated Green Space.
     */
    public GreenSpaceDTO getGreenSpace(){ return greenSpace; }

    /**
     * Retrieves the starting date of the task.
     *
     * @return The starting date of the task.
     */
    public Calendar getStartingDate() {
        return startingDate;
    }
}
