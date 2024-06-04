package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.GSTask;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.mapper.dto.GSTaskDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

public class Agenda implements Serializable {

    /**
     * A list of Tasks which consists of all the processed Green Space Tasks.
     */
    private final List<GSTask> entriesList;

    private List<GSTask> freeAgendaEntriesList;

    /**
     * Constructs a new Agenda object.
     */
    public Agenda() {
        entriesList = new ArrayList<>();
    }

    public Optional<GSTask> addEntry(GSTask selectedTaskClone, String startingDate)
    {
        Optional<GSTask> optionalValue = Optional.empty();

        if(addTask(selectedTaskClone, startingDate))
        {
            optionalValue = Optional.of(selectedTaskClone);
        }

        return optionalValue;
    }

    private boolean addTask(GSTask task, String startingDate)
    {
        task.plan(startingDate);
        return entriesList.add(task);
    }

    public List<GSTask> getAgendaEntries(String email)
    {
        freeAgendaEntriesList = new ArrayList<>();
        for(GSTask gSTask : entriesList)
        {
            if(!gSTask.hasTeam() && gSTask.hasUserEmail(email))
            {
                freeAgendaEntriesList.add(gSTask);
            }
        }
        return freeAgendaEntriesList;
    }

    public GSTask getSelectedTask(int i)
    {
        return freeAgendaEntriesList.get(i);
    }

    public boolean checkTeamSchedule(Team selectedTeam, GSTask selectedTask)
    {
        List<GSTask> teamSchedule = new ArrayList<>();
        for(GSTask gSTask : entriesList)
        {
            if(gSTask.belongsToTeam(selectedTeam))
            {
                teamSchedule.add(gSTask);
            }
        }

        /*verification of the team schedule
        for(GSTask gSTask : teamSchedule)
        {
            int numberOfDays = gSTask.getExpectedDuration() / 8;


        }*/

        return true;
    }

    public Optional<GSTask> assignTeam(GSTask selectedTask, Team selectedTeam)
    {
        Optional<GSTask> optionalValue = Optional.empty();

        optionalValue = Optional.of(selectedTask.assignTeam(selectedTeam));

        return optionalValue;
    }
}
