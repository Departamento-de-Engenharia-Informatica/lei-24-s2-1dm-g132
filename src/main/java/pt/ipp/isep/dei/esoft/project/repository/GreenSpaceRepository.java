package pt.ipp.isep.dei.esoft.project.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.Job;

public class GreenSpaceRepository {

    private final List<GreenSpace> greenSpaces;

    public GreenSpaceRepository() {
        greenSpaces = new ArrayList<>();
    }

    public Optional<GreenSpace> registerGreenSpace(String name, String address, int area, String type) {
        Optional<GreenSpace> optionalValue = Optional.empty();
        GreenSpace greenSpace = new GreenSpace(name, address, area, type);
        if (addGreenSpace(greenSpace)) {
            optionalValue = Optional.of(greenSpace);
        }
        return optionalValue;
    }

    private boolean addGreenSpace(GreenSpace greenSpace) {
        boolean success = false;
        if (validate(greenSpace)) {
            success = greenSpaces.add(greenSpace.clone());
        }
        return success;
    }

    private boolean validate(GreenSpace greenSpace) {
        return greenSpacesDoNotContain(greenSpace);
    }

    private boolean greenSpacesDoNotContain(GreenSpace greenSpace) {
        return !greenSpaces.contains(greenSpace);
    }

    public List<GreenSpace> getGreenSpaces() {
        return List.copyOf(greenSpaces);
    }





}
