
package pt.ipp.isep.dei.esoft.project.repository;
import pt.ipp.isep.dei.esoft.project.domain.Job;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.lang.IllegalArgumentException;

public class JobRepository implements Serializable {

    private final List<Job> jobs;

    /**
     * Constructs a new JobRepository object.
     * Initializes the list of jobs.
     */
    public JobRepository() {
        jobs = new ArrayList<>();
    }

    /**
     * Retrieves a job by its description.
     * @param jobDescription The description of the job to be retrieved.
     * @return The job with the specified description.
     * @throws IllegalArgumentException If the job with the specified description does not exist.
     */
    public Job getJobByName(String jobDescription) {
        Job newJob = new Job(jobDescription);
        Job job = null;
        if (jobs.contains(newJob)) {
            job = jobs.get(jobs.indexOf(newJob));
        }
        if (job == null) {
            throw new IllegalArgumentException(
                    "Job requested for [" + jobDescription + "] does not exist.");
        }
        return job;
    }

    /**
     * Adds a new job to the repository.
     * @param job The job to be added.
     * @return An optional containing the newly added job, or empty if the operation fails.
     */
    public Optional<Job> add(Job job) {
        Optional<Job> newJob = Optional.empty();
        boolean operationSuccess = false;
        if (validateJob(job)) {
            newJob = Optional.of(job.clone());
            operationSuccess = jobs.add(newJob.get());
        }
        if (!operationSuccess) {
            newJob = Optional.empty();
        }
        return newJob;
    }

    /**
     * Validates a job before adding it to the repository.
     * @param job The job to be validated.
     * @return True if the job is valid and can be added, false otherwise.
     */
    private boolean validateJob(Job job) {
        return !jobs.contains(job);
    }

    /**
     * Retrieves a list of all jobs stored in the repository.
     * @return A list of all jobs.
     */
    public List<Job> getJobs() {
        // This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(jobs);
    }
}
