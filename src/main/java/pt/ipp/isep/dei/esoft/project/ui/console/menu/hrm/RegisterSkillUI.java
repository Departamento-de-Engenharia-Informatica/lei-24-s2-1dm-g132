
/**

 This class represents the user interface for registering a new skill.
 It interacts with the user to input skill details, submits the data to the controller,
 and displays the list of skills in the repository.
 */
package pt.ipp.isep.dei.esoft.project.ui.console.menu.hrm;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterSkillController;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import java.lang.IllegalArgumentException;

import java.util.List;
import java.util.Scanner;

public class RegisterSkillUI implements Runnable {
    private String name;
    private final RegisterSkillController controller;

    /**
     * Constructs a new RegisterSkillUI object.
     */
    public RegisterSkillUI() {
        controller = new RegisterSkillController();
    }

    /**
     * Runs the Register Skill user interface.
     */
    public void run() {
        System.out.println("\n\n--- Register Skill ------------------------");
        requestData();
        submitData();
    }

    /**
     * Submits the skill data to the controller for registration.
     * Displays a success message if the skill is successfully created,
     * or an error message if not.
     */
    private void submitData() {
        try {
            Skill skill = controller.registerSkill(name);
            if (skill != null) {
                System.out.println("\nSkill successfully created!");
            } else {
                System.out.println("\nSkill not created!");
            }
        } catch(IllegalArgumentException e){
            System.out.println("\nERROR: " + e.getMessage());
        }
    }

    /**
     * Displays the list of skills in the repository.
     */
    private void displaySkills() {
        System.out.println("\nSkills in repository:");
        List<Skill> skills = controller.getSkills();
        for (Skill skill : skills) {
            System.out.println(skill.toString());
        }
    }

    private void requestData(){
        name = RequestSkillName();
    }


    /**
     * Requests the skill name input from the user.
     * @return The inputted skill name.
     */
    private String RequestSkillName() {
        Scanner input = new Scanner(System.in);
        System.out.print("Name: ");
        return input.nextLine();
    }
}


