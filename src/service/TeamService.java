package service;

import java.util.Map;
import java.util.stream.Collectors;

import exception.DuplicateTeamException;
import mapper.TeamMapper;
import model.dto.TeamDTO;
import model.entity.Team;
import repository.TeamRepository;
import validation.TeamFormatValidation;

public class TeamService {
    private TeamRepository repository;

    public TeamService(TeamRepository repository) {
        this.repository = repository;
    }

    public void addTeam(TeamDTO dto) {
        Team team = TeamMapper.toEntity(dto);

        new TeamFormatValidation().validate(team);

        if (repository.getAllTeams().containsValue(team)) {
            throw new DuplicateTeamException("O time " + team.getName() + " já foi cadastrado.");
        }

        repository.addTeam(team);
    }

    public Map<Integer, TeamDTO> getAllTeams() {
        Map<Integer, Team> allTeams = repository.getAllTeams();

        // TODO: validação + exception para o EmptyMap().

        return allTeams.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> TeamMapper.toDto(entry.getValue())));
    }

    public TeamDTO getTeamFromId(int id) {
        return repository.getAllTeams().containsKey(id) ? TeamMapper.toDto(repository.getTeamFromId(id)) : null;
    }

    public boolean updateTeam(int id, TeamDTO dto) {
        Team updatedTeam = TeamMapper.toEntity(dto);
        Map<Integer, Team> allTeams = repository.getAllTeams();

        if (allTeams.containsValue(updatedTeam)) {
            return false;
        }

        if (!allTeams.containsKey(id)) {
            return false;
        }

        repository.updateTeam(id, updatedTeam);
        return true;
    }

    public boolean deleteTeam(int id) {
        if (repository.getAllTeams().containsKey(id)) {
            repository.deleteTeam(id);
            return true;
        }
        return false;
    }
}
