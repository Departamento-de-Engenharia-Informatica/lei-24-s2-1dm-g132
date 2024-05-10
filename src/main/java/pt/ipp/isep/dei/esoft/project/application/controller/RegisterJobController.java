package pt.ipp.isep.dei.esoft.project.application.controller;

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
            JobRepository jobRepository = getJobRepository();

            Job newJob = new Job(name);

            jobRepository.add(newJob);

            return newJob;
        }
}



