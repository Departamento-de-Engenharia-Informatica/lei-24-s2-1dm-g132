package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterJobController;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.repository.JobRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class RegisterJobUI implements Runnable {
    private String Jobname;

    private final RegisterJobController controller;


    public RegisterJobUI() {

        controller = new RegisterJobController();

    }
    private RegisterJobController controller() {
        return controller;
    }


    public void run() {
        System.out.println("\n\n--- Register Job ------------------------");
        Jobname = displayAndSelectJob();

        requestData();
        submitData();
    }

    private void submitData() {
        Optional<Job> job = controller().registerJob(Jobname);
        if (job.isPresent()) {
            System.out.println("\nJob successfully created!");
        } else {
            System.out.println("\nJob not created!");
        }

    }
    private void requestData() {
        Jobname = requestJobName();
    }



    private String requestJobName() {
        Scanner input = new Scanner(System.in);
        System.out.print("Job Name: ");
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
        String name = jobs.get(answer - 1).getJobName();
        return name;
    }

    private void displayJobOptions(List<Job> jobs) {
        //display the jobs as a menu with number options to select
        int i = 1;
        for (Job job : jobs) {
            System.out.println("  " + i + " - " + job.getJobName());
            i++;
        }
    }




}
