package exceptions;

public class InvalidStringContainerPatternException extends RuntimeException{
    public InvalidStringContainerPatternException() {
    }

    public InvalidStringContainerPatternException(String message) {
        super(message);
    }
}
