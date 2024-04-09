package controller;

import javax.swing.*;
import java.util.List;

public class CompanyController {
    public static void create() {
        String name = JOptionPane.showInputDialog("Enter name's store: ");
        String address = JOptionPane.showInputDialog("Enter address's store: ");

        instanceModel().create(new Store(name, address));
    }

    public static void delete() {
        instanceModel().delete(Util.listToArray(instanceModel().findAll()));
    }

    public static void update() {
        Store selectedStore = (Store) Util.listToArray(instanceModel().findAll());

        selectedStore.setName(JOptionPane.showInputDialog("Enter name's store: ", selectedStore.getName()));
        selectedStore.setAddress(JOptionPane.showInputDialog("Enter address's store: ", selectedStore.getAddress()));

        instanceModel().update(selectedStore);
    }

    public static void showAll() {
        String stores = "List of stores: \n";

        for (Object temp : instanceModel().findAll()) {
            Store store = (Store) temp;
            stores += store.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, stores);
    }

    public static void filter() {
        String field = Util.listToArray(List.of(new String[]{"name", "location"}));
        List<Object> filter = instanceModel().getByField(field, JOptionPane.showInputDialog("Search: "));

        String stores = "List of stores filter for " + field + " : \n";

        for (Object temp : filter) {
            Store store = (Store) temp;
            stores += store.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, stores);
    }

    public static StoreModel instanceModel() {
        return new StoreModel();
    }
}
