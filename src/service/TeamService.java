package service;

import java.util.Map;
import java.util.stream.Collectors;

import mapper.TeamMapper;
import model.dto.TeamDTO;
import model.entity.Team;
import repository.TeamRepository;

public class TeamService {
    private TeamRepository repository;

    public TeamService(TeamRepository repository) {
        this.repository = repository;
    }

    public boolean addTeam(TeamDTO dto) {
        Team team = TeamMapper.toEntity(dto);

        if (!repository.getAllTeams().containsValue(team)) {
            repository.addTeam(team);
            return true;
        }
        return false;
    }

    public Map<Integer, TeamDTO> getAllTeams() {
        return repository.getAllTeams().entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> TeamMapper.toDto(entry.getValue())));
    }

    public TeamDTO getTeamFromId(int id) {
        return repository.getAllTeams().containsKey(id) ? TeamMapper.toDto(repository.getTeamFromId(id)) : null;
    }

    public boolean updateTeam(int id, TeamDTO dto) {
        Team updatedTeam = TeamMapper.toEntity(dto);

        if (repository.getAllTeams().containsKey(id)) {
            repository.updateTeam(id, updatedTeam);
            return true;
        }
        return false;
    }

    public boolean deleteTeam(int id) {
        if (repository.getAllTeams().containsKey(id)) {
            repository.deleteTeam(id);
            return true;
        }
        return false;
    }
}
