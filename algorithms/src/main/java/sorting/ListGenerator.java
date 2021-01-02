package sorting;

import java.util.Random;

public class ListGenerator {

	private Random random = new Random();
	private int[] numbersList;

	public int[] generateRandomNumberList(int length){
		numbersList = new int[length];
		for (int index = 0; index < length; index++) {
			numbersList[index] = random.nextInt(100);
		}
		return numbersList;
	}

	public void printRandomNumberList(){
		for (int index = 0; index < numbersList.length; index++) {
			if (index % 25 == 0) {
				System.out.println();
			}
			if(numbersList[index] < 10){
				System.out.print(" ");
			}
			System.out.print(numbersList[index] + ", ");
		}
	}
}
