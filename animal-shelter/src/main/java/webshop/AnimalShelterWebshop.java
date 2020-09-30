package webshop;

import animals.Animal;
import animals.Dog;
import animals.Shelter;
import utilities.Observer;

public class AnimalShelterWebshop extends Observer implements AnimalShelter {

    private AnimalShelterGUI animalShelterGUI;
    private Shelter shelter;
    private Inventory inventory;
    private ShoppingCart shoppingCart;

    public AnimalShelterWebshop() {
        shelter = Shelter.getInstance();
        shelter.addObserver(this);
        inventory = Inventory.getInstance();
        inventory.addObserver(this);
        shoppingCart = new ShoppingCart();
        shoppingCart.addObserver(this);
    }

    @Override
    public void registerGUI(AnimalShelterGUI animalShelterGUI) {
        this.animalShelterGUI = animalShelterGUI;
    }

    @Override
    public void addAnimal(Animal animal) {
        shelter.addAnimal(animal);
    }


    @Override
    public void reserveAnimal(Animal animal, String reservorName) {
        animal.reserve(reservorName);
    }


    @Override
    public void walkDog(Dog dog) {

    }


    @Override
    public void addProduct(Product product) {
        inventory.addProduct(product);
    }


    @Override
    public void addToCart(Sellable sellable) {
        shoppingCart.addProductToCart(sellable);
    }


    @Override
    public void removeFromCart(Sellable sellable) {
        shoppingCart.removeProductFromCart(sellable);
    }


    @Override
    public void checkoutCart() {

    }

    @Override
    public void update() {
        animalShelterGUI.updateAnimals((shelter.getAnimals()));
        animalShelterGUI.updateInventory(inventory.getProducts());
        animalShelterGUI.updateShoppingCart(shoppingCart.getSellables());
        animalShelterGUI.updateTotalPrice(shoppingCart.getTotalPrice());
    }
}
