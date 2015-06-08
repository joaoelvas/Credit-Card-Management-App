// This is an Academic Project, and was published after finishing the lecture.
// @author Joao Elvas @ FCT/UNL
// @author Rodolfo Simoes @ FCT/UNL

package dataStructures;

public class EmptyDictionaryException extends RuntimeException
{

    static final long serialVersionUID = 0L;


    public EmptyDictionaryException( )
    {
        super();
    }

    public EmptyDictionaryException( String message )
    {
        super(message);
    }

}

