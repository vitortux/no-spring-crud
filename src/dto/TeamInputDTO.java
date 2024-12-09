package dto;

public record TeamInputDTO(
        String name,
        String city,
        String coach,
        String arena,
        String owner,
        int championships) {
}