package exceptions;

public class StackOverflow {
    public static void main(String[] args) {
        recursiveFunction(1);
    }

    private static int recursiveFunction(int i){
        i++;
        return recursiveFunction(i);
    }
}
