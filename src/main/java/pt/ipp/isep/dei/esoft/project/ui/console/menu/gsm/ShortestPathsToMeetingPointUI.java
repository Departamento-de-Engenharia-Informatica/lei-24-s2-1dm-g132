package pt.ipp.isep.dei.esoft.project.ui.console.menu.gsm;

import pt.ipp.isep.dei.esoft.project.application.controller.ShortestPathsToMeetingPointController;

public class ShortestPathsToMeetingPointUI implements Runnable{
    private ShortestPathsToMeetingPointController controller;

    public ShortestPathsToMeetingPointUI(){
        this.controller = new ShortestPathsToMeetingPointController();
    }

    @Override
    public void run() {
        try{
            if (controller.getShortestPathsToMeetingPoint()) {
                System.out.println("Success");
            } else {
                System.out.println("Error");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
