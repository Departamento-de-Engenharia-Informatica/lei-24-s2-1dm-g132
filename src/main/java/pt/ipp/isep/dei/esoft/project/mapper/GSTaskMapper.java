package pt.ipp.isep.dei.esoft.project.mapper;

import pt.ipp.isep.dei.esoft.project.domain.GSTask;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.mapper.dto.GSTaskDTO;
import pt.ipp.isep.dei.esoft.project.mapper.dto.GreenSpaceDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Mapper class for converting GSTask entities and DTOs.
 * This class provides static methods to convert GSTask objects to GSTaskDTO objects
 * and vice versa.
 */
public class GSTaskMapper {

    /**
     * Converts a GSTaskDTO to a GSTask entity.
     *
     * @param taskDTO The GSTaskDTO to convert.
     * @param selectedGreenSpace The GreenSpace associated with the task.
     * @return The corresponding GSTask entity.
     */
    public static GSTask toModel(GSTaskDTO taskDTO, GreenSpace selectedGreenSpace)
    {
        return new GSTask(taskDTO.getTitle(), taskDTO.getDescription(), taskDTO.getDegreeOfUrgency(), taskDTO.getExpectedDuration(), selectedGreenSpace);
    }

    /**
     * Converts a GSTask entity to a GSTaskDTO for To-Do list.
     *
     * @param gSTask The GSTask entity to convert.
     * @return The corresponding GSTaskDTO object.
     */
    public static GSTaskDTO toDTOToDo(GSTask gSTask) {
        return new GSTaskDTO(gSTask.getTitle(), gSTask.getDescription(), gSTask.getDegreeOfUrgency(), gSTask.getExpectedDuration(), new GreenSpaceDTO(gSTask.getGreenSpace().getName(), gSTask.getGreenSpace().getAddress(), gSTask.getGreenSpace().getArea(), gSTask.getGreenSpace().getType()));
    }

    /**
     * Converts a list of GSTask entities to a list of GSTaskDTOs for To-Do list.
     *
     * @param associatedToDoEntriesList The list of GSTask entities to convert.
     * @return A list of GSTaskDTO objects corresponding to the input list.
     */
    public static List<GSTaskDTO> toDTOToDo(List<GSTask> associatedToDoEntriesList)
    {
        List<GSTaskDTO> associatedToDoEntriesListDTO = new ArrayList<>();
        for(GSTask gSTask : associatedToDoEntriesList)
        {
            associatedToDoEntriesListDTO.add(toDTOToDo(gSTask));
        }
        return associatedToDoEntriesListDTO;
    }

    /**
     * Converts a GSTask entity to a GSTaskDTO for the Agenda.
     *
     * @param gSTask The GSTask entity to convert.
     * @return The corresponding GSTaskDTO object.
     */
    public static GSTaskDTO toDTOAgenda(GSTask gSTask) {
        return new GSTaskDTO(gSTask.getTitle(), gSTask.getDescription(), gSTask.getDegreeOfUrgency(), gSTask.getExpectedDuration(), new GreenSpaceDTO(gSTask.getGreenSpace().getName(), gSTask.getGreenSpace().getAddress(), gSTask.getGreenSpace().getArea(), gSTask.getGreenSpace().getType()), gSTask.getStartingDate());
    }

    /**
     * Converts a list of GSTask entities to a list of GSTaskDTOs for the Agenda.
     *
     * @param freeAgendaEntriesList The list of GSTask entities to convert.
     * @return A list of GSTaskDTO objects corresponding to the input list.
     */
    public static List<GSTaskDTO> toDTOAgenda(List<GSTask> freeAgendaEntriesList)
    {
        List<GSTaskDTO> freeAgendaEntriesListDTO = new ArrayList<>();
        for(GSTask gSTask : freeAgendaEntriesList)
        {
            freeAgendaEntriesListDTO.add(toDTOAgenda(gSTask));
        }
        return freeAgendaEntriesListDTO;
    }
}
