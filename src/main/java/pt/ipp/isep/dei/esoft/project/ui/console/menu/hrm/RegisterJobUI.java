
/**

 This class represents the user interface for registering a new job.
 It interacts with the user to input job details, submits the data to the controller,
 and displays the list of jobs in the repository.
 */
package pt.ipp.isep.dei.esoft.project.ui.console.menu.hrm;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterJobController;
import pt.ipp.isep.dei.esoft.project.domain.Job;

import java.lang.IllegalArgumentException;

import java.util.List;
import java.util.Scanner;

public class RegisterJobUI implements Runnable {
    private String Jobname;
    private final RegisterJobController controller;

    /**
     * Constructs a new RegisterJobUI object.
     */
    public RegisterJobUI() {
        controller = new RegisterJobController();
    }

    /**
     * Runs the Register Job user interface.
     */
    public void run() {
        System.out.println("\n\n--- Register Job ------------------------");
        requestData();
        submitData();
    }

    /**
     * Submits the job data to the controller for registration.
     * Displays a success message if the job is successfully created,
     * or an error message if not.
     */
    private void submitData() {

        try {
            Job job = controller.registerJob(Jobname);
            if (job != null) {
                System.out.println("\nJob successfully created!");
            } else {
                System.out.println("\nJob not created!");
            }
        } catch(IllegalArgumentException e){
            System.out.println("\nERROR: " + e.getMessage());
        }
    }

    /**
     * Displays the list of jobs in the repository.
     */
    private void displayJobs() {
        System.out.println("\nJobs in repository:");
        List<Job> jobs = controller.getJobs();
        for (Job job : jobs) {
            System.out.println(job.toString());
        }
    }

    private void requestData(){
        Jobname = requestJobName();
    }

    /**
     * Requests the job name input from the user.
     * @return The inputted job name.
     */
    private String requestJobName() {
        Scanner input = new Scanner(System.in);
        System.out.print("Job Name: ");
        return input.nextLine();
    }
}