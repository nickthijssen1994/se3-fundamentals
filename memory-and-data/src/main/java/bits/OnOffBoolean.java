package bits;

public class OnOffBoolean {

    private static int iterations = 20;

    public static void main(String[] args) {
        isNot();
        exclusiveOr();
        withoutVariables();
    }

    private static void isNot() {
        System.out.println("Using bool = !bool");
        boolean bool = false;
        for (int i = 0; i <= iterations; i += 2) {
            System.out.println("Value of i: " + i + ", value of bool: " + bool);
            bool = !bool;
        }
        System.out.println();
    }

    private static void exclusiveOr() {
        System.out.println("Using bool ^= true");
        boolean bool = false;
        for (int i = 0; i <= iterations; i += 2) {
            System.out.println("Value of i: " + i + ", value of bool: " + bool);
            bool ^= true;
        }
        System.out.println();
    }

    private static void withoutVariables() {
        System.out.println("Using (i % 4 == 2)");
        for (int i = 0; i <= iterations; i += 2) {
            System.out.println("Value of i: " + i + ", value of bool: " + (i % 4 == 2));
        }
        System.out.println();
    }
}
