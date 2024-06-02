package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.mapper.dto.GreenSpaceDTO;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;

import java.util.List;

public class ListGreenSpacesController {

    private GreenSpaceRepository greenSpaceRepository;

    private ListGreenSpacesController() {
        this.greenSpaceRepository = new GreenSpaceRepository();
    }

    private GreenSpaceRepository getGreenSpaceRepository() {
        if (greenSpaceRepository == null) {
            greenSpaceRepository = new GreenSpaceRepository();
        }
        return greenSpaceRepository;
    }


    public List<GreenSpaceDTO> greenSpaces() {
        return greenSpaceRepository.getGreenSpaces();
    }
}
