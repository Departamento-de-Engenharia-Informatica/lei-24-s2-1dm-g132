package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.mapper.dto.GreenSpaceDTO;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.serialization.GreenSpaceRepositoryFile;

public class RegisterGreenSpaceController {

    private GreenSpaceRepository greenSpaceRepository;

    private GreenSpaceRepositoryFile greenSpaceRepositoryFile;

    private RegisterGreenSpaceController() {
        this.greenSpaceRepository = getGreenSpaceRepository();
        this.greenSpaceRepositoryFile = new GreenSpaceRepositoryFile();
    }

    private GreenSpaceRepository getGreenSpaceRepository() {
        if (greenSpaceRepository == null) {
            greenSpaceRepository = Repositories.getInstance().getGreenSpaceRepository();
        }
        return greenSpaceRepository;
    }

    public boolean registerGreenSpace(GreenSpaceDTO greenSpace) {
        if(!greenSpaceRepository.registerGreenSpace(greenSpace))
        {
            return false;
        }
        if(!greenSpaceRepositoryFile.save(greenSpaceRepository))
        {
            System.out.println("Error while saving Green Space Repository in external file!");
        }
        return true;
    }
}
