package student.honig.roey;

import java.util.ArrayList;

public class MinMaxHeap {

    public static void buildMinMaxHeapFromArray(ArrayList<Integer> A)
    {
        for (int i = A.size()/2; i > -1; i--) {
            heapify(A,i);
        }
    }

    private static void heapify(ArrayList<Integer> A, int i){
        if (MinMaxHeap.isOnMinLevel(i)) {
            pushDownMin(A,i);
        } else {
            pushDownMax(A,i);
        }
    }

    public static boolean isOnMinLevel(int i) {
        int j = i + 1;
        if (log2(j) % 2 == 0) {
            return false;
        }
        return true;
    }

    private static int log2(int N)
    {
        // calculate log2 N indirectly
        // using log() method
        int result = (int)(Math.log(N) / Math.log(2));
        return result;
    }

    private static void pushDownMin(ArrayList<Integer> A, int i){
        if (hasChildren(A,i)) {
            int m = getIndexOfSmallestChildeOrGrandchile(A,i);
            if (isGrandchild(m,i)) {
                if (A.get(m) < A.get(i)) {
                    swapKeys(A,i,m);
                    int indexOfParent = parentOfIndex(m);
                    if (A.get(m) > A.get(indexOfParent)) {
                        swapKeys(A,m,indexOfParent);
                    }
                    pushDownMin(A,m);
                }
            } else if (A.get(m) < A.get(i)) {
                swapKeys(A,i,m);
            }
        }
    }

    private static void pushDownMax(ArrayList<Integer> A, int i){
        if (hasChildren(A,i)) {
            int m = getIndexOfLargestChildeOrGrandchile(A,i);
            if (isGrandchild(m,i)) {
                if (A.get(m) > A.get(i)) {
                    swapKeys(A,i,m);
                    int indexOfParent = parentOfIndex(m);
                    if (A.get(m) < A.get(indexOfParent)) {
                        swapKeys(A,m,indexOfParent);
                    }
                    pushDownMax(A,m);
                }
            } else if (A.get(m) > A.get(i)) {
                swapKeys(A,i,m);
            }
        }
    }

    private static boolean hasChildren(ArrayList<Integer> A, int i){
        if (leftChildIndex(i) >= A.size()) {
            return false;
        }
        return true;
    }

    private static boolean hasRightChild(ArrayList<Integer> A, int i){
        if (rightChildIndex(i) >= A.size()) {
            return false;
        }
        return true;
    }

    private static int leftChildIndex(int i) {
        return 2 * i + 1;
    }

    private static int rightChildIndex(int i) {
        return 2 * i + 2;
    }

    private static int getIndexOfSmallestChildeOrGrandchile(ArrayList<Integer> A,int i) {
        int indexLeft = leftChildIndex(i);
        int indexRight = rightChildIndex(i);
        int indexLeftLeft = leftChildIndex(indexLeft);
        int indexLeftRight = rightChildIndex(indexLeft);
        int indexRightLeft = leftChildIndex(indexRight);
        int indexRightRight = rightChildIndex(indexRight);
        int indexToReturn = indexLeft;
        int minKey = A.get(indexLeft);
        if (indexRight < A.size() && minKey > A.get(indexRight)) {
            minKey = A.get(indexRight);
            indexToReturn = indexRight;
        }
        if (indexLeftLeft < A.size() && minKey > A.get(indexLeftLeft)) {
            minKey = A.get(indexLeftLeft);
            indexToReturn = indexLeftLeft;
        }
        if (indexLeftRight < A.size() && minKey > A.get(indexLeftRight)) {
            minKey = A.get(indexLeftRight);
            indexToReturn = indexLeftRight;
        }
        if (indexRightLeft < A.size() && minKey > A.get(indexRightLeft)) {
            minKey = A.get(indexRightLeft);
            indexToReturn = indexRightLeft;
        }
        if (indexRightRight < A.size() && minKey > A.get(indexRightRight)) {
            minKey = A.get(indexRightRight);
            indexToReturn = indexRightRight;
        }
        return indexToReturn;
    }

    private static int getIndexOfLargestChildeOrGrandchile(ArrayList<Integer> A,int i) {
        int indexLeft = leftChildIndex(i);
        int indexRight = rightChildIndex(i);
        int indexLeftLeft = leftChildIndex(indexLeft);
        int indexLeftRight = rightChildIndex(indexLeft);
        int indexRightLeft = leftChildIndex(indexRight);
        int indexRightRight = rightChildIndex(indexRight);
        int indexToReturn = indexLeft;
        int maxKey = A.get(indexLeft);
        if (indexRight < A.size() && maxKey < A.get(indexRight)) {
            maxKey = A.get(indexRight);
            indexToReturn = indexRight;
        }
        if (indexLeftLeft < A.size() && maxKey < A.get(indexLeftLeft)) {
            maxKey = A.get(indexLeftLeft);
            indexToReturn = indexLeftLeft;
        }
        if (indexLeftRight < A.size() && maxKey < A.get(indexLeftRight)) {
            maxKey = A.get(indexLeftRight);
            indexToReturn = indexLeftRight;
        }
        if (indexRightLeft < A.size() && maxKey < A.get(indexRightLeft)) {
            maxKey = A.get(indexRightLeft);
            indexToReturn = indexRightLeft;
        }
        if (indexRightRight < A.size() && maxKey < A.get(indexRightRight)) {
            maxKey = A.get(indexRightRight);
            indexToReturn = indexRightRight;
        }
        return indexToReturn;
    }

    private static int getIndexOfLargestChilde(ArrayList<Integer> A,int i) {
        int indexLeft = leftChildIndex(i);
        int indexRight = rightChildIndex(i);
        int indexToReturn = indexLeft;
        int maxKey = A.get(indexLeft);
        if (indexRight < A.size() && maxKey < A.get(indexRight)) {
            maxKey = A.get(indexRight);
            indexToReturn = indexRight;
        }
        return indexToReturn;
    }

    public static boolean isGrandchild(int m, int i){
        if (leftChildIndex(i) == m || rightChildIndex(i) == m) {
            return false;
        }
        if (leftChildIndex(leftChildIndex(i)) == m || leftChildIndex(rightChildIndex(i)) == m || rightChildIndex(leftChildIndex(i)) == m || rightChildIndex(rightChildIndex(i)) == m ) {
            return true;
        }
        return false;
    }

    private static void swapKeys(ArrayList<Integer> A, int i, int m){
        int tempKey = A.get(i);
        A.set(i,A.get(m));
        A.set(m,tempKey);
    }

    private static int parentOfIndex(int i) {
        if (i % 2 == 0) {
            return (i / 2) - 1;
        } else {
            return (i-1) / 2;
        }
    }

    public static boolean hasGrandchildren(ArrayList<Integer> A, int i){
        if (leftChildIndex(leftChildIndex(i)) >= A.size()) {
            return false;
        }
        return true;
    }

    public static int getIndexOfLastElementInTheSubHeap(ArrayList<Integer> A, int i){
        if (hasChildren(A,i)) {
            if (hasRightChild(A,i)) {
                int leftBranchLastElementIndex = getIndexOfLastElementInTheSubHeap(A,leftChildIndex(i));
                int rightBranchLastElementIndex = getIndexOfLastElementInTheSubHeap(A,rightChildIndex(i));
                return leftBranchLastElementIndex > rightBranchLastElementIndex? leftBranchLastElementIndex : rightBranchLastElementIndex;
            } else {
                return getIndexOfLastElementInTheSubHeap(A,leftChildIndex(i));
            }
        } else {
            return i;
        }

    }

    public static void heapExtractMax(ArrayList<Integer> A){
        int indexOfLast = getIndexOfLastElementInTheSubHeap(A,0);
        A.set(0,A.get(indexOfLast));
        A.remove(indexOfLast);
        buildMinMaxHeapFromArray(A);
    }

    public static void heapExtractMin(ArrayList<Integer> A){
        int indexOfLast = getIndexOfLastElementInTheSubHeap(A,0);
        int indexOfMiniumKey = 0;
        if (hasRightChild(A,0)) {
            indexOfMiniumKey = A.get(1) < A.get(2) ? 1 : 2;
        } else {
            indexOfMiniumKey = 1;
        }
        A.set(indexOfMiniumKey,A.get(indexOfLast));
        A.remove(indexOfLast);
        buildMinMaxHeapFromArray(A);
    }
}
