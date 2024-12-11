package model.dto;

public record TeamDTO(
                String name,
                String city,
                String coach,
                String arena,
                String owner,
                int championships) {

        @Override
        public String toString() {
                return "Team [name=" + name + ", city=" + city + ", coach=" + coach + ", arena=" + arena + ", owner="
                                + owner + ", championships=" + championships + "]";
        }
}