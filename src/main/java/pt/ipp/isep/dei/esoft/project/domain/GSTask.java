package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Represents a Green Space Task in the system.
 */
public class GSTask implements Serializable {

    private String title;

    private String description;

    private String degreeOfUrgency;

    private int expectedDuration;

    private GreenSpace greenSpace;

    private String status;

    private Calendar startingDate;

    public GSTask(String title, String description, String degreeOfUrgency, int expectedDuration, GreenSpace selectedGreenSpace)
    {
        this.title = title;
        this.description = description;
        this.degreeOfUrgency = degreeOfUrgency;
        this.expectedDuration = expectedDuration;
        this.greenSpace = selectedGreenSpace;
        this.status = "Pending";
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

    public GreenSpace getGreenSpace() {
        return greenSpace;
    }

    public void setProcessed()
    {
        this.status = "Processed";
    }

    public void plan(String startingDate)
    {
        this.status = "Planned";
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

        this.startingDate = cal;
    }

    public boolean isPending()
    {
        return this.status.equalsIgnoreCase("Pending");
    }

    public boolean hasUserEmail(String email)
    {
        return this.greenSpace.hasUserEmail(email);
    }

    @Override
    public GSTask clone()
    {
        return new GSTask(this.title, this.description, this.degreeOfUrgency, this.expectedDuration, this.greenSpace);
    }

}
