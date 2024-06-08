package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.mapper.GreenSpaceMapper;
import pt.ipp.isep.dei.esoft.project.mapper.dto.GSTaskDTO;
import pt.ipp.isep.dei.esoft.project.mapper.dto.GreenSpaceDTO;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.repository.ToDoList;
import pt.ipp.isep.dei.esoft.project.repository.serialization.ToDoListFile;

import java.util.List;
import java.util.Optional;

/**
 * Controller class for adding entries to the to-do list.
 * Provides methods to interact with green spaces and the to-do list.
 */
public class AddEntryToDoController {

    /**
     * The repository instance used to manage green spaces.
     */
    private GreenSpaceRepository greenSpaceRepository;

    /**
     * The to-do list instance used to manage to-do list entries.
     */
    private ToDoList toDoList;

    /**
     * The application session instance used to manage the current user session.
     */
    private ApplicationSession applicationSession;

    /**
     * The selected green space instance.
     */
    private GreenSpace selectedGreenSpace;

    /**
     * The file instance used to manage the serialization of the to-do list.
     */
    private ToDoListFile toDoListFile;

    /**
     * Initializes a new instance of the AddEntryToDoController class.
     * Initializes necessary repositories and files.
     */
    public AddEntryToDoController()
    {
        getGreenSpaceRepository();
        getToDoList();
        getApplicationSession();
        toDoListFile = new ToDoListFile();
    }

    /**
     * Retrieves the green space repository instance.
     *
     * @return The GreenSpaceRepository object.
     */
    private GreenSpaceRepository getGreenSpaceRepository() {
        if (greenSpaceRepository == null) {
            Repositories repositories = Repositories.getInstance();
            greenSpaceRepository = repositories.getGreenSpaceRepository();
        }
        return greenSpaceRepository;
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
     * Retrieves the list of green spaces associated with the current user session.
     *
     * @return A list of GreenSpaceDTO objects representing the green spaces.
     */
    public List<GreenSpaceDTO> getGreenSpaces(){
        String email = applicationSession.getCurrentSession().getUserEmail();
        List<GreenSpace> associatedGreenSpacesList = greenSpaceRepository.getGreenSpaces(email);
        List<GreenSpaceDTO> associatedGreenSpacesListDTO = GreenSpaceMapper.toDTO(associatedGreenSpacesList);
        return associatedGreenSpacesListDTO;
    }

    /**
     * Selects a green space from the list based on its index.
     *
     * @param i The index of the green space to be selected.
     */
    public void getSelectedGreenSpace(int i)
    {
        selectedGreenSpace = greenSpaceRepository.getSelectedGreenSpace(i);
    }

    /**
     * Adds a task entry to the to-do list for the selected green space.
     * Updates the to-do list file if the entry is successfully added.
     *
     * @param taskDto The GSTaskDTO object representing the task to be added.
     * @return True if the entry is successfully added and file is updated, false otherwise.
     */
    public boolean addEntry(GSTaskDTO taskDto)
    {
        Optional<GSTask> newTask = Optional.empty();

        newTask = toDoList.addEntry(taskDto, selectedGreenSpace);

        if(newTask.isPresent())
        {
            if(!toDoListFile.save(toDoList))
            {
                return false;
            }
        }

        return true;
    }

}
