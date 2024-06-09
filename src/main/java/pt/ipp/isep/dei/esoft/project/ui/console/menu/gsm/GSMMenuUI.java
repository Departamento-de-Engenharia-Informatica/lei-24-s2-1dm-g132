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
        options.add(new MenuItem("MDISC - Import csv file with water supply", new ImportWaterSupplyPointsCsvUI()));
        options.add(new MenuItem("MDISC - Calculate minimum accumulated cost", new MinimalAcumulatedCostGraphUI()));
        options.add(new MenuItem("MDISC - Import csv file with meeting points", new ImportMeetingPointsCsvUI()));
        options.add(new MenuItem("MDISC - Place signs to evacuate park users to an assembly point and to one of the several meeting points", new ShortestPathsToMeetingPointUI()));
        options.add(new MenuItem("List All GreenSpaces Managed by Me", new ListGreenSpacesUI()));


        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- GSM MENU -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
