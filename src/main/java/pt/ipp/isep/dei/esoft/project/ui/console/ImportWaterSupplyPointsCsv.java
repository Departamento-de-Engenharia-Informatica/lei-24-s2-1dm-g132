package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ImportWaterSupplyPointsCsvController;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

public class ImportWaterSupplyPointsCsv implements Runnable {
    private ImportWaterSupplyPointsCsvController controller;

    public ImportWaterSupplyPointsCsv() {
        this.controller = new ImportWaterSupplyPointsCsvController();
    }

    @Override
    public void run() {
        try {
            String filePath = Utils.readLineFromConsole("Type the file path to the csv: ");
            controller.loadGraph(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
