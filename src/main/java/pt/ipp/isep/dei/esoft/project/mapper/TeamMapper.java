package pt.ipp.isep.dei.esoft.project.mapper;

import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.mapper.dto.TeamDTO;

import java.util.ArrayList;
import java.util.List;

public class TeamMapper {

    public static TeamDTO toDTO(Team team) {
        return new TeamDTO(team.getCollaborators());
    }

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
