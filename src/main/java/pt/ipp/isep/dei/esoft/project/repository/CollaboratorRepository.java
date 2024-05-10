package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CollaboratorRepository {

    private final List<Collaborator> collaborators;
    private List<Collaborator> tempCollaboratorsList;

    public CollaboratorRepository() {
        collaborators = new ArrayList<>();
    }

    public Optional<Collaborator> registerCollaborator(String name, String birthdate, String admissionDate,
                                                       String address, int phoneNumber, String email, int taxpayerNumber,
                                                       String identificationDocumentType, String identificationDocumentNumber, Job job) {


        // When a Collaborator is added, it should fail if the Collaborator already exists in the list of Collaborators.
        // In order to not return null if the operation fails, we use the Optional class.
        Optional<Collaborator> optionalValue = Optional.empty();

        Collaborator collaborator = new Collaborator(name, birthdate, admissionDate, address, phoneNumber, email, taxpayerNumber,
                identificationDocumentType, identificationDocumentNumber, job);

        if (addCollaborator(collaborator)) {
            optionalValue = Optional.of(collaborator);
        }
        return optionalValue;
    }

    public Optional<Collaborator> assignSkill(Collaborator collaborator, List<Skill> selectedSkillsList){
        Optional<Collaborator> optionalValue;

        optionalValue = Optional.of(collaborator.assignSkill(selectedSkillsList));

        return optionalValue;
    }

    private boolean addCollaborator(Collaborator collaborator) {
        boolean success = false;
        if (validate(collaborator)) {
            // A clone of the task is added to the list of tasks, to avoid side effects and outside manipulation.
            success = collaborators.add(collaborator.clone());
        }
        return success;
    }

    private boolean validate(Collaborator collaborator) {
        return collaboratorsDoNotContain(collaborator);
    }


    private boolean collaboratorsDoNotContain(Collaborator collaborator) {
        return !collaborators.contains(collaborator);
    }

    public List<Collaborator> getCollaborators() {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(collaborators);
    }

    public Collaborator getCollaboratorByIdNumber(String collaboratorIdNumber){
        for(Collaborator collaborator: collaborators)
        {
            if(collaborator.sameIdNumber(collaboratorIdNumber))
            {
                return collaborator;
            }
        }
        return null;
    }

    public List<Collaborator> createTempCollaboratorsList(){
        tempCollaboratorsList = new ArrayList<>();

        for (Collaborator collaborator : collaborators) {
            tempCollaboratorsList.add(collaborator.clone());
        }

        return tempCollaboratorsList;
    }

    public List<Collaborator> generateTeam(List<Skill> selectedSkillsList, int minTeamSize, int maxTeamSize){
        List<Collaborator> teamProposal = new ArrayList<>();
        List<Skill> skillsNeeded = new ArrayList<>(selectedSkillsList); // Copy of the selectedSkillsList

        // Count the number of collaborators possessing each required skill
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
