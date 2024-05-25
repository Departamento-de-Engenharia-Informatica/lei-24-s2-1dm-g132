package pt.ipp.isep.dei.esoft.project.ui.console.menu.gsm;

import pt.ipp.isep.dei.esoft.project.application.controller.ShortestPathsToMeetingPointController;
import pt.ipp.isep.dei.esoft.project.domain.graph.Vertice;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

public class ShortestPathsToMeetingPointUI implements Runnable{
    private ShortestPathsToMeetingPointController controller;

    public ShortestPathsToMeetingPointUI(){
        this.controller = new ShortestPathsToMeetingPointController();
    }

    @Override
    public void run() {
        try{
            Vertice meetingPoint = (Vertice) Utils.showAndSelectOne(controller.getAllMeetingPoints(), "Select the Meeting Point");
            if (controller.getShortestPathsToMeetingPoint(meetingPoint)) {
                System.out.println("Success");
            } else {
                System.out.println("Error");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
