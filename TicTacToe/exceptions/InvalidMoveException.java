package exceptions;

public class InvalidMoveException extends RuntimeException
{
    private String message;

    public InvalidMoveException(String message)
    {
        super(message);
    }
}
