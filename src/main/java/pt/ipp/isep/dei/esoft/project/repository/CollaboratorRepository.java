package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Job;

import java.util.ArrayList;
import java.util.List;

public class CollaboratorRepository {

    private List<Collaborator> collaborators;
    public CollaboratorRepository() {
        collaborators = new ArrayList<>();
    }

}
