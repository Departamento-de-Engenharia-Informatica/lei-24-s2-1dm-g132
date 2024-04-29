package pt.ipp.isep.dei.esoft.project.ui.console.utils;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterCollaboratorController;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterJobController;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.repository.JobRepository;

import java.util.Scanner;

public class RegisterJobUI {
    private String name;

    private final RegisterJobController controller;



    public RegisterJobUI() {
        controller= new RegisterJobController();

    }

    public void run() {
        System.out.println("\n\n--- Register Job ------------------------");
        requestDataforJobName();
        submitData();
    }

    private void submitData() {
        Job newJob = controller.registerJob(name);
        System.out.println("\nJob successfully registered!");
    }

    private void requestDataforJobName() {
        System.out.println("Job name: ");
    }

    private void requestData(){
        name = RequestJobName();
    }
    private String RequestJobName() {
        Scanner input = new Scanner(System.in);
        System.out.print("Name: ");
        return input.nextLine();
    }

}

