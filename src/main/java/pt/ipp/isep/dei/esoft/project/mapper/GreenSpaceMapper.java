package pt.ipp.isep.dei.esoft.project.mapper;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.mapper.dto.GreenSpaceDTO;

public class GreenSpaceMapper {

    public static GreenSpaceDTO toDTO(GreenSpace greenSpace) {
        return new GreenSpaceDTO(greenSpace.getName(), greenSpace.getAddress(), greenSpace.getArea(), greenSpace.getType(), greenSpace.getGsmEmail());
    }

    public static GreenSpace fromDTO(GreenSpaceDTO greenSpaceDto){
        return new GreenSpace(greenSpaceDto.getName(), greenSpaceDto.getAddress(), greenSpaceDto.getArea(), greenSpaceDto.getType(), greenSpaceDto.getGsmEmail());
    }
}
