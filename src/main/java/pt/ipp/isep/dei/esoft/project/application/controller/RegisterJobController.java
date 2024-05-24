/*package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.List;
import java.util.Optional;

public class RegisterJobController {

        private JobRepository jobRepository;

        public RegisterJobController()
        {
            getJobRepository();

        }

        public RegisterJobController(JobRepository jobRepository)
        {
            this.jobRepository = jobRepository;
        }

        private JobRepository getJobRepository()
        {
            if (jobRepository == null)
            {
                Repositories repositories = Repositories.getInstance();

                jobRepository = repositories.getJobRepository();
            }
            return jobRepository;
        }



    public List<Job> getJobs() {
        JobRepository jobRepository = getJobRepository();
        return jobRepository.getJobs();
    }



        public Job registerJob(String name) {


            if (name == null || name.isEmpty()) {
                throw new IllegalArgumentException("Job name cannot be null or empty");
            }
            JobRepository jobRepository = getJobRepository();

            Job newJob = new Job(name);

            jobRepository.add(newJob);



            return newJob;
        }
}*/
/**

 This class represents the controller for registering a new job.
 It interacts with the job repository to store and retrieve job information.
 */
package pt.ipp.isep.dei.esoft.project.application.controller;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.repository.JobRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.serialization.JobRepositoryFile;

import java.lang.IllegalArgumentException;

import java.util.List;

public class RegisterJobController {

    private JobRepository jobRepository;

    private JobRepositoryFile jobRepositoryFile;

    /**
     * Constructs a new RegisterJobController object.
     * Initializes the job repository by obtaining it from the Repositories singleton instance.
     */
    public RegisterJobController() {
        getJobRepository();
        jobRepositoryFile = new JobRepositoryFile();
    }

    /**
     * Constructs a new RegisterJobController object with a specified job repository.
     * @param jobRepository The job repository to be used by the controller.
     */
    public RegisterJobController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    /**
     * Retrieves the job repository, either by initializing it from the Repositories singleton instance
     * or using the one provided in the constructor.
     * @return The job repository.
     */
    private JobRepository getJobRepository() {
        if (jobRepository == null) {
            Repositories repositories = Repositories.getInstance();
            jobRepository = repositories.getJobRepository();
        }
        return jobRepository;
    }

    /**
     * Retrieves the list of jobs from the job repository.
     * @return The list of jobs.
     */
    public List<Job> getJobs() {
        JobRepository jobRepository = getJobRepository();
        return jobRepository.getJobs();
    }

    /**
     * Registers a new job with the given name.
     * Validates the input name and adds the job to the repository.
     * @param name The name of the job to be registered.
     * @return The newly created job.
     * @throws IllegalArgumentException If the name is null or empty.
     */
    public Job registerJob(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Job name cannot be null or empty");
        }
        JobRepository jobRepository = getJobRepository();
        Job newJob = new Job(name);
        jobRepository.add(newJob);
        if(!jobRepositoryFile.save(jobRepository))
        {
            System.out.println("Error while saving Job Repository in external file!");
        }
        return newJob;
    }
}



