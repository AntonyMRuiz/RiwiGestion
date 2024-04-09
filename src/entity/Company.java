package entity;

public class Company {
    private int id;
    private String name;
    private String sector;
    private String location;
    private String contact;

    public Company() {
    }

    public Company(String name, String sector, String location, String contact) {
        this.name = name;
        this.sector = sector;
        this.location = location;
        this.contact = contact;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "name: " + this.name +
                ", sector='" + sector +
                ", location='" + location +
                ", contact='" + contact +
                '}';
    }
}
