package pt.ipp.isep.dei.esoft.project.mapper;

import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.mapper.dto.TeamDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Mapper class for converting Team entities and DTOs.
 * This class provides static methods to convert Team objects to TeamDTO objects
 * and vice versa.
 */
public class TeamMapper {

    /**
     * Converts a Team entity to a TeamDTO.
     *
     * @param team The Team entity to convert.
     * @return The corresponding TeamDTO object.
     */
    public static TeamDTO toDTO(Team team) {
        return new TeamDTO(team.getCollaborators());
    }

    /**
     * Converts a list of Team entities to a list of TeamDTOs.
     *
     * @param teamsList The list of Team entities to convert.
     * @return A list of TeamDTO objects corresponding to the input list.
     */
    public static List<TeamDTO> toDTO(List<Team> teamsList)
    {
        List<TeamDTO> teamsListDTO = new ArrayList<>();
        for(Team team : teamsList)
        {
            teamsListDTO.add(toDTO(team));
        }
        return teamsListDTO;
    }
}
