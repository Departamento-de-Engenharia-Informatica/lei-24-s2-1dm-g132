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
 * Class responsible for storing and managing pending tasks data.
 */
public class ToDoList implements Serializable {

    /**
     * A list of Tasks which consists of all the registered Green Space Tasks.
     */
    private final List<GSTask> entriesList;

    /**
     * Constructs a new ToDoList object.
     */
    public ToDoList() {
        entriesList = new ArrayList<>();
    }

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

    private boolean addTask(GSTask task)
    {
        return entriesList.add(task);
    }

}
