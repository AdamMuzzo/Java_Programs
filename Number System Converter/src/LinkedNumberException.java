/**
 * Custom exception class for LinkedNumber operations.
 * Extends RuntimeException for unchecked exception behavior.
 */
public class LinkedNumberException extends RuntimeException {

    /**
     * Constructs a new LinkedNumberException with the specified detail message.
     *
     * @param msg the detail message (which is saved for later retrieval by the {@link #getMessage()} method)
     */
    public LinkedNumberException(String msg) {
        super(msg);
    }
}