package pt.ipp.isep.dei.esoft.project.ui.console;


import pt.ipp.isep.dei.esoft.project.application.controller.AssignSkillsController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class AssignSkillsUI implements Runnable{
    private final AssignSkillsController controller;

    private String collaboratorIdNumber;

    public AssignSkillsUI() {
        controller = new AssignSkillsController();
    }

    private AssignSkillsController getController(){
        return controller;
    }

    public void run() {
        boolean dadosInvalidos = true;

        System.out.println("\n\n--- Assign Skills to Collaborator ------------------------");

        do{
            try {
                collaboratorIdNumber = displayAndSelectCollaborator();
                dadosInvalidos = false;

            }catch (InputMismatchException e){
                System.out.println("\nERROR: " + "Invalid input value.\n");
            }
            catch (RuntimeException e){
                System.out.println("\nERROR: " + e.getMessage());
                return;
            }
        }while(dadosInvalidos);

        try {
            displayAndSelectSkills();
        }
        catch (RuntimeException e){
            System.out.println("\nERROR: " + e.getMessage());
            return;
        }

        try {
            assignSkills();
        }catch (IllegalArgumentException e){
            System.out.println("\nERROR: " + e.getMessage());
        }
        catch (RuntimeException e){
            System.out.println("\nERROR: " + e.getMessage());
        }

    }

    private void assignSkills(){
        Optional<Collaborator> collaborator = getController().assignSkills(collaboratorIdNumber);

        if (collaborator.isPresent()) {
            System.out.println("\nSkills successfully assigned!");
        } else {
            System.out.println("\nSkills not assigned successfully!");
        }
    }

    private void displayAndSelectSkills(){
        boolean dadosInvalidos;
        boolean cont = true;

        List<Skill> skills = controller.getSkills();

        int listSize = skills.size();

        if(listSize == 0)
            throw new RuntimeException("There are no skills to display at the moment.");

        int answer = -1;

        Scanner input = new Scanner(System.in);

        do {
            while (answer < 0 || answer > listSize) {
                dadosInvalidos = true;
                displaySkillOptions(skills);
                do {
                    System.out.print("Select a skill (press 0 to stop): ");
                    try {
                        answer = input.nextInt();
                        dadosInvalidos = false;
                    } catch (InputMismatchException e) {
                        System.out.println("ERROR: " + "Invalid input value.\n");
                    }
                }while(dadosInvalidos);
            }

            if(answer != 0)
            {
                String name = skills.get(answer - 1).getName();
                controller.addSelectedSkillName(name);
            }
            else{
                cont = false;
            }

            answer = -1;

        }while(cont);
    }

    private String displayAndSelectCollaborator() {
        //Display the list of jobs
        List<Collaborator> collaborators = controller.getCollaborators();

        int listSize = collaborators.size();

        if(listSize == 0)
            throw new RuntimeException("There are no collaborators to display at the moment.");

        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {
            displayCollaboratorOptions(collaborators);
            System.out.print("Select a collaborator: ");
            answer = input.nextInt();
        }

        String idNumber = collaborators.get(answer - 1).getIdentificationDocumentNumber();
        return idNumber;
    }

    private void displaySkillOptions(List<Skill> skills) {
        //display the skills as a menu with number options to select
        int i = 1;
        for (Skill skill : skills) {
            System.out.println("  " + i + " - " + skill.getName());
            i++;
        }
    }

    private void displayCollaboratorOptions(List<Collaborator> collaborators) {
        //display the collaborators as a menu with number options to select
        int i = 1;
        for (Collaborator collaborator : collaborators) {
            System.out.println("  " + i + " - " + collaborator.getName() + " : " + collaborator.getIdentificationDocumentNumber());
            i++;
        }
    }
}
