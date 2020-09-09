package bits;

public class OnOffBit {

    private static int iterations = 20;

    public static void main(String[] args) {
        exclusiveOr();
        subtraction();
        withoutVariables();
    }

    private static void exclusiveOr(){

        System.out.println("Using bit ^= 1");

        byte bit = 0; // 0 = 0000 0000, 1 = 0000 0001

        for (int i = 0; i <= iterations; i += 2) {
            System.out.println("Value of i: " + i + ", value of bit: " + bit);
            bit ^= 1; // 0000 0000 ^= 0000 0001
        }

        System.out.println();
    }

    private static void subtraction(){

        System.out.println("Using bit = 1 - bit");

        byte bit = 0; // 0 = 0000 0000, 1 = 0000 0001

        for (int i = 0; i <= iterations; i += 2) {
            System.out.println("Value of i: " + i + ", value of bit: " + bit);
            bit = (byte) (1 - bit);
        }

        System.out.println();
    }

    private static void withoutVariables(){

        System.out.println("Using ((i >> 1) & 1)");

        for (int i = 0; i <= iterations; i += 2) {
            System.out.println("Value of i: " + i + ", value of bit: " + ((i >> 1) & 1));
        }

        System.out.println();
    }

}
