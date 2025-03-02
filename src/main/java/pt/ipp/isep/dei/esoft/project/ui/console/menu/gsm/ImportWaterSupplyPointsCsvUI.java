package pt.ipp.isep.dei.esoft.project.ui.console.menu.gsm;

import pt.ipp.isep.dei.esoft.project.application.controller.ImportWaterSupplyPointsCsvController;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

public class ImportWaterSupplyPointsCsvUI implements Runnable {
    private ImportWaterSupplyPointsCsvController controller;

    public ImportWaterSupplyPointsCsvUI() {
        this.controller = new ImportWaterSupplyPointsCsvController();
    }

    @Override
    public void run() {
        try {
            String filePath = Utils.readLineFromConsole("Type the file path to the csv: ");
            if (controller.loadGraph(filePath)){
                System.out.println("Success");
            } else {
                System.out.println("Error");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
