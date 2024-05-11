package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeamRepository {
    private final List<Team> teams;

    public TeamRepository(){
        teams = new ArrayList<>();
    }

    public void removeUnavailableCollaborators(List<Collaborator> tempCollaboratorsList){
        for(Team team : teams){
            team.removeUnavailableCollaborators(tempCollaboratorsList);
        }
    }

    public Optional<Team> registerTeam(List<Collaborator> teamProposal) {

        Optional<Team> optionalValue = Optional.empty();

        Team team = new Team(teamProposal);

        if (addTeam(team)) {
            optionalValue = Optional.of(team);
        }
        return optionalValue;
    }

    private boolean addTeam(Team team) {
        boolean success = false;
        if (!this.teams.contains(team)) {
            success = teams.add(team.clone());
        }
        return success;
    }

    public List<Team> getTeams() {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(teams);
    }
}
