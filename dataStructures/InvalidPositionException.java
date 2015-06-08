// This is an Academic Project, and was published after finishing the lecture.
// @author Joao Elvas @ FCT/UNL
// @author Rodolfo Simoes @ FCT/UNL

package dataStructures;

public class InvalidPositionException extends RuntimeException
{

    static final long serialVersionUID = 0L;


    public InvalidPositionException( )
    {
        super();
    }

    public InvalidPositionException( String message )
    {
        super(message);
    }

}

