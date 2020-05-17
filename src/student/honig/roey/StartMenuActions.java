package student.honig.roey;

public enum StartMenuActions {
    NON_VALID_OPTION,
    LOAD_HEAP_FROM_FILE,
    EXIT_PROGRAM;

    private static StartMenuActions[] allValues = values();
    public static StartMenuActions fromOrdinal(int n) {
        if (n >= 0 && n < allValues.length) {
            return allValues[n];
        } else {
            return NON_VALID_OPTION;
        }

    }
}
