package pt.ipp.isep.dei.esoft.project.domain;

import java.util.List;

/**
 * Represents a team in the system.
 */
public class Team {

    /**
     * The list of collaborators in which the team consists.
     */
    private List<Collaborator> collaborators;

    /**
     * Initializes a new instance of Team with the provided list of collaborators.
     *
     * @param collaborators The list of collaborators in the team.
     */
    public Team(List<Collaborator> collaborators)
    {
        this.collaborators = collaborators;
    }

    /**
     * Removes unavailable collaborators that are already on the team.
     *
     * @param tempCollaboratorsList The list of collaborators from which the unavailable collaborators are going to be removed.
     */
    public void removeUnavailableCollaborators(List<Collaborator> tempCollaboratorsList){
        for(Collaborator collaborator: collaborators){
            tempCollaboratorsList.remove(collaborator);
        }
    }

    /**
     * Checks if this team is equal to another object.
     *
     * @param o The object to compare.
     * @return True if the object is a Team and has the same collaborators, otherwise false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Team team = (Team) o;
        return this.collaborators.equals(team.collaborators);
    }

    /**
     * Creates a shallow copy of this team.
     *
     * @return A new Team instance with the same collaborators.
     */
    @Override
    public Team clone() {
        return new Team(this.collaborators);
    }

    /**
     * Returns a string representation of the team.
     *
     * @return A string containing the names and skills of the team's collaborators.
     */
    @Override
    public String toString() {
        String printTeam = "Team:";
        for(Collaborator collaborator : collaborators)
        {
            printTeam = String.format("%s\n%s: %s", printTeam,  collaborator.getName(), collaborator.getSkills());
        }
        return printTeam;
    }
}
