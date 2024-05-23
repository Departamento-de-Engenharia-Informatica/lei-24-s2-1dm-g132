package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.JobRepository;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.serialization.CollaboratorRepositoryFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Controller class responsible for handling operations related to collaborator registration.
 */
public class RegisterCollaboratorController {

    /**
     * The job repository needed for the operations of this class.
     */
    private JobRepository jobRepository;

    /**
     * The collaborator repository needed for the operations of this class.
     */
    private CollaboratorRepository collaboratorRepository;

    private CollaboratorRepositoryFile collaboratorRepositoryFile;

    /**
     * Constructs a new RegisterCollaboratorController object.
     */
    public RegisterCollaboratorController()
    {
        getJobRepository();
        getCollaboratorRepository();
        collaboratorRepositoryFile = new CollaboratorRepositoryFile();
    }

    /**
     * Retrieves the job repository instance.
     *
     * @return The JobRepository object.
     */
    private JobRepository getJobRepository() {
        if (jobRepository == null) {
            Repositories repositories = Repositories.getInstance();
            jobRepository = repositories.getJobRepository();
        }
        return jobRepository;
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
     * @param jobName                     The name of the job the collaborator is associated with.
     * @return An Optional containing the newly registered collaborator, if registration was successful.
     */
    public Optional<Collaborator> registerCollaborator(String name, String birthdate, String admissionDate,
                                                       String address, int phoneNumber, String email, int taxpayerNumber,
                                                       String identificationDocumentType, String identificationDocumentNumber, String jobName) {

        Job job = getJobByName(jobName);

        collaboratorRepository = getCollaboratorRepository();

        Optional<Collaborator> newCollaborator = Optional.empty();

        newCollaborator = collaboratorRepository.registerCollaborator(name, birthdate, admissionDate, address, phoneNumber, email, taxpayerNumber,
                            identificationDocumentType, identificationDocumentNumber, job);

        if (newCollaborator.isPresent())
        {
            if(!collaboratorRepositoryFile.save(collaboratorRepository))
            {
                System.out.println("Error while saving Collaborator Repository in external file!");
            }
        }

        return newCollaborator;
    }

    /**
     * Retrieves the Job object with the provided name.
     *
     * @param jobName The name of the job.
     * @return The Job object corresponding to the provided name.
     */
    private Job getJobByName(String jobName) {
        JobRepository jobRepository = getJobRepository();
        Job jobByName =
                getJobRepository().getJobByName(jobName);
        return jobByName;

    }

    /**
     * Retrieves the list of available jobs.
     *
     * @return The list of Job objects.
     */
    public List<Job> getJobs() {
        JobRepository jobRepository = getJobRepository();
        return jobRepository.getJobs();
    }

}
