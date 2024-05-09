package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private List<Collaborator> collaborators;

    public Team(List<Collaborator> collaborators)
    {
        this.collaborators = collaborators;
    }

    public void removeUnavailableCollaborators(List<Collaborator> tempCollaboratorsList){
        for(Collaborator collaborator: collaborators){
            tempCollaboratorsList.remove(collaborator);
        }
    }

    @Override
    public Team clone() {
        return new Team(this.collaborators);
    }
}
