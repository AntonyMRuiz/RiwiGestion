package entity;

public class Contract {
    private int id;
    private String applicationDate;
    private String status;
    private double salary;
    private int vacancyId;
    private int coderId;

    private Vacancy vacancy;
    private Coder coder;

    public Contract() {
    }

    public Contract(String status, double salary, int vacancyId, int coderId, Vacancy vacancy, Coder coder) {
        this.status = status;
        this.salary = salary;
        this.vacancyId = vacancyId;
        this.coderId = coderId;
        this.vacancy = vacancy;
        this.coder = coder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getVacancyId() {
        return vacancyId;
    }

    public void setVacancyId(int vacancyId) {
        this.vacancyId = vacancyId;
    }

    public int getCoderId() {
        return coderId;
    }

    public void setCoderId(int coderId) {
        this.coderId = coderId;
    }

    public Vacancy getVacancy() {
        return vacancy;
    }

    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
    }

    public Coder getCoder() {
        return coder;
    }

    public void setCoder(Coder coder) {
        this.coder = coder;
    }

    @Override
    public String toString() {
        return  "| vacancy: " + this.vacancy.getTitle() + " |\n" +
                "| technology: " + this.vacancy.getTechnology() + " |\n" +
                "| applicationDate: " + this.applicationDate + " |\n" +
                "| status: " + this.status + " |\n" +
                "| salary: " + this.salary + " |\n" +
                "| coder: " + this.coder.getName() + " |\n" +
                "| vacancy: " + this.coder.getCv() +" |\n \n";
    }
}
