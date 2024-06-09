package pt.ipp.isep.dei.esoft.project.ui.console.menu.collaborator;

import pt.ipp.isep.dei.esoft.project.application.controller.ListGSTasksController;
import pt.ipp.isep.dei.esoft.project.domain.GSTask;

public class ListTasksUI implements Runnable {

    private ListGSTasksController listGSTasksController = new ListGSTasksController();

    @Override
    public void run() {
        try {
            for (GSTask gsTask : listGSTasksController.getTasksForLoggedCollaborator()){
                System.out.println("Title: " + gsTask.getTitle());
                System.out.println("  Description: " + gsTask.getDescription());
                System.out.println("  Status: " + gsTask.getStatus());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
