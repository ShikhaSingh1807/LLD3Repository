package exceptions;

public class InvalidBotCountException extends RuntimeException
{
    private String message;

    public InvalidBotCountException(String message)
    {
        super(message);
    }
}
