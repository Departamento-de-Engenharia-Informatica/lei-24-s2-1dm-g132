package pt.ipp.isep.dei.esoft.project.ui.console.menu.gsm;

import pt.ipp.isep.dei.esoft.project.ui.console.menu.MenuItem;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class GSMMenuUI implements Runnable{

    public GSMMenuUI() {
    }

    @Override
    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Import csv file with water supply", new ImportWaterSupplyPointsCsvUI()));
        options.add(new MenuItem("Calculate minimum accumulated cost", new MinimalAcumulatedCostGraphUI()));
        options.add(new MenuItem("Import csv file with meeting points", new ImportMeetingPointsCsvUI()));
        options.add(new MenuItem("Place signs to evacuate park users to an assembly point and to one of the several meeting points", new ShortestPathsToMeetingPointUI()));


        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- GSM MENU -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
