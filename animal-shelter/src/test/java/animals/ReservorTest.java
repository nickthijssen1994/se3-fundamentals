package animals;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ReservorTest {

    @Test
    public void TestConstructor(){
        Date reservedAt = new Date();
        Reservor reservor = new Reservor("John Doe", reservedAt);
        assertEquals("John Doe", reservor.getName());
        assertEquals(reservedAt, reservor.getReservedAt());
    }
}
