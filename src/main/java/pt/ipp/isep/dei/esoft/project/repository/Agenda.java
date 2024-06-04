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

    public Optional<GSTask> assignTeam(GSTask selectedTask, Team selectedTeam)
    {
        Optional<GSTask> optionalValue = Optional.empty();

        optionalValue = Optional.of(selectedTask.assignTeam(selectedTeam));

        return optionalValue;
    }
}
