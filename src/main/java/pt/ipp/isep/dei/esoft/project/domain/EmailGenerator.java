package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Calendar;

public interface EmailGenerator {
    public abstract boolean sendEmail(String title, Calendar startingDate, Team selectedTeam);
}
