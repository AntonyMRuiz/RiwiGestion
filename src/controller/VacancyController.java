package controller;

import entity.Company;
import entity.Vacancy;
import model.VacancyModel;
import util.Utils;

import javax.swing.*;
import java.util.List;

public class VacancyController {
    public static void create() {
        String title = JOptionPane.showInputDialog("Enter title's Vacancy: ");
        String description = JOptionPane.showInputDialog("Enter description's Vacancy: ");
        String duration = JOptionPane.showInputDialog("Enter duration's Vacancy: ");

        Company companyId = (Company) Utils.selectOption(CompanyController.instanceModel().findAll());

        String technology = JOptionPane.showInputDialog("Enter technology's Vacancy: ");

        instanceModel().create(new Vacancy(title, description, duration, "Active", technology, companyId.getId()));
    }

    public static void delete() {
        instanceModel().delete(Utils.selectOption(instanceModel().findAll()));
    }

    public static void update() {
        Vacancy selectedVacancy = (Vacancy) Utils.selectOption(instanceModel().findAll());

        if (selectedVacancy.getStatus() == "Inactiva") {
            JOptionPane.showMessageDialog(null, "You are not allowed to update the status of the vacancy.");
            return;
        }

        selectedVacancy.setTitle(JOptionPane.showInputDialog("Enter title's Vacancy: ", selectedVacancy.getTitle()));
        selectedVacancy.setDescription(JOptionPane.showInputDialog("Enter description's Vacancy: ", selectedVacancy.getDescription()));
        selectedVacancy.setDuration(JOptionPane.showInputDialog("Enter duration's Vacancy: ", selectedVacancy.getDuration()));

        selectedVacancy.setCompanyId(((Company) Utils.selectOption(CompanyController.instanceModel().findAll())).getId());

        instanceModel().update(selectedVacancy);
    }

    public static void showAll() {
        String vacancys = "List of Products : \n";

        for (Object temp : instanceModel().findAll()) {
            Vacancy vacancy = (Vacancy) temp;
            vacancys += vacancy.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, vacancys);
    }

    public static void filter() {
        String field = Utils.selectOption(List.of(new String[]{"company.name", "company.sector", "company.location", "company.contact",
                "vacancy.title", "vacancy.description", "vacancy.duration", "vacancy.status", "vacancy.technology",
                "vacancy.id", "company.id"}));
        List<Object> filter = instanceModel().getByField(field, JOptionPane.showInputDialog("Search: "));

        String vacancys = "List of Product filter for " + field + " : \n";

        for (Object temp : filter) {
            Vacancy vacancy = (Vacancy) temp;
            vacancys += vacancy.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, vacancys);
    }

    public static VacancyModel instanceModel() {
        return new VacancyModel();
    }
}
