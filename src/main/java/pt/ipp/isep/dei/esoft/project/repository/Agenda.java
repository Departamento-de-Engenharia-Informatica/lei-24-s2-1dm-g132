package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.GSTask;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.mapper.dto.GSTaskDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Agenda implements Serializable {

    /**
     * A list of Tasks which consists of all the processed Green Space Tasks.
     */
    private final List<GSTask> entriesList;

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
}
