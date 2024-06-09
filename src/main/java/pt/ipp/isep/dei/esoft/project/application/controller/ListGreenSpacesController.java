package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.mapper.dto.GreenSpaceDTO;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.List;

public class ListGreenSpacesController {

    private GreenSpaceRepository greenSpaceRepository;

    private ListGreenSpacesController() {
        this.greenSpaceRepository = getGreenSpaceRepository();
    }

    private GreenSpaceRepository getGreenSpaceRepository() {
        if (greenSpaceRepository == null) {
            greenSpaceRepository = Repositories.getInstance().getGreenSpaceRepository();
        }
        return greenSpaceRepository;
    }


    public List<GreenSpaceDTO> greenSpaces(String email) {
        return greenSpaceRepository.getGreenSpaces(email);
    }
}
