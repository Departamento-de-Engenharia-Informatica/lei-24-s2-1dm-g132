package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.JobRepository;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;

import java.util.List;
import java.util.Optional;

public class RegisterCollaboratorController {

    private JobRepository jobRepository;
    private CollaboratorRepository collaboratorRepository;

    public RegisterCollaboratorController()
    {
        getJobRepository();
        getCollaboratorRepository();
    }

    private JobRepository getJobRepository() {
        if (jobRepository == null) {
            Repositories repositories = Repositories.getInstance();
            jobRepository = repositories.getJobRepository();
        }
        return jobRepository;
    }

    private CollaboratorRepository getCollaboratorRepository() {
        if (collaboratorRepository == null) {
            Repositories repositories = Repositories.getInstance();
            collaboratorRepository = repositories.getCollaboratorRepository();
        }
        return collaboratorRepository;
    }

    public Optional<Collaborator> registerCollaborator(String name, String birthdate, String admissionDate,
                                                       String address, int phoneNumber, String email, int taxpayerNumber,
                                                       String identificationDocumentType, int identificationDocumentNumber, String jobName) {

        Job job = getJobByName(jobName);

        Optional<CollaboratorRepository> collaboratorRepository = Optional.ofNullable(getCollaboratorRepository());

        Optional<Collaborator> newCollaborator = Optional.empty();

        if (collaboratorRepository.isPresent()) {
            newCollaborator = collaboratorRepository.get()
                    .registerCollaborator(name, birthdate, admissionDate, address, phoneNumber, email, taxpayerNumber,
                            identificationDocumentType, identificationDocumentNumber, job);
        }
        return newCollaborator;
    }

    private Job getJobByName(String jobName) {
        JobRepository jobRepository = getJobRepository();

        //Get the Job by its name
        Job jobByName =
                getJobRepository().getJobByName(jobName);
        return jobByName;

    }

    //return the list of jobs
    public List<Job> getJobs() {
        JobRepository jobRepository = getJobRepository();
        return jobRepository.getJobs();
    }

}
