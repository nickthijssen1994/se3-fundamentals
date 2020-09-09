package exceptions;

import java.util.ArrayList;
import java.util.List;

public class OutOfMemory {
    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>();
        while (true){
            int integer = 42;
            integers.add(integer);
        }
    }
}
