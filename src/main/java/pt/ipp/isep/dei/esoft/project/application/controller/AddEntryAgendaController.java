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

/**
 * Controller class for adding entries to the agenda.
 * Provides methods to interact with the to-do list and agenda, and to add new entries.
 */
public class AddEntryAgendaController {

    /**
     * The to-do list instance used to manage to-do list entries.
     */
    private ToDoList toDoList;

    /**
     * The application session instance used to manage the current user session.
     */
    private ApplicationSession applicationSession;

    /**
     * The agenda instance used to manage agenda entries.
     */
    private Agenda agenda;

    /**
     * A clone of the selected task from the to-do list.
     */
    private GSTask selectedTaskClone;

    /**
     * The file instance used to manage the serialization of the to-do list.
     */
    private ToDoListFile toDoListFile;

    /**
     * The file instance used to manage the serialization of the agenda.
     */
    private AgendaFile agendaFile;

    /**
     * Initializes a new instance of the AddEntryAgendaController class.
     * Initializes necessary repositories and files.
     */
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

    /**
     * Retrieves the application session instance.
     *
     * @return The ApplicationSession object.
     */
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

    /**
     * Retrieves the list of to-do list entries for the current user session.
     *
     * @return A list of GSTaskDTO objects representing the to-do list entries.
     */
    public List<GSTaskDTO> getTodoListEntries()
    {
        String email = applicationSession.getCurrentSession().getUserEmail();
        List<GSTask> associatedToDoEntriesList = toDoList.getToDoListEntries(email);
        List<GSTaskDTO> associatedToDoEntriesListDTO = GSTaskMapper.toDTOToDo(associatedToDoEntriesList);
        return associatedToDoEntriesListDTO;
    }

    /**
     * Selects a task from the to-do list based on its index.
     *
     * @param i The index of the task to be selected.
     */
    public void getSelectedTask(int i)
    {
        selectedTaskClone = toDoList.getSelectedTask(i);
    }

    /**
     * Adds an entry to the agenda with the specified starting date.
     * Updates the to-do list and agenda files if the entry is successfully added.
     *
     * @param startingDate The starting date for the agenda entry.
     * @return True if the entry is successfully added and files are updated, false otherwise.
     */
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
