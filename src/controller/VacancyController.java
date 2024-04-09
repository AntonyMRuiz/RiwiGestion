package controller;

import javax.swing.*;
import java.util.List;

public class VacancyController {
    public static void create() {
        String name = JOptionPane.showInputDialog("Enter name's Product: ");
        Float price = Float.parseFloat(JOptionPane.showInputDialog("Enter price's Product: "));
        int stock = Integer.parseInt(JOptionPane.showInputDialog("Enter stock's Product: "));

        Store id_Store = (Store) Util.listToArray(StoreController.instanceModel().findAll());

        instanceModel().create(new Product(name, price, stock, id_Store.getId(), id_Store));
    }

    public static void delete() {
        instanceModel().delete(Util.listToArray(instanceModel().findAll()));
    }

    public static void update() {
        Product selectedProduct = (Product) Util.listToArray(instanceModel().findAll());

        selectedProduct.setName(JOptionPane.showInputDialog("Enter name's Product: ", selectedProduct.getName()));
        selectedProduct.setPrice(Float.parseFloat(JOptionPane.showInputDialog("Enter price's Product: ", selectedProduct.getPrice())));
        selectedProduct.setStock(Integer.parseInt(JOptionPane.showInputDialog("Enter stock's Product: ", selectedProduct.getStock())));

        selectedProduct.setId_Store(((Store) Util.listToArray(StoreController.instanceModel().findAll())).getId());
        instanceModel().update(selectedProduct);
    }

    public static void updateStock(int stock, Product product) {
        product.setStock(stock);
        instanceModel().update(product);
    }

    public static void showAll() {
        String products = "List of Products : \n";

        for (Object temp : instanceModel().findAll()) {
            Product product = (Product) temp;
            products += product.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, products);
    }

    public static void filter() {
        String field = Util.listToArray(List.of(new String[]{"product.name", "product.price" ,"store.name", "store.locate"}));
        List<Object> filter = instanceModel().getByField(field, JOptionPane.showInputDialog("Search: "));

        String products = "List of Product filter for " + field + " : \n";

        for (Object temp : filter) {
            Product product = (Product) temp;
            products += product.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, products);
    }

    public static ProductModel instanceModel() {
        return new ProductModel();
    }
}
