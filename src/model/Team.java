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

    public static Team fromCsv(String csv) {
        String[] data = csv.split(",");
        return new Team(data[0], data[1], data[2], data[3], data[4], Integer.parseInt(data[5]));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + ((coach == null) ? 0 : coach.hashCode());
        result = prime * result + ((arena == null) ? 0 : arena.hashCode());
        result = prime * result + ((owner == null) ? 0 : owner.hashCode());
        result = prime * result + championships;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Team other = (Team) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (city == null) {
            if (other.city != null)
                return false;
        } else if (!city.equals(other.city))
            return false;
        if (coach == null) {
            if (other.coach != null)
                return false;
        } else if (!coach.equals(other.coach))
            return false;
        if (arena == null) {
            if (other.arena != null)
                return false;
        } else if (!arena.equals(other.arena))
            return false;
        if (owner == null) {
            if (other.owner != null)
                return false;
        } else if (!owner.equals(other.owner))
            return false;
        if (championships != other.championships)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return name + "," + city + "," + coach + "," + arena + "," + owner + "," + championships;
    }
}
