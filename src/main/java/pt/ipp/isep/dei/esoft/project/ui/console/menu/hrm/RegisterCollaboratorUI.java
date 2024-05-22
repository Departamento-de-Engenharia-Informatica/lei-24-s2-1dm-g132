package pt.ipp.isep.dei.esoft.project.ui.console.menu.hrm;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterCollaboratorController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Job;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * UI class responsible for registering collaborators through console interaction.
 */
public class RegisterCollaboratorUI implements Runnable {

    /**
     * The controller associated with this UI.
     */
    private final RegisterCollaboratorController controller;

    /**
     * The collaborator name inputted by the user.
     */
    private String collaboratorName;

    /**
     * The collaborator birthdate inputted by the user.
     */
    private String collaboratorBirthdate;

    /**
     * The collaborator admission date inputted by the user.
     */
    private String collaboratorAdmissionDate;

    /**
     * The collaborator address inputted by the user.
     */
    private String collaboratorAddress;

    /**
     * The collaborator phone number inputted by teh user.
     */
    private int collaboratorPhoneNumber;

    /**
     * The collaborator email inputted by the user.
     */
    private String collaboratorEmail;

    /**
     * The collaborator taxpayer number inputted by the user.
     */
    private int collaboratorTaxpayerNumber;

    /**
     * The collaborator identification document type inputted by the user.
     */
    private String collaboratorIdentificationDocumentType;

    /**
     * The collaborator identification document number inputted by the user.
     */
    private String collaboratorIdentificationDocumentNumber;

    /**
     * The collaborator job name inputted by the user.
     */
    private String jobName;

    /**
     * Constructs a new RegisterCollaboratorUI object.
     */
    public RegisterCollaboratorUI() {
        controller = new RegisterCollaboratorController();
    }

    /**
     * Retrieves the controller associated with this UI.
     *
     * @return The RegisterCollaboratorController object.
     */
    private RegisterCollaboratorController getController(){
        return controller;
    }

    /**
     * Executes the process of registering a collaborator.
     */
    public void run() {
        boolean dadosInvalidos1 = true;
        boolean dadosInvalidos2 = true;

        System.out.println("\n\n--- Register Collaborator ------------------------");

        do{
            try {
                jobName = displayAndSelectJob();
                dadosInvalidos1 = false;

            } catch (InputMismatchException e){
                System.out.println("\nERROR: " + "Invalid input value.\n");
            }
            catch (RuntimeException e){
                System.out.println("\nERROR: " + e.getMessage());
                return;
            }
        }while (dadosInvalidos1);

        do {
            try {
                requestData();
                submitData();
                dadosInvalidos2 = false;
            }
            catch (IllegalArgumentException e){
                System.out.println("\nERROR: " + e.getMessage());
            }
            catch (InputMismatchException e){
                System.out.println("\nERROR: " + "Invalid input value.\n");
            }
        }while (dadosInvalidos2);
    }

    /**
     * Submits the collected data for collaborator registration.
     */
    private void submitData() {
        Optional<Collaborator> collaborator = getController().registerCollaborator(collaboratorName, collaboratorBirthdate, collaboratorAdmissionDate,
                collaboratorAddress, collaboratorPhoneNumber, collaboratorEmail, collaboratorTaxpayerNumber, collaboratorIdentificationDocumentType, collaboratorIdentificationDocumentNumber, jobName);

        if (collaborator.isPresent()) {
            System.out.println("\nCollaborator successfully registered!");
        } else {
            System.out.println("\nCollaborator not registered successfully!");
        }
    }

    /**
     * Requests data from the user for collaborator registration.
     */
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

    /**
     * Requests the name of the collaborator from the user.
     *
     * @return The collaborator name inputted by the user.
     */
    private String requestCollaboratorName() {
        Scanner input = new Scanner(System.in);
        System.out.print("Collaborator Name: ");
        return input.nextLine();
    }

    /**
     * Requests the birthdate of the collaborator from the user.
     *
     * @return The collaborator birthdate inputted by the user.
     */
    private String requestCollaboratorBirthdate() {
        Scanner input = new Scanner(System.in);
        System.out.print("Collaborator Birthdate (year/month/day): ");
        return input.nextLine();
    }

    /**
     * Requests the admission date of the collaborator from the user.
     *
     * @return The collaborator admission date inputted by the user.
     */
    private String requestCollaboratorAdmissionDate() {
        Scanner input = new Scanner(System.in);
        System.out.print("Collaborator Admission Date (year/month/day): ");
        return input.nextLine();
    }

    /**
     * Requests the address of the collaborator from the user.
     *
     * @return The collaborator address inputted by the user.
     */
    private String requestCollaboratorAddress() {
        Scanner input = new Scanner(System.in);
        System.out.print("Collaborator Address (street, zipcode, city): ");
        return input.nextLine();
    }

    /**
     * Requests the phone number of the collaborator from the user.
     *
     * @return The collaborator phone number inputted by the user.
     */
    private int requestCollaboratorPhoneNumber() {
        Scanner input = new Scanner(System.in);
        System.out.print("Collaborator Phone Number: ");
        return input.nextInt();
    }

    /**
     * Requests the email of the collaborator from the user.
     *
     * @return The collaborator email inputted by the user.
     */
    private String requestCollaboratorEmail() {
        Scanner input = new Scanner(System.in);
        System.out.print("Collaborator Email: ");
        return input.nextLine();
    }

    /**
     * Requests the taxpayer number of the collaborator from the user.
     *
     * @return The collaborator taxpayer number inputted by the user.
     */
    private int requestCollaboratorTaxpayerNumber() {
        Scanner input = new Scanner(System.in);
        System.out.print("Collaborator Taxpayer Number: ");
        return input.nextInt();
    }

    /**
     * Requests the identification document type of the collaborator from the user.
     *
     * @return The collaborator identification document type inputted by the user.
     */
    private String requestCollaboratorIdentificationDocumentType() {
        Scanner input = new Scanner(System.in);
        System.out.print("Collaborator Identification Document Type (CC, BI or Passport): ");
        return input.nextLine();
    }

    /**
     * Requests the identification document number of the collaborator from the user.
     *
     * @return The collaborator identification document number inputted by the user.
     */
    private String requestCollaboratorIdentificationDocumentNumber() {
        Scanner input = new Scanner(System.in);
        System.out.print("Collaborator Identification Document Number: ");
        return input.nextLine();
    }

    /**
     * Displays the list of available jobs and prompts the user to select one.
     *
     * @return The name of the selected job.
     */
    private String displayAndSelectJob() {
        List<Job> jobs = controller.getJobs();

        int listSize = jobs.size();

        if(listSize == 0)
            throw new RuntimeException("There are no jobs to display at the moment.");

        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {
            displayJobOptions(jobs);
            System.out.print("Select a job: ");
            answer = input.nextInt();
        }

        String name = jobs.get(answer - 1).getJobName();
        return name;
    }

    /**
     * Displays the available jobs as a numbered menu.
     *
     * @param jobs The list of available jobs.
     */
    private void displayJobOptions(List<Job> jobs) {
        int i = 1;
        for (Job job : jobs) {
            System.out.println("  " + i + " - " + job.getJobName());
            i++;
        }
    }
}
