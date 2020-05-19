package student.honig.roey;

import java.util.ArrayList;
/** This is a service class with static methods to build Max-Min Heap and perform numerous actions on it.
 * In Addition it lists the Run-Time complexity of the Routines discussed in Intro To Algorithms Task13
 *
 * @author Roey Honig
 * @author ID: roeyhonig94@gmail.com
 * @author ID: 060873940
 */
public class MaxMinHeapService {

    /** Builds a Max-Min-Heap. Mutating Function!.
     *<p>
     * Since heapify = O(log(n)) (see explanation in the appropriate function API) and we perform it n (technically n/2 times) times: buildMinMaxHeapFromArray = O(n*log(n))
     *</p>
     * @param A - An Array of numbers
     */
    public static void buildMinMaxHeapFromArray(ArrayList<Integer> A)
    {
        for (int i = A.size()/2; i > -1; i--) {
            heapify(A,i);
        }
    }

    /** Builds a Max-Min-Heap. Mutating Function!.
     *<p>
     * Since pushDownMax = O(log(n)) and pushDownMin = O(log(n)) (see explanation in the appropriate functions API) and we perform 1 of each depending if we are on a Max or Min level: heapify = O(log(n))
     *</p>
     * @param A - An Array of numbers
     * @param i - index of array element
     */
    public static void heapify(ArrayList<Integer> A, int i){
        if (MaxMinHeapService.isOnMinLevel(i)) {
            pushDownMin(A,i);
        } else {
            pushDownMax(A,i);
        }
    }

    /** Remove The Maximum Element from the Heap while maintaining a Max-Min-Heap. Mutating Function!.
     *<p>
     * heapExtractMax =  buildMinMaxHeapFromArray = O(n*log(n))
     *</p>
     * @param A - An Array of numbers
     */
    public static void heapExtractMax(ArrayList<Integer> A){
        int indexOfLast = getIndexOfLastElementInTheSubHeap(A,0);
        A.set(0,A.get(indexOfLast));
        A.remove(indexOfLast);
        buildMinMaxHeapFromArray(A);
    }

    /** Remove The Minimum Element from the Heap while maintaining a Max-Min-Heap. Mutating Function!.
     *<p>
     * heapExtractMin =  buildMinMaxHeapFromArray = O(n*log(n))
     *</p>
     * @param A - An Array of numbers
     */
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

    /** Insert a new key value into the heap while maintaining a Max-Min-Heap. Mutating Function!.
     *<p>
     * heapInsert =  buildMinMaxHeapFromArray = O(n*log(n))
     *</p>
     * @param A - An Array of numbers
     * @param key - value to be inserted into the heap
     */
    public static void heapInsert(ArrayList<Integer> A, Integer key){
        A.add(key);
        buildMinMaxHeapFromArray(A);
    }

    /** Delete a key at specific index from the heap while maintaining a Max-Min-Heap. Mutating Function!.
     *<p>
     * heapDelete =  buildMinMaxHeapFromArray = O(n*log(n))
     *</p>
     * @param A - An Array of numbers
     * @param i - value to be inserted into the heap
     * @return True if deletion process didn't encounter difficulties.
     */
    public static boolean heapDelete(ArrayList<Integer> A, int i){
        if (A.size() == 0 || i < 0 || i >= A.size()) {
            return false;
        }
        int indexOfLast = getIndexOfLastElementInTheSubHeap(A,0);
        A.set(i,A.get(indexOfLast));
        A.remove(indexOfLast);
        buildMinMaxHeapFromArray(A);
        return true;
    }

    // Private

    private static boolean isOnMinLevel(int i) {
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

    /** Make the sub tree, rooted at index i a Max-Min-Heap. However!!!, the underline assumptions of this function is that both children of the node at index i are already Min-Max Heaps. This assumptions is valid when we heapify the tree from the bottom up to the top.
     *<p>
     * When there are no grandchildren, a simple check to swap the elements at index i and index of the largest child will result in a Max-Min Heap.
     *</p>
     *<p>
     * When there are 4 grandchildren for example, each of them resides over his own Max-Min heap, so we can be sure 1 of the 4 grandchildren is the highest value of all the elements in the sub heap rooted at index i. We just need to compare it with the element at the root. If it is bigger then the element at the root (index i) we switch between them and recursively call the pushDownMAx function again this time on the grandchild (where we've just push down the root to).
     *</p>
     *<p>
     * In the worst case scenario, we could be finding ourselves keep pushing the original element at index i down  again and again, all the way down. so the amount of computational operations is directly proportional to the sub tree (rooted at index i) height
     *</p>
     *<p>
     * As an upper bound we can take the height of the entire tree which is h proportional to LOG(n) (log to the base of 2).
     *</p>
     *<p>
     * So pushDownMax = O(log(n))
     *</p>
     * @param A - An Array of numbers
     * @param i - value to be inserted into the heap
     */
    public static void pushDownMax(ArrayList<Integer> A, int i){
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

    /** Make the sub tree, rooted at index i a Min-Max-Heap. However!!!, the underline assumptions of this function is that both children of the node at index i are already Max-Min Heaps. This assumptions is valid when we heapify the tree from the bottom up to the top.
     *<p>
     * When there are no grandchildren, a simple check to swap the elements at index i and index of the largest child will result in a Min-Max Heap.
     *</p>
     *<p>
     * When there are 4 grandchildren for example, each of them resides over his own Min-Max heap, so we can be sure 1 of the 4 grandchildren is the lowest value of all the elements in the sub heap rooted at index i. We just need to compare it with the element at the root. If it is smaller then the element at the root (index i) we switch between them and recursively call the pushDownMin function again this time on the grandchild (where we've just push down the root to).
     *</p>
     *<p>
     * In the worst case scenario, we could be finding ourselves keep pushing the original element at index i down  again and again, all the way down. so the amount of computational operations is directly proportional to the sub tree (rooted at index i) height
     *</p>
     *<p>
     * As an upper bound we can take the height of the entire tree which is h proportional to LOG(n) (log to the base of 2).
     *</p>
     *<p>
     * So pushDownMin = O(log(n))
     *</p>
     * @param A - An Array of numbers
     * @param i - value to be inserted into the heap
     */
    public static void pushDownMin(ArrayList<Integer> A, int i){
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

    private static boolean isGrandchild(int m, int i){
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

    private static int getIndexOfLastElementInTheSubHeap(ArrayList<Integer> A, int i){
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

}
