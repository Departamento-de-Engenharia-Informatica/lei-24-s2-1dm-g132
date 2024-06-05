package pt.ipp.isep.dei.esoft.project.mapper;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.mapper.dto.GreenSpaceDTO;

import java.util.ArrayList;
import java.util.List;

public class GreenSpaceMapper {

    public static GreenSpaceDTO toDTO(GreenSpace greenSpace) {
        return new GreenSpaceDTO(greenSpace.getName(), greenSpace.getAddress(), greenSpace.getArea(), greenSpace.getType());
    }

    public static List<GreenSpaceDTO> toDTO(List<GreenSpace> associatedGreenSpacesList)
    {
        List<GreenSpaceDTO> associatedGreenSpacesListDTO = new ArrayList<>();
        for(GreenSpace greenSpace : associatedGreenSpacesList)
        {
            associatedGreenSpacesListDTO.add(toDTO(greenSpace));
        }
        return associatedGreenSpacesListDTO;
    }

    public static GreenSpace fromDTO(GreenSpaceDTO greenSpaceDto, String gsmEmail){
        return new GreenSpace(greenSpaceDto.getName(), greenSpaceDto.getAddress(), greenSpaceDto.getArea(), greenSpaceDto.getType(), gsmEmail);
    }

}
