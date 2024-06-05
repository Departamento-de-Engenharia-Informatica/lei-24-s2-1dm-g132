package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GSTaskTest {

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

        gsTask.plan("2024/6/15");

        gsTask.assignTeam(team);
    }

}
