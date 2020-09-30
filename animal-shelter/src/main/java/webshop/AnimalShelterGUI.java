package webshop;

import animals.Animal;

import java.util.List;

public interface AnimalShelterGUI {

    void updateAnimals(List<Animal> animals);

    void updateInventory(List<Product> products);

    void updateShoppingCart(List<Sellable> sellables);

    void updateTotalPrice(int totalPrice);
}
