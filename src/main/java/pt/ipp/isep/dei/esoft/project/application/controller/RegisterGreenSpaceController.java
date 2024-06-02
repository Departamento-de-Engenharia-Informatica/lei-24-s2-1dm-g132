package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.mapper.dto.GreenSpaceDTO;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

public class RegisterGreenSpaceController {

    private GreenSpaceRepository greenSpaceRepository;

    private RegisterGreenSpaceController() {
        this.greenSpaceRepository = getGreenSpaceRepository();
    }

    private GreenSpaceRepository getGreenSpaceRepository() {
        if (greenSpaceRepository == null) {
            greenSpaceRepository = Repositories.getInstance().getGreenSpaceRepository();
        }
        return greenSpaceRepository;
    }

    public boolean registerGreenSpace(GreenSpaceDTO greenSpace) {
        return greenSpaceRepository.registerGreenSpace(greenSpace);
    }
}
