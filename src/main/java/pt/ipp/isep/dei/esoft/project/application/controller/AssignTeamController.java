package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.domain.GSTask;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.mapper.GSTaskMapper;
import pt.ipp.isep.dei.esoft.project.mapper.TeamMapper;
import pt.ipp.isep.dei.esoft.project.mapper.dto.GSTaskDTO;
import pt.ipp.isep.dei.esoft.project.mapper.dto.TeamDTO;
import pt.ipp.isep.dei.esoft.project.repository.Agenda;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.TeamRepository;
import pt.ipp.isep.dei.esoft.project.repository.serialization.AgendaFile;

import java.util.List;
import java.util.Optional;

public class AssignTeamController {

    private ApplicationSession applicationSession;

    private Agenda agenda;

    private TeamRepository teamRepository;

    private AgendaFile agendaFile;

    private GSTask selectedTask;

    public AssignTeamController() {
        getApplicationSession();
        getAgenda();
        getTeamRepository();
        agendaFile = new AgendaFile();
    }

    private ApplicationSession getApplicationSession() {
        if (applicationSession == null) {
            applicationSession = ApplicationSession.getInstance();
        }
        return applicationSession;
    }

    private Agenda getAgenda() {
        if (agenda == null) {
            Repositories repositories = Repositories.getInstance();
            agenda = repositories.getAgenda();
        }
        return agenda;
    }

    /**
     * Retrieves the team repository instance.
     *
     * @return The TeamRepository object.
     */
    private TeamRepository getTeamRepository() {
        if (teamRepository == null) {
            Repositories repositories = Repositories.getInstance();
            teamRepository = repositories.getTeamRepository();
        }
        return teamRepository;
    }

    public List<GSTaskDTO> getAgendaEntries() {
        String email = applicationSession.getCurrentSession().getUserEmail();
        List<GSTask> freeAgendaEntriesList = agenda.getAgendaEntries(email);
        List<GSTaskDTO> freeAgendaEntriesListDTO = GSTaskMapper.toDTOAgenda(freeAgendaEntriesList);
        return freeAgendaEntriesListDTO;
    }

    public List<TeamDTO> getSelectedTask(int i)
    {
        selectedTask = agenda.getSelectedTask(i);

        List<Team> teamsList = teamRepository.getTeams();
        List<TeamDTO> teamsListDTO = TeamMapper.toDTO(teamsList);
        return teamsListDTO;
    }

    public Optional<GSTask> assignTeam(int i)
    {
        Optional<GSTask> updatedTask = Optional.empty();

        Team selectedTeam = teamRepository.getSelectedTeam(i);

        if(agenda.checkTeamSchedule(selectedTeam, selectedTask))
        {
            updatedTask = agenda.assignTeam(selectedTask, selectedTeam);

            if(updatedTask.isPresent())
            {
                if(!agendaFile.save(agenda))
                {
                    System.out.println("Error while saving Agenda in external file!");
                }
            }
        }
        else
        {
            System.out.printf("Selected team cannot be responsible for the task due to schedule conflicts.");
        }

        return updatedTask;
    }

}
