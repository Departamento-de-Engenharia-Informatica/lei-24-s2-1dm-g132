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
            String filePath = Utils.readLineFromConsole("Type the file path to the csv: ");
            controller.loadGraph(filePath);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
