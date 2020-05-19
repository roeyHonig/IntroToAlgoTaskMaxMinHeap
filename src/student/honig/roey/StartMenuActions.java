package student.honig.roey;
/** Enumeration class stating different actions the user can perform upon lunching the main program
 * @author Roey Honig
 * @author ID: roeyhonig94@gmail.com
 * @author ID: 060873940
 */
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
