package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterCollaboratorController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Job;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class RegisterCollaboratorUI implements Runnable {

    private final RegisterCollaboratorController controller;
    private String collaboratorName;
    private String collaboratorBirthdate;
    private String collaboratorAdmissionDate;
    private String collaboratorAddress;
    private int collaboratorPhoneNumber;
    private String collaboratorEmail;
    private int collaboratorTaxpayerNumber;
    private String collaboratorIdentificationDocumentType;
    private String collaboratorIdentificationDocumentNumber;
    private String jobName;

    public RegisterCollaboratorUI() {
        controller = new RegisterCollaboratorController();
    }

    private RegisterCollaboratorController getController(){
        return controller;
    }

    public void run() {
        System.out.println("\n\n--- Register Collaborator ------------------------");

        jobName = displayAndSelectJob();

        requestData();

        submitData();
    }

    private void submitData() {
        Optional<Collaborator> collaborator = getController().registerCollaborator(collaboratorName, collaboratorBirthdate, collaboratorAdmissionDate,
                collaboratorAddress, collaboratorPhoneNumber, collaboratorEmail, collaboratorTaxpayerNumber, collaboratorIdentificationDocumentType, collaboratorIdentificationDocumentNumber, jobName);

        if (collaborator.isPresent()) {
            System.out.println("\nCollaborator successfully registered!");
        } else {
            System.out.println("\nCollaborator not registered successfully!");
        }
    }

    private void requestData() {

        collaboratorName = requestCollaboratorName();

        collaboratorBirthdate = requestCollaboratorBirthdate();

        collaboratorAdmissionDate = requestCollaboratorAdmissionDate();

        collaboratorAddress = requestCollaboratorAddress();

        collaboratorPhoneNumber = requestCollaboratorPhoneNumber();

        collaboratorEmail = requestCollaboratorEmail();

        collaboratorTaxpayerNumber = requestCollaboratorTaxpayerNumber();

        collaboratorIdentificationDocumentType = requestCollaboratorIdentificationDocumentType();

        collaboratorIdentificationDocumentNumber = requestCollaboratorIdentificationDocumentNumber();
    }

    private String requestCollaboratorName() {
        Scanner input = new Scanner(System.in);
        System.out.print("Collaborator Name: ");
        return input.nextLine();
    }

    private String requestCollaboratorBirthdate() {
        Scanner input = new Scanner(System.in);
        System.out.print("Collaborator Birthdate: ");
        return input.nextLine();
    }

    private String requestCollaboratorAdmissionDate() {
        Scanner input = new Scanner(System.in);
        System.out.print("Collaborator Admission Date: ");
        return input.nextLine();
    }

    private String requestCollaboratorAddress() {
        Scanner input = new Scanner(System.in);
        System.out.print("Collaborator Address: ");
        return input.nextLine();
    }

    private int requestCollaboratorPhoneNumber() {
        Scanner input = new Scanner(System.in);
        System.out.print("Collaborator Phone Number: ");
        return input.nextInt();
    }

    private String requestCollaboratorEmail() {
        Scanner input = new Scanner(System.in);
        System.out.print("Collaborator Email: ");
        return input.nextLine();
    }

    private int requestCollaboratorTaxpayerNumber() {
        Scanner input = new Scanner(System.in);
        System.out.print("Collaborator Taxpayer Number: ");
        return input.nextInt();
    }

    private String requestCollaboratorIdentificationDocumentType() {
        Scanner input = new Scanner(System.in);
        System.out.print("Collaborator Identification Document Type (CC, BI or Passport): ");
        return input.nextLine();
    }

    private String requestCollaboratorIdentificationDocumentNumber() {
        Scanner input = new Scanner(System.in);
        System.out.print("Collaborator Identification Document Number: ");
        return input.nextLine();
    }

    private String displayAndSelectJob() {
        //Display the list of jobs
        List<Job> jobs = controller.getJobs();

        int listSize = jobs.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {
            displayJobOptions(jobs);
            System.out.print("Select a job: ");
            answer = input.nextInt();
        }

        String name = jobs.get(answer - 1).getName();
        return name;
    }

    private void displayJobOptions(List<Job> jobs) {
        //display the jobs as a menu with number options to select
        int i = 1;
        for (Job job : jobs) {
            System.out.println("  " + i + " - " + job.getName());
            i++;
        }
    }
}
