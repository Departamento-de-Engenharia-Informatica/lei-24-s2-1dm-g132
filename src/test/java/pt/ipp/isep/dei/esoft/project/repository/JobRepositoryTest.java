package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;

import pt.ipp.isep.dei.esoft.project.domain.Job;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
public class JobRepositoryTest {
    @Test
    void testAddJob() {

        JobRepository jobRepository = new JobRepository();
        Job job = new Job("Motorista");


        Optional<Job> addedJob = jobRepository.add(job);

        // Assert
        assertTrue(addedJob.isPresent());
        assertEquals(job, addedJob.get());
    }

    @Test
    void testAddDuplicateJob() {

        JobRepository jobRepository = new JobRepository();
        Job job = new Job("Motorista");
        jobRepository.add(job);


        Optional<Job> addedJob = jobRepository.add(job);

        // Assert
        assertTrue(addedJob.isEmpty());
    }

    @Test
    void testGetJobs() {

        JobRepository jobRepository = new JobRepository();
        Job job1 = new Job("Motorista");
        Job job2 = new Job("Eletricista");
        jobRepository.add(job1);
        jobRepository.add(job2);


        List<Job> jobs = jobRepository.getJobs();

        // Assert
        assertEquals(2, jobs.size());
        assertTrue(jobs.contains(job1));
        assertTrue(jobs.contains(job2));
    }

    @Test
    void testGetJobByName() {

        JobRepository jobRepository = new JobRepository();
        Job job1 = new Job("Motorista");
        jobRepository.add(job1);


        Job retrievedJob = jobRepository.getJobByName("Motorista");

        // Assert
        assertEquals(job1, retrievedJob);
    }

    @Test
    void testGetJobByNameNonExisting() {

        JobRepository jobRepository = new JobRepository();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> jobRepository.getJobByName("Nao existe"));
    }

}
