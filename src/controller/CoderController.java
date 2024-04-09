package controller;

import javax.swing.*;
import java.util.List;

public class CoderController {
    public static void create() {
        String name = JOptionPane.showInputDialog("Enter name's customer: ");
        String lastName = JOptionPane.showInputDialog("Enter last name's customer: ");
        String email = JOptionPane.showInputDialog("Enter email's customer: ");

        instanceModel().create(new Customer(name, lastName, email));
    }

    public static void delete() {
        instanceModel().delete(Util.listToArray(instanceModel().findAll()));
    }

    public static void update() {
        Customer selectedStore = (Customer) Util.listToArray(instanceModel().findAll());

        selectedStore.setName(JOptionPane.showInputDialog("Enter name's customer: ", selectedStore.getName()));
        selectedStore.setLastName(JOptionPane.showInputDialog("Enter last name's customer: ", selectedStore.getLastName()));
        selectedStore.setEmail(JOptionPane.showInputDialog("Enter email's customer: ",selectedStore.getEmail()));

        instanceModel().update(selectedStore);
    }

    public static void showAll() {
        String customers = "List of customer: \n";

        for (Object temp : instanceModel().findAll()) {
            Customer customer = (Customer) temp;
            customers += customer.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, customers);
    }

    public static void filter() {
        String field = Util.listToArray(List.of(new String[]{"name", "lastName", "email"}));
        List<Object> filter = instanceModel().getByField(field, JOptionPane.showInputDialog("Search: "));

        String customers = "List of customer filter for " + field + " : \n";

        for (Object temp : filter) {
            Customer customer = (Customer) temp;
            customers += customer.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, customers);
    }

    public static CustomerModel instanceModel() {
        return new CustomerModel();
    }
}
