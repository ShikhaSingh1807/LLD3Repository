package exceptions;

public class MaximumPlayerInGameException extends RuntimeException
{
    private String message;

    public MaximumPlayerInGameException(String message)
    {
        super(message);
    }
}
