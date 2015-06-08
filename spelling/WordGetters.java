// This is an Academic Project, and was published after finishing the lecture.
// @author Joao Elvas @ FCT/UNL
// @author Rodolfo Simoes @ FCT/UNL

package spelling;

import java.io.Serializable;

/**
 * @author Joao Elvas (41934) <j.elvas@campus.fct.unl.pt>
 * @author Rodolfo Ferreira (41654) <rsi.ferreira@campus.fct.unl.pt>
 */

public interface WordGetters extends Serializable {
	
	/**
	 * Metodo que retorna uma palavra
	 * @return -> palavra
	 */
	String getWord();
	
	/**
	 * Metodo que retorna a frequencia da palavra 
	 * @return -> frequencia da palavra
	 */
	int getFrequency();
	
	/**
	 * Metodo que retorna o numero de linhas em que palavra aparece 
	 * @return -> numero de linhas em que palavra aparece
	 */
	int getCounter();
	
	/**
	 * Metodo que retorna um vector com as linhas em que a palavra aparece
	 * @return -> vector com as linhas em que a palavra aparece
	 */
	int[] getLines(); 
}
