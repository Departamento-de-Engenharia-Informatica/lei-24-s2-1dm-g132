package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repository class for managing teams.
 */
public class TeamRepository implements Serializable {

    /**
     * A list of teams which consists of all the registered teams.
     */
    private final List<Team> teams;

    /**
     * Constructs a new TeamRepository object.
     */
    public TeamRepository(){
        teams = new ArrayList<>();
    }

    /**
     * Removes unavailable collaborators that are already on teams.
     *
     * @param tempCollaboratorsList The list of collaborators from which the unavailable collaborators are going to be removed.
     */
    public void removeUnavailableCollaborators(List<Collaborator> tempCollaboratorsList){
        for(Team team : teams){
            team.removeUnavailableCollaborators(tempCollaboratorsList);
        }
    }

    /**
     * Registers a new team based on the provided team proposal.
     *
     * @param teamProposal The list of collaborators proposed for the team.
     * @return An Optional containing the newly registered team if successful, otherwise empty.
     */
    public Optional<Team> registerTeam(List<Collaborator> teamProposal) {

        Optional<Team> optionalValue = Optional.empty();

        Team team = new Team(teamProposal);

        if (addTeam(team)) {
            optionalValue = Optional.of(team);
        }
        return optionalValue;
    }

    /**
     * Adds a team to the repository if it doesn't already exist.
     *
     * @param team The team to be added.
     * @return True if the team was successfully added, false otherwise.
     */
    private boolean addTeam(Team team) {
        boolean success = false;
        if (!this.teams.contains(team)) {
            success = teams.add(team.clone());
        }
        return success;
    }

    /**
     * Retrieves a defensive copy of the list of teams.
     *
     * @return A list of Team objects.
     */
    public List<Team> getTeams() {
        return List.copyOf(teams);
    }

    /**
     * Retrieves a selected team from the list of teams by its index.
     *
     * @param i the index of the team
     * @return the selected team
     */
    public Team getSelectedTeam(int i)
    {
        return teams.get(i);
    }
}
