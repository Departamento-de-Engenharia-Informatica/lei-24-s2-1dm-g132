package pt.ipp.isep.dei.esoft.project.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpaceDTO;

public class GreenSpaceRepository {

    private final List<GreenSpaceDTO> greenSpaces;

    public GreenSpaceRepository() {
        greenSpaces = new ArrayList<>();
    }

    public Optional<GreenSpaceDTO> registerGreenSpace(String name, String address, int area, String type) {
        Optional<GreenSpaceDTO> optionalValue = Optional.empty();
        GreenSpaceDTO greenSpace = new GreenSpaceDTO(name, address, area, type);
        if (addGreenSpace(greenSpace)) {
            optionalValue = Optional.of(greenSpace);
        }
        return optionalValue;
    }

    private boolean addGreenSpace(GreenSpaceDTO greenSpace) {
        boolean success = false;
        if (validate(greenSpace)) {
            success = greenSpaces.add(greenSpace.clone());
        }
        return success;
    }
    
    private boolean validate(GreenSpaceDTO greenSpace) {
        return greenSpacesDoNotContain(greenSpace);
    }

    private boolean greenSpacesDoNotContain(GreenSpaceDTO greenSpace) {
        return !greenSpaces.contains(greenSpace);
    }

    public List<GreenSpaceDTO> getGreenSpaces() {
        return List.copyOf(greenSpaces);
    }





}
