package model.dto;

public record TeamDTO(
        String name,
        String city,
        String coach,
        String arena,
        String owner,
        int championships) {
}