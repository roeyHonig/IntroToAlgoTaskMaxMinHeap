package student.honig.roey;

public enum HeapMenuActions {

    NON_VALID_OPTION,
    PRINT_MAX_MIN_HEAP,
    PRINT_ORIGINAL_HEAP_FROM_LOCAL_FILE,
    HEAPIFY,
    EXTRACT_MAX,
    EXTRACT_MIN,
    HEAP_INSERT,
    HEAP_DELETE,
    EXIT_PROGRAM;

    private static HeapMenuActions[] allValues = values();
    public static HeapMenuActions fromOrdinal(int n) {
        if (n >= 0 && n < allValues.length) {
            return allValues[n];
        } else {
            return NON_VALID_OPTION;
        }

    }
}
