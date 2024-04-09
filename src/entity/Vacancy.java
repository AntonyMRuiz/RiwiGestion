package entity;

public class Vacancy {
    private int id;
    private String title;
    private String description;
    private String duration;
    private String status;
    private String technology;
    private int companyId;

    private Company company;

    public Vacancy() {
    }

    public Vacancy(String title, String description, String duration, String status, String technology, int companyId) {
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.status = status;
        this.technology = technology;
        this.companyId = companyId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return  "| title:  " + this.title + " |\n" +
                "| description: " + this.description + " |\n" +
                "| duration: " + this.duration + " |\n" +
                "| status: " + this.status + " |\n" +
                "| technology: " + this.technology + " |\n" +
                "| company: " + this.company.getName() + " |\n \n";
    }
}
