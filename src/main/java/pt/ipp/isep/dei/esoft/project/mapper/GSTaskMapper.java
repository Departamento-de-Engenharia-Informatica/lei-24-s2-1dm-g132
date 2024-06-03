package pt.ipp.isep.dei.esoft.project.mapper;

import pt.ipp.isep.dei.esoft.project.domain.GSTask;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.mapper.dto.GSTaskDTO;

import java.util.ArrayList;
import java.util.List;

public class GSTaskMapper {
    public static GSTask toModel(GSTaskDTO taskDTO, GreenSpace selectedGreenSpace)
    {
        return new GSTask(taskDTO.getTitle(), taskDTO.getDescription(), taskDTO.getDegreeOfUrgency(), taskDTO.getExpectedDuration(), selectedGreenSpace);
    }

    public static GSTaskDTO toDTO(GSTask gSTask) {
        return new GSTaskDTO(gSTask.getTitle(), gSTask.getDescription(), gSTask.getDegreeOfUrgency(), gSTask.getExpectedDuration(), gSTask.getGreenSpace());
    }

    public static List<GSTaskDTO> toDTO(List<GSTask> associatedToDoEntriesList)
    {
        List<GSTaskDTO> associatedToDoEntriesListDTO = new ArrayList<>();
        for(GSTask gSTask : associatedToDoEntriesList)
        {
            associatedToDoEntriesListDTO.add(toDTO(gSTask));
        }
        return associatedToDoEntriesListDTO;
    }


}
