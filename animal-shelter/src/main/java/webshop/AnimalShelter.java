package webshop;

import animals.Animal;
import animals.Dog;

public interface AnimalShelter {
    void registerGUI(AnimalShelterGUI animalShelterGUI);

    void addAnimal(Animal animal);

    void reserveAnimal(Animal animal, String reservorName);

    void walkDog(Dog dog);

    void addProduct(Product product);

    void addToCart(Sellable sellable);

    void removeFromCart(Sellable sellable);

    void checkoutCart();

    void update();
}
