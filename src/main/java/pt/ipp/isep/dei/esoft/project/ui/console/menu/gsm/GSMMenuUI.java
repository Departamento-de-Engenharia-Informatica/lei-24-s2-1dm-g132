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
        options.add(new MenuItem("Import a csv file", new ImportWaterSupplyPointsCsvUI()));
        options.add(new MenuItem("Apply an algorithm that returns the routes to be opened and pipes needed to be laid with a minimum accumulated cost", new MinimalAcumulatedCostGraphUI()));
        options.add(new MenuItem("Place signs to evacuate park users to an Assembly Point", new ImportMeetingPointsCsvUI()));
        options.add(new MenuItem("Place signs to evacuate park users to one of the several Assembly Points", new ShortestPathsToMeetingPointUI()));


        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- GSM MENU -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
