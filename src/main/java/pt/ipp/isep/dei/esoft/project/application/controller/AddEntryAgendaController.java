package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.domain.GSTask;
import pt.ipp.isep.dei.esoft.project.mapper.GSTaskMapper;
import pt.ipp.isep.dei.esoft.project.mapper.dto.GSTaskDTO;
import pt.ipp.isep.dei.esoft.project.repository.Agenda;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.ToDoList;
import pt.ipp.isep.dei.esoft.project.repository.serialization.AgendaFile;
import pt.ipp.isep.dei.esoft.project.repository.serialization.ToDoListFile;

import java.util.List;
import java.util.Optional;

public class AddEntryAgendaController {

    private ToDoList toDoList;

    private ApplicationSession applicationSession;

    private Agenda agenda;

    private GSTask selectedTaskClone;

    private ToDoListFile toDoListFile;

    private AgendaFile agendaFile;

    public AddEntryAgendaController()
    {
        getToDoList();
        getApplicationSession();
        getAgenda();
        toDoListFile = new ToDoListFile();
        agendaFile = new AgendaFile();
    }

    /**
     * Retrieves the to-do list instance.
     *
     * @return The ToDoList object.
     */
    private ToDoList getToDoList() {
        if (toDoList == null) {
            Repositories repositories = Repositories.getInstance();
            toDoList = repositories.getToDoList();
        }
        return toDoList;
    }

    private ApplicationSession getApplicationSession() {
        if (applicationSession == null) {
            applicationSession = ApplicationSession.getInstance();
        }
        return applicationSession;
    }

    /**
     * Retrieves the agenda instance.
     *
     * @return The Agenda object.
     */
    private Agenda getAgenda() {
        if (agenda == null) {
            Repositories repositories = Repositories.getInstance();
            agenda = repositories.getAgenda();
        }
        return agenda;
    }

    public List<GSTaskDTO> getTodoListEntries()
    {
        String email = applicationSession.getCurrentSession().getUserEmail();
        List<GSTask> associatedToDoEntriesList = toDoList.getToDoListEntries(email);
        List<GSTaskDTO> associatedToDoEntriesListDTO = GSTaskMapper.toDTOToDo(associatedToDoEntriesList);
        return associatedToDoEntriesListDTO;
    }

    public void getSelectedTask(int i)
    {
        selectedTaskClone = toDoList.getSelectedTask(i);
    }

    public boolean addEntry(String startingDate)
    {
        Optional<GSTask> updatedTask = Optional.empty();

        updatedTask = agenda.addEntry(selectedTaskClone, startingDate);

        if(updatedTask.isPresent())
        {
            if(!toDoListFile.save(toDoList))
            {
                return false;
            }
            if(!agendaFile.save(agenda))
            {
                return false;
            }
        }

        return true;
    }
}
