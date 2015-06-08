// This is an Academic Project, and was published after finishing the lecture.
// @author Joao Elvas @ FCT/UNL
// @author Rodolfo Simoes @ FCT/UNL

package spelling;

import java.io.Serializable;

import dataStructures.Entry;
import dataStructures.Iterator;
import exceptionPackage.NoFrequencyOnTextException;

/**
 * @author Joao Elvas (41934) <j.elvas@campus.fct.unl.pt>
 * @author Rodolfo Ferreira (41654) <rsi.ferreira@campus.fct.unl.pt>
 */

public interface Text extends Serializable {
	/**
	 *  Interface que serve para simular um Texto 
	 */

	
	
	/**
	 * Metodo que verifica se existem erros 
	 * @return true -> existem erros , false -> nao existem erros 
	 */
	boolean hasErrors();
	
	/**
	 * Metodo que retorna o @textId do texto 
	 * @return
	 */
	String getTextId();
	
	/**
	 * Metodo que retorna o numero de linhas do testo
	 * @return -> numero de linhas do texto
	 */
	int getTextLenght();
	
	/**
	 * Metdoq eu retorna o numero de vezes que a palavra @word ocorre no texto
	 * @param word -> palavra a procurar no texto
	 * @return -> numero de vezes que a palavra @word ocorre no texto
	 */
	int wordFrequency(String word);
	
	/**
	 * Metodo que retorna a linha com o numero @lineNr
	 * @param lineNr -> numero da linha a retornar
	 * @return -> linha com numero @lineNr
	 */
	String getLine(int lineNr);
	
	/**
	 * Metodo que retorna o iterador de linhas de um texto
	 * @return
	 */
	Iterator<String> listText();
	
	/**
	 * Metodo que retorna o iterador das linhas pretendidas de um texto
	 * @param min
	 * @param max
	 * @return
	 */
	Iterator<String> listText(int min, int max);
	
	/**
	 * Metodo que retorna a lista de erros de um texto
	 * @return
	 */
	Iterator<Entry<String, Word>> listSpellingErrors();
	
	Iterator<Entry<String,Word>> wordsWithTypeAndFrequency(String type, int frequency) throws NoFrequencyOnTextException;
}
