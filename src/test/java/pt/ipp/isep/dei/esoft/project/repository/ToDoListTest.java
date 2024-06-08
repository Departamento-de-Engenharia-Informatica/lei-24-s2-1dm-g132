package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.GSTask;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.mapper.dto.GSTaskDTO;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ToDoListTest {

    @Test
    void ensureAddEntryWorks()
    {
        ToDoList toDoList = new ToDoList();
        GSTaskDTO gsTaskDTO = new GSTaskDTO("Pruning Trees", "Prune all trees", "Medium", 4);
        GreenSpace greenSpace = new GreenSpace("Jardim do morro", "Ponte D. Luís", 6, "garden", "gsm1@this.app");

        Optional<GSTask> gsTask = toDoList.addEntry(gsTaskDTO, greenSpace);
    }

    @Test
    void ensureGetToDoListEntriesWorks()
    {
        ToDoList toDoList = new ToDoList();
        GSTaskDTO gsTaskDTO1 = new GSTaskDTO("Pruning Trees", "Prune all trees", "Medium", 4);
        GreenSpace greenSpace1 = new GreenSpace("Jardim do morro", "Ponte D. Luís", 6, "garden", "gsm1@this.app");

        Optional<GSTask> gsTask1 = toDoList.addEntry(gsTaskDTO1, greenSpace1);

        GSTaskDTO gsTaskDTO2 = new GSTaskDTO("Pruning Trees", "Prune all trees", "Medium", 4);
        GreenSpace greenSpace2 = new GreenSpace("Jardim do morro", "Ponte D. Luís", 6, "garden", "gsm2@this.app");

        Optional<GSTask> gsTask2 = toDoList.addEntry(gsTaskDTO2, greenSpace2);

        List<GSTask> expectedList = new ArrayList<>();
        gsTask2.ifPresent(expectedList::add);

        List<GSTask> result = toDoList.getToDoListEntries("gsm2@this.app");

        assertEquals(expectedList, result);
    }

    @Test
    void ensureGetSelectedTaskWorks1()
    {
        ToDoList toDoList = new ToDoList();
        GSTaskDTO gsTaskDTO1 = new GSTaskDTO("Pruning Trees", "Prune all trees", "Medium", 4);
        GreenSpace greenSpace1 = new GreenSpace("Jardim do morro", "Ponte D. Luís", 6, "garden", "gsm1@this.app");

        Optional<GSTask> gsTask1 = toDoList.addEntry(gsTaskDTO1, greenSpace1);

        GSTaskDTO gsTaskDTO2 = new GSTaskDTO("Pruning Trees", "Prune all trees", "Medium", 4);
        GreenSpace greenSpace2 = new GreenSpace("Jardim do morro", "Ponte D. Luís", 6, "garden", "gsm2@this.app");

        Optional<GSTask> gsTask2 = toDoList.addEntry(gsTaskDTO2, greenSpace2);

        List<GSTask> result = toDoList.getToDoListEntries("gsm2@this.app");

        toDoList.getSelectedTask(0);
    }

    @Test
    void ensureGetSelectedTaskWorks2()
    {
        ToDoList toDoList = new ToDoList();
        GSTaskDTO gsTaskDTO1 = new GSTaskDTO("Pruning Trees", "Prune all trees", "Medium", 4);
        GreenSpace greenSpace1 = new GreenSpace("Jardim do morro", "Ponte D. Luís", 6, "garden", "gsm1@this.app");

        Optional<GSTask> gsTask1 = toDoList.addEntry(gsTaskDTO1, greenSpace1);

        GSTaskDTO gsTaskDTO2 = new GSTaskDTO("Pruning Trees", "Prune all trees", "Medium", 4);
        GreenSpace greenSpace2 = new GreenSpace("Jardim do morro", "Ponte D. Luís", 6, "garden", "gsm2@this.app");

        Optional<GSTask> gsTask2 = toDoList.addEntry(gsTaskDTO2, greenSpace2);

        List<GSTask> result = toDoList.getToDoListEntries("gsm2@this.app");

        assertThrows(IndexOutOfBoundsException.class,
                () -> toDoList.getSelectedTask(1));
    }


}
