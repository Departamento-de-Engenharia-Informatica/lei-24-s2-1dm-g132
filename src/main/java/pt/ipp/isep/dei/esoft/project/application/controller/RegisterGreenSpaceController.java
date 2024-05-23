package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpaceDTO;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;

import java.util.List;



public class RegisterGreenSpaceController {

    public void registerGreenSpace(GreenSpaceDTO greenSpaceDTO) {
        GreenSpaceDTO greenSpace = new GreenSpaceDTO(greenSpaceDTO.getName(), greenSpaceDTO.getAddress(), greenSpaceDTO.getArea(), greenSpaceDTO.getType());
        greenSpace.setName(greenSpaceDTO.getName());
        greenSpace.setAddress(greenSpaceDTO.getAddress());
        greenSpace.setArea(greenSpaceDTO.getArea());
        greenSpace.setType(greenSpaceDTO.getType());

        greenSpaceRepository.registerGreenSpace(greenSpace.getName(), greenSpace.getAddress(), greenSpace.getArea(), greenSpace.getType());
    }

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
    public List<GreenSpaceDTO> greenSpaces() {
        GreenSpaceRepository greenSpaceRepository = getGreenSpaceRepository();
        return greenSpaceRepository.getGreenSpaces();
    }

    public GreenSpaceDTO registerGreenSpace(String name, String address, int area, String type) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Green Space name cannot be null or empty");
        }
        GreenSpaceRepository greenSpaceRepository = getGreenSpaceRepository();
        GreenSpaceDTO newGreenSpace = new GreenSpaceDTO(name, address, area, type);
        greenSpaceRepository.add(newGreenSpace);
        return newGreenSpace;
    }





}
