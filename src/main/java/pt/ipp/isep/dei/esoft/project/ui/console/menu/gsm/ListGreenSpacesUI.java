package pt.ipp.isep.dei.esoft.project.ui.console.menu.gsm;

import pt.ipp.isep.dei.esoft.project.application.controller.ListGreenSpacesController;
import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.mapper.dto.GreenSpaceDTO;

public class ListGreenSpacesUI implements Runnable {

    private final ListGreenSpacesController listGreenSpacesController;

    public ListGreenSpacesUI() {
        this.listGreenSpacesController = new ListGreenSpacesController();
    }

    @Override
    public void run() {
        try {
            for (GreenSpaceDTO greenSpaceDTO : listGreenSpacesController.greenSpaces(ApplicationSession.getInstance().getCurrentSession().getUserEmail())){
                System.out.println("Name: " + greenSpaceDTO.getName());
                System.out.println("  Type: " + greenSpaceDTO.getType());
                System.out.println("  Address: " + greenSpaceDTO.getAddress());
                System.out.println("  Area: " + greenSpaceDTO.getArea());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
