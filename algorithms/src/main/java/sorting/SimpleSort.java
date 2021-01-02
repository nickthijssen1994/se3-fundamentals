package sorting;

public class SimpleSort {

	public static int[] sortList(int[] unsortedList){
		boolean isSorted = false;
		int currentIndex = 0;
		int nextIndex = 1;
		int listLength = unsortedList.length;
		int[] tempList = unsortedList;
		long startTime = System.currentTimeMillis();

		while (!isSorted){
			if(tempList[currentIndex] <= tempList[nextIndex]){
				currentIndex++;
				nextIndex++;
			}
			else{
				int currentNumber = tempList[currentIndex];
				int nextNumber = tempList[nextIndex];
				tempList[currentIndex] = nextNumber;
				tempList[nextIndex] = currentNumber;
				currentIndex = 0;
				nextIndex = 1;
			}

			if(nextIndex >= listLength){
				isSorted = true;
			}
		}

		long endTime = System.currentTimeMillis();
		long duration = endTime - startTime;
		printSortedList(tempList);
		System.out.println();
		System.out.println("Sorting " + listLength + " numbers took " + duration + " milliseconds.");
		return tempList;
	}

	public static void printSortedList(int[] sortedList) {

		for (int index = 0; index < sortedList.length; index++) {
			if (index % 25 == 0) {
				System.out.println();
			}
			if (sortedList[index] < 10) {
				System.out.print(" ");
			}
			System.out.print(sortedList[index] + ", ");
		}
	}

}
