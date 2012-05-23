/**
 * 
 */
package interviewQuestions;

/**
 * @author Rahul
 * 
 */
public class ShiftedArrayNumberFinder {
	
	int ShiftedArrayIndexNumber(Integer[] A, Integer startIndex, Integer endIndex) {
		if (startIndex == endIndex) {
			return A[startIndex];
		} 
		else {
			int midIndex = (startIndex + endIndex) / 2;
			if (A[startIndex] >= A[midIndex]) {
				return ShiftedArrayIndexNumber(A, startIndex, midIndex);
			} else {
				return ShiftedArrayIndexNumber(A, midIndex + 1, endIndex);
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ShiftedArrayNumberFinder finder = new ShiftedArrayNumberFinder();
		Integer A[] = { 10, 12, 2, 3, 4, 5, 7 };
		int number = finder.ShiftedArrayIndexNumber(A, 0, 6);
		System.out.println(number);
	}

}
