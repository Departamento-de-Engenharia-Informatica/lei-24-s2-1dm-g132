package pt.ipp.isep.dei.esoft.project.mapper;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.mapper.dto.GreenSpaceDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Mapper class for converting GreenSpace entities and DTOs.
 * This class provides static methods to convert GreenSpace objects to GreenSpaceDTO objects
 * and vice versa.
 */
public class GreenSpaceMapper {

    /**
     * Converts a GreenSpace entity to a GreenSpaceDTO.
     *
     * @param greenSpace The GreenSpace entity to convert.
     * @return The corresponding GreenSpaceDTO object.
     */
    public static GreenSpaceDTO toDTO(GreenSpace greenSpace) {
        return new GreenSpaceDTO(greenSpace.getName(), greenSpace.getAddress(), greenSpace.getArea(), greenSpace.getType());
    }

    /**
     * Converts a list of GreenSpace entities to a list of GreenSpaceDTOs.
     *
     * @param associatedGreenSpacesList The list of GreenSpace entities to convert.
     * @return A list of GreenSpaceDTO objects corresponding to the input list.
     */
    public static List<GreenSpaceDTO> toDTO(List<GreenSpace> associatedGreenSpacesList)
    {
        List<GreenSpaceDTO> associatedGreenSpacesListDTO = new ArrayList<>();
        for(GreenSpace greenSpace : associatedGreenSpacesList)
        {
            associatedGreenSpacesListDTO.add(toDTO(greenSpace));
        }
        return associatedGreenSpacesListDTO;
    }

    /**
     * Converts a GreenSpaceDTO to a GreenSpace entity.
     *
     * @param greenSpaceDto The GreenSpaceDTO to convert.
     * @param gsmEmail      The email of the GreenSpace Manager associated with the GreenSpace.
     * @return The corresponding GreenSpace entity.
     */
    public static GreenSpace fromDTO(GreenSpaceDTO greenSpaceDto, String gsmEmail){
        return new GreenSpace(greenSpaceDto.getName(), greenSpaceDto.getAddress(), greenSpaceDto.getArea(), greenSpaceDto.getType(), gsmEmail);
    }

}
