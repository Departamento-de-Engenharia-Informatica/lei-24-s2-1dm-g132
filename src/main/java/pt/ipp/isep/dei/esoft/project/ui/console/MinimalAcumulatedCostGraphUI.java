package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.MinimalAcumulatedCostGraphController;

import java.util.List;

public class MinimalAcumulatedCostGraphUI implements Runnable {
    private MinimalAcumulatedCostGraphController controller;

    public MinimalAcumulatedCostGraphUI() {
        this.controller = new MinimalAcumulatedCostGraphController();
    }

    @Override
    public void run() {
        try {
            List<String> list = controller.getMinimalCostGraph();
            System.out.println("Minimal Cost Graph:\nEdges: " + list.size() + "\n");
            for (String a : list){
                System.out.println(a);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
