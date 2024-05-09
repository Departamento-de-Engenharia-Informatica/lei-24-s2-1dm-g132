/*
package pt.ipp.isep.dei.esoft.project.ui.console;


import pt.ipp.isep.dei.esoft.project.application.controller.RegisterSkillController;
import pt.ipp.isep.dei.esoft.project.domain.Skill;


import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class RegisterSkillUI implements Runnable{
    private String name;

    private final RegisterSkillController controller;



    public RegisterSkillUI() {
        controller= new RegisterSkillController();

    }

    public void run() {
        System.out.println("\n\n--- Register Skill ------------------------");

        name = displayAndSelectSkill();

        requestData();
        submitData();
    }


    private void submitData() {
        Optional<Skill> skill = controller.registerSkill(name);

        if (skill.isPresent()) {
            System.out.println("\nSkill successfully created!");
        } else {
            System.out.println("\nSkill not created!");
        }
    }


    private void requestData(){
        name = RequestSkillName();
    }



    private String RequestSkillName() {
        Scanner input = new Scanner(System.in);
        System.out.print("Name: ");
        return input.nextLine();
    }
    private String displayAndSelectSkill() {
        //Display the list of skills
        List<Skill> skills = controller.getSkills();

        int listSize = skills.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {
            displayAndSelectSkill();
            System.out.print("Select a Skill: ");
            answer = input.nextInt();
        }

        String description = skills.get(answer - 1).getName();
        return description;
    }
    private void displaySkillsOptions(List<Skill> skills) {
        //display the skills as a menu with number options to select
        int i = 1;
        for (Skill skill : skills) {
            System.out.println("  " + i + " - " + skill.getName());
            i++;
        }
    }






}
*/
package pt.ipp.isep.dei.esoft.project.ui.console;


import pt.ipp.isep.dei.esoft.project.application.controller.RegisterSkillController;
import pt.ipp.isep.dei.esoft.project.domain.Skill;


import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class RegisterSkillUI implements Runnable{
    private String name;

    private final RegisterSkillController controller;



    public RegisterSkillUI() {
        controller= new RegisterSkillController();

    }

    public void run() {
        System.out.println("\n\n--- Register Skill ------------------------");

        requestData();
        submitData();
    }


    private void submitData() {
        Skill skill = controller.registerSkill(name);

        if (skill != null) {
            System.out.println("\nSkill successfully created!");
        } else {
            System.out.println("\nSkill not created!");
        }
    }


    private void requestData(){
        name = RequestSkillName();
    }



    private String RequestSkillName() {
        Scanner input = new Scanner(System.in);
        System.out.print("Name: ");
        return input.nextLine();
    }

}

