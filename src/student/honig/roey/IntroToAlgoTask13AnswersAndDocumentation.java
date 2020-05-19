package student.honig.roey;
/** This is the complete documentation to Task13.
 *<p>
 * In This Task, students required to write a program enabling the user to interact with a Max-Min Heap.
 *</p>
 *<p>
 * To Run the program, execute the main() method in class 'MaxMinHeapPlayground'
 *</p>
 *<p>
 * A Max-Min heap is a binary tree with Maximum And Minimum levels (tree depth) intertwined together. The top most level, the root of the tree, is a maximum level, follow by a minimum level, followed by a maximum level and so on. Every sub-tree, rooted from a node in a Maximum level is by itself, a local max-min heap.
 *</p>
 *<p>
 * A local Max-Min Heap has the following properties:
 *</p>
 *<p>
 * - The Maximum key is the value of the root of the tree
 *</p>
 *<p>
 * - The Minimum key is the smallest value among the sons of the tree root
 *</p>
 *<p>
 * Let's write the routine to find the maximum value of Max-Min Heap
 *</p>
 * <pre>
 *     FindMax(A)
 *          return A[0]
 *
 * </pre>
 *<p>
 * Let's write the routine to find the minimum value of Max-Min Heap
 *</p>
 * <pre>
 *     FindMin(A)
 *          i {@literal <--}LeftIndex(A[0])
 *          j {@literal <--} RightIndex(A[0])
 *          IF A[i]{@literal <} A[j] RETURN A[i]
 *          ELSE RETURN A[j]
 *
 * </pre>
 *<p>
 * Both of these routines perform a constant number of operations regardless of n - size of the heap. There for, FindMax(A) = FindMin(A) = O(1)
 *</p>
 *
 * @author Roey Honig
 * @author ID: roeyhonig94@gmail.com
 * @author ID: 060873940
 */
public abstract class IntroToAlgoTask13AnswersAndDocumentation {
}
