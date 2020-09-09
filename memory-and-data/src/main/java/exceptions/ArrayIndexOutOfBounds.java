package exceptions;

public class ArrayIndexOutOfBounds {
    public static void main(String[] args) {

        String[] array = new String[10];

        for (int index = 0; index < 10; index++) {
            array[index] = "Hallo";
        }

        System.out.println(array[1]);
        System.out.println(array[9]);
        System.out.println(array[10]);
    }
}
