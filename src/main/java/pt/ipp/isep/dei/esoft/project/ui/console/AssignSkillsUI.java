package pt.ipp.isep.dei.esoft.project.ui.console;


import pt.ipp.isep.dei.esoft.project.application.controller.AssignSkillsController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;

import java.util.List;
import java.util.Scanner;

public class AssignSkillsUI implements Runnable{
    private final AssignSkillsController controller;

    private int collaboratorIdNumber;

    public AssignSkillsUI() {
        controller = new AssignSkillsController();
    }

    private AssignSkillsController getController(){
        return controller;
    }

    public void run() {
        System.out.println("\n\n--- Assign Skills to Collaborator ------------------------");

        collaboratorIdNumber = displayAndSelectCollaborator();

    }


    private int displayAndSelectCollaborator() {
        //Display the list of jobs
        List<Collaborator> collaborators = controller.getCollaborators();

        int listSize = collaborators.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {
            displayCollaboratorOptions(collaborators);
            System.out.print("Select a collaborator: ");
            answer = input.nextInt();
        }

        int idNumber = collaborators.get(answer - 1).getIdentificationDocumentNumber();
        return idNumber;
    }

    private void displayCollaboratorOptions(List<Collaborator> collaborators) {
        //display the collaborators as a menu with number options to select
        int i = 1;
        for (Collaborator collaborator : collaborators) {
            System.out.println("  " + i + " - " + collaborator.getName() + ":" + collaborator.getIdentificationDocumentNumber());
            i++;
        }
    }
}
