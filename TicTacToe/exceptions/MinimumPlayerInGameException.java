package exceptions;

public class MinimumPlayerInGameException extends RuntimeException
{
    private String message;

    public MinimumPlayerInGameException(String message)
    {
        super(message);
    }
}
