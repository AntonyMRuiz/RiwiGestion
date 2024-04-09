package controller;

import entity.Company;
import model.CompanyModel;
import util.Utils;

import javax.swing.*;
import java.util.List;

public class CompanyController {
    public static void create() {
        String name = JOptionPane.showInputDialog("Enter name's company: ");
        String sector = JOptionPane.showInputDialog("Enter Sector's company: ");
        String location = JOptionPane.showInputDialog("Enter location's company: ");
        String contact = JOptionPane.showInputDialog("Enter contact's company: ");

        instanceModel().create(new Company(name, sector, location, contact));
    }

    public static void delete() {
        instanceModel().delete(Utils.selectOption(instanceModel().findAll()));
    }

    public static void update() {
        Company selectedCompany = (Company) Utils.selectOption(instanceModel().findAll());

        selectedCompany.setName(JOptionPane.showInputDialog("Enter name's company: ", selectedCompany.getName()));
        selectedCompany.setSector(JOptionPane.showInputDialog("Enter sector's company: ", selectedCompany.getSector()));
        selectedCompany.setLocation(JOptionPane.showInputDialog("Enter location's company: ", selectedCompany.getLocation()));
        selectedCompany.setContact(JOptionPane.showInputDialog("Enter Contact",selectedCompany.getContact()));

        instanceModel().update(selectedCompany);
    }

    public static void showAll() {
        String companys = "List of Company: \n";

        for (Object temp : instanceModel().findAll()) {
            Company company = (Company) temp;
            companys += company.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, companys);
    }

    public static void filter() {
        String field = Utils.selectOption(List.of(new String[]{"name", "location"}));
        List<Object> filter = instanceModel().getByField(field, JOptionPane.showInputDialog("Search: "));

        String companys = "List of Company filter for " + field + " : \n";

        for (Object temp : filter) {
            Company company = (Company) temp;
            companys += company.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, companys);
    }

    public static CompanyModel instanceModel() {
        return new CompanyModel();
    }
}
