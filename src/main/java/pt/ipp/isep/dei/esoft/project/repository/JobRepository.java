package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Job;

import java.util.ArrayList;
import java.util.List;

public class JobRepository {

    private final List<Job> jobs;
    public JobRepository() {
        jobs = new ArrayList<>();
    }

    public void add(Job job) {
        jobs.add(job);
    }


    public Job getJobByName(String jobName) {
        Job newJob = new Job(jobName);
        Job job = null;
        if (jobs.contains(newJob)) {
            job = jobs.get(jobs.indexOf(newJob));
        }
        if (job == null) {
            throw new IllegalArgumentException(
                    "Job requested for [" + jobName + "] does not exist.");
        }
        return job;
    }

    public List<Job> getJobs() {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(jobs);
    }



}
