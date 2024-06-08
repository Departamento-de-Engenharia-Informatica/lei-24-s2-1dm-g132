package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.GSTask;
import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

/**
 * Represents an agenda that manages a list of Green Space Tasks (GSTasks).
 * This class provides methods for adding, retrieving, and scheduling tasks.
 */
public class Agenda implements Serializable {

    /**
     * A list of Tasks which consists of all the processed Green Space Tasks.
     */
    private final List<GSTask> entriesList;

    /**
     * A list of tasks that are available for scheduling.
     */
    private List<GSTask> freeAgendaEntriesList;

    /**
     * Constructs a new Agenda object.
     */
    public Agenda() {
        entriesList = new ArrayList<>();
    }

    /**
     * Adds a task entry to the agenda with a specified starting date.
     *
     * @param selectedTaskClone the task to be added
     * @param startingDate the starting date of the task
     * @return an Optional containing the added task if successful, otherwise an empty Optional
     */
    public Optional<GSTask> addEntry(GSTask selectedTaskClone, String startingDate)
    {
        Optional<GSTask> optionalValue = Optional.empty();

        if(addTask(selectedTaskClone, startingDate))
        {
            optionalValue = Optional.of(selectedTaskClone);
        }

        return optionalValue;
    }

    /**
     * Adds a task to the agenda with a specified starting date.
     *
     * @param task the task to be added
     * @param startingDate the starting date of the task
     * @return true if the task was successfully added, otherwise false
     */
    private boolean addTask(GSTask task, String startingDate)
    {
        task.plan(startingDate);
        return entriesList.add(task);
    }

    /**
     * Retrieves a list of agenda entries for a specified user email.
     *
     * @param email the email of the user
     * @return a list of tasks that are available for the specified user
     */
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

    /**
     * Retrieves a selected task from the free agenda entries list by its index.
     *
     * @param i the index of the task
     * @return the selected task
     */
    public GSTask getSelectedTask(int i)
    {
        return freeAgendaEntriesList.get(i);
    }

    /**
     * Checks the schedule of a team to determine if a specified task can be scheduled.
     *
     * @param selectedTeam the team to be checked
     * @param selectedTask the task to be scheduled
     * @return true if the task can be scheduled for the team, otherwise false
     */
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
        //Simplificação: se for uma task de mais de um dia, só pode começar num dia livre

        int workDayHours = 8;
        int availableDayHours = 8;
        int minDays = selectedTask.getExpectedDuration() / workDayHours;
        int totalOfRequestedHours = selectedTask.getExpectedDuration();

        for(GSTask gSTask : teamSchedule)
        {
            if(minDays == 0)                                                                                                    //Quando a task selecionada pode ser feita dentro de um dia
            {
                if(gSTask.getStartingDate().equals(selectedTask.getStartingDate()))                                             //Tirar o tempo das tasks que também começam no mesmo dia
                {
                    availableDayHours -= gSTask.getExpectedDuration();
                }
                else if(gSTask.getStartingDate().before(selectedTask.getStartingDate()) && gSTask.getExpectedDuration() > 8)    //Caso a task inicie antes mas tenha mais que um dia
                {
                    Calendar endingDate = (Calendar) gSTask.getStartingDate().clone();
                    endingDate.add(Calendar.DAY_OF_MONTH, gSTask.getExpectedDuration() / workDayHours);
                    if(endingDate.equals(selectedTask.getStartingDate()))                                                       //Task termina no mesmo dia que a selecionada começa
                    {
                        availableDayHours -= gSTask.getExpectedDuration() % workDayHours;
                    }
                    else if (endingDate.after(selectedTask.getStartingDate()))                                                  //Task termina só depois da selecionada começar (não pode)
                        return false;
                }
            }
            else                                                                                                                //Quando a task selecionada necessita de mais de um dia
            {
                if(gSTask.getStartingDate().equals(selectedTask.getStartingDate()))                                             //Simplificação da task só poder começar num dia sem tasks anteriormente
                {
                    return false;
                }
                else if(gSTask.getStartingDate().before(selectedTask.getStartingDate()) && gSTask.getExpectedDuration() > workDayHours)     //Quando a task começa antes mas tem mais que um dia
                {
                    Calendar endingDate = (Calendar) gSTask.getStartingDate().clone();
                    endingDate.add(Calendar.DAY_OF_MONTH, gSTask.getExpectedDuration() / workDayHours);
                    if(endingDate.equals(selectedTask.getStartingDate()) || endingDate.after(selectedTask.getStartingDate()))               //O dia não pode já ter parte de outra task ou a outra task não pode terminar depois do começo da selecionada
                    {
                        return false;
                    }
                }
                else if(gSTask.getStartingDate().after(selectedTask.getStartingDate()))                                                     //Caso a task seja depois da selecionada
                {
                    Calendar endingDateSelectedTask = (Calendar) selectedTask.getStartingDate().clone();
                    endingDateSelectedTask.add(Calendar.DAY_OF_MONTH, selectedTask.getExpectedDuration() / workDayHours);
                    if(endingDateSelectedTask.after(gSTask.getStartingDate()))                                                              //Se a task selecionada só terminar depois do começo desta
                    {
                        return false;
                    }
                    else if(endingDateSelectedTask.equals(gSTask.getStartingDate()))                                                        //Se a selecionada terminar no mesmo dia que esta começa
                    {
                        if(gSTask.getExpectedDuration() >= workDayHours)                                                                    //Se esta for maior que um dia de trabalho
                            return false;
                        else                                                                                                                //Se der para terminar a selecionada dias e terminar esta
                            availableDayHours -= gSTask.getExpectedDuration() % 8;
                    }
                }
            }
        }

        if(availableDayHours < totalOfRequestedHours % 8)
            return false;
        else
            return true;
    }

    /**
     * Assigns a team to a specified task.
     *
     * @param selectedTask the task to be assigned
     * @param selectedTeam the team to be assigned
     * @return an Optional containing the assigned task if successful, otherwise an empty Optional
     */
    public Optional<GSTask> assignTeam(GSTask selectedTask, Team selectedTeam)
    {
        Optional<GSTask> optionalValue = Optional.empty();

        optionalValue = Optional.of(selectedTask.assignTeam(selectedTeam));

        return optionalValue;
    }
}
