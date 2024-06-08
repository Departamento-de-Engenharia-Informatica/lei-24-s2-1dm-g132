package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Calendar;

/**
 * Interface for generating and sending email notifications.
 */
public interface EmailGenerator {

    /**
     * Sends an email notification with the specified title, starting date, and team.
     *
     * @param title The title of the email.
     * @param startingDate The starting date of the task or event.
     * @param selectedTeam The team to which the email will be sent.
     * @return True if the email was successfully sent, false otherwise.
     */
    public abstract boolean sendEmail(String title, Calendar startingDate, Team selectedTeam);
}
