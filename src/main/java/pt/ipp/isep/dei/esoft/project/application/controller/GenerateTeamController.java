package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;
import pt.ipp.isep.dei.esoft.project.repository.TeamRepository;

import java.util.List;
import java.util.Optional;

public class GenerateTeamController {
    private CollaboratorRepository collaboratorRepository;
    private SkillRepository skillRepository;
    private TeamRepository teamRepository;

    public GenerateTeamController(){
        getCollaboratorRepository();
        getSkillRepository();
        getTeamRepository();
    }

    private CollaboratorRepository getCollaboratorRepository() {
        if (collaboratorRepository == null) {
            Repositories repositories = Repositories.getInstance();
            collaboratorRepository = repositories.getCollaboratorRepository();
        }
        return collaboratorRepository;
    }

    private SkillRepository getSkillRepository() {
        if (skillRepository == null) {
            Repositories repositories = Repositories.getInstance();
            skillRepository = repositories.getSkillRepository();
        }
        return skillRepository;
    }

    private TeamRepository getTeamRepository() {
        if (teamRepository == null) {
            Repositories repositories = Repositories.getInstance();
            teamRepository = repositories.getTeamRepository();
        }
        return teamRepository;
    }

    public Optional<Team> registerTeam(List<Collaborator> teamProposal) {


        Optional<TeamRepository> teamRepository = Optional.ofNullable(getTeamRepository());

        Optional<Team> newTeam = Optional.empty();

        if (teamRepository.isPresent()) {
            newTeam = teamRepository.get()
                    .registerTeam(teamProposal);
        }
        return newTeam;
    }

    public List<Skill> getSkills() {
        SkillRepository skillRepository = getSkillRepository();
        skillRepository.createSelectedSkillsList();
        return skillRepository.getSkills();
    }

    public void addSelectedSkillName(String name){
        skillRepository.addSelectedSkill(name);
    }

    public List<Collaborator> generateTeamProposal(int minTeamSize, int maxTeamSize){
        List<Skill> selectedSkillsList = skillRepository.getSelectedSkillsList();

        createAvailableCollaboratorsList();

        List<Collaborator> teamProposal = selectCollaboratorsForTeam(selectedSkillsList, minTeamSize, maxTeamSize);

        return teamProposal;
    }

    private void createAvailableCollaboratorsList(){
        List<Collaborator> tempCollaboratorsList = collaboratorRepository.createTempCollaboratorsList();

        teamRepository.removeUnavailableCollaborators(tempCollaboratorsList);
    }

    private List<Collaborator> selectCollaboratorsForTeam(List<Skill> selectedSkillsList, int minTeamSize, int maxTeamSize){
        return collaboratorRepository.generateTeam(selectedSkillsList, minTeamSize, maxTeamSize);
    }
}
