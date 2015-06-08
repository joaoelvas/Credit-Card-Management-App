// This is an Academic Project, and was published after finishing the lecture.
// @author Joao Elvas @ FCT/UNL
// @author Rodolfo Simoes @ FCT/UNL

package dataStructures;

public class FullQueueException extends RuntimeException
{                                     

    static final long serialVersionUID = 0L;


    public FullQueueException( )   
    {
        super();
    }

    public FullQueueException( String message )
    {
        super(message);
    }

}
