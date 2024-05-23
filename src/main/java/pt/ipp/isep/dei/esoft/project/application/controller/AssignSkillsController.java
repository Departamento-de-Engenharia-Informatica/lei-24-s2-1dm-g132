package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;
import pt.ipp.isep.dei.esoft.project.repository.serialization.CollaboratorRepositoryFile;

import java.util.List;
import java.util.Optional;

/**
 * Controller class responsible for handling operations related to assigning skills to collaborators.
 */
public class AssignSkillsController {

    /**
     * The collaborator repository needed for the operations of this class.
     */
    private CollaboratorRepository collaboratorRepository;

    /**
     * The skill repository needed for the operations of this class.
     */
    private SkillRepository skillRepository;

    private CollaboratorRepositoryFile collaboratorRepositoryFile;

    /**
     * Constructs a new AssignSkillsController object.
     */
    public AssignSkillsController(){
        getCollaboratorRepository();
        getSkillRepository();
        collaboratorRepositoryFile = new CollaboratorRepositoryFile();
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
     * Assigns skills to a collaborator.
     *
     * @param collaboratorIdNumber The identification number of the collaborator.
     * @return An optional containing the updated collaborator if the assignment was successful, otherwise empty.
     */
    public Optional<Collaborator> assignSkills(String collaboratorIdNumber){
        List<Skill> selectedSkillsList = getSelectedSkillsList();

        Collaborator collaborator = getCollaboratorByIdNumber(collaboratorIdNumber);

        collaboratorRepository = getCollaboratorRepository();

        Optional<Collaborator> updatedCollaborator = Optional.empty();


        updatedCollaborator = collaboratorRepository.assignSkill(collaborator, selectedSkillsList);

        if (updatedCollaborator.isPresent())
        {
            if(!collaboratorRepositoryFile.save(collaboratorRepository))
            {
                System.out.println("Error while saving Collaborator Repository in external file!");
            }
        }

        return updatedCollaborator;
    }

    /**
     * Retrieves the list of selected skills.
     *
     * @return The list of selected skills.
     */
    private List<Skill> getSelectedSkillsList(){
        return skillRepository.getSelectedSkillsList();
    }

    /**
     * Retrieves a collaborator by its identification number.
     *
     * @param collaboratorIdNumber The identification number of the collaborator.
     * @return The collaborator corresponding to the provided identification number.
     */
    private Collaborator getCollaboratorByIdNumber(String collaboratorIdNumber){
        return collaboratorRepository.getCollaboratorByIdNumber(collaboratorIdNumber);
    }

    /**
     * Retrieves the list of collaborators.
     *
     * @return The list of collaborators.
     */
    public List<Collaborator> getCollaborators() {
        return collaboratorRepository.getCollaborators();
    }

    /**
     * Retrieves the list of skills not assigned to the selected collaborator.
     *
     * @return The list of skills not assigned to the selected collaborator.
     */
    public List<Skill> getSkills(String collaboratorIdNumber) {
        SkillRepository skillRepository = getSkillRepository();
        skillRepository.createSelectedSkillsList();
        List<Skill> tempSkillsList = skillRepository.createTempSkillsList();

        return collaboratorRepository.removeAlreadyAssignedSkills(collaboratorIdNumber, tempSkillsList);
    }

    /**
     * Adds a selected skill by name.
     *
     * @param name The name of the skill to add.
     */
    public void addSelectedSkillName(String name){
        skillRepository.addSelectedSkill(name);
    }

}
