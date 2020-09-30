package animals;

import webshop.Sellable;

import java.util.Date;

public abstract class Animal implements Sellable {

    protected Species species;
    private String name;
    private Gender gender;
    private Reservor reservedBy;

    public Animal(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
    }

    public boolean reserve(String reservedBy) {

        if (this.reservedBy == null) {
            this.reservedBy = new Reservor(reservedBy, new Date());
            return true;
        }
        return false;
    }

    @Override
    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public Reservor getReservedBy() {
        return reservedBy;
    }

    @Override
    public String toString() {
        String reserved = ", Op For Adoption";
        if (reservedBy != null) {
            reserved = ", Reserved By - " + reservedBy.getName();
        }
        return "(" + species.getDescription() + ") " + name + ", " + gender.getDescription() + reserved + ", €" + getPrice();
    }

    public Species getSpecies() {
        return species;
    }
}
