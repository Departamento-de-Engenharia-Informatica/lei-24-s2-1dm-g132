package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GSTaskTest {

    @Test
    void ensureGSTaskIsCreatedSuccessfully()
    {
        GreenSpace greenSpace = new GreenSpace("Parque da águia", "Rua de Trás, 3666-389, Matosinhos", 5, "MediumSizedPark", "gsm1@this.app");
        GSTask gsTask = new GSTask("Pruning Trees", "Prune the trees in the frontyard.", "Low", 2, greenSpace);
    }

    @Test
    void testEqualsSameObject() {
        GreenSpace greenSpace = new GreenSpace("Parque da águia", "Rua de Trás, 3666-389, Matosinhos", 5, "MediumSizedPark", "gsm1@this.app");
        GSTask gsTask = new GSTask("Pruning Trees", "Prune the trees in the frontyard.", "Low", 2, greenSpace);

        assertEquals(gsTask, gsTask);
    }

    @Test
    void ensureTwoInstancesOfDifferentObjectNotEqual()
    {
        GreenSpace greenSpace = new GreenSpace("Parque da águia", "Rua de Trás, 3666-389, Matosinhos", 5, "MediumSizedPark", "gsm1@this.app");
        GSTask gsTask1 = new GSTask("Pruning Trees", "Prune the trees in the frontyard.", "Low", 2, greenSpace);
        GSTask gsTask2 = new GSTask("Pruning Trees", "Prune the trees in the frontyard.", "Low", 2, greenSpace);

        assertNotEquals(gsTask1, gsTask2);
    }

    @Test
    void ensureGSTaskDoesNotEqualNull() {
        GreenSpace greenSpace = new GreenSpace("Parque da águia", "Rua de Trás, 3666-389, Matosinhos", 5, "MediumSizedPark", "gsm1@this.app");
        GSTask gsTask = new GSTask("Pruning Trees", "Prune the trees in the frontyard.", "Low", 2, greenSpace);

        assertNotEquals(null,  gsTask);
    }

    @Test
    void ensureGSTaskDoesNotEqualOtherObject() {
        GreenSpace greenSpace = new GreenSpace("Parque da águia", "Rua de Trás, 3666-389, Matosinhos", 5, "MediumSizedPark", "gsm1@this.app");
        GSTask gsTask = new GSTask("Pruning Trees", "Prune the trees in the frontyard.", "Low", 2, greenSpace);

        assertNotEquals(new Object(), gsTask);
    }

    @Test
    void ensureCloneWorks() {
        GreenSpace greenSpace = new GreenSpace("Parque da águia", "Rua de Trás, 3666-389, Matosinhos", 5, "MediumSizedPark", "gsm1@this.app");
        GSTask gsTask = new GSTask("Pruning Trees", "Prune the trees in the frontyard.", "Low", 2, greenSpace);

        GSTask clone = gsTask.clone();
    }

    @Test
    void ensureGetTitleWorks() {
        GreenSpace greenSpace = new GreenSpace("Parque da águia", "Rua de Trás, 3666-389, Matosinhos", 5, "MediumSizedPark", "gsm1@this.app");
        GSTask gsTask = new GSTask("Pruning Trees", "Prune the trees in the frontyard.", "Low", 2, greenSpace);

        assertEquals("Pruning Trees", gsTask.getTitle());
    }

    @Test
    void ensureGetDescriptionWorks() {
        GreenSpace greenSpace = new GreenSpace("Parque da águia", "Rua de Trás, 3666-389, Matosinhos", 5, "MediumSizedPark", "gsm1@this.app");
        GSTask gsTask = new GSTask("Pruning Trees", "Prune the trees in the frontyard.", "Low", 2, greenSpace);

        assertEquals("Prune the trees in the frontyard.", gsTask.getDescription());
    }

    @Test
    void ensureGetDegreeOfUrgencyWorks() {
        GreenSpace greenSpace = new GreenSpace("Parque da águia", "Rua de Trás, 3666-389, Matosinhos", 5, "MediumSizedPark", "gsm1@this.app");
        GSTask gsTask = new GSTask("Pruning Trees", "Prune the trees in the frontyard.", "Low", 2, greenSpace);

        assertEquals("Low", gsTask.getDegreeOfUrgency());
    }

    @Test
    void ensureGetExpectedDurationWorks() {
        GreenSpace greenSpace = new GreenSpace("Parque da águia", "Rua de Trás, 3666-389, Matosinhos", 5, "MediumSizedPark", "gsm1@this.app");
        GSTask gsTask = new GSTask("Pruning Trees", "Prune the trees in the frontyard.", "Low", 2, greenSpace);

        assertEquals(2, gsTask.getExpectedDuration());
    }

    @Test
    void ensureGetGreenSpaceWorks() {
        GreenSpace greenSpace = new GreenSpace("Parque da águia", "Rua de Trás, 3666-389, Matosinhos", 5, "MediumSizedPark", "gsm1@this.app");
        GSTask gsTask = new GSTask("Pruning Trees", "Prune the trees in the frontyard.", "Low", 2, greenSpace);

        assertEquals(greenSpace, gsTask.getGreenSpace());
    }

    @Test
    void ensureGetStartingDateWorks() {
        GreenSpace greenSpace = new GreenSpace("Parque da águia", "Rua de Trás, 3666-389, Matosinhos", 5, "MediumSizedPark", "gsm1@this.app");
        GSTask gsTask = new GSTask("Pruning Trees", "Prune the trees in the frontyard.", "Low", 2, greenSpace);
        gsTask.plan("2024/7/12");

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2024);
        cal.set(Calendar.MONTH, 7 - 1);
        cal.set(Calendar.DAY_OF_MONTH, 12);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        assertTrue(cal.equals(gsTask.getStartingDate()));
    }

    @Test
    void setInvalidTitle()
    {
        GreenSpace greenSpace = new GreenSpace("Parque da águia", "Rua de Trás, 3666-389, Matosinhos", 5, "MediumSizedPark", "gsm1@this.app");
        assertThrows(IllegalArgumentException.class,
                () -> new GSTask("Pruning Trees***", "Prune the trees in the frontyard.", "Low", 2, greenSpace));
    }

    @Test
    void setInvalidDegreeOfUrgency()
    {
        GreenSpace greenSpace = new GreenSpace("Parque da águia", "Rua de Trás, 3666-389, Matosinhos", 5, "MediumSizedPark", "gsm1@this.app");
        assertThrows(IllegalArgumentException.class,
                () -> new GSTask("Pruning Trees", "Prune the trees in the frontyard.", "Lowi", 2, greenSpace));
    }

    @Test
    void setInvalidExpectedDuration()
    {
        GreenSpace greenSpace = new GreenSpace("Parque da águia", "Rua de Trás, 3666-389, Matosinhos", 5, "MediumSizedPark", "gsm1@this.app");
        assertThrows(IllegalArgumentException.class,
                () -> new GSTask("Pruning Trees", "Prune the trees in the frontyard.", "Low", -1, greenSpace));
    }

    @Test
    void ensureSetProcessedWorks()
    {
        GreenSpace greenSpace = new GreenSpace("Parque da águia", "Rua de Trás, 3666-389, Matosinhos", 5, "MediumSizedPark", "gsm1@this.app");
        GSTask gsTask = new GSTask("Pruning Trees", "Prune the trees in the frontyard.", "Low", 2, greenSpace);

        gsTask.setProcessed();

        assertEquals("Processed", gsTask.getStatus());
    }

    @Test
    void ensurePlanWorks()
    {
        GreenSpace greenSpace = new GreenSpace("Parque da águia", "Rua de Trás, 3666-389, Matosinhos", 5, "MediumSizedPark", "gsm1@this.app");
        GSTask gsTask = new GSTask("Pruning Trees", "Prune the trees in the frontyard.", "Low", 2, greenSpace);

        gsTask.plan("2024/6/25");

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2024);
        cal.set(Calendar.MONTH, 6 - 1);
        cal.set(Calendar.DAY_OF_MONTH, 25);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        assertEquals("Planned", gsTask.getStatus());
        assertTrue(cal.equals(gsTask.getStartingDate()));
    }

    @Test
    void setInvalidStartingDate1()
    {
        GreenSpace greenSpace = new GreenSpace("Parque da águia", "Rua de Trás, 3666-389, Matosinhos", 5, "MediumSizedPark", "gsm1@this.app");
        GSTask gsTask = new GSTask("Pruning Trees", "Prune the trees in the frontyard.", "Low", 2, greenSpace);

        assertThrows(IllegalArgumentException.class,
                () -> gsTask.plan("2024/7"));
    }

    @Test
    void setInvalidStartingDate2()
    {
        GreenSpace greenSpace = new GreenSpace("Parque da águia", "Rua de Trás, 3666-389, Matosinhos", 5, "MediumSizedPark", "gsm1@this.app");
        GSTask gsTask = new GSTask("Pruning Trees", "Prune the trees in the frontyard.", "Low", 2, greenSpace);

        assertThrows(IllegalArgumentException.class,
                () -> gsTask.plan("2024/7/12a"));
    }

    @Test
    void setInvalidStartingDate3()
    {
        GreenSpace greenSpace = new GreenSpace("Parque da águia", "Rua de Trás, 3666-389, Matosinhos", 5, "MediumSizedPark", "gsm1@this.app");
        GSTask gsTask = new GSTask("Pruning Trees", "Prune the trees in the frontyard.", "Low", 2, greenSpace);

        assertThrows(IllegalArgumentException.class,
                () -> gsTask.plan("2023/7/12"));
    }

    @Test
    void ensureIsPendingWorks()
    {
        GreenSpace greenSpace = new GreenSpace("Parque da águia", "Rua de Trás, 3666-389, Matosinhos", 5, "MediumSizedPark", "gsm1@this.app");
        GSTask gsTask = new GSTask("Pruning Trees", "Prune the trees in the frontyard.", "Low", 2, greenSpace);

        assertTrue(gsTask.isPending());
    }

    @Test
    void ensureHasUserEmailWorks()
    {
        GreenSpace greenSpace = new GreenSpace("Parque da águia", "Rua de Trás, 3666-389, Matosinhos", 5, "MediumSizedPark", "gsm1@this.app");
        GSTask gsTask = new GSTask("Pruning Trees", "Prune the trees in the frontyard.", "Low", 2, greenSpace);

        assertTrue(gsTask.hasUserEmail("gsm1@this.app"));
    }

    @Test
    void ensureHasTeamWorks(){
        Job job = new Job("Jardineiro");
        Collaborator collaborator = new Collaborator("André Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job);

        List<Collaborator> collaborators = new ArrayList<>();
        collaborators.add(collaborator);

        Team team = new Team(collaborators);

        GreenSpace greenSpace = new GreenSpace("Parque da águia", "Rua de Trás, 3666-389, Matosinhos", 5, "MediumSizedPark", "gsm1@this.app");
        GSTask gsTask = new GSTask("Pruning Trees", "Prune the trees in the frontyard.", "Low", 2, greenSpace);

        gsTask.plan("2024/10/15");

        gsTask.assignTeam(team);

        assertTrue(gsTask.hasTeam());
    }

    @Test
    void ensureBelongsToTeamWorks1()
    {
        Job job = new Job("Jardineiro");
        Collaborator collaborator = new Collaborator("André Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job);

        List<Collaborator> collaborators = new ArrayList<>();
        collaborators.add(collaborator);

        Team team = new Team(collaborators);

        GreenSpace greenSpace = new GreenSpace("Parque da águia", "Rua de Trás, 3666-389, Matosinhos", 5, "MediumSizedPark", "gsm1@this.app");
        GSTask gsTask = new GSTask("Pruning Trees", "Prune the trees in the frontyard.", "Low", 2, greenSpace);

        gsTask.plan("2024/10/15");

        gsTask.assignTeam(team);

        assertTrue(gsTask.belongsToTeam(team));
    }

    @Test
    void ensureBelongsToTeamWorks2()
    {
        Job job = new Job("Jardineiro");
        Collaborator collaborator = new Collaborator("André Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job);

        List<Collaborator> collaborators = new ArrayList<>();
        collaborators.add(collaborator);

        Team team = new Team(collaborators);

        GreenSpace greenSpace = new GreenSpace("Parque da águia", "Rua de Trás, 3666-389, Matosinhos", 5, "MediumSizedPark", "gsm1@this.app");
        GSTask gsTask = new GSTask("Pruning Trees", "Prune the trees in the frontyard.", "Low", 2, greenSpace);

        gsTask.plan("2024/10/15");

        assertFalse(gsTask.belongsToTeam(team));
    }

    @Test
    void ensureSendEmailWorks()
    {
        Job job = new Job("Jardineiro");
        Collaborator collaborator = new Collaborator("André Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job);

        List<Collaborator> collaborators = new ArrayList<>();
        collaborators.add(collaborator);

        Team team = new Team(collaborators);

        GreenSpace greenSpace = new GreenSpace("Parque da águia", "Rua de Trás, 3666-389, Matosinhos", 5, "MediumSizedPark", "gsm1@this.app");

        GSTask gsTask = new GSTask("Pruning Trees", "Prune the trees in the frontyard.", "Low", 2, greenSpace);

        gsTask.plan("2024/10/15");

        gsTask.assignTeam(team);
    }



}
