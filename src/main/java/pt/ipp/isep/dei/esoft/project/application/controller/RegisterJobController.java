package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.repository.JobRepository;

public class RegisterJobController {

        private JobRepository jobRepository;

        public RegisterJobController() {
            getJobRepository();
        }

        private JobRepository getJobRepository() {
            if (jobRepository == null) {
                jobRepository = new JobRepository();
            }
            return jobRepository;
        }

        public Job registerJob(String name) {
            JobRepository jobRepository = getJobRepository();

            Job newJob = new Job(name);

            jobRepository.add(newJob);

            return newJob;
        }
}
