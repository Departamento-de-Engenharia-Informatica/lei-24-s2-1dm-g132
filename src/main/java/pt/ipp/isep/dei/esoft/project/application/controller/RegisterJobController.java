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

    /*public Optional<Job> createJob(String name) {
        Employee employee = getEmployeeFromSession();
        Optional<Organization> organization = getOrganizationRepository().getOrganizationByEmployee(employee);

        Optional<Job> newJob = Optional.empty();

        if (organization.isPresent()) {
            newJob = organization.get().createJob(name, jobDescription, employee);
        }
        return newJob;
    }*/

    public List<Job> getJobs() {
        JobRepository jobRepository = getJobRepository();
        return jobRepository.getJobs();
    }

    /*private OrganizationRepository getOrganizationRepository() {
        if (organizationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            organizationRepository = repositories.getOrganizationRepository();
        }
        return organizationRepository;

    }*/

        public Job registerJob(String name) {
            JobRepository jobRepository = getJobRepository();

            Job newJob = new Job(name);

            jobRepository.add(newJob);

            return newJob;
        }
}



