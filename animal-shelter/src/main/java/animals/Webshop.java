package animals;

import java.util.ArrayList;
import java.util.List;

public class Webshop {

    private List<Sellable> inventory;

    public Webshop() {
        inventory = new ArrayList<>();
    }

    public void addToInventory(Sellable sellable) {
        this.inventory.add(sellable);
    }

    public List<Sellable> getInventory() {
        return inventory;
    }
}
