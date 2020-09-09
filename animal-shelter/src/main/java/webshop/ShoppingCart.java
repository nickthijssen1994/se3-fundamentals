package webshop;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private List<Sellable> products;

    public ShoppingCart() {
        products = new ArrayList<>();
    }

    public void addProductToCart(Sellable sellable){
        this.products.add(sellable);
    }

    public double getTotalPrice(){
        double totalPrice = 0;
        for (Sellable product : products) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }
}
