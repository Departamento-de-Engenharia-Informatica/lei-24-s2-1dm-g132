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

/**
 * Controller class for assigning teams to tasks in the agenda.
 * Provides methods to interact with the agenda, teams, and tasks.
 */
public class AssignTeamController {

    /**
     * The application session instance used to manage the current user session.
     */
    private ApplicationSession applicationSession;

    /**
     * The agenda instance used to manage agenda entries.
     */
    private Agenda agenda;

    /**
     * The team repository instance used to manage teams.
     */
    private TeamRepository teamRepository;

    /**
     * The file instance used to manage the serialization of the agenda.
     */
    private AgendaFile agendaFile;

    /**
     * The selected task instance.
     */
    private GSTask selectedTask;

    /**
     * Initializes a new instance of the AssignTeamController class.
     * Initializes necessary repositories and files.
     */
    public AssignTeamController() {
        getApplicationSession();
        getAgenda();
        getTeamRepository();
        agendaFile = new AgendaFile();
    }

    /**
     * Retrieves the application session instance.
     *
     * @return The ApplicationSession object.
     */
    private ApplicationSession getApplicationSession() {
        if (applicationSession == null) {
            applicationSession = ApplicationSession.getInstance();
        }
        return applicationSession;
    }

    /**
     * Retrieves the agenda instance.
     *
     * @return The Agenda object.
     */
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

    /**
     * Retrieves the list of agenda entries for the current user session.
     *
     * @return A list of GSTaskDTO objects representing the agenda entries.
     */
    public List<GSTaskDTO> getAgendaEntries() {
        String email = applicationSession.getCurrentSession().getUserEmail();
        List<GSTask> freeAgendaEntriesList = agenda.getAgendaEntries(email);
        List<GSTaskDTO> freeAgendaEntriesListDTO = GSTaskMapper.toDTOAgenda(freeAgendaEntriesList);
        return freeAgendaEntriesListDTO;
    }

    /**
     * Retrieves the list of teams.
     *
     * @return A list of TeamDTO objects representing the teams.
     */
    public List<TeamDTO> getTeams() {
        List<Team> teamsList = teamRepository.getTeams();
        List<TeamDTO> teamsListDTO = TeamMapper.toDTO(teamsList);
        return teamsListDTO;
    }

    /**
     * Selects a task from the agenda based on its index.
     *
     * @param i The index of the task to be selected.
     */
    public void getSelectedTask(int i)
    {
        selectedTask = agenda.getSelectedTask(i);
    }

    /**
     * Assigns a team to the selected task based on the team's index.
     * Updates the agenda file if the team is successfully assigned and there are no scheduling conflicts.
     *
     * @param i The index of the team to be assigned.
     * @return True if the team is successfully assigned and file is updated, false otherwise.
     * @throws UnsupportedOperationException If the team has scheduling conflicts.
     */
    public boolean assignTeam(int i)
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
                    return false;
                }
            }
        }
        else
        {
            throw new UnsupportedOperationException("Team has scheduling conflicts.");
        }

        return true;
    }

}
