package student.honig.roey;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MaxMinHeapPlayground {

    private enum StartMenuActions{
        NON_VALID_OPTION,
        LOAD_HEAP_FROM_FILE,
        EXIT_PROGRAM;
    }

    private enum HeapMenuActions{
        NON_VALID_OPTION,
        BUILD_MAX_MIN_HEAP,
        EXTRACT_MAX,
        EXIT_PROGRAM;
    }

    private static boolean exitProgram = false;

    public static void main(String[] args) {
        // write your code here

        //creating File instance to reference text file in Java
        File text = new File("/Users/roeyHonig/textFiles/array2.txt");


        System.out.print("Please Enter Your Array spaced with ,: ");
        Scanner scanner = null;
        try
        {
            scanner = new Scanner(text);
            scanner.useDelimiter(",|\n");
        }
        catch (FileNotFoundException ex)
        {
            // insert code to run when exception occurs
        }
        /*
        int roey = 0;
        int ido = 0;
        int may = 0;
        //String line = scanner.nextLine();
        if (scanner != null && scanner.hasNextInt()){
            roey = scanner.nextInt();
        }
        if (scanner != null && scanner.hasNextInt()){
            ido = scanner.nextInt();
        }
        if (scanner != null && scanner.hasNextInt()){
            may = scanner.nextInt();
        }
        */
        //int roey = StartMenuOption.LOAD_HEAP_FROM_FILE.ordinal();
        ArrayList<Integer> B = new ArrayList<>();
        if (scanner != null) {

            while (scanner.hasNext()) {
                if (scanner.hasNextInt()) {
                    B.add(scanner.nextInt());
                } else {
                    scanner.next();
                }
            }
        }

        System.out.println("the array as Max-Min Heap after deleting: " + stringRepresentationOfArrayList(B));
        presentStartMenu();
        while (!exitProgram) {
            presentHeapMenu();
        }

        /*
        int[] A = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
        ArrayList<Integer> B = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            B.add(A[i]);
        }
        System.out.println("the array is: " + stringRepresentationOfArray(A));
        System.out.println("the arrayList is: " + stringRepresentationOfArrayList(B));
        MaxMinHeapService.buildMinMaxHeapFromArray(B);
        System.out.println("the array as Max-Min Heap is: " + stringRepresentationOfArrayList(B));
        Boolean roey = MaxMinHeapService.isOnMinLevel(7);
        System.out.println("is on min level: " + roey);
        boolean ido = MaxMinHeapService.isGrandchild(11,1);
        System.out.println("is grandchild: " + ido);
        boolean may = MaxMinHeapService.hasGrandchildren(B,4);
        System.out.println("has grandchild: " + may);
        int yhaoo = MaxMinHeapService.getIndexOfLastElementInTheSubHeap(B,3);
        System.out.println("last element index: " + yhaoo);
        MaxMinHeapService.heapDelete(B,2);
        System.out.println("the array as Max-Min Heap after deleting: " + stringRepresentationOfArrayList(B));
        */
    }

    private static void presentStartMenu(){
        boolean validChoiseEntered = false;
        do {
            System.out.println("Welcome to the Max-Min Heap Playground");
            System.out.println("What would you like to do now?");
            System.out.println("1. Build a Max-Min Heap from a local file");
            System.out.println("2. Exit Program");
            System.out.print("Please Enter Your Choice: ");
            Scanner scanner = new Scanner(System.in);

            int choice = 0;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                validChoiseEntered = true;
            } else {
                System.out.println("Not a Number");
            }
            scanner.nextLine();
            System.out.println("");
        } while (!validChoiseEntered);




        /*
        int first = 0;
        try {
            first = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Not a Number");
        }
        System.out.print("Please Enter Your Choice: ");
        */


        /*
        String first = scanner.nextLine();
        try {
            Integer firstAsInteger = Integer.parseInt(first);
        } catch (Exception e) {
            System.out.println("Not a Number");
        }
        */
    }

    private static void presentHeapMenu(){
        boolean validChoiseEntered = false;
        do {
            System.out.println("Welcome to the Max-Min Heap Playground");
            System.out.println("What would you like to do now?");
            System.out.println("1. Build a Max-Min Heap from a local file");
            System.out.println("2. Exit Program");
            System.out.print("Please Enter Your Choice: ");
            Scanner scanner = new Scanner(System.in);

            int choice = 0;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                validChoiseEntered = true;
            } else {
                System.out.println("Not a Number");
            }
            scanner.nextLine();
            System.out.println("");
        } while (!validChoiseEntered);




        /*
        int first = 0;
        try {
            first = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Not a Number");
        }
        System.out.print("Please Enter Your Choice: ");
        */


        /*
        String first = scanner.nextLine();
        try {
            Integer firstAsInteger = Integer.parseInt(first);
        } catch (Exception e) {
            System.out.println("Not a Number");
        }
        */
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
