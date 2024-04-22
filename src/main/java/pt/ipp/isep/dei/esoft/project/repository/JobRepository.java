package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Job;

import java.util.ArrayList;
import java.util.List;

public class JobRepository {

    private List<Job> jobs;
    public JobRepository() {
        jobs = new ArrayList<>();
    }

}
