package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repository class responsible for managing collaborator data.
 */
public class CollaboratorRepository {

    /**
     * A list of collaborators which consists of all the registered collaborators.
     */
    private final List<Collaborator> collaborators;

    /**
     * A temporary list of collaborators for collaborator selection.
     */
    private List<Collaborator> tempCollaboratorsList;

    /**
     * Constructs a new CollaboratorRepository object.
     */
    public CollaboratorRepository() {
        collaborators = new ArrayList<>();
    }

    /**
     * Registers a new collaborator with the provided details.
     *
     * @param name                        The name of the collaborator.
     * @param birthdate                   The birthdate of the collaborator.
     * @param admissionDate               The admission date of the collaborator.
     * @param address                     The address of the collaborator.
     * @param phoneNumber                 The phone number of the collaborator.
     * @param email                       The email of the collaborator.
     * @param taxpayerNumber              The taxpayer number of the collaborator.
     * @param identificationDocumentType  The type of identification document of the collaborator.
     * @param identificationDocumentNumber The identification document number of the collaborator.
     * @param job                         The job of the collaborator.
     * @return An Optional containing the newly registered collaborator, if registration was successful.
     */
    public Optional<Collaborator> registerCollaborator(String name, String birthdate, String admissionDate,
                                                       String address, int phoneNumber, String email, int taxpayerNumber,
                                                       String identificationDocumentType, String identificationDocumentNumber, Job job) {


        Optional<Collaborator> optionalValue = Optional.empty();

        Collaborator collaborator = new Collaborator(name, birthdate, admissionDate, address, phoneNumber, email, taxpayerNumber,
                identificationDocumentType, identificationDocumentNumber, job);

        if (addCollaborator(collaborator)) {
            optionalValue = Optional.of(collaborator);
        }
        return optionalValue;
    }

    /**
     * Assigns skills to a collaborator.
     *
     * @param collaborator         The collaborator to assign skills to.
     * @param selectedSkillsList   The list of skills to assign.
     * @return An Optional containing the updated collaborator with assigned skills.
     */
    public Optional<Collaborator> assignSkill(Collaborator collaborator, List<Skill> selectedSkillsList){
        Optional<Collaborator> optionalValue;

        optionalValue = Optional.of(collaborator.assignSkill(selectedSkillsList));

        return optionalValue;
    }

    /**
     * Adds a collaborator to the repository if it passes validation.
     *
     * @param collaborator The collaborator to be added.
     * @return True if the collaborator was successfully added, false otherwise.
     */
    private boolean addCollaborator(Collaborator collaborator) {
        boolean success = false;
        if (validate(collaborator)) {
            success = collaborators.add(collaborator.clone());
        }
        return success;
    }

    /**
     * Validates if a collaborator can be added to the repository.
     *
     * @param collaborator The collaborator to be validated.
     * @return True if the collaborator can be added, false otherwise.
     */
    private boolean validate(Collaborator collaborator) {
        return collaboratorsDoNotContain(collaborator);
    }

    /**
     * Checks if the repository already contains a collaborator with the same details.
     *
     * @param collaborator The collaborator to check.
     * @return True if the repository does not contain the collaborator, false otherwise.
     */
    private boolean collaboratorsDoNotContain(Collaborator collaborator) {
        return !collaborators.contains(collaborator);
    }

    /**
     * Retrieves a defensive copy of the list of collaborators.
     *
     * @return A list of Collaborator objects.
     */
    public List<Collaborator> getCollaborators() {
        return List.copyOf(collaborators);
    }

    /**
     * Retrieves a collaborator by their identification number.
     *
     * @param collaboratorIdNumber The identification number of the collaborator.
     * @return The Collaborator object corresponding to the provided identification number.
     * @throws RuntimeException if the collaborator is not found.
     */
    public Collaborator getCollaboratorByIdNumber(String collaboratorIdNumber){
        for(Collaborator collaborator: collaborators)
        {
            if(collaborator.sameIdNumber(collaboratorIdNumber))
            {
                return collaborator;
            }
        }
        throw new RuntimeException("Collaborator not found.");
    }

    /**
     * Creates a temporary list of collaborators, which can be used for operations such as team generation.
     *
     * @return A list of Collaborator objects.
     */
    public List<Collaborator> createTempCollaboratorsList(){
        tempCollaboratorsList = new ArrayList<>();

        for (Collaborator collaborator : collaborators) {
            tempCollaboratorsList.add(collaborator.clone());
        }

        return tempCollaboratorsList;
    }

    /**
     * Generates a team of collaborators based on selected skills and team size constraints.
     *
     * @param selectedSkillsList The list of skills required for the team.
     * @param minTeamSize        The minimum size of the team.
     * @param maxTeamSize        The maximum size of the team.
     * @return A list of Collaborator objects representing the proposed team.
     * @throws IllegalArgumentException if not all required skills are covered or if there are insufficient collaborators to form the team.
     */
    public List<Collaborator> generateTeam(List<Skill> selectedSkillsList, int minTeamSize, int maxTeamSize){
        List<Collaborator> teamProposal = new ArrayList<>();
        List<Skill> skillsNeeded = new ArrayList<>(selectedSkillsList); // Copy of the selectedSkillsList

        for (Collaborator collaborator : tempCollaboratorsList) {
            for (Skill skill : collaborator.getSkills()) {
                if (skillsNeeded.contains(skill)) {
                    if(!teamProposal.contains(collaborator))
                    {
                        teamProposal.add(collaborator); // Add collaborator to the team
                    }
                    skillsNeeded.remove(skill); // Reduce the quantity needed for this skill
                }
            }
        }

        tempCollaboratorsList.removeAll(teamProposal);

        // Check if all required skills are covered by the team
        if (!skillsNeeded.isEmpty()) {
            throw new IllegalArgumentException("Cannot generate team. Not all required skills are covered.");
        }

        // Adjust team size if necessary
        while (teamProposal.size() < minTeamSize) {
            if (tempCollaboratorsList.isEmpty()) {
                throw new IllegalArgumentException("Cannot generate team. Insufficient collaborators.");
            }
            Collaborator collaboratorToAdd = tempCollaboratorsList.remove(0); // Remove collaborator from the beginning of the list
            teamProposal.add(collaboratorToAdd);
        }
        while (teamProposal.size() > maxTeamSize) {
            teamProposal.remove(teamProposal.size() - 1); // Remove collaborators if team size exceeds maxTeamSize
        }

        return teamProposal;
    }
}
