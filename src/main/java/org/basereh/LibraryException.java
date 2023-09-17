package org.basereh;

public class LibraryException extends Exception {       // todo comment RuntimeException?
    public LibraryException(String errorMessage) {
        super(errorMessage);
    }
}
