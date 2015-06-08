// This is an Academic Project, and was published after finishing the lecture.
// @author Joao Elvas @ FCT/UNL
// @author Rodolfo Simoes @ FCT/UNL

package spelling;

import java.io.Serializable;

/**
 * @author Joao Elvas (41934) <j.elvas@campus.fct.unl.pt>
 * @author Rodolfo Ferreira (41654) <rsi.ferreira@campus.fct.unl.pt>
 */

public interface MainDictionary extends Serializable {
	/**
	 *  Interface que serve para simular um Dicionario 
	 */
	
	
	
	/**
	 * Metodo que verifica se a palavra @word existe no dicionario
	 * @param word -> palavra a procurar
	 * @return true -> a palavra existe , false -> a palavra nao existe
	 */
	boolean hasWord(String word);
	
	/**
	 * Metodo que adiciona uma palavra @word ao dicionario  
	 * @param word -> palavra a adicionar 
	 */
	void addWord(String word);
}
