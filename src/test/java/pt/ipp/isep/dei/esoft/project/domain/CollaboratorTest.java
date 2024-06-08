package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CollaboratorTest {

    @Test
    void ensureCollaboratorIsCreatedSuccessfully() {
        Job job = new Job("Jardineiro");
        Collaborator collaborator = new Collaborator("André Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job);
    }

    @Test
    void testEqualsSameObject() {
        Job job = new Job("Jardineiro");
        Collaborator collaborator = new Collaborator("André Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job);

        assertEquals(collaborator, collaborator);
    }

    @Test
    void ensureTwoCollaboratorsWithSameIdentificationDocumentNumberEquals() {
        Job job = new Job("Jardineiro");
        Collaborator collaborator1 = new Collaborator("André Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job);

        Collaborator collaborator2 = new Collaborator("Ana Dana", "2001/6/24", "2022/6/30", "Rua das Poças, 2890-234, Matosinhos",
                939393939, "anadan4@gmail.com", 457395439, "BI", "20735924 7", job);

        assertEquals(collaborator1, collaborator2);
    }

    @Test
    void ensureTwoCollaboratorsWithoutSameIdentificationDocumentNumberNotEquals() {
        Job job = new Job("Jardineiro");
        Collaborator collaborator1 = new Collaborator("André Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job);

        Collaborator collaborator2 = new Collaborator("Ana Dana", "2001/6/24", "2022/6/30", "Rua das Poças, 2890-234, Matosinhos",
                939393939, "anadan4@gmail.com", 457395439, "BI", "20455924 7", job);

        assertNotEquals(collaborator1, collaborator2);
    }

    @Test
    void ensureCollaboratorDoesNotEqualNull() {
        Job job = new Job("Jardineiro");
        Collaborator collaborator = new Collaborator("André Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job);

        assertNotEquals(collaborator, null);
    }

    @Test
    void ensureCollaboratorDoesNotEqualOtherObject() {
        Job job = new Job("Jardineiro");
        Collaborator collaborator = new Collaborator("André Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job);

        assertNotEquals(collaborator, new Object());
    }

    @Test
    void ensureCloneWorks() {
        Job job = new Job("Jardineiro");
        Collaborator collaborator = new Collaborator("André Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job);

        Collaborator clone = collaborator.clone();

        assertEquals(collaborator, clone);
    }

    @Test
    void ensureGetNameWorks() {
        Job job = new Job("Jardineiro");
        Collaborator collaborator = new Collaborator("André Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job);

        assertEquals("André Gomes", collaborator.getName());
    }

    @Test
    void ensureGetIdentificationDocumentNumberWorks() {
        Job job = new Job("Jardineiro");
        Collaborator collaborator = new Collaborator("André Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job);

        assertEquals("20735924 7", collaborator.getIdentificationDocumentNumber());
    }

    @Test
    void ensureAssignSkillWorks() {
        Job job = new Job("Jardineiro");
        Collaborator collaborator = new Collaborator("Andre Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job);

        Skill skill1 = new Skill("Podar arvores");
        Skill skill2 = new Skill("Conduzir pesados");

        List<Skill> skills = new ArrayList<>();
        skills.add(skill1);
        skills.add(skill2);

        collaborator.assignSkill(skills);
    }

    @Test
    void testRemoveAlreadyAssignedSkills() {
        Job job = new Job("Jardineiro");
        Collaborator collaborator = new Collaborator("Andre Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job);

        Skill skill1 = new Skill("Podar arvores");
        Skill skill2 = new Skill("Conduzir pesados");

        List<Skill> skills = new ArrayList<>();
        skills.add(skill1);
        skills.add(skill2);

        collaborator.assignSkill(skills);

        List<Skill> expectedSkills = new ArrayList<>();
        collaborator.removeAlreadyAssignedSkills(skills);

        assertEquals(expectedSkills, skills);
    }

    @Test
    void ensureGetSkillsWorks() {
        Job job = new Job("Jardineiro");
        Collaborator collaborator = new Collaborator("Andre Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job);

        Skill skill1 = new Skill("Podar arvores");
        Skill skill2 = new Skill("Conduzir pesados");

        List<Skill> skills = new ArrayList<>();
        skills.add(skill1);
        skills.add(skill2);

        collaborator.assignSkill(skills);

        assertEquals(skills, collaborator.getSkills());
    }

    @Test
    void setNameWithNumbers() {
        Job job = new Job("Jardineiro");
        assertThrows(IllegalArgumentException.class,
                () -> new Collaborator("4ndré G0mes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                        919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job));
    }

    @Test
    void setEmptyName() {
        Job job = new Job("Jardineiro");
        assertThrows(IllegalArgumentException.class,
                () -> new Collaborator("", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                        919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job));
    }

    @Test
    void setTooBigName() {
        Job job = new Job("Jardineiro");
        assertThrows(IllegalArgumentException.class,
                () -> new Collaborator("Maria Giovana Alves Da Silva Pereira Cabral", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                        919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job));
    }

    @Test
    void setBirthdateWithInvalidFormat() {
        Job job = new Job("Jardineiro");
        assertThrows(IllegalArgumentException.class,
                () -> new Collaborator("André Gomes", "2000/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                        919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job));
    }

    @Test
    void setBirthdateWithNonNumericValue() {
        Job job = new Job("Jardineiro");
        assertThrows(IllegalArgumentException.class,
                () -> new Collaborator("André Gomes", "2000/1/a13", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                        919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job));
    }

    @Test
    void setBirthdateLessthan18YearsOld() {
        Job job = new Job("Jardineiro");
        assertThrows(IllegalArgumentException.class,
                () -> new Collaborator("André Gomes", "2010/1/13", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                        919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job));
    }

    @Test
    void setAdmissionDateWithInvalidFormat() {
        Job job = new Job("Jardineiro");
        assertThrows(IllegalArgumentException.class,
                () -> new Collaborator("André Gomes", "2000/1/1", "2020/2", "Rua Amanhã, 3366-089, Porto",
                        919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job));
    }

    @Test
    void setAdmissionDateWithNonNumericValue() {
        Job job = new Job("Jardineiro");
        assertThrows(IllegalArgumentException.class,
                () -> new Collaborator("André Gomes", "2000/1/1", "2020/2/e20", "Rua Amanhã, 3366-089, Porto",
                        919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job));
    }

    @Test
    void setAdmissionDateLessthan18YearsOld() {
        Job job = new Job("Jardineiro");
        assertThrows(IllegalArgumentException.class,
                () -> new Collaborator("André Gomes", "2000/1/13", "2017/2/20", "Rua Amanhã, 3366-089, Porto",
                        919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job));
    }

    @Test
    void setAddressWithInvalidFormat() {
        Job job = new Job("Jardineiro");
        assertThrows(IllegalArgumentException.class,
                () -> new Collaborator("André Gomes", "2000/1/13", "2020/2/20", "Rua Amanhã: 3366-089, Porto",
                        919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job));
    }

    @Test
    void setAddressWithEmptyStreet() {
        Job job = new Job("Jardineiro");
        assertThrows(IllegalArgumentException.class,
                () -> new Collaborator("André Gomes", "2000/1/13", "2020/2/20", ", 3366-089, Porto",
                        919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job));

    }

    @Test
    void setAddressWithInvalidZipcode() {
        Job job = new Job("Jardineiro");
        assertThrows(IllegalArgumentException.class,
                () -> new Collaborator("André Gomes", "2000/1/13", "2020/2/20", "Rua Amanhã, 33a-089, Porto",
                        919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job));

    }

    @Test
    void setAddressWithEmptyCity() {
        Job job = new Job("Jardineiro");
        assertThrows(IllegalArgumentException.class,
                () -> new Collaborator("André Gomes", "2000/1/13", "2020/2/20", "Rua Amanhã, 3366-089,",
                        919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job));


    }

    @Test
    void setPhoneNumberWithInvalidFormat() {
        Job job = new Job("Jardineiro");
        assertThrows(IllegalArgumentException.class,
                () -> new Collaborator("André Gomes", "2000/1/13", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                        999191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job));
    }

    @Test
    void setTaxpayerNumberWithInvalidFormat() {
        Job job = new Job("Jardineiro");
        assertThrows(IllegalArgumentException.class,
                () -> new Collaborator("André Gomes", "2000/1/13", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                        919191919, "andreamanha3@gmail.com", 54688266, "BI", "20735924 7", job));

    }

    @Test
    void setEmailAddressWithInvalidFormat() {
        Job job = new Job("Jardineiro");
        assertThrows(IllegalArgumentException.class,
                () -> new Collaborator("André Gomes", "2000/1/13", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                        919191919, "@andreamanha3@gmail.com", 546882666, "BI", "20735924 7", job));
    }

    @Test
    void setInvalidIdentificationDocumentType() {
        Job job = new Job("Jardineiro");
        assertThrows(IllegalArgumentException.class,
                () -> new Collaborator("André Gomes", "2000/1/13", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                        919191919, "andreamanha3@gmail.com", 546882666, "B", "20735924 7", job));
    }

    @Test
    void setInvalidIdentificationDocumentNumberBI() {
        Job job = new Job("Jardineiro");
        assertThrows(IllegalArgumentException.class,
                () -> new Collaborator("André Gomes", "2000/1/13", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                        919191919, "andreamanha3@gmail.com", 546882666, "BI", "207359247", job));
    }

    @Test
    void setInvalidIdentificationDocumentNumberCC() {
        Job job = new Job("Jardineiro");
        assertThrows(IllegalArgumentException.class,
                () -> new Collaborator("André Gomes", "2000/1/13", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                        919191919, "andreamanha3@gmail.com", 546882666, "CC", "207359247", job));
    }

    @Test
    void setInvalidIdentificationDocumentNumberPassport() {
        Job job = new Job("Jardineiro");
        assertThrows(IllegalArgumentException.class,
                () -> new Collaborator("André Gomes", "2000/1/13", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                        919191919, "andreamanha3@gmail.com", 546882666, "Passport", "207359247", job));
    }

    @Test
    void sameIdNumberWithSameId() {
        Job job = new Job("Jardineiro");
        Collaborator collaborator = new Collaborator("André Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job);

        assertTrue(collaborator.sameIdNumber("20735924 7"));
    }

    @Test
    void sameIdNumberWithoutSameId() {
        Job job = new Job("Jardineiro");
        Collaborator collaborator = new Collaborator("André Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job);

        assertFalse(collaborator.sameIdNumber("207359256 7"));
    }


}
