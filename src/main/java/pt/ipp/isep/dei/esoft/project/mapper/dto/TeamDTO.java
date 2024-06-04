package pt.ipp.isep.dei.esoft.project.mapper.dto;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;

import java.util.List;

public class TeamDTO {

    private List<Collaborator> collaborators;

    public TeamDTO(List<Collaborator> collaborators) {
        this.collaborators = collaborators;
    }

    public List<Collaborator> getCollaborators() {
        return collaborators;
    }
}
