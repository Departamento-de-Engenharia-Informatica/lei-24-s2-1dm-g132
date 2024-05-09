package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.GenerateTeamController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class GenerateTeamUI implements Runnable{
    private final GenerateTeamController controller;

    private int minTeamSize, maxTeamSize;

    private List<Collaborator> teamProposal;

    public GenerateTeamUI(){
        controller = new GenerateTeamController();
    }

    private GenerateTeamController getController(){
        return controller;
    }

    public void run() {
        System.out.println("\n\n--- Generate Team Proposal ------------------------");

        minTeamSize = requestMinTeamSize();
        maxTeamSize = requestMaxTeamSize();

        displayAndSelectSkills();

        generateTeamProposal();

        if(teamProposalAnswer())
        {
            submitData();
        }
    }

    private void submitData() {
        Optional<Team> team = getController().registerTeam(teamProposal);

        if (team.isPresent()) {
            System.out.println("\nTeam successfully created!");
        } else {
            System.out.println("\nTeam not created successfully!");
        }
    }

    private void generateTeamProposal(){
        teamProposal = getController().generateTeamProposal(minTeamSize, maxTeamSize);
    }

    private boolean teamProposalAnswer() {
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


    private int requestMinTeamSize(){
        Scanner input = new Scanner(System.in);
        System.out.print("Minimum Team Size: ");
        return input.nextInt();
    }

    private int requestMaxTeamSize(){
        Scanner input = new Scanner(System.in);
        System.out.print("Maximum Team Size: ");
        return input.nextInt();
    }

    private void displayAndSelectSkills(){
        boolean cont = true;

        List<Skill> skills = controller.getSkills();

        int listSize = skills.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        do {
            while (answer < 0 || answer > listSize) {
                displaySkillOptions(skills);
                System.out.print("Select a skill (press 0 to stop): ");
                answer = input.nextInt();
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

    private void displaySkillOptions(List<Skill> skills) {
        //display the skills as a menu with number options to select
        int i = 1;
        for (Skill skill : skills) {
            System.out.println("  " + i + " - " + skill.getName());
            i++;
        }
    }
}
