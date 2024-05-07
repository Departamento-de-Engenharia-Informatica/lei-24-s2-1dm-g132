package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CollaboratorRepository {

    private final List<Collaborator> collaborators;
    public CollaboratorRepository() {
        collaborators = new ArrayList<>();
    }

    public Optional<Collaborator> registerCollaborator(String name, String birthdate, String admissionDate,
                                                       String address, int phoneNumber, String email, int taxpayerNumber,
                                                       String identificationDocumentType, int identificationDocumentNumber, Job job) {


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

}
