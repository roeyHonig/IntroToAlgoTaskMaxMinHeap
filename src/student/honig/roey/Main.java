package student.honig.roey;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int[] A = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
        ArrayList<Integer> B = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            B.add(A[i]);
        }
        System.out.println("the array is: " + stringRepresentationOfArray(A));
        System.out.println("the arrayList is: " + stringRepresentationOfArrayList(B));
        MinMaxHeap.buildMinMaxHeapFromArray(B);
        System.out.println("the array as Max-Min Heap is: " + stringRepresentationOfArrayList(B));

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

    private static String stringRepresentationOfArrayList(ArrayList<Integer> A) {
        String stringToReturn = "{";
        for (int i = 0; i < A.size(); i++) {
            if (i == A.size() - 1) {
                stringToReturn += "" + A.get(i);
            } else {
                stringToReturn += "" + A.get(i) + ", ";
            }
        }
        stringToReturn += "}";
        return stringToReturn;
    }
}
