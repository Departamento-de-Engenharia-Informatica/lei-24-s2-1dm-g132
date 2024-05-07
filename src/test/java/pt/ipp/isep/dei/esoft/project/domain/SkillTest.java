package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class SkillTest {

    @Test
    void ensureTwoSkillsWithSameNameEquals() {
        Skill skill1 = new Skill("Condutor de Pesados");
        Skill skill2 = new Skill("Condutor de Pesados");
        assertEquals(skill1, skill2);

    }

    @Test
    void ensureSkillWithDifferentNameNotEquals() {
        Skill skill1 = new Skill("Especialista de Pesados");
        Skill skill2 = new Skill("Eletrecista");
        assertNotEquals(skill1, skill2);
    }
    @Test
    void ensureSkillDoesNotEqualNull() {
        Skill skill = new Skill("Especialista de Pesados");
        assertNotEquals(skill, null);
    }
    @Test
    void ensureJobDoesNotEqualOtherObject() {
        Skill skill = new Skill("Especialista de Pesados");
        assertNotEquals(skill, new Object());
    }
    @Test
    void ensureTheSameObjectIsEqual() {
        Skill skill = new Skill("Especialista de Pesados");
        assertEquals(skill, skill);
    }
    @Test
    void ensureHashCodeIsEqualForEqualObjects() {
        String name = "Especialista de Pesados";
        Skill skill1 = new Skill(name);
        Skill skill2 = new Skill(name);
        assertEquals(skill1.hashCode(), skill2.hashCode());
    }
    @Test
    void ensureHashCodeIsNotEqualForDifferentObjects() {

        Skill skill1 = new Skill("Especialista de Pesados");
        Skill skill2 = new Skill("Eletrecista");
        assertNotEquals(skill2.hashCode(), skill1.hashCode());
    }
    @Test
    void ensureHasNameWorksForTheSameName() {
        String name = "Especialista de Pesados";
        Skill skill = new Skill(name);
        assertTrue(skill.hasName(name));

    }
    @Test
    void ensureHasNameFailsForDifferentNames() {
        String name = "Especialista de Pesados";
        Skill skill = new Skill(name);
        assertFalse(skill.hasName("eletricista"));

    }
    @Test
    void ensureCloneWorks() {
        String name = "Especialista de Pesados";
        Skill skill = new Skill(name);
        Skill clone = skill.clone();
        assertEquals(skill, clone);
    }





}
