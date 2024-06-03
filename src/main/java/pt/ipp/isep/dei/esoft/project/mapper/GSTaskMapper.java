package pt.ipp.isep.dei.esoft.project.mapper;

import pt.ipp.isep.dei.esoft.project.domain.GSTask;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.mapper.dto.GSTaskDTO;

public class GSTaskMapper {
    public static GSTask toModel(GSTaskDTO taskDTO, GreenSpace selectedGreenSpace)
    {
        return new GSTask(taskDTO.getTitle(), taskDTO.getDescription(), taskDTO.getDegreeOfUrgency(), taskDTO.getExpectedDuration(), selectedGreenSpace);
    }
}
