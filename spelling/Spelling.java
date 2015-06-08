// This is an Academic Project, and was published after finishing the lecture.
// @author Joao Elvas @ FCT/UNL
// @author Rodolfo Simoes @ FCT/UNL

package spelling;

import java.io.Serializable;

import dataStructures.*;
import exceptionPackage.*;

/**
 * @author Joao Elvas (41934) <j.elvas@campus.fct.unl.pt>
 * @author Rodolfo Ferreira (41654) <rsi.ferreira@campus.fct.unl.pt>
 */

public interface Spelling extends Serializable {
	
	/**
	 * Metodo que veifica se a palavra @word existe no dicionario 
	 * @param word - palatra a verificar 
	 * @return true -> a palavra existe , false -> a palavra nao existe
	 * @throws NoWordFoundException 
	 */
	boolean hasWord(String word); 
	
	/**
	 * Metodo que verifica se existe um testo com o mesmo @textId
	 * @param textId -> Identificador do texto a verificar 
	 * @return true -> o texto existe , false -> o texto nao existe
	 */
	boolean hasText(String textId) ; 
	
	/**
	 * Metodo que verifica se um texto @textId tem erros 
	 * @param textId -> testo a verificar 
	 * @return true -> existe erros no texto , false -> nao existe erros no texto 
	 * @throws ErrorsNotFoundException 
	 */
	boolean hasErrors(String textId); 
	
	/**
	 * Metodo que adiciona uma palavra @word ao dicionario 
	 * @param word -> palavra a adicionar 
	 */
	void addWord(String word) ; 
	
	/**
	 * Metodo que cria um texto @textId sem linhas
	 * @param textId -> identificador do texto a criar 
	 * @param String[] text -> todas as linhas do texto
	 * @throws TextNotFoundException 
	 * @throws TextAlreadyExistException 
	 */
	void createText(String textId, String[] text) throws TextAlreadyExistException; 
	
	/**
	 * Metodo que remove um texto @textId
	 * @param textId -> identificador do texto a ser removido 
	 * @throws TextNotFoundException 
	 */
	void remText(String textId) throws TextNotFoundException; 
	
	/**
	 * Metodo que retorna o numero de linhas de um determinado texto @textId
	 * @param textId -> identificar do texto a saber o numero de linhas
	 * @return -> numero de linhas 
	 */
	int getTextLenght(String textId); //RETORNA O NUMERO DE LINHAS
	
	/**
	 * Metodo que retorna o numero de vezes que a palavra @word aparese no texto @textId
	 * @param textId -> ifntificador do texto a procurar a frequencia da palavra 
	 * @param word -> palavra a procurar a frequencia 
	 * @return -> numeor de vezes que a palavra ocorre no texto 
	 * @throws TextNotFoundException 
	 */
	int wordFrequencyOnText(String textId, String word) throws TextNotFoundException; 
	
	/**
	 * Metodo que retorna a linha @lineNr do texto @textId
	 * @param textId -> identificar do testo a procurar a linha 
	 * @param lineNr -> numero da lina a ser procurada
	 * @return -> linha pedida 
	 */
	String getLineFromText(String textId, int lineNr); 
	
	/**
	 * Metodo que retorna o iterador de Linhas de um texto @textId 
	 * @param textId -> identificador do texto 
	 * @return -> iterador de Linhas 
	 */
	Iterator<String> listText(String textId); 
	
	/** 
	 * Metodo que retorna um iterador das linhas pretendidas (@minNumLine e @maxNumLine inclusive) de um texto @textId 
	 * @param textId -> texto a procurar as linhas 
	 * @param minNumLine -> primeira linha que se quer obter
	 * @param maxNumLine -> ultima linha que se quer obter
	 * @return -> Iterador de linhas
	 */
	Iterator<String> listTextSection(String textId, int minNumLine, int maxNumLine); 
	
	/**
	 * Metodo que retorna um iterador de todas as palavras de um texto @textId que estao com erro por ordem alfabetica 
	 * @param textId -> texto a iterar os erros 
	 * @return -> iterador de erros 
	 */
	Iterator<Entry<String, Word>> listSpelingErrors(String textId); 
	
	/**
	 * @param textId
	 * @param type
	 * @param frequency
	 * @return -> iterador de palavras com frequencia <code>frequency</code>
	 * @throws TextNotFoundException
	 * @throws NoFrequencyOnTextException
	 */
	Iterator<Entry<String,Word>> wordsWithTypeAndFrequency(String textId, String type, int frequency) throws TextNotFoundException, NoFrequencyOnTextException; 
}
