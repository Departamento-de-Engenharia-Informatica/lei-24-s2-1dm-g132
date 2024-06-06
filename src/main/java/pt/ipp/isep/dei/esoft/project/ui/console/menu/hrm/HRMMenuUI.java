package pt.ipp.isep.dei.esoft.project.ui.console.menu.hrm;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MenuItem;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class HRMMenuUI implements Runnable {

    public HRMMenuUI() {

    }

    @Override
    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Register a Skill", new RegisterSkillUI()));
        options.add(new MenuItem("Register a Job", new RegisterJobUI()));
        options.add(new MenuItem("Register a Collaborator", new RegisterCollaboratorUI()));
        options.add(new MenuItem("Assign Skills to Collaborator", new AssignSkillsUI()));
        options.add(new MenuItem("Generate Team Proposal", new GenerateTeamUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- HRM Menu --------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
            //printJobRepository();
            //printSkillRepository();
            //printCollaboratorRepository();
            //printTeamRepository();
        } while (option != -1);
    }

    /*private void printJobRepository() {
        List<Job> jobs = Repositories.getInstance().getJobRepository().getJobs();
        System.out.println("Jobs in repository:");
        for (Job job : jobs) {
            System.out.println(job);
        }
    }

    private void printSkillRepository() {
        List<Skill> skills = Repositories.getInstance().getSkillRepository().getSkills();
        System.out.println("Skills in repository:");
        for (Skill skill : skills) {
            System.out.println(skill);
        }
    }

    private void printCollaboratorRepository() {
        List<Collaborator> collaborators = Repositories.getInstance().getCollaboratorRepository().getCollaborators();
        System.out.println("Collaborators in repository:");
        for (Collaborator collaborator : collaborators) {
            System.out.println(collaborator);
        }
    }

    private void printTeamRepository() {
        List<Team> teams = Repositories.getInstance().getTeamRepository().getTeams();
        System.out.println("Teams in repository:");
        for (Team team : teams) {
            System.out.println(team);
        }
    }*/
}
