package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TeamTest {

    @Test
    void ensureTeamIsCreatedSuccessfully() {
        Job job1 = new Job("Jardineiro");
        Job job2 = new Job("Eletricista");
        Collaborator collaborator1 = new Collaborator("André Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job1);
        Collaborator collaborator2 = new Collaborator("Fábio Martins", "1990/5/25", "2015/2/10", "Rua da Areosa, 5696-089, Matosinhos",
                929894375, "fabioelect@gmail.com", 554765206, "BI", "20771024 7", job2);

        List<Collaborator> collaborators = new ArrayList<>();
        collaborators.add(collaborator1);
        collaborators.add(collaborator2);

        Team team = new Team(collaborators);
    }

    @Test
    void testEqualsSameObject() {
        Job job1 = new Job("Jardineiro");
        Job job2 = new Job("Eletricista");
        Collaborator collaborator1 = new Collaborator("André Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job1);
        Collaborator collaborator2 = new Collaborator("Fábio Martins", "1990/5/25", "2015/2/10", "Rua da Areosa, 5696-089, Matosinhos",
                929894375, "fabioelect@gmail.com", 554765206, "BI", "20771024 7", job2);

        List<Collaborator> collaborators = new ArrayList<>();
        collaborators.add(collaborator1);
        collaborators.add(collaborator2);

        Team team = new Team(collaborators);

        assertEquals(team, team);
    }

    @Test
    void testEqualsEqualTeams() {
        Job job1 = new Job("Jardineiro");
        Job job2 = new Job("Eletricista");
        Collaborator collaborator1 = new Collaborator("André Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job1);
        Collaborator collaborator2 = new Collaborator("Fábio Martins", "1990/5/25", "2015/2/10", "Rua da Areosa, 5696-089, Matosinhos",
                929894375, "fabioelect@gmail.com", 554765206, "BI", "20771024 7", job2);

        List<Collaborator> collaborators = new ArrayList<>();
        collaborators.add(collaborator1);
        collaborators.add(collaborator2);

        Team team1 = new Team(collaborators);
        Team team2 = new Team(collaborators);

        assertEquals(team1, team2);
    }

    @Test
    void ensureTeamDoesNotEqualNull() {
        Job job1 = new Job("Jardineiro");
        Job job2 = new Job("Eletricista");
        Collaborator collaborator1 = new Collaborator("André Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job1);
        Collaborator collaborator2 = new Collaborator("Fábio Martins", "1990/5/25", "2015/2/10", "Rua da Areosa, 5696-089, Matosinhos",
                929894375, "fabioelect@gmail.com", 554765206, "BI", "20771024 7", job2);

        List<Collaborator> collaborators = new ArrayList<>();
        collaborators.add(collaborator1);
        collaborators.add(collaborator2);

        Team team = new Team(collaborators);

        assertNotEquals(team, null);
    }

    @Test
    void ensureTeamDoesNotEqualOtherObject() {
        Job job1 = new Job("Jardineiro");
        Job job2 = new Job("Eletricista");
        Collaborator collaborator1 = new Collaborator("André Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job1);
        Collaborator collaborator2 = new Collaborator("Fábio Martins", "1990/5/25", "2015/2/10", "Rua da Areosa, 5696-089, Matosinhos",
                929894375, "fabioelect@gmail.com", 554765206, "BI", "20771024 7", job2);

        List<Collaborator> collaborators = new ArrayList<>();
        collaborators.add(collaborator1);
        collaborators.add(collaborator2);

        Team team = new Team(collaborators);

        assertNotEquals(team, new Object());
    }

    @Test
    void ensureCloneWorks() {
        Job job1 = new Job("Jardineiro");
        Job job2 = new Job("Eletricista");
        Collaborator collaborator1 = new Collaborator("André Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job1);
        Collaborator collaborator2 = new Collaborator("Fábio Martins", "1990/5/25", "2015/2/10", "Rua da Areosa, 5696-089, Matosinhos",
                929894375, "fabioelect@gmail.com", 554765206, "BI", "20771024 7", job2);

        List<Collaborator> collaborators = new ArrayList<>();
        collaborators.add(collaborator1);
        collaborators.add(collaborator2);

        Team team = new Team(collaborators);

        Team clone = team.clone();

        assertEquals(team, clone);
    }

    @Test
    void testRemoveUnavailableCollaborators() {
        Job job1 = new Job("Jardineiro");
        Job job2 = new Job("Eletricista");
        Collaborator collaborator1 = new Collaborator("André Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job1);
        Collaborator collaborator2 = new Collaborator("Fábio Martins", "1990/5/25", "2015/2/10", "Rua da Areosa, 5696-089, Matosinhos",
                929894375, "fabioelect@gmail.com", 554765206, "BI", "20771024 7", job2);

        List<Collaborator> collaborators = new ArrayList<>();
        collaborators.add(collaborator1);
        collaborators.add(collaborator2);

        Team team = new Team(collaborators);

        team.removeUnavailableCollaborators(collaborators);
    }
}