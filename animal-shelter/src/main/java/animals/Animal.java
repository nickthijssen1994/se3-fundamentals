package animals;

import java.util.Date;

public class Animal {

    private String name;
    private Gender gender;
    private Reservor reservedBy;

    public Animal(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
    }

    public boolean Reserve(String reservedBy){

        if(this.reservedBy == null){
            this.reservedBy = new Reservor(reservedBy, new Date());
            return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public Reservor getReservedBy() {
        return reservedBy;
    }
}
