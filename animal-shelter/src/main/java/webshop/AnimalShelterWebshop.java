package webshop;

import animals.Animal;
import animals.Dog;
import animals.Shelter;
import utilities.Observer;

public class AnimalShelterWebshop extends Observer {

    private AnimalShelterGUI animalShelterGUI;
    private Shelter shelter;
    private Inventory inventory;
    private ShoppingCart shoppingCart;

    public AnimalShelterWebshop() {
        shelter = new Shelter();
        shelter.addObserver(this);
        inventory = new Inventory();
        inventory.addObserver(this);
        shoppingCart = new ShoppingCart();
        shoppingCart.addObserver(this);
    }

    public void registerGUI(AnimalShelterGUI animalShelterGUI) {
        this.animalShelterGUI = animalShelterGUI;
    }

    public void addAnimal(Animal animal) {
        shelter.addAnimal(animal);
    }


    public void reserveAnimal(Animal animal, String reservorName) {

    }


    public void walkDog(Dog dog) {

    }


    public void addProduct(Product product) {
        inventory.addProduct(product);
    }


    public void addToCart(Sellable sellable) {
        shoppingCart.addProductToCart(sellable);
    }


    public void removeFromCart(Sellable sellable) {
        shoppingCart.removeProductFromCart(sellable);
    }


    public void checkoutCart() {

    }

    @Override
    public void update() {
        animalShelterGUI.updateAnimals((shelter.getAnimals()));
        animalShelterGUI.updateInventory(inventory.getProducts());
        animalShelterGUI.updateShoppingCart(shoppingCart.getSellables());
        animalShelterGUI.updateTotalPrice((int) shoppingCart.getTotalPrice());
    }
}
