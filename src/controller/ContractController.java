package controller;

import javax.swing.*;
import java.util.List;

public class ContractController {
    public static void create() {
        String date = JOptionPane.showInputDialog("Enter Date (YYYY-MM-DD): ");

        Customer id_Customer = (Customer) Util.listToArray(CustomerController.instanceModel().findAll());

        Product id_Product = (Product) Util.listToArray(ProductController.instanceModel().findAll());

        int quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter quantity " + id_Product.getName() + " : "));

        if (quantity <= id_Product.getStock() && quantity > 0) {
            JOptionPane.showMessageDialog(null, id_Product.getName() + " added correctly");

            id_Product.setStock((id_Product.getStock() - quantity));
            ProductController.updateStock((id_Product.getStock() - quantity), id_Product);
        } else {
            JOptionPane.showMessageDialog(null, id_Product.getName() + " has " + id_Product.getStock() + " stock, insufficient stock");
            return;
        }

        instanceModel().create(new Purchase(date, quantity, id_Customer.getId(), id_Product.getId(), id_Customer, id_Product));
    }

    public static void delete() {
        instanceModel().delete(Util.listToArray(instanceModel().findAll()));
    }

    public static void update() {
        Purchase selectedPurchase = (Purchase) Util.listToArray(instanceModel().findAll());

        Customer id_Customer = (Customer) Util.listToArray(CustomerController.instanceModel().findAll());
        selectedPurchase.setId_customer(id_Customer.getId());

        Product id_Product = (Product) Util.listToArray(ProductController.instanceModel().findAll());
        selectedPurchase.setId_product(id_Product.getId());

        selectedPurchase.setDate(JOptionPane.showInputDialog("Enter Date (YYYY-MM-DD): ", selectedPurchase.getDate()));

        int quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter quantity " + selectedPurchase.getProduct().getName() + " : ", selectedPurchase.getQuantity()));

        if (quantity <= selectedPurchase.getProduct().getStock() + selectedPurchase.getQuantity() && quantity > 0) {
            JOptionPane.showMessageDialog(null, selectedPurchase.getProduct().getName() + "stock updated correctly");

            selectedPurchase.getProduct().setStock(selectedPurchase.getProduct().getStock() + selectedPurchase.getQuantity() - quantity);
            ProductController.updateStock((selectedPurchase.getProduct().getStock() + selectedPurchase.getQuantity() - quantity), id_Product);
        } else {
            JOptionPane.showMessageDialog(null, selectedPurchase.getProduct().getName() + " has " + selectedPurchase.getProduct().getStock() + " stock, insufficient stock");
            return;
        }

        selectedPurchase.setQuantity(quantity);

        instanceModel().update(selectedPurchase);
    }

    public static void showAll() {
        String purchases = "List of Purchase : \n";

        for (Object temp : instanceModel().findAll()) {
            Purchase purchase = (Purchase) temp;
            purchases += purchase.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, purchases);
    }

    public static void filter() {
        String field = Util.listToArray(List.of(new String[]{"product.name", "product.price" ,"store.name", "store.locate"}));
        List<Object> filter = instanceModel().getByField(field, JOptionPane.showInputDialog("Search: "));

        String purchases = "List of Purchase filter for " + field + " : \n";

        for (Object temp : filter) {
            Purchase purchase = (Purchase) temp;
            purchases += purchase.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, purchases);
    }

    public static PurchaseModel instanceModel() {
        return new PurchaseModel();
    }
}
