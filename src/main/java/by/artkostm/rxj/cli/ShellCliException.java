package by.artkostm.rxj.cli;

/**
 * 
 * @author Artsiom_Chuiko
 *
 */
public class ShellCliException extends Exception
{
    private static final long serialVersionUID = 5092876811837407733L;
    
    public ShellCliException(final String message)
    {
        super(message);
    }

    public ShellCliException(final String message, final Throwable throwable)
    {
        super(message, throwable);
    }

    public ShellCliException(final Throwable throwable)
    {
        super(throwable);
    }
}
