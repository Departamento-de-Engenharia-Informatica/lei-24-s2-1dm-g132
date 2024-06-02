package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.mapper.dto.GreenSpaceDTO;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;

public class RegisterGreenSpaceController {

    private GreenSpaceRepository greenSpaceRepository;

    private RegisterGreenSpaceController() {
        this.greenSpaceRepository = new GreenSpaceRepository();
    }

    private GreenSpaceRepository getGreenSpaceRepository() {
        if (greenSpaceRepository == null) {
            greenSpaceRepository = new GreenSpaceRepository();
        }
        return greenSpaceRepository;
    }

    public boolean registerGreenSpace(GreenSpaceDTO greenSpace) {
        return greenSpaceRepository.registerGreenSpace(greenSpace);
    }
}
