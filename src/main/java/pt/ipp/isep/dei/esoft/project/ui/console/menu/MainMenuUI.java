package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.DevTeamUI;
import pt.ipp.isep.dei.esoft.project.ui.console.authorization.AuthenticationUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Paulo Maio pam@isep.ipp.pt
 */
public class MainMenuUI implements Runnable {

    public MainMenuUI() {
    }

    public void run() {
        testJob(); // Test the Job class
        testSkill(); // Test the Skill class

        printJobRepository(); // Print the content of the Job repository
        printSkillRepository(); // Print the content of the Skill repository

        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Do Login", new AuthenticationUI()));
        options.add(new MenuItem("Know the Development Team", new DevTeamUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- MAIN MENU --------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);

    }

    private void testJob() {
        // Test the Job class
        String jobName = "Podador";
        Job job = new Job(jobName);
        Repositories.getInstance().getJobRepository().add(job);
        System.out.println("Job Name: " + job.getName());
    }

    private void testSkill() {
        // Test the Skill class
        String skillName = "Especialista de pesados";
        Skill skill = new Skill(skillName);
        Repositories.getInstance().getSkillRepository().add(skill);
        System.out.println("Skill Name: " + skill.getName());
    }
    private void printJobRepository() {
        List<Job> jobs = Repositories.getInstance().getJobRepository().getJobs();
        System.out.println("Jobs in repository:");
        for (Job job : jobs) {
            System.out.println(job.getName());
        }
    }

    private void printSkillRepository() {
        List<Skill> skills = Repositories.getInstance().getSkillRepository().getSkills();
        System.out.println("Skills in repository:");
        for (Skill skill : skills) {
            System.out.println(skill.getName());
        }
    }
}