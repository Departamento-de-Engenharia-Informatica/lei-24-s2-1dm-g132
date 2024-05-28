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

    /*
    public Optional<GreenSpaceDTO> registerGreenSpace(String name, String address, int area, String type) {
        Optional<GreenSpaceDTO> optionalValue = Optional.empty();
        GreenSpaceDTO greenSpace = new GreenSpaceDTO(name, address, area, type);
        if (addGreenSpace(greenSpace)) {
            optionalValue = Optional.of(greenSpace);
        }
        return optionalValue;
    }
    public GreenSpaceDTO getGreenSpaceByName(String name, String address, int area, String type) {
        GreenSpaceDTO newgreenSpace = new GreenSpaceDTO(name, address, area, type);
        GreenSpaceDTO greenSpace = null;
        if (greenSpaces.contains(newgreenSpace)) {
            greenSpace = greenSpaces.get(greenSpaces.indexOf(newgreenSpace));
        }
        if (greenSpace == null) {
            throw new IllegalArgumentException(
                    "Green Space requested for [" + name + "] does not exist.");
        }
        return greenSpace;
    }


     */

    private boolean addGreenSpace(GreenSpaceDTO greenSpace) {
        boolean success = false;
        if (validate(greenSpace)) {
            success = greenSpaces.add(greenSpace.clone());
        }
        return success;
    }
    public void add(GreenSpaceDTO greenSpace) {
        this.greenSpaces.add(greenSpace);
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
