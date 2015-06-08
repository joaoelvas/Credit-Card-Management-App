// This is an Academic Project, and was published after finishing the lecture.
// @author Joao Elvas @ FCT/UNL
// @author Rodolfo Simoes @ FCT/UNL

package exceptionPackage;

public class SpellingErrorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SpellingErrorException() {
		super();
	}
	
	public SpellingErrorException(String msg) {
		super(msg);
	}
}
