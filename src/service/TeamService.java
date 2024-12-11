package service;

import java.util.Map;
import java.util.stream.Collectors;

import exception.DuplicateTeamException;
import exception.TeamNotFoundException;
import exception.NoTeamsException;
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

        if (allTeams.isEmpty()) {
            throw new NoTeamsException("Não existem times cadastrados no banco de dados.");
        }

        return allTeams.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> TeamMapper.toDto(entry.getValue())));
    }

    public TeamDTO getTeamFromId(int id) {
        Team team = repository.getTeamFromId(id);

        if (team == null) {
            throw new TeamNotFoundException("Time com ID " + id + " não encontrado.");
        }

        return TeamMapper.toDto(team);
    }

    public void updateTeam(int id, TeamDTO dto) {
        Team updatedTeam = TeamMapper.toEntity(dto);
        Map<Integer, Team> allTeams = repository.getAllTeams();

        if (!allTeams.containsKey(id)) {
            throw new TeamNotFoundException("Não foi possível encontrar o time de ID: " + id);
        }

        if (allTeams.entrySet().stream()
                .anyMatch(entry -> entry.getValue().equals(updatedTeam) && entry.getKey() != id)) {
            throw new DuplicateTeamException("Já existe um time com as mesmas informações.");
        }

        repository.updateTeam(id, updatedTeam);
    }

    public void deleteTeam(int id) {
        if (!repository.getAllTeams().containsKey(id)) {
            throw new TeamNotFoundException("Não foi possível encontrar o time de ID: " + id);
        }

        repository.deleteTeam(id);
    }
}
