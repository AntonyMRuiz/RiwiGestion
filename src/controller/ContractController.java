package controller;

import entity.Coder;
import entity.Contract;
import entity.Vacancy;
import model.ContractModel;
import util.Utils;

import javax.swing.*;
import java.util.List;

public class ContractController {
    public static void create() {

        Vacancy vacancy = (Vacancy) Utils.selectOption(VacancyController.instanceModel().findAll());

        if (vacancy.getStatus() == "Inactive") {
            JOptionPane.showMessageDialog(null,"Not possible to create contract (Vacancy) because it is inactive)");
            return;
        } else {
            vacancy.setStatus("Inactive");
        }

        double salary = Double.parseDouble(JOptionPane.showInputDialog("Enter Salary: "));

        Coder coder = (Coder) Utils.selectOption(CoderController.instanceModel().findAll());

        coder.getCv().contains(vacancy.getTechnology());

        instanceModel().create(new Contract("Active", salary, vacancy.getId(), coder.getId(), vacancy, coder));
    }

    public static void delete() {
        instanceModel().delete(Utils.selectOption(instanceModel().findAll()));
    }

    public static void update() {
        Contract selectedContract = (Contract) Utils.selectOption(instanceModel().findAll());

        Vacancy vacancy = (Vacancy) Utils.selectOption(VacancyController.instanceModel().findAll());
        selectedContract.setVacancyId(vacancy.getId());

        Coder coder = (Coder) Utils.selectOption(CoderController.instanceModel().findAll());
        selectedContract.setCoderId(coder.getId());

        selectedContract.setSalary(Double.parseDouble(JOptionPane.showInputDialog("Enter Salary: ", selectedContract.getSalary())));

        instanceModel().update(selectedContract);
    }

    public static void showAll() {
        String contracts = "List of Contracts : \n";

        for (Object temp : instanceModel().findAll()) {
            Contract contract = (Contract) temp;
            contracts += contract.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, contracts);
    }

    public static void filter() {
        String field = Utils.selectOption(List.of(new String[]{"product.name", "product.price" ,"store.name", "store.locate"}));
        List<Object> filter = instanceModel().getByField(field, JOptionPane.showInputDialog("Search: "));

        String contracts = "List of Purchase filter for " + field + " : \n";

        for (Object temp : filter) {
            Contract contract = (Contract) temp;
            contracts += contract.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, contracts);
    }

    public static ContractModel instanceModel() {
        return new ContractModel();
    }
}
