package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CollaboratorRepositoryTest {

    @Test
    void testRegisterCollaborator() {
        CollaboratorRepository collaboratorRepository = new CollaboratorRepository();
        Job job = new Job("Jardineiro");
        Collaborator collaborator = new Collaborator("André Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job);

        Optional<Collaborator> returnCollaborator = collaboratorRepository.registerCollaborator("André Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job);

        assertEquals(collaborator, returnCollaborator.get());
    }

    @Test
    void testAssignSkill() {
        CollaboratorRepository collaboratorRepository = new CollaboratorRepository();
        Job job = new Job("Jardineiro");
        Collaborator collaborator = new Collaborator("André Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job);

        Skill skill = new Skill("Podar árvores");
        List<Skill> skills = new ArrayList<>();
        skills.add(skill);

        Optional<Collaborator> returnCollaborator = collaboratorRepository.assignSkill(collaborator, skills);
    }

    @Test
    void ensureRegisterCollaboratorWorks() {
        CollaboratorRepository collaboratorRepository = new CollaboratorRepository();
        Job job = new Job("Jardineiro");
        Collaborator collaborator = new Collaborator("André Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job);

        Optional<Collaborator> returnCollaborator = collaboratorRepository.registerCollaborator("André Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job);

        assertEquals(collaborator, returnCollaborator.get());
        assertNotSame(collaborator, returnCollaborator.get());
    }

    @Test
    void ensureGetCollaboratorsReturnsAnImmutableList() {
        CollaboratorRepository collaboratorRepository = new CollaboratorRepository();
        Job job = new Job("Jardineiro");
        collaboratorRepository.registerCollaborator("André Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job);

        assertThrows(UnsupportedOperationException.class,
                () -> collaboratorRepository.getCollaborators().add(new Collaborator("André Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                        919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job)));

    }

    @Test
    void ensureGetCollaboratorsReturnsTheCorrectList() {
        //Arrange
        CollaboratorRepository collaboratorRepository = new CollaboratorRepository();
        Job job = new Job("Jardineiro");
        Collaborator collaborator = new Collaborator("André Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job);
        collaboratorRepository.registerCollaborator("André Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job);
        int expectedSize = 1;

        //Act
        int size = collaboratorRepository.getCollaborators().size();

        //Assert
        assertEquals(expectedSize, size);
        assertEquals(collaborator, collaboratorRepository.getCollaborators().get(size - 1));
    }

    @Test
    void ensureAddingDuplicateCollaboratorFails(){
        //Arrange
        Job job = new Job("Jardineiro");
        CollaboratorRepository collaboratorRepository = new CollaboratorRepository();
        //Add the first collaborator
        collaboratorRepository.registerCollaborator("André Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job);

        //Act
        Optional<Collaborator> duplicateCollaborator = collaboratorRepository.registerCollaborator("André Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job);

        //Assert
        assertTrue(duplicateCollaborator.isEmpty());
    }

    @Test
    void ensureAddingDifferentCollaboratorsWorks() {
        //Arrange
        CollaboratorRepository collaboratorRepository = new CollaboratorRepository();
        Job job = new Job("Jardineiro");
        //Add the first collaborator
        collaboratorRepository.registerCollaborator("André Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job);
        Collaborator collaborator = new Collaborator("Andreia Baltasar", "2003/3/3", "2024/4/24", "Rua Passada, 3399-086, Porto",
                919354919, "andreiapassado0@gmail.com", 546442206, "BI", "20730192 7", job);

        //Act
        Optional<Collaborator> result = collaboratorRepository.registerCollaborator("Andreia Baltasar", "2003/3/3", "2024/4/24", "Rua Passada, 3399-086, Porto",
                919354919, "andreiapassado0@gmail.com", 546442206, "BI", "20730192 7", job);

        //Assert
        assertEquals(collaborator, result.get());
    }

    @Test
    void getCollaboratorByIdNumberEmptyList() {
        CollaboratorRepository collaboratorRepository = new CollaboratorRepository();
        String idNumber = "20730192 7";
        assertThrows(RuntimeException.class,
                () -> collaboratorRepository.getCollaboratorByIdNumber(idNumber));
    }

    @Test
    void ensureGetCollaboratorFailsForNonExistingCollaborator() {
        CollaboratorRepository collaboratorRepository = new CollaboratorRepository();
        String idNumber= "20730192 6";
        Job job = new Job("Jardineiro");
        //Add the first collaborator
        collaboratorRepository.registerCollaborator("André Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job);
        assertThrows(RuntimeException.class,
                () -> collaboratorRepository.getCollaboratorByIdNumber(idNumber));

    }

    @Test
    void ensureGetCollaboratorForExistingCollaborator() {
        CollaboratorRepository collaboratorRepository = new CollaboratorRepository();
        String idNumber = "20735924 7";
        Job job = new Job("Jardineiro");
        collaboratorRepository.registerCollaborator("André Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job);
        Collaborator collaborator = new Collaborator("André Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job);
        Collaborator collaborator1 = collaboratorRepository.getCollaboratorByIdNumber(idNumber);

        assertEquals(collaborator, collaborator1);
    }

    @Test
    void ensureCreateTempCollaboratorsListReturnsTheCorrectList() {
        //Arrange
        CollaboratorRepository collaboratorRepository = new CollaboratorRepository();
        Job job = new Job("Jardineiro");
        collaboratorRepository.registerCollaborator("André Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job);
        int expectedSize = 1;

        //Act
        int size = collaboratorRepository.createTempCollaboratorsList().size();

        //Assert
        assertEquals(expectedSize, size);
        assertEquals(collaboratorRepository.getCollaborators().get(size - 1), collaboratorRepository.createTempCollaboratorsList().get(size - 1));
    }

    @Test
    void ensureGenerateTeamWorks(){
        CollaboratorRepository collaboratorRepository = new CollaboratorRepository();
        Job job = new Job("Jardineiro");
        collaboratorRepository.registerCollaborator("André Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job);

        Collaborator collaborator = collaboratorRepository.getCollaboratorByIdNumber("20735924 7");
        Skill skill = new Skill("Podar árvores");
        List<Skill> skills = new ArrayList<>();
        skills.add(skill);

        collaborator.assignSkill(skills);

        collaboratorRepository.createTempCollaboratorsList();

        collaboratorRepository.generateTeam(skills, 0, 2);
    }

    @Test
    void ensureGenerateTeamNotAllSkillsAreCovered(){
        CollaboratorRepository collaboratorRepository = new CollaboratorRepository();
        Job job = new Job("Jardineiro");
        collaboratorRepository.registerCollaborator("André Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job);

        Collaborator collaborator = collaboratorRepository.getCollaboratorByIdNumber("20735924 7");
        Skill skill1 = new Skill("Podar árvores");
        Skill skill2 = new Skill("Conduzir pesados");
        List<Skill> skills = new ArrayList<>();
        skills.add(skill1);

        collaborator.assignSkill(skills);

        skills.add(skill2);

        collaboratorRepository.createTempCollaboratorsList();

        assertThrows(IllegalArgumentException.class,
                () -> collaboratorRepository.generateTeam(skills, 0, 2));
    }

    @Test
    void ensureGenerateTeamNotEnoughCollaborators(){
        CollaboratorRepository collaboratorRepository = new CollaboratorRepository();
        Job job = new Job("Jardineiro");
        collaboratorRepository.registerCollaborator("André Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job);

        Collaborator collaborator = collaboratorRepository.getCollaboratorByIdNumber("20735924 7");
        Skill skill = new Skill("Podar árvores");
        List<Skill> skills = new ArrayList<>();
        skills.add(skill);

        collaborator.assignSkill(skills);

        collaboratorRepository.createTempCollaboratorsList();

        assertThrows(IllegalArgumentException.class,
                () -> collaboratorRepository.generateTeam(skills, 2, 3));
    }

    @Test
    void ensureGenerateTeamMaxCollaboratorsExceeded() {
        CollaboratorRepository collaboratorRepository = new CollaboratorRepository();
        Job job = new Job("Jardineiro");
        collaboratorRepository.registerCollaborator("André Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job);

        collaboratorRepository.registerCollaborator("João Oliveira", "2000/2/2", "2020/3/30", "Rua Passada, 3786-089, Porto",
                913333333, "joaipassado3@gmail.com", 546002206, "BI", "20731224 7", job);

        Collaborator collaborator1 = collaboratorRepository.getCollaboratorByIdNumber("20735924 7");
        Collaborator collaborator2 = collaboratorRepository.getCollaboratorByIdNumber("20731224 7");
        Skill skill1 = new Skill("Podar árvores");
        Skill skill2 = new Skill("Conduzir pesados");
        List<Skill> skills = new ArrayList<>();
        skills.add(skill1);

        collaborator1.assignSkill(skills);

        skills.add(skill2);

        collaborator2.assignSkill(skills);

        skills.add(skill1);

        collaboratorRepository.createTempCollaboratorsList();

        assertThrows(IllegalArgumentException.class,
                () -> collaboratorRepository.generateTeam(skills, 0, 1));
    }
}