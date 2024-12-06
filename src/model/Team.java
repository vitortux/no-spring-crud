package model;

public class Team {
    private String name;
    private String city;
    private String coach;
    private String arena;
    private String owner;
    private int championships;

    public Team(String name, String city, String coach, String arena, String owner, int championships) {
        this.name = name;
        this.city = city;
        this.coach = coach;
        this.arena = arena;
        this.owner = owner;
        this.championships = championships;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public String getArena() {
        return arena;
    }

    public void setArena(String arena) {
        this.arena = arena;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getChampionships() {
        return championships;
    }

    public void setChampionships(int championships) {
        this.championships = championships;
    }

    @Override
    public String toString() {
        return name + "," + city + "," + coach + "," + arena + "," + owner + "," + championships;
    }
}
