package pt.ipp.isep.dei.esoft.project.mapper.dto;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;

import java.util.List;

/**
 * Data Transfer Object (DTO) for representing a team of collaborators.
 * This class holds a list of collaborators.
 */
public class TeamDTO {

    /**
     * The list of collaborators in the team.
     */
    private List<Collaborator> collaborators;

    /**
     * Constructs a TeamDTO object with the specified list of collaborators.
     *
     * @param collaborators The list of collaborators to be associated with the team.
     */
    public TeamDTO(List<Collaborator> collaborators) {
        this.collaborators = collaborators;
    }

    /**
     * Retrieves the list of collaborators in the team.
     *
     * @return The list of collaborators.
     */
    public List<Collaborator> getCollaborators() {
        return collaborators;
    }
}
