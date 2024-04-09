package entity;

public class Coder {
    private int id;
    private String name;
    private String lastName;
    private String document;
    private String clan;
    private String cv;
    private int cohorte;

    public Coder() {
    }

    public Coder(String name, String lastName, String document, String clan, String cv, int cohorte) {
        this.name = name;
        this.lastName = lastName;
        this.document = document;
        this.clan = clan;
        this.cv = cv;
        this.cohorte = cohorte;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getClan() {
        return clan;
    }

    public void setClan(String clan) {
        this.clan = clan;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public int getCohorte() {
        return cohorte;
    }

    public void setCohorte(int cohorte) {
        this.cohorte = cohorte;
    }

    @Override
    public String toString() {
        return  "| name: " + this.name + " |\n" +
                "| lastName: " + this.lastName + " |\n" +
                "| document: " + this.document + " |\n" +
                "| clan: " + this.clan + " cohorte: " + this.cohorte + " |\n" +
                "|-------------------------------|\n" +
                "| cv: '" + this.cv + " |\n \n";
    }
}
