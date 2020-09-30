package webshop;

import utilities.Observable;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart extends Observable {

    private final List<Sellable> sellables;

    public ShoppingCart() {
        sellables = new ArrayList<>();
    }

    public void addProductToCart(Sellable sellable) {
        sellables.add(sellable);
        notifyObservers();
    }

    public void removeProductFromCart(Sellable sellable){
        sellables.remove(sellable);
        notifyObservers();
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for (Sellable product : sellables) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }

    public List<Sellable> getSellables() {
        return sellables;
    }
}
