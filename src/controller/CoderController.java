package controller;

import entity.Coder;
import model.CoderModel;
import util.Utils;

import javax.swing.*;
import java.util.List;

public class CoderController {
    public static void create() {
        String name = JOptionPane.showInputDialog("Enter name's coder: ");
        String lastName = JOptionPane.showInputDialog("Enter last name's coder: ");
        String document = JOptionPane.showInputDialog("Enter document's coder: ");

        int cohort = Integer.parseInt(JOptionPane.showInputDialog("Enter cohort: "));

        String cv = JOptionPane.showInputDialog("Enter cv (technology that the developer uses): ");

        String clan = Utils.selectOption(List.of(new String[]{"Meta", "Lovelace"}));

        instanceModel().create(new Coder(name, lastName, document, clan, cv, cohort));
    }

    public static void delete() {
        instanceModel().delete(Utils.selectOption(instanceModel().findAll()));
    }

    public static void update() {
        Coder selectedCoder = (Coder) Utils.selectOption(instanceModel().findAll());

        selectedCoder.setName(JOptionPane.showInputDialog("Enter name's customer: ", selectedCoder.getName()));
        selectedCoder.setLastName(JOptionPane.showInputDialog("Enter last name's customer: ", selectedCoder.getLastName()));

        instanceModel().update(selectedCoder);
    }

    public static void showAll() {
        String coders = "List of Coder: \n";

        for (Object temp : instanceModel().findAll()) {
            Coder coder = (Coder) temp;
            coders += coder.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, coders);
    }

    public static void filter() {
        String field = Utils.selectOption(List.of(new String[]{"name", "lastName", "email"}));
        List<Object> filter = instanceModel().getByField(field, JOptionPane.showInputDialog("Search: "));

        String coders = "List of Coder filter for " + field + " : \n";

        for (Object temp : filter) {
            Coder coder = (Coder) temp;
            coders += coder.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, coders);
    }

    public static CoderModel instanceModel() {
        return new CoderModel();
    }
}
