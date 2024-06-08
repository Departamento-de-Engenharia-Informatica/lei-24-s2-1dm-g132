package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;
import pt.ipp.isep.dei.esoft.project.repository.TeamRepository;
import pt.ipp.isep.dei.esoft.project.repository.serialization.TeamRepositoryFile;

import java.util.List;
import java.util.Optional;

/**
 * Controller class responsible for handling operations related to generating team proposals.
 */
public class GenerateTeamController {

    /**
     * The collaborator repository needed for the operations of this class.
     */
    private CollaboratorRepository collaboratorRepository;

    /**
     * The skill repository needed for the operations of this class.
     */
    private SkillRepository skillRepository;

    /**
     * The team repository needed for the operations of this class.
     */
    private TeamRepository teamRepository;

    /**
     * The file instance used to manage the serialization of the team repository.
     */
    private TeamRepositoryFile teamRepositoryFile;

    /**
     * Constructs a new GenerateTeamController object.
     */
    public GenerateTeamController(){
        getCollaboratorRepository();
        getSkillRepository();
        getTeamRepository();
        teamRepositoryFile = new TeamRepositoryFile();
    }

    /**
     * Retrieves the collaborator repository instance.
     *
     * @return The CollaboratorRepository object.
     */
    private CollaboratorRepository getCollaboratorRepository() {
        if (collaboratorRepository == null) {
            Repositories repositories = Repositories.getInstance();
            collaboratorRepository = repositories.getCollaboratorRepository();
        }
        return collaboratorRepository;
    }

    /**
     * Retrieves the skill repository instance.
     *
     * @return The SkillRepository object.
     */
    private SkillRepository getSkillRepository() {
        if (skillRepository == null) {
            Repositories repositories = Repositories.getInstance();
            skillRepository = repositories.getSkillRepository();
        }
        return skillRepository;
    }

    /**
     * Retrieves the team repository instance.
     *
     * @return The TeamRepository object.
     */
    private TeamRepository getTeamRepository() {
        if (teamRepository == null) {
            Repositories repositories = Repositories.getInstance();
            teamRepository = repositories.getTeamRepository();
        }
        return teamRepository;
    }

    /**
     * Registers a new team based on the provided team proposal.
     *
     * @param teamProposal The list of collaborators proposed for the team.
     * @return An Optional containing the newly registered team if successful, otherwise empty.
     */
    public Optional<Team> registerTeam(List<Collaborator> teamProposal) {


        teamRepository = getTeamRepository();

        Optional<Team> newTeam = Optional.empty();


        newTeam = teamRepository.registerTeam(teamProposal);

        if (newTeam.isPresent())
        {
            if(!teamRepositoryFile.save(teamRepository))
            {
                System.out.println("Error while saving Collaborator Repository in external file!");
            }
        }

        return newTeam;
    }

    /**
     * Retrieves the available skills.
     *
     * @return The list of available skills.
     */
    public List<Skill> getSkills() {
        SkillRepository skillRepository = getSkillRepository();
        skillRepository.createSelectedSkillsList();
        return skillRepository.getSkills();
    }

    /**
     * Adds a selected skill name to the skill repository.
     *
     * @param name The name of the selected skill.
     */
    public void addSelectedSkillName(String name){
        skillRepository.addSelectedSkill(name);
    }

    /**
     * Generates a team proposal based on the specified criteria.
     *
     * @param minTeamSize The minimum team size.
     * @param maxTeamSize The maximum team size.
     * @return The list of collaborators proposed for the team.
     */
    public List<Collaborator> generateTeamProposal(int minTeamSize, int maxTeamSize){
        List<Skill> selectedSkillsList = skillRepository.getSelectedSkillsList();

        createAvailableCollaboratorsList();

        List<Collaborator> teamProposal = selectCollaboratorsForTeam(selectedSkillsList, minTeamSize, maxTeamSize);

        return teamProposal;
    }

    /**
     * Creates a list of available collaborators by removing unavailable collaborators from the team repository.
     */
    private void createAvailableCollaboratorsList(){
        List<Collaborator> tempCollaboratorsList = collaboratorRepository.createTempCollaboratorsList();

        teamRepository.removeUnavailableCollaborators(tempCollaboratorsList);
    }

    /**
     * Selects collaborators for the team based on the provided skills and team size criteria.
     *
     * @param selectedSkillsList The list of selected skills.
     * @param minTeamSize The minimum team size.
     * @param maxTeamSize The maximum team size.
     * @return The list of collaborators selected for the team.
     */
    private List<Collaborator> selectCollaboratorsForTeam(List<Skill> selectedSkillsList, int minTeamSize, int maxTeamSize){
        return collaboratorRepository.generateTeam(selectedSkillsList, minTeamSize, maxTeamSize);
    }
}
