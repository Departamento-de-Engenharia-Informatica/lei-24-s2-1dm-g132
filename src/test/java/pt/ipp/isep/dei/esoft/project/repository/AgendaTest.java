package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.mapper.dto.GSTaskDTO;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AgendaTest {

    @Test
    void ensureAddEntryWorks() {
        Agenda agenda = new Agenda();

        GreenSpace greenSpace = new GreenSpace("Jardim do morro", "Ponte D. Luís", 6, "garden", "gsm1@this.app");
        GSTask gsTask = new GSTask("Pruning Trees", "Prune all trees", "Medium", 4, greenSpace);

        agenda.addEntry(gsTask, "2024/8/9");
    }

    @Test
    void ensureGetAgendaEntriesWorks() {
        Agenda agenda = new Agenda();

        GreenSpace greenSpace1 = new GreenSpace("Jardim do morro", "Ponte D. Luís", 6, "garden", "gsm1@this.app");
        GSTask gsTask1 = new GSTask("Pruning Trees", "Prune all trees", "Medium", 4, greenSpace1);

        Optional<GSTask> gsTaskOptional1 = agenda.addEntry(gsTask1, "2024/8/9");

        GreenSpace greenSpace2 = new GreenSpace("Jardim do morro", "Ponte D. Luís", 6, "garden", "gsm2@this.app");
        GSTask gsTask2 = new GSTask("Pruning Trees", "Prune all trees", "Medium", 4, greenSpace2);

        Optional<GSTask> gsTaskOptional2 = agenda.addEntry(gsTask2, "2024/8/9");

        List<GSTask> expectedList = new ArrayList<>();
        gsTaskOptional2.ifPresent(expectedList::add);

        List<GSTask> result = agenda.getAgendaEntries("gsm2@this.app");

        assertEquals(expectedList, result);
    }

    @Test
    void ensureGetSelectedTaskWorks1() {
        Agenda agenda = new Agenda();

        GreenSpace greenSpace1 = new GreenSpace("Jardim do morro", "Ponte D. Luís", 6, "garden", "gsm1@this.app");
        GSTask gsTask1 = new GSTask("Pruning Trees", "Prune all trees", "Medium", 4, greenSpace1);

        Optional<GSTask> gsTaskOptional1 = agenda.addEntry(gsTask1, "2024/8/9");

        GreenSpace greenSpace2 = new GreenSpace("Jardim do morro", "Ponte D. Luís", 6, "garden", "gsm2@this.app");
        GSTask gsTask2 = new GSTask("Pruning Trees", "Prune all trees", "Medium", 4, greenSpace2);

        Optional<GSTask> gsTaskOptional2 = agenda.addEntry(gsTask2, "2024/8/9");

        List<GSTask> result = agenda.getAgendaEntries("gsm2@this.app");

        agenda.getSelectedTask(0);
    }

    @Test
    void ensureGetSelectedTaskWorks2() {
        Agenda agenda = new Agenda();

        GreenSpace greenSpace1 = new GreenSpace("Jardim do morro", "Ponte D. Luís", 6, "garden", "gsm1@this.app");
        GSTask gsTask1 = new GSTask("Pruning Trees", "Prune all trees", "Medium", 4, greenSpace1);

        Optional<GSTask> gsTaskOptional1 = agenda.addEntry(gsTask1, "2024/8/9");

        GreenSpace greenSpace2 = new GreenSpace("Jardim do morro", "Ponte D. Luís", 6, "garden", "gsm2@this.app");
        GSTask gsTask2 = new GSTask("Pruning Trees", "Prune all trees", "Medium", 4, greenSpace2);

        Optional<GSTask> gsTaskOptional2 = agenda.addEntry(gsTask2, "2024/8/9");

        List<GSTask> result = agenda.getAgendaEntries("gsm2@this.app");

        assertThrows(IndexOutOfBoundsException.class,
                () -> agenda.getSelectedTask(1));
    }

    @Test
    void ensureAssignTeamWorks() {
        Agenda agenda = new Agenda();

        GreenSpace greenSpace1 = new GreenSpace("Jardim do morro", "Ponte D. Luís", 6, "garden", "gsm1@this.app");
        GSTask gsTask1 = new GSTask("Pruning Trees", "Prune all trees", "Medium", 4, greenSpace1);

        Optional<GSTask> gsTaskOptional1 = agenda.addEntry(gsTask1, "2024/8/9");

        GreenSpace greenSpace2 = new GreenSpace("Jardim do morro", "Ponte D. Luís", 6, "garden", "gsm2@this.app");
        GSTask gsTask2 = new GSTask("Pruning Trees", "Prune all trees", "Medium", 4, greenSpace2);

        Optional<GSTask> gsTaskOptional2 = agenda.addEntry(gsTask2, "2024/8/9");

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

        if (gsTaskOptional2.isPresent()) {
            GSTask gsTask2Retrieved = gsTaskOptional2.get();
            agenda.assignTeam(gsTask2Retrieved, team);
        }
    }

    @Test
    void ensureCheckTeamScheduleWorks1() {
        Agenda agenda = new Agenda();

        GreenSpace greenSpace1 = new GreenSpace("Jardim do morro", "Ponte D. Luís", 6, "garden", "gsm1@this.app");
        GSTask gsTask1 = new GSTask("Pruning Trees", "Prune all trees", "Medium", 4, greenSpace1);

        Optional<GSTask> gsTaskOptional1 = agenda.addEntry(gsTask1, "2024/8/9");

        GreenSpace greenSpace2 = new GreenSpace("Jardim do morro", "Ponte D. Luís", 6, "garden", "gsm2@this.app");
        GSTask gsTask2 = new GSTask("Pruning Trees", "Prune all trees", "Medium", 4, greenSpace2);

        Optional<GSTask> gsTaskOptional2 = agenda.addEntry(gsTask2, "2024/8/9");

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

        if (gsTaskOptional2.isPresent()) {
            GSTask gsTask2Retrieved = gsTaskOptional2.get();
            agenda.assignTeam(gsTask2Retrieved, team);
        }

        if (gsTaskOptional1.isPresent()) {
            GSTask gsTask1Retrieved = gsTaskOptional1.get();
            assertTrue(agenda.checkTeamSchedule(team, gsTask1Retrieved));
        }
    }

    @Test
    void ensureCheckTeamScheduleWorks2() {
        Agenda agenda = new Agenda();

        GreenSpace greenSpace1 = new GreenSpace("Jardim do morro", "Ponte D. Luís", 6, "garden", "gsm1@this.app");
        GSTask gsTask1 = new GSTask("Pruning Trees", "Prune all trees", "Medium", 5, greenSpace1);

        Optional<GSTask> gsTaskOptional1 = agenda.addEntry(gsTask1, "2024/8/9");

        GreenSpace greenSpace2 = new GreenSpace("Jardim do morro", "Ponte D. Luís", 6, "garden", "gsm2@this.app");
        GSTask gsTask2 = new GSTask("Pruning Trees", "Prune all trees", "Medium", 4, greenSpace2);

        Optional<GSTask> gsTaskOptional2 = agenda.addEntry(gsTask2, "2024/8/9");

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

        if (gsTaskOptional2.isPresent()) {
            GSTask gsTask2Retrieved = gsTaskOptional2.get();
            agenda.assignTeam(gsTask2Retrieved, team);
        }

        if (gsTaskOptional1.isPresent()) {
            GSTask gsTask1Retrieved = gsTaskOptional1.get();
            assertFalse(agenda.checkTeamSchedule(team, gsTask1Retrieved));
        }
    }
}