package sorting;

public class Main {

	public static void main(String[] args) {
		ListGenerator generator = new ListGenerator();
		SimpleSort.sortList(generator.generateRandomNumberList(1000));
	}
}
