package student.honig.roey;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/** This is the main program allowing the user to build a Max-Min heap from data, stored in a local text file, as well as manipulating the Max-Min heap by performing other actions on it.
 *
 * @author Roey Honig
 * @author ID: roeyhonig94@gmail.com
 * @author ID: 060873940
 */
public class MaxMinHeapPlayground {

    private static boolean exitProgram = false;
    private static ArrayList<Integer> originalHeap = new ArrayList<>();
    private static ArrayList<Integer> currentHeapifyHeap = new ArrayList<>();
    private static ArrayList<Integer> currentHeap = new ArrayList<>();

    public static void main(String[] args) {
        presentStartMenu();
        while (!exitProgram) {
            presentHeapMenu();
        }

    }

    private static void presentStartMenu(){
        do {
            boolean validChoiceEntered = false;
            int choice = 0;
            StartMenuActions action;
            do {
                presentTextForStartMenu();
                Scanner scanner = new Scanner(System.in);
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    validChoiceEntered = true;
                } else {
                    System.out.println("Non Valid Selection. Please choose again");
                }
                scanner.nextLine();
                System.out.println("");
                action = StartMenuActions.fromOrdinal(choice);
            } while (!validChoiceEntered);
            handleStartMenuAction(action);
        } while (!exitProgram && (currentHeap == null || currentHeap.size() == 0));
    }

    private static void presentHeapMenu(){
        presentHeapMenuIntroductionText();
        do {
            boolean validChoiceEntered = false;
            int choice = 0;
            HeapMenuActions action;
            do {
                presentHeapMenuText();
                Scanner scanner = new Scanner(System.in);
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    validChoiceEntered = true;
                } else {
                    System.out.println("Not a Number");
                }
                scanner.nextLine();
                System.out.println();
                action = HeapMenuActions.fromOrdinal(choice);
            } while (!validChoiceEntered);
            handleHeapMenuAction(action);
        } while (!exitProgram && (currentHeap == null || currentHeap.size() == 0));
    }

    private static void handleStartMenuAction(StartMenuActions action){
        switch(action) {
            case LOAD_HEAP_FROM_FILE:
                boolean success = buildHeap();
                if (!success) {
                    System.out.println("Ooops, Something went wrong while trying to build the Max-Min Heap. Please try again");
                }
                break;
            case EXIT_PROGRAM:
                exitProgram = true;
                break;
            default:
                System.out.println("Non Valid Selection. Please choose again");
        }
        System.out.println();
    }

    private static void handleHeapMenuAction(HeapMenuActions action){
        switch(action) {
            case PRINT_MAX_MIN_HEAP:
                System.out.println("current Max-Min Heap: " + stringRepresentationOfArrayList(currentHeap));
                break;
            case PRINT_ORIGINAL_HEAP_FROM_LOCAL_FILE:
                System.out.println("the original Heap: " + stringRepresentationOfArrayList(originalHeap));
                break;
            case HEAPIFY:
                heapify();
                break;
            case EXTRACT_MAX:
                MaxMinHeapService.heapExtractMax(currentHeap);
                System.out.println("current Max-Min Heap: " + stringRepresentationOfArrayList(currentHeap));
                break;
            case EXTRACT_MIN:
                MaxMinHeapService.heapExtractMin(currentHeap);
                System.out.println("current Max-Min Heap: " + stringRepresentationOfArrayList(currentHeap));
                break;
            case HEAP_INSERT:
                heapInsert();
                break;
            case HEAP_DELETE:
                heapDelete();
                break;
            case EXIT_PROGRAM:
                exitProgram = true;
                break;
            default:
                System.out.println("Non Valid Selection. Please choose again");

        }
        System.out.println();

    }

    private static ArrayList<Integer> getHeapFromFileWithFullPath(String path){
        //creating File instance to reference text file in Java
        File text = new File(path);
        Scanner scanner = null;
        try
        {
            scanner = new Scanner(text);
            scanner.useDelimiter(",|\n");
        }
        catch (FileNotFoundException ex)
        {
            return null;
        }

        ArrayList<Integer> parsedArrayList = new ArrayList<>();
        if (scanner != null) {

            while (scanner.hasNext()) {
                if (scanner.hasNextInt()) {
                    parsedArrayList.add(scanner.nextInt());
                } else {
                    scanner.next();
                }
            }
        }
        return parsedArrayList;
    }

    private static boolean buildHeap(){
        System.out.println("Please enter The full path, including .txt extension, to the file containing the Heap Data.");
        System.out.println("On a MacOS, this could be something like '/Users/roeyHonig/textFiles/array2.txt'");
        System.out.println("On a PC machine, this could be something like 'C:/Documents/array2.txt'");
        System.out.println("The text file should contain only numbers with the ',' as deliminator such as 1,2,3,4,5,6,7,8,9");
        System.out.print("Please enter The full path:");
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
        originalHeap = getHeapFromFileWithFullPath(path);
        currentHeapifyHeap = getHeapFromFileWithFullPath(path);
        currentHeap = getHeapFromFileWithFullPath(path);
        if (currentHeap == null || currentHeap.size() == 0) {
            return false;
        }
        MaxMinHeapService.buildMinMaxHeapFromArray(currentHeap);
        return true;
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

    private static void heapify(){
        System.out.print("Enter the index 0-"+(currentHeapifyHeap.size()-1) + " for performing heapify action: ");
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            int index = scanner.nextInt();
            if (index < 0 || index >= currentHeapifyHeap.size()) {
                System.out.println("Selected index is out of bounds");
                return;
            }
            MaxMinHeapService.heapify(currentHeapifyHeap,index);
            System.out.println("Heap after heapify of index "+index+" : " + stringRepresentationOfArrayList(currentHeapifyHeap));
        } else {
            System.out.println("Not a Number");
        }
        scanner.nextLine();
        System.out.println();
    }

    private static void heapInsert(){
        System.out.print("Enter the key value to insert into the Max-Min-Heap: ");
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            int key = scanner.nextInt();
            MaxMinHeapService.heapInsert(currentHeap,key);
            System.out.println("Heap after inserting key "+key+" : " + stringRepresentationOfArrayList(currentHeap));
        } else {
            System.out.println("Not a Number");
        }
        scanner.nextLine();
        System.out.println();
    }

    private static void heapDelete(){
        System.out.print("Enter the index 0-"+(currentHeap.size()-1) + " to delete: ");
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            int index = scanner.nextInt();
            if (index < 0 || index >= currentHeap.size()) {
                System.out.println("Selected index is out of bounds");
                return;
            }
            MaxMinHeapService.heapDelete(currentHeap,index);
            System.out.println("Heap after delete of index "+index+" : " + stringRepresentationOfArrayList(currentHeap));
        } else {
            System.out.println("Not a Number");
        }
        scanner.nextLine();
        System.out.println();
    }

    private static void presentTextForStartMenu() {
        System.out.println("Welcome to the Max-Min Heap Playground");
        System.out.println("Please choose an option");
        System.out.println("1. Build a Max-Min Heap from data stored in a local txt file");
        System.out.println("2. Exit Program");
        System.out.print("Please Enter Your Choice: ");
    }

    private static void presentHeapMenuIntroductionText() {
        System.out.println("Playground is ready for you");
        System.out.println("You can view your original Heap");
        System.out.println("You can incrementally perform heapify actions on the original heap, thus mutating it, step by step into Max-Min Heap");
        System.out.println("In Addition, we've already built a Max-Min-Heap from your original heap. You can perform actions on it also.");
    }

    private static void presentHeapMenuText() {
        System.out.println("What would you like to do now?:");
        System.out.println("1. Print the current Max-Min Heap");
        System.out.println("2. Print the original Heap, which was loaded from local file");
        System.out.println("3. Perform Heapify on a specific index. Mutating Action!");
        System.out.println("4. Extract the maximum key from the Max-Min-Heap. Mutating Action!");
        System.out.println("5. Extract the minimum key from the Max-Min-Heap. Mutating Action!");
        System.out.println("6. Insert key to the Max-Min-Heap. Mutating Action!");
        System.out.println("7. Delete index from the Max-Min-Heap. Mutating Action!");
        System.out.println("8. Exit Program");
        System.out.print("Please Enter Your Choice: ");
    }

}
