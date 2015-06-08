// This is an Academic Project, and was published after finishing the lecture.
// @author Joao Elvas @ FCT/UNL
// @author Rodolfo Simoes @ FCT/UNL

package exceptionPackage;

public class NoNewWordsOnTheListException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoNewWordsOnTheListException() {
		super();
	}
	
	public NoNewWordsOnTheListException(String msg) {
		super(msg);
	}
}
