package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.AssignSkillsController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * UI class responsible for assigning skills to collaborator through console interaction.
 */
public class AssignSkillsUI implements Runnable{

    /**
     * The controller associated with this UI.
     */
    private final AssignSkillsController controller;

    /**
     * The collaborator identification number inputted by the user.
     */
    private String collaboratorIdNumber;

    /**
     * Constructs a new AssignSkillsUI object.
     */
    public AssignSkillsUI() {
        controller = new AssignSkillsController();
    }

    /**
     * Retrieves the controller associated with this UI.
     *
     * @return The AssignSkillsController object.
     */
    private AssignSkillsController getController(){
        return controller;
    }

    /**
     * Executes the process of assigning skills to a collaborator.
     */
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
            displayAndSelectSkills(collaboratorIdNumber);
        }
        catch (RuntimeException e){
            System.out.println("\nERROR: " + e.getMessage());
            return;
        }

        try {
            assignSkills();
        }catch (RuntimeException e){
            System.out.println("\nERROR: " + e.getMessage());
        }

    }

    /**
     * Assigns skills to a collaborator based on the provided collaborator identification number.
     */
    private void assignSkills(){
        Optional<Collaborator> collaborator = getController().assignSkills(collaboratorIdNumber);

        if (collaborator.isPresent()) {
            System.out.println("\nSkills successfully assigned!");
        } else {
            System.out.println("\nSkills not assigned successfully!");
        }
    }

    /**
     * Displays the available skills and allows the user to select skills to assign.
     */
    private void displayAndSelectSkills(String collaboratorIdNumber){
        boolean dadosInvalidos;
        boolean cont = true;

        List<Skill> skills = controller.getSkills(collaboratorIdNumber);

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
                skills.remove(answer -1);
            }
            else{
                cont = false;
            }

            answer = -1;

        }while(cont);
    }

    /**
     * Displays the list of available collaborators and prompts the user to select one.
     *
     * @return The identification document number of the selected collaborator.
     */
    private String displayAndSelectCollaborator() {
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

    /**
     * Displays the available skills as a numbered menu.
     *
     * @param skills The list of available jobs.
     */
    private void displaySkillOptions(List<Skill> skills) {
        int i = 1;
        for (Skill skill : skills) {
            System.out.println("  " + i + " - " + skill.getName());
            i++;
        }
    }

    /**
     * Displays the available collaborators as a numbered menu.
     *
     * @param collaborators The list of available jobs.
     */
    private void displayCollaboratorOptions(List<Collaborator> collaborators) {
        int i = 1;
        for (Collaborator collaborator : collaborators) {
            System.out.println("  " + i + " - " + collaborator.getName() + " : " + collaborator.getIdentificationDocumentNumber());
            i++;
        }
    }
}
