package student.honig.roey;

public class Main {

    public static void main(String[] args) {
	// write your code here
        /*
        for (int i = 0; i < 100; i++) {
            String level = MinMaxHeap.isOnMinLevel(i)? "Min":"Max";
            System.out.println("the array index: " + i + " is on a " + level + " level");
        }
        */;
        int[] A = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
        System.out.println("the array is: " + stringRepresentationOfArray(A));
        MinMaxHeap.buildMinMaxHeapFromArray(A);
        System.out.println("the array as Min-Max Heap is: " + stringRepresentationOfArray(A));

    }

    private static String stringRepresentationOfArray(int[] A) {
        String stringToReturn = "{";
        for (int i = 0; i < A.length; i++) {
            if (i == A.length - 1) {
                stringToReturn += "" + A[i];
            } else {
                stringToReturn += "" + A[i] + ", ";
            }
        }
        stringToReturn += "}";
        return stringToReturn;
    }
}
