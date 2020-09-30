package animals;

import java.util.Date;

public class Reservor {

    private String name;
    private Date reservedAt;

    public Reservor(String name, Date reservedAt) {
        this.name = name;
        this.reservedAt = reservedAt;
    }

    public String getName() {
        return name;
    }

    public Date getReservedAt() {
        return reservedAt;
    }
}
