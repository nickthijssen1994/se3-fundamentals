package webshop;

import utilities.Observable;

import java.util.ArrayList;
import java.util.List;

public final class Inventory extends Observable {

    private static Inventory instance;
    private final List<Product> products;

    private Inventory() {
        products = new ArrayList<>();
    }

    public static Inventory getInstance(){
        if(instance == null){
            instance = new Inventory();
        }
        return instance;
    }

    public void addProduct(Product product) {
        products.add(product);
        notifyObservers();
    }

    public List<Product> getProducts() {
        return products;
    }
}
