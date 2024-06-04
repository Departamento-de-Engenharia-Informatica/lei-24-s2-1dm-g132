package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Represents a Green Space Task in the system.
 */
public class GSTask implements Serializable {

    private String title;

    private String description;

    private DegreeOfUrgency degreeOfUrgency;

    private int expectedDuration;

    private GreenSpace greenSpace;

    private GSTaskStatus status;

    private Calendar startingDate;

    private Team assignedTeam;

    private enum DegreeOfUrgency {
        Low, Medium, High
    }

    private enum GSTaskStatus {
        Pending, Processed, Planned, Postponed, Canceled, Done
    }

    public GSTask(String title, String description, String degreeOfUrgency, int expectedDuration, GreenSpace selectedGreenSpace)
    {
        setTitle(title);
        this.description = description;
        setDegreeOfUrgency(degreeOfUrgency);
        setExpectedDuration(expectedDuration);
        this.greenSpace = selectedGreenSpace;
        this.status = GSTaskStatus.Pending;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDegreeOfUrgency() {
        return degreeOfUrgency.toString();
    }

    public int getExpectedDuration() {
        return expectedDuration;
    }

    public GreenSpace getGreenSpace() {
        return greenSpace;
    }

    public Calendar getStartingDate() {
        return startingDate;
    }

    private void setTitle(String title)
    {
        if (title.matches("[a-zA-Z ]*" )) {
            this.title = title.trim();
        } else {
            throw new IllegalArgumentException("Title cannot contain special characters.");
        }
    }

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

    private void setExpectedDuration(int expectedDuration)
    {
        if (expectedDuration > 0) {
            this.expectedDuration = expectedDuration;
        } else {
            throw new IllegalArgumentException("Expected duration must be a positive integer.");
        }
    }

    public void setProcessed()
    {
        this.status = GSTaskStatus.Processed;
    }

    public void plan(String startingDate)
    {
        this.status = GSTaskStatus.Planned;
        setStartingDate(startingDate);
    }

    private void setStartingDate(String startingDate) {
        String[] parts = startingDate.trim().split("/");

        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid birthdate format. Use year/month/day.");
        }

        for (String part : parts) {
            if (!part.matches("\\d+")) {
                throw new IllegalArgumentException("Non-numeric value found in birthdate.");
            }
        }

        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);

        Calendar currentDate = Calendar.getInstance();

        if(cal.before(currentDate))
        {
            throw new IllegalArgumentException("Task starting date must be after the current date.");
        }

        this.startingDate = cal;
    }

    public boolean isPending()
    {
        return this.status.equals(GSTaskStatus.Pending);
    }

    public boolean hasUserEmail(String email)
    {
        return this.greenSpace.hasUserEmail(email);
    }

    public boolean hasTeam()
    {
        return this.assignedTeam != null;
    }

    public boolean belongsToTeam(Team selectedTeam)
    {
        return this.assignedTeam.equals(selectedTeam);
    }

    public GSTask assignTeam(Team selectedTeam) {
        this.assignedTeam = selectedTeam;
        return this;
    }

    @Override
    public GSTask clone()
    {
        return new GSTask(this.title, this.description, this.degreeOfUrgency.toString(), this.expectedDuration, this.greenSpace);
    }

}
