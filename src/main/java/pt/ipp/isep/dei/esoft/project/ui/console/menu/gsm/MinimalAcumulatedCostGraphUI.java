package pt.ipp.isep.dei.esoft.project.ui.console.menu.gsm;

import pt.ipp.isep.dei.esoft.project.application.controller.MinimalAcumulatedCostGraphController;

public class MinimalAcumulatedCostGraphUI implements Runnable {
    private MinimalAcumulatedCostGraphController controller;

    public MinimalAcumulatedCostGraphUI() {
        this.controller = new MinimalAcumulatedCostGraphController();
    }

    @Override
    public void run() {
        try {
            if (controller.getMinimalCostGraph()){
                System.out.println("Sucess!");
            } else {
                System.out.println("Error!");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
