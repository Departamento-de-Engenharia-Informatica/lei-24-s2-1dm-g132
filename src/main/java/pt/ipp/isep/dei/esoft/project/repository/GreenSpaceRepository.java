package pt.ipp.isep.dei.esoft.project.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.mapper.GreenSpaceMapper;
import pt.ipp.isep.dei.esoft.project.mapper.dto.GreenSpaceDTO;

public class GreenSpaceRepository implements Serializable {

    private final List<GreenSpace> greenSpaces;

    private List<GreenSpace> associatedGreenSpacesList;

    public GreenSpaceRepository() {
        greenSpaces = new ArrayList<>();
    }

    public boolean registerGreenSpace(GreenSpaceDTO greenSpaceDto) {
        return add(GreenSpaceMapper.fromDTO(greenSpaceDto));
    }
    public Optional<GreenSpaceDTO> getGreenSpaceByName(String name) {
        for (GreenSpace greenSpace : greenSpaces){
            if (greenSpace.getName().equals(name)){
                return Optional.of(GreenSpaceMapper.toDTO(greenSpace));
            }
        }
        return Optional.empty();
    }

    public boolean add(GreenSpace greenSpace) {
        return this.greenSpaces.add(greenSpace);
    }

    public List<GreenSpaceDTO> getGreenSpaces() {
        return greenSpaces.stream().map(GreenSpaceMapper::toDTO).collect(Collectors.toList());
    }

    public List<GreenSpace> getGreenSpaces(String email) {
        associatedGreenSpacesList = new ArrayList<>();
        for(GreenSpace greenSpace : greenSpaces)
        {
            if(greenSpace.hasUserEmail(email))
            {
                associatedGreenSpacesList.add(greenSpace);
            }
        }
        return associatedGreenSpacesList;
    }

    public GreenSpace getSelectedGreenSpace(int i)
    {
        return associatedGreenSpacesList.get(i);
    }

}
