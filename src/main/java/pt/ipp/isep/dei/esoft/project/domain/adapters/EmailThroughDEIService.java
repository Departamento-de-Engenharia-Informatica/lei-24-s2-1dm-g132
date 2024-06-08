package pt.ipp.isep.dei.esoft.project.domain.adapters;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.EmailGenerator;
import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Formatter;

/**
 * Adapter class for sending emails through DEI service.
 * Implements the EmailGenerator interface to send email notifications.
 */
public class EmailThroughDEIService implements EmailGenerator {

    /**
     * Constructs a new EmailThroughDEIService object.
     */
    public EmailThroughDEIService() {
    }

    /**
     * Sends an email notification with the specified title, starting date, and team.
     * The email is formatted and saved to a file named "EmailMessage.txt".
     *
     * @param title The title of the task.
     * @param startingDate The starting date of the task.
     * @param selectedTeam The team assigned to the task.
     * @return True if the email was successfully saved to the file, false otherwise.
     */
    @Override
    public boolean sendEmail(String title, Calendar startingDate, Team selectedTeam) {
        String printEmail = "From: MusgoSublime@dei.isep.ipp.pt";
        printEmail = String.format("%s\nTo:", printEmail);
        for(Collaborator collaborator : selectedTeam.getCollaborators())
        {
            printEmail = String.format("%s %s;", printEmail, collaborator.getEmail());
        }
        printEmail = String.format("%s\n\nYou have been assigned with the task %s, which is planned to start at %d/%d/%d.\n\nBest regards,\nMusgoSublime", printEmail, title,
                startingDate.get(Calendar.YEAR), startingDate.get(Calendar.MONTH)+1, startingDate.get(Calendar.DAY_OF_MONTH));

        Formatter ficheiro;
        try {
            ficheiro = new Formatter(new File("EmailMessage.txt"));
            try {
                ficheiro.format("%s", printEmail);
            } finally {
                ficheiro.close();
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
