package animals;

import java.util.Date;

public abstract class Animal implements Sellable {

    private String name;
    private Gender gender;
    private Reservor reservedBy;

    public Animal(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
    }

    public boolean Reserve(String reservedBy) {

        if (this.reservedBy == null) {
            this.reservedBy = new Reservor(reservedBy, new Date());
            return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public double getPrice() {
        return 0;
    }

    @Override
    public void setPrice(double price) {

    }

    public Gender getGender() {
        return gender;
    }

    public Reservor getReservedBy() {
        return reservedBy;
    }
}
