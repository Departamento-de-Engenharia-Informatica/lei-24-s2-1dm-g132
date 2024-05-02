package pt.ipp.isep.dei.esoft.project.ui.console;


import pt.ipp.isep.dei.esoft.project.application.controller.RegisterSkillController;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.Scanner;

public class RegisterSkillUI {
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
        Skill newSkill = controller.registerSkill(name);
        System.out.println("\nSkill successfully registered!");
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
