package donezo.exceptions;

/**
 * The DonezoException class handles exceptions faced in Donezo
 */
public class DonezoException extends Exception {
    /**
     * Constructs a new DonezoException with the specified detail message.
     *
     * @param message the detail message to describe the exception
     */
    public DonezoException(String message) {
        super(message);
    }
}
