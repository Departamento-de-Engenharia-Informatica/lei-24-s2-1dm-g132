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

        SkillRepository skillRepository = new SkillRepository();
        Skill skill = new Skill("Eletrecista");


        Optional<Skill> addedSkill = skillRepository.add(skill);

        // Assert
        assertTrue(addedSkill.isPresent());
        assertEquals(skill, addedSkill.get());
    }

    @Test
    void testAddDuplicateSkill() {

        SkillRepository skillRepository = new SkillRepository();
        Skill skill = new Skill("Eletrecista");
        skillRepository.add(skill);


        Optional<Skill> addedSkill = skillRepository.add(skill);

        // Assert
        assertTrue(addedSkill.isEmpty());
    }

    @Test
    void testGetSkills() {

        SkillRepository skillRepository = new SkillRepository();
        Skill skill1 = new Skill("Eletrecista");
        Skill skill2 = new Skill("Podador");
        skillRepository.add(skill1);
        skillRepository.add(skill2);


        List<Skill> skills = skillRepository.getSkills();

        // Assert
        assertEquals(2, skills.size());
        assertTrue(skills.contains(skill1));
        assertTrue(skills.contains(skill2));
    }

    @Test
    void testGetSkillByName() {

        SkillRepository skillRepository = new SkillRepository();
        Skill skill1 = new Skill("Eletrecista");
        skillRepository.add(skill1);


        Skill retrievedSkill = skillRepository.getSkillByName("Eletrecista");

        // Assert
        assertEquals(skill1, retrievedSkill);
    }

    @Test
    void testGetSkillByNameNonExisting() {

        SkillRepository skillRepository = new SkillRepository();


        assertThrows(IllegalArgumentException.class, () -> skillRepository.getSkillByName("Nao existe"));
    }

    @Test
    void testCreateSelectedSkillsList() {

        SkillRepository skillRepository = new SkillRepository();


        skillRepository.createSelectedSkillsList();

        // Assert
        assertNotNull(skillRepository.getSelectedSkillsList());
        assertEquals(0, skillRepository.getSelectedSkillsList().size());
    }

    @Test
    void testCreateTempSkillsList() {

        SkillRepository skillRepository = new SkillRepository();

        Skill skill1 = new Skill("Podar árvores");
        Skill skill2 = new Skill("Conduzir pesados");

        skillRepository.add(skill1);
        skillRepository.add(skill2);

        List<Skill> expectedSkills = new ArrayList<>();
        expectedSkills.add(skill1);
        expectedSkills.add(skill2);

        List<Skill> resultSkills = skillRepository.createTempSkillsList();

        // Assert
        assertEquals(expectedSkills, resultSkills);
    }

    @Test
    void testAddSelectedSkill() {

        SkillRepository skillRepository = new SkillRepository();
        Skill skill1 = new Skill("Podador");
        Skill skill2 = new Skill("Eletrecista");
        skillRepository.add(skill1);
        skillRepository.add(skill2);


        skillRepository.createSelectedSkillsList();
        skillRepository.addSelectedSkill("Podador");

        // Assert
        assertEquals(1, skillRepository.getSelectedSkillsList().size());
        assertTrue(skillRepository.getSelectedSkillsList().contains(skill1));
    }
}
