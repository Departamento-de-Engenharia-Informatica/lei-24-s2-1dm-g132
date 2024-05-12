package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TeamRepositoryTest {

    @Test
    void testRegisterTeam() {
        TeamRepository teamRepository = new TeamRepository();
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

        Optional<Team> returnTeam = teamRepository.registerTeam(collaborators);

        assertEquals(team, returnTeam.get());
    }

    @Test
    void ensureRegisterTeamWorks() {
        TeamRepository teamRepository = new TeamRepository();
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

        Optional<Team> returnTeam = teamRepository.registerTeam(collaborators);

        assertEquals(team, returnTeam.get());
        assertNotSame(team, returnTeam.get());
    }

    @Test
    void ensureGetTeamsReturnsAnImmutableList() {
        TeamRepository teamRepository = new TeamRepository();
        Job job1 = new Job("Jardineiro");
        Job job2 = new Job("Eletricista");
        Collaborator collaborator1 = new Collaborator("André Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job1);
        Collaborator collaborator2 = new Collaborator("Fábio Martins", "1990/5/25", "2015/2/10", "Rua da Areosa, 5696-089, Matosinhos",
                929894375, "fabioelect@gmail.com", 554765206, "BI", "20771024 7", job2);

        List<Collaborator> collaborators = new ArrayList<>();
        collaborators.add(collaborator1);
        collaborators.add(collaborator2);

        teamRepository.registerTeam(collaborators);

        collaborators.remove(collaborator1);

        assertThrows(UnsupportedOperationException.class,
                () -> teamRepository.getTeams().add(new Team(collaborators)));
    }

    @Test
    void ensureGetTeamsReturnsTheCorrectList() {
        TeamRepository teamRepository = new TeamRepository();
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

        Optional<Team> returnTeam = teamRepository.registerTeam(collaborators);
        int expectedSize = 1;

        int size = teamRepository.getTeams().size();

        assertEquals(expectedSize, size);
        assertEquals(team, teamRepository.getTeams().get(size - 1));
    }

    @Test
    void ensureAddingDuplicateTeamFails(){
        TeamRepository teamRepository = new TeamRepository();
        Job job1 = new Job("Jardineiro");
        Job job2 = new Job("Eletricista");
        Collaborator collaborator1 = new Collaborator("André Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job1);
        Collaborator collaborator2 = new Collaborator("Fábio Martins", "1990/5/25", "2015/2/10", "Rua da Areosa, 5696-089, Matosinhos",
                929894375, "fabioelect@gmail.com", 554765206, "BI", "20771024 7", job2);

        List<Collaborator> collaborators = new ArrayList<>();
        collaborators.add(collaborator1);
        collaborators.add(collaborator2);

        teamRepository.registerTeam(collaborators);

        Optional<Team> duplicateTeam = teamRepository.registerTeam(collaborators);

        assertTrue(duplicateTeam.isEmpty());
    }

    @Test
    void ensureAddingDifferentTeamsWorks() {
        TeamRepository teamRepository = new TeamRepository();
        Job job1 = new Job("Jardineiro");
        Job job2 = new Job("Eletricista");
        Collaborator collaborator1 = new Collaborator("André Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job1);
        Collaborator collaborator2 = new Collaborator("Fábio Martins", "1990/5/25", "2015/2/10", "Rua da Areosa, 5696-089, Matosinhos",
                929894375, "fabioelect@gmail.com", 554765206, "BI", "20771024 7", job2);

        List<Collaborator> collaborators1 = new ArrayList<>();
        List<Collaborator> collaborators2 = new ArrayList<>();
        collaborators1.add(collaborator1);
        collaborators2.add(collaborator2);

        teamRepository.registerTeam(collaborators1);

        Team team = new Team(collaborators2);

        Optional<Team> result = teamRepository.registerTeam(collaborators2);

        assertEquals(team, result.get());
    }

    @Test
    void ensureRemoveUnavailableCollaboratorsWorksWithEmptyTeams() {
        TeamRepository teamRepository = new TeamRepository();
        Job job1 = new Job("Jardineiro");
        Job job2 = new Job("Eletricista");
        Collaborator collaborator1 = new Collaborator("André Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job1);
        Collaborator collaborator2 = new Collaborator("Fábio Martins", "1990/5/25", "2015/2/10", "Rua da Areosa, 5696-089, Matosinhos",
                929894375, "fabioelect@gmail.com", 554765206, "BI", "20771024 7", job2);

        List<Collaborator> collaborators = new ArrayList<>();
        collaborators.add(collaborator1);
        collaborators.add(collaborator2);

        List<Collaborator> collaboratorsExpected = new ArrayList<>();
        collaboratorsExpected.add(collaborator1);
        collaboratorsExpected.add(collaborator2);

        teamRepository.removeUnavailableCollaborators(collaborators);

        assertEquals(collaboratorsExpected, collaborators);
    }

    @Test
    void ensureRemoveUnavailableCollaboratorsWorks() {
        TeamRepository teamRepository = new TeamRepository();
        Job job1 = new Job("Jardineiro");
        Job job2 = new Job("Eletricista");
        Collaborator collaborator1 = new Collaborator("André Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job1);
        Collaborator collaborator2 = new Collaborator("Fábio Martins", "1990/5/25", "2015/2/10", "Rua da Areosa, 5696-089, Matosinhos",
                929894375, "fabioelect@gmail.com", 554765206, "BI", "20771024 7", job2);

        List<Collaborator> collaborators = new ArrayList<>();
        collaborators.add(collaborator1);
        collaborators.add(collaborator2);

        List<Collaborator> collaborators1 = new ArrayList<>();
        collaborators1.add(collaborator1);
        collaborators1.add(collaborator2);

        teamRepository.registerTeam(collaborators);
        List<Collaborator> collaboratorsExpected = new ArrayList<>();

        teamRepository.removeUnavailableCollaborators(collaborators1);

        assertEquals(collaboratorsExpected, collaborators1);
    }

    @Test
    void ensureRemoveUnavailableCollaboratorsWorksWithEmptyArgument() {
        TeamRepository teamRepository = new TeamRepository();
        Job job1 = new Job("Jardineiro");
        Job job2 = new Job("Eletricista");
        Collaborator collaborator1 = new Collaborator("André Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job1);
        Collaborator collaborator2 = new Collaborator("Fábio Martins", "1990/5/25", "2015/2/10", "Rua da Areosa, 5696-089, Matosinhos",
                929894375, "fabioelect@gmail.com", 554765206, "BI", "20771024 7", job2);

        List<Collaborator> collaborators = new ArrayList<>();
        collaborators.add(collaborator1);
        collaborators.add(collaborator2);

        List<Collaborator> collaboratorsOther = new ArrayList<>();

        teamRepository.registerTeam(collaborators);

        teamRepository.removeUnavailableCollaborators(collaboratorsOther);
    }
}