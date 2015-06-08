// This is an Academic Project, and was published after finishing the lecture.
// @author Joao Elvas @ FCT/UNL
// @author Rodolfo Simoes @ FCT/UNL

package spelling;

import java.io.Serializable;

/**
 * @author Joao Elvas (41934) <j.elvas@campus.fct.unl.pt>
 * @author Rodolfo Ferreira (41654) <rsi.ferreira@campus.fct.unl.pt>
 */

public interface WordSetters extends Serializable {
	
	/**
	 * Metodo que adiciona +1 a frequencia da palavra 
	 * @param line -> 
	 */
	void addFrequency(int line);

}
