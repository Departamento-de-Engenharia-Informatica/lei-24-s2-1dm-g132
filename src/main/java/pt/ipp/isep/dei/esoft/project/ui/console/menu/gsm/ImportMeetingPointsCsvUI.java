package pt.ipp.isep.dei.esoft.project.ui.console.menu.gsm;

import pt.ipp.isep.dei.esoft.project.application.controller.ImportMeetingPointsCsvController;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

public class ImportMeetingPointsCsvUI implements Runnable {

    private ImportMeetingPointsCsvController controller;

    public ImportMeetingPointsCsvUI() {
        this.controller = new ImportMeetingPointsCsvController();
    }

    @Override
    public void run() {
        try{
            String placesFilePath = Utils.readLineFromConsole("Type the file path with the places: ");
            String distancesFilePath = Utils.readLineFromConsole("Type the file path with the distances matrix: ");
            if (controller.loadGraph(placesFilePath, distancesFilePath)){
                System.out.println("Success");
            } else {
                System.out.println("Error");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
