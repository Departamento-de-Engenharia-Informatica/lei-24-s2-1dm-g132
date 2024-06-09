package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.domain.GSTask;
import pt.ipp.isep.dei.esoft.project.mapper.dto.GSTaskDTO;
import pt.ipp.isep.dei.esoft.project.repository.Agenda;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.TeamRepository;

import java.util.List;

public class ListGSTasksController {

    private final Agenda agenda;
    private final TeamRepository teamRepository;

    public ListGSTasksController() {
        this.agenda = getAgenda();
        this.teamRepository = getTeamRepository();
    }

    private Agenda getAgenda() {
        if (agenda == null) {
            return Repositories.getInstance().getAgenda();
        }
        return agenda;
    }

    private TeamRepository getTeamRepository() {
        if (teamRepository == null) {
            return Repositories.getInstance().getTeamRepository();
        }
        return teamRepository;

    }

    public List<GSTask> getTasksForLoggedCollaborator() {
        String email = ApplicationSession.getInstance().getCurrentSession().getUserEmail();
        return agenda.getTasksByCollaborator(email);
    }

}
