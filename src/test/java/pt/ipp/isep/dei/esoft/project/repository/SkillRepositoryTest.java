package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;

import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;


public class SkillRepositoryTest {
    @Test
    void testAddSkill() {
        // Arrange
        SkillRepository skillRepository = new SkillRepository();
        Skill skill = new Skill("Eletrecista");

        // Act
        Optional<Skill> addedSkill = skillRepository.add(skill);

        // Assert
        assertTrue(addedSkill.isPresent());
        assertEquals(skill, addedSkill.get());
    }

    @Test
    void testAddDuplicateSkill() {
        // Arrange
        SkillRepository skillRepository = new SkillRepository();
        Skill skill = new Skill("Eletrecista");
        skillRepository.add(skill);

        // Act
        Optional<Skill> addedSkill = skillRepository.add(skill);

        // Assert
        assertTrue(addedSkill.isEmpty());
    }

    @Test
    void testGetSkills() {
        // Arrange
        SkillRepository skillRepository = new SkillRepository();
        Skill skill1 = new Skill("Eletrecista");
        Skill skill2 = new Skill("Podador");
        skillRepository.add(skill1);
        skillRepository.add(skill2);

        // Act
        List<Skill> skills = skillRepository.getSkills();

        // Assert
        assertEquals(2, skills.size());
        assertTrue(skills.contains(skill1));
        assertTrue(skills.contains(skill2));
    }

    @Test
    void testGetSkillByName() {
        // Arrange
        SkillRepository skillRepository = new SkillRepository();
        Skill skill1 = new Skill("Eletrecista");
        skillRepository.add(skill1);

        // Act
        Skill retrievedSkill = skillRepository.getSkillByName("Eletrecista");

        // Assert
        assertEquals(skill1, retrievedSkill);
    }

    @Test
    void testGetSkillByNameNonExisting() {
        // Arrange
        SkillRepository skillRepository = new SkillRepository();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> skillRepository.getSkillByName("Nao existe"));
    }

    @Test
    void testCreateSelectedSkillsList() {
        // Arrange
        SkillRepository skillRepository = new SkillRepository();

        // Act
        skillRepository.createSelectedSkillsList();

        // Assert
        assertNotNull(skillRepository.getSelectedSkillsList());
        assertEquals(0, skillRepository.getSelectedSkillsList().size());
    }

    @Test
    void testAddSelectedSkill() {
        // Arrange
        SkillRepository skillRepository = new SkillRepository();
        Skill skill1 = new Skill("Podador");
        Skill skill2 = new Skill("Eletrecista");
        skillRepository.add(skill1);
        skillRepository.add(skill2);

        // Act
        skillRepository.createSelectedSkillsList();
        skillRepository.addSelectedSkill("Podador");

        // Assert
        assertEquals(1, skillRepository.getSelectedSkillsList().size());
        assertTrue(skillRepository.getSelectedSkillsList().contains(skill1));
    }
}
