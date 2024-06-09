package pt.ipp.isep.dei.esoft.project.domain;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Properties;

/**
 * Represents a Green Space Task (GSTask) in the system.
 * This class manages the details and lifecycle of a task assigned to a Green Space.
 */
public class GSTask implements Serializable {

    /**
     * The title of the task.
     */
    private String title;

    /**
     * The description of the task.
     */
    private String description;

    /**
     * The degree of urgency of the task (Low, Medium, High).
     */
    private DegreeOfUrgency degreeOfUrgency;

    /**
     * The expected duration of the task in working hours.
     */
    private int expectedDuration;

    /**
     * The GreenSpace to which the task is assigned.
     */
    private GreenSpace greenSpace;

    /**
     * The status of the GSTask (Pending, Processed, Planned, Postponed, Canceled, Done).
     */
    private GSTaskStatus status;

    /**
     * The starting date of the task.
     */
    private Calendar startingDate;

    /**
     * The team assigned to the task.
     */
    private Team assignedTeam;

    /**
     * Enum for the degree of urgency of a task.
     */
    private enum DegreeOfUrgency {
        Low, Medium, High
    }

    /**
     * Enum for the status of a GSTask.
     */
    private enum GSTaskStatus {
        Pending, Processed, Planned, Postponed, Canceled, Done
    }

    /**
     * Constructs a new GSTask with the provided parameters.
     *
     * @param title The title of the task.
     * @param description The description of the task.
     * @param degreeOfUrgency The degree of urgency of the task (Low, Medium, High).
     * @param expectedDuration The expected duration of the task in hours.
     * @param selectedGreenSpace The GreenSpace to which the task is assigned.
     */
    public GSTask(String title, String description, String degreeOfUrgency, int expectedDuration, GreenSpace selectedGreenSpace)
    {
        setTitle(title);
        this.description = description;
        setDegreeOfUrgency(degreeOfUrgency);
        setExpectedDuration(expectedDuration);
        this.greenSpace = selectedGreenSpace;
        this.status = GSTaskStatus.Pending;
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
     * Retrieves the description of the task.
     *
     * @return The description of the task.
     */
    public String getDegreeOfUrgency() {
        return degreeOfUrgency.toString();
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
     * Retrieves the GreenSpace to which the task is assigned.
     *
     * @return The GreenSpace object.
     */
    public GreenSpace getGreenSpace() {
        return greenSpace;
    }

    /**
     * Retrieves the starting date of the task.
     *
     * @return The starting date of the task.
     */
    public Calendar getStartingDate() {
        return startingDate;
    }

    /**
     * Retrieves the status of the task.
     *
     * @return The status of the task.
     */
    public String getStatus() {
        return status.toString();
    }

    /**
     * Sets the title of the task.
     *
     * @param title The title of the task.
     * @throws IllegalArgumentException If the title is blank or contains special characters.
     */
    private void setTitle(String title)
    {
        if (title.isBlank())
        {
            throw new IllegalArgumentException("Title is empty. Please enter a task title.");
        }

        if (title.matches("[a-zA-Z ]*" )) {
            this.title = title.trim();
        } else {
            throw new IllegalArgumentException("Title cannot contain special characters.");
        }
    }

    /**
     * Sets the degree of urgency of the task.
     *
     * @param degreeOfUrgency The degree of urgency of the task (Low, Medium, High).
     * @throws IllegalArgumentException If the degree of urgency is invalid.
     */
    private void setDegreeOfUrgency(String degreeOfUrgency)
    {
        if(degreeOfUrgency.equalsIgnoreCase(DegreeOfUrgency.Low.toString()))
            this.degreeOfUrgency = DegreeOfUrgency.Low;
        else if(degreeOfUrgency.equalsIgnoreCase(DegreeOfUrgency.Medium.toString()))
            this.degreeOfUrgency = DegreeOfUrgency.Medium;
        else if(degreeOfUrgency.equalsIgnoreCase(DegreeOfUrgency.High.toString()))
            this.degreeOfUrgency = DegreeOfUrgency.High;
        else throw new IllegalArgumentException("Invalid degree of urgency.");
    }

    /**
     * Sets the expected duration of the task.
     *
     * @param expectedDuration The expected duration of the task in hours.
     * @throws IllegalArgumentException If the expected duration is not a positive integer.
     */
    private void setExpectedDuration(int expectedDuration)
    {
        if (expectedDuration > 0) {
            this.expectedDuration = expectedDuration;
        } else {
            throw new IllegalArgumentException("Expected duration must be a positive integer.");
        }
    }

    /**
     * Sets the status of the task to "Processed".
     */
    public void setProcessed()
    {
        this.status = GSTaskStatus.Processed;
    }

    /**
     * Plans the task with the specified starting date.
     *
     * @param startingDate The starting date of the task in the format "year/month/day".
     * @throws IllegalArgumentException If the starting date format is invalid or the date is in the past.
     */
    public void plan(String startingDate)
    {
        this.status = GSTaskStatus.Planned;
        setStartingDate(startingDate);
    }

    /**
     * Sets the starting date of the task.
     *
     * @param startingDate The starting date of the task in the format "year/month/day".
     * @throws IllegalArgumentException If the starting date format is invalid, contains non-numeric characters,
     *                                  or the date is in the past.
     */
    private void setStartingDate(String startingDate) {
        String[] parts = startingDate.trim().split("/");

        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid starting date format. Use year/month/day.");
        }

        for (String part : parts) {
            if (!part.matches("\\d+")) {
                throw new IllegalArgumentException("Non-numeric value found in starting date.");
            }
        }

        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        Calendar currentDate = Calendar.getInstance();

        if(cal.before(currentDate))
        {
            throw new IllegalArgumentException("Task starting date must be after the current date.");
        }

        this.startingDate = cal;
    }

    /**
     * Checks if the task is pending.
     *
     * @return True if the task is pending, false otherwise.
     */
    public boolean isPending()
    {
        return this.status.equals(GSTaskStatus.Pending);
    }

    /**
     * Checks if the GreenSpace associated with the task has the specified user email.
     *
     * @param email The email address to check.
     * @return True if the GreenSpace has the specified email, false otherwise.
     */
    public boolean hasUserEmail(String email)
    {
        return this.greenSpace.hasUserEmail(email);
    }

    /**
     * Checks if the task is assigned to a team.
     *
     * @return True if the task is assigned to a team, false otherwise.
     */
    public boolean hasTeam()
    {
        return this.assignedTeam != null;
    }

    /**
     * Checks if the task belongs to the specified team.
     *
     * @param selectedTeam The team to check against.
     * @return True if the task belongs to the specified team, false otherwise.
     */
    public boolean belongsToTeam(Team selectedTeam)
    {
        if(this.assignedTeam == null)
            return false;
        else
            return this.assignedTeam.equals(selectedTeam);
    }

    /**
     * Assigns a team to the task and sends an email notification to the team members.
     *
     * @param selectedTeam The team to assign to the task.
     * @return The updated GSTask object.
     */
    public GSTask assignTeam(Team selectedTeam) {
        this.assignedTeam = selectedTeam;

        InputStream inputStream;

        try{
            Properties props = new Properties();

            inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
            if (inputStream != null) {
                props.load(inputStream);
            } else {
                throw new FileNotFoundException("property file not found in the classpath");
            }

            String className = props.getProperty("EmailGenerator.Class");

            Class<?> oClass = Class.forName(className);

            EmailGenerator eg = (EmailGenerator) oClass.newInstance();

            if(!eg.sendEmail(this.title, this.startingDate, this.assignedTeam))
            {
                System.out.println("Error sending email to collaborators.");
            }
        }
        catch (Exception e) {
            System.out.println("Exception: " + e);
        }

        return this;
    }

    /**
     * Creates a clone of the current GSTask object.
     *
     * @return A new GSTask object with the same attributes as the original.
     */
    @Override
    public GSTask clone()
    {
        return new GSTask(this.title, this.description, this.degreeOfUrgency.toString(), this.expectedDuration, this.greenSpace);
    }

    public Team getAssignedTeam() {
        return assignedTeam;
    }
}
