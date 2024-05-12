package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class JobTest {
    @Test
    void ensureTwoJobsWithSameNameEquals() {
        Job job1 = new Job("Podador");
        Job job2 = new Job("Podador");
        assertEquals(job1, job2);

    }

    @Test
    void ensureJobWithDifferentNameNotEquals() {
        Job job1 = new Job("Podador");
        Job job2 = new Job("Eletrecista");
        assertNotEquals(job1, job2);

    }
    @Test
    void ensureJobDoesNotEqualNull() {
        Job job = new Job("Podador");
        assertNotEquals(job, null);
    }
    @Test
    void ensureJobDoesNotEqualOtherObject() {
        Job job = new Job("Podador");
        assertNotEquals(job, new Object());
    }
    @Test
    void ensureTheSameObjectIsEqual() {
        Job job = new Job("Podador");
        assertEquals(job, job);
    }
    @Test
    void ensureHashCodeIsEqualForEqualObjects() {
        String name = "Podador";
        Job job1 = new Job(name);
        Job job2 = new Job(name);
        assertEquals(job1.hashCode(), job2.hashCode());
    }
    @Test
    void ensureHashCodeIsNotEqualForDifferentObjects() {

        Job job1 = new Job("Podador");
        Job job2 = new Job("Eletrecista");
        assertNotEquals(job2.hashCode(), job1.hashCode());

    }
    @Test
    void ensureHasNameWorksForTheSameName() {
        String name = "Podador";
        Job job = new Job(name);
        assertTrue(job.hasName(name));

    }
    @Test
    void ensureHasNameFailsForDifferentNames() {
        String name = "Podador";
        Job job = new Job(name);
        assertFalse(job.hasName("eletricista"));

    }
    @Test
    void ensureCloneWorks() {
        String name = "Podador";
        Job job = new Job(name);
        Job clone = job.clone();
        assertEquals(job, clone);
    }


}
