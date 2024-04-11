package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.ImportWaterSupplyPointsCsv;
import pt.ipp.isep.dei.esoft.project.ui.console.MinimalAcumulatedCostGraphUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class GSMMenuUI implements Runnable{

    public GSMMenuUI() {
    }

    @Override
    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("US12", new ImportWaterSupplyPointsCsv()));
        options.add(new MenuItem("US13", new MinimalAcumulatedCostGraphUI()));


        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- GSM MENU -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
