package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.GenerateTeamController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * UI class responsible for generating a team proposal through console interaction.
 */
public class GenerateTeamUI implements Runnable{

    /**
     * The controller associated with this UI.
     */
    private final GenerateTeamController controller;

    /**
     * The minimum and maximum team size inputted by the user.
     */
    private int minTeamSize, maxTeamSize;

    /**
     * The team proposal generated by the application.
     */
    private List<Collaborator> teamProposal;

    /**
     * Constructs a new GenerateTeamUI object.
     */
    public GenerateTeamUI(){
        controller = new GenerateTeamController();
    }

    /**
     * Retrieves the controller associated with this UI.
     *
     * @return The GenerateTeamController object.
     */
    private GenerateTeamController getController(){
        return controller;
    }

    /**
     * Executes the process of generating a team proposal and creating it if accepted.
     */
    public void run() {
        boolean dadosInvalidos = true;

        System.out.println("\n\n--- Generate Team Proposal ------------------------");

        do{
            try{
                minTeamSize = requestMinTeamSize();
                maxTeamSize = requestMaxTeamSize();
            }catch (InputMismatchException e){
                System.out.println("\nERROR: " + "Invalid input value.\n");
            }
            catch (RuntimeException e){
                System.out.println("\nERROR: " + e.getMessage());
                return;
            }

            if(minTeamSize <= maxTeamSize){
                dadosInvalidos = false;
            }
            else{
                System.out.println("ERROR: Minimum team size can't be lower than maximum team size.\n");
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
            generateTeamProposal();
        }catch (IllegalArgumentException e){
            System.out.println("\nERROR: " + e.getMessage());
            return;
        }

        if(teamProposalAnswer())
        {
            submitData();
        }
    }

    /**
     * Submits the team proposal data (registering the team).
     */
    private void submitData() {
        Optional<Team> team = getController().registerTeam(teamProposal);

        if (team.isPresent()) {
            System.out.println("\nTeam successfully created!");
        } else {
            System.out.println("\nTeam not created successfully!");
        }
    }

    /**
     * Generates the team proposal.
     */
    private void generateTeamProposal(){
        teamProposal = getController().generateTeamProposal(minTeamSize, maxTeamSize);
    }

    /**
     * Prompts the user to accept or reject the team proposal.
     *
     * @return True if the user accepts the team proposal, otherwise false.
     */
    private boolean teamProposalAnswer() {
        System.out.println("\nTeam Proposal:");
        for(Collaborator collaborator : teamProposal)
        {
            System.out.println(collaborator.toStringTeam());
        }
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.print("Accept team proposal? (y or n): ");
            String answer = input.nextLine().trim().toLowerCase();
            if (answer.equals("y")) {
                return true;
            } else if (answer.equals("n")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter 'y' or 'n'.");
            }
        }
    }

    /**
     * Requests the minimum team size from the user.
     *
     * @return The minimum team size entered by the user.
     */
    private int requestMinTeamSize(){
        Scanner input = new Scanner(System.in);
        System.out.print("Minimum Team Size: ");
        return input.nextInt();
    }

    /**
     * Requests the maximum team size from the user.
     *
     * @return The maximum team size entered by the user.
     */
    private int requestMaxTeamSize(){
        Scanner input = new Scanner(System.in);
        System.out.print("Maximum Team Size: ");
        return input.nextInt();
    }

    /**
     * Displays the available skills and prompts the user to select skills for the team.
     */
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
}
