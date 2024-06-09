package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.GSTask;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.mapper.GSTaskMapper;
import pt.ipp.isep.dei.esoft.project.mapper.dto.GSTaskDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Represents a ToDoList containing Green Space Tasks (GSTasks).
 */
public class ToDoList implements Serializable {

    /**
     * A list of GSTasks which consists of all the entries in the ToDoList.
     */
    private final List<GSTask> entriesList;

    /**
     * A list of GSTasks representing the associated ToDo entries.
     */
    private List<GSTask> associatedToDoEntriesList;

    /**
     * Constructs a new ToDoList object.
     */
    public ToDoList() {
        entriesList = new ArrayList<>();
    }

    /**
     * Adds a new GSTask entry to the ToDoList based on the provided task DTO and selected green space.
     *
     * @param taskDTO           The GSTaskDTO representing the task to be added.
     * @param selectedGreenSpace The GreenSpace associated with the task.
     * @return An Optional containing the newly added GSTask if successful, otherwise empty.
     */
    public Optional<GSTask> addEntry(GSTaskDTO taskDTO, GreenSpace selectedGreenSpace)
    {
        Optional<GSTask> optionalValue = Optional.empty();

        GSTask task =  GSTaskMapper.toModel(taskDTO, selectedGreenSpace);

        if(addTask(task))
        {
            optionalValue = Optional.of(task);
        }

        return optionalValue;
    }

    /**
     * Adds a GSTask to the ToDoList.
     *
     * @param task The GSTask to be added.
     * @return True if the task was successfully added, false otherwise.
     */
    private boolean addTask(GSTask task)
    {
        return entriesList.add(task);
    }

    /**
     * Retrieves a list of GSTasks in the ToDoList that are pending and associated with the given email.
     *
     * @param email The email of the user requesting the ToDoList.
     * @return A list of GSTask objects.
     */
    public List<GSTask> getToDoListEntries(String email)
    {
        associatedToDoEntriesList = new ArrayList<>();
        for(GSTask gSTask : entriesList)
        {
            if(gSTask.isPending() && gSTask.hasUserEmail(email))
            {
                associatedToDoEntriesList.add(gSTask);
            }
        }
        return associatedToDoEntriesList;
    }

    /**
     * Retrieves a selected GSTask from the associated ToDo entries list by its index.
     * Marks the task as processed.
     *
     * @param i The index of the selected task.
     * @return A cloned instance of the selected GSTask.
     */
    public GSTask getSelectedTask(int i)
    {
        GSTask selectedTask = associatedToDoEntriesList.get(i);
        selectedTask.setProcessed();
        return selectedTask.clone();
    }

}
