package animals;

import java.util.Date;

public class Dog extends Animal {

    private Date lastWalk;

    public Dog(String name, Gender gender) {
        super(name, gender);
        this.lastWalk = new Date();
    }

    public boolean needsWalk() {
        Date today = new Date();
        return (today.getTime() - lastWalk.getTime()) > 86400000;
    }

    public Date getLastWalk() {
        return lastWalk;
    }
}
