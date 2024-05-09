package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.TaskCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JobRepository {

    private final List<Job> jobs;
    public JobRepository() {
        jobs = new ArrayList<>();
    }
    public Job getJobByName(String JobDescription) {
        Job newJob = new Job(JobDescription);
        Job job = null;
        if (jobs.contains(newJob)) {
            job = jobs.get(jobs.indexOf(newJob));
        }
        if (job == null) {
            throw new IllegalArgumentException(
                    "Job requested for [" + JobDescription + "] does not exist.");
        }
        return job;
    }

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
    private boolean validateJob(Job job) {
        boolean isValid = !jobs.contains(job);
        return isValid;
    }
    public List<Job> getJobs() {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(jobs);
    }



}
