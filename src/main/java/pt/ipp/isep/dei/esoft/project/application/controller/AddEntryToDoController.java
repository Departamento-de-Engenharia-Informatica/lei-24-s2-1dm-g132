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
 * Controller class responsible for handling operations related to the addition of entries to teh To-Do List.
 */
public class AddEntryToDoController {

    /**
     * The green space repository needed for the operations of this class.
     */
    private GreenSpaceRepository greenSpaceRepository;

    /**
     * The To-Do List needed for the operations of this class.
     */
    private ToDoList toDoList;

    /**
     * The Application Session needed for the operations of this class.
     */
    private ApplicationSession applicationSession;

    private GreenSpace selectedGreenSpace;

    private ToDoListFile toDoListFile;

    /**
     * Constructs a new AddEntryToDoController object.
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

    private ApplicationSession getApplicationSession() {
        if (applicationSession == null) {
            applicationSession = ApplicationSession.getInstance();
        }
        return applicationSession;
    }

    public List<GreenSpaceDTO> getGreenSpaces(){
        String email = applicationSession.getCurrentSession().getUserEmail();
        List<GreenSpace> associatedGreenSpacesList = greenSpaceRepository.getGreenSpaces(email);
        List<GreenSpaceDTO> associatedGreenSpacesListDTO = GreenSpaceMapper.toDTO(associatedGreenSpacesList);
        return associatedGreenSpacesListDTO;
    }

    public void getSelectedGreenSpace(int i)
    {
        selectedGreenSpace = greenSpaceRepository.getSelectedGreenSpace(i);
    }

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
