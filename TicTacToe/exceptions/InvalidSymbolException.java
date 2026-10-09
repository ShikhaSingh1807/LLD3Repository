package exceptions;

public class InvalidSymbolException extends RuntimeException
{
    private String message;

    public InvalidSymbolException(String message)
    {
        super(message);
    }
}