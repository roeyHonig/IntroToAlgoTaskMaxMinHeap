package student.honig.roey;

public class MinMaxHeap {

    public static void buildMinMaxHeapFromArray(int[] A)
    {
        for (int i = A.length/2; i > -1; i--) {
            pushDown(A,i);
        }
    }

    private static void pushDown(int[] A, int i){
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

    private static void pushDownMin(int[] A, int i){
        if (hasChildren(A,i)) {
            int m = getIndexOfSmallestChildeOrGrandchile(A,i);
            if (isGrandchild(m,i)) {
                if (A[m] < A[i]) {
                    swapKeys(A,i,m);
                    int indexOfParent = parentOfIndex(m);
                    if (A[m] > A[indexOfParent]) {
                        swapKeys(A,m,indexOfParent);
                    }
                    pushDownMin(A,m);
                }
            } else if (A[m] < A[i]) {
                swapKeys(A,i,m);
            }
        }
    }

    private static void pushDownMax(int[] A, int i){
        if (hasChildren(A,i)) {
            int m = getIndexOfLargestChildeOrGrandchile(A,i);
            if (isGrandchild(m,i)) {
                if (A[m] > A[i]) {
                    swapKeys(A,i,m);
                    int indexOfParent = parentOfIndex(m);
                    if (A[m] < A[indexOfParent]) {
                        swapKeys(A,m,indexOfParent);
                    }
                    pushDownMax(A,m);
                }
            } else if (A[m] > A[i]) {
                swapKeys(A,i,m);
            }
        }
    }

    private static boolean hasChildren(int[] A, int i){
        if (leftChildIndex(i) >= A.length) {
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

    private static int getIndexOfSmallestChildeOrGrandchile(int[] A,int i) {
        int indexLeft = leftChildIndex(i);
        int indexRight = rightChildIndex(i);
        int indexLeftLeft = leftChildIndex(indexLeft);
        int indexLeftRight = rightChildIndex(indexLeft);
        int indexRightLeft = leftChildIndex(indexRight);
        int indexRightRight = rightChildIndex(indexRight);
        int indexToReturn = indexLeft;
        int minKey = A[indexLeft];
        if (indexRight < A.length && minKey > A[indexRight]) {
            minKey = A[indexRight];
            indexToReturn = indexRight;
        }
        if (indexLeftLeft < A.length && minKey > A[indexLeftLeft]) {
            minKey = A[indexLeftLeft];
            indexToReturn = indexLeftLeft;
        }
        if (indexLeftRight < A.length && minKey > A[indexLeftRight]) {
            minKey = A[indexLeftRight];
            indexToReturn = indexLeftRight;
        }
        if (indexRightLeft < A.length && minKey > A[indexRightLeft]) {
            minKey = A[indexRightLeft];
            indexToReturn = indexRightLeft;
        }
        if (indexRightRight < A.length && minKey > A[indexRightRight]) {
            minKey = A[indexRightRight];
            indexToReturn = indexRightRight;
        }
        return indexToReturn;
    }

    private static int getIndexOfLargestChildeOrGrandchile(int[] A,int i) {
        int indexLeft = leftChildIndex(i);
        int indexRight = rightChildIndex(i);
        int indexLeftLeft = leftChildIndex(indexLeft);
        int indexLeftRight = rightChildIndex(indexLeft);
        int indexRightLeft = leftChildIndex(indexRight);
        int indexRightRight = rightChildIndex(indexRight);
        int indexToReturn = indexLeft;
        int maxKey = A[indexLeft];
        if (indexRight < A.length && maxKey < A[indexRight]) {
            maxKey = A[indexRight];
            indexToReturn = indexRight;
        }
        if (indexLeftLeft < A.length && maxKey < A[indexLeftLeft]) {
            maxKey = A[indexLeftLeft];
            indexToReturn = indexLeftLeft;
        }
        if (indexLeftRight < A.length && maxKey < A[indexLeftRight]) {
            maxKey = A[indexLeftRight];
            indexToReturn = indexLeftRight;
        }
        if (indexRightLeft < A.length && maxKey < A[indexRightLeft]) {
            maxKey = A[indexRightLeft];
            indexToReturn = indexRightLeft;
        }
        if (indexRightRight < A.length && maxKey < A[indexRightRight]) {
            maxKey = A[indexRightRight];
            indexToReturn = indexRightRight;
        }
        return indexToReturn;
    }

    private static boolean isGrandchild(int m, int i){
        if (leftChildIndex(i) == m || rightChildIndex(i) == m) {
            return false;
        }
        return true;
    }

    private static void swapKeys(int[] A, int i, int m){
        int tempKey = A[i];
        A[i] = A[m];
        A[m] = tempKey;
    }

    private static int parentOfIndex(int i) {
        if (i % 2 == 0) {
            return (i / 2) - 1;
        } else {
            return (i-1) / 2;
        }
    }

}
