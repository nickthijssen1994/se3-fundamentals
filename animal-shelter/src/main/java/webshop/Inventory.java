package webshop;

import utilities.Observable;

import java.util.ArrayList;
import java.util.List;

public class Inventory extends Observable {

    private final List<Product> products;

    public Inventory() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
        notifyObservers();
    }

    public List<Product> getProducts() {
        return products;
    }
}
