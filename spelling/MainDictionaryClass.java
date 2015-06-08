// This is an Academic Project, and was published after finishing the lecture.
// @author Joao Elvas @ FCT/UNL
// @author Rodolfo Simoes @ FCT/UNL

package spelling;

import dataStructures.ChainedHashTable;
import dataStructures.Dictionary;

/**
 * @author Joao Elvas (41934) <j.elvas@campus.fct.unl.pt>
 * @author Rodolfo Ferreira (41654) <rsi.ferreira@campus.fct.unl.pt>
 */

@SuppressWarnings("serial")
public class MainDictionaryClass implements MainDictionary {
	
	private Dictionary<String, String> words;
	
	public MainDictionaryClass() {
		words = new ChainedHashTable<String, String>();
	}
	
	@Override
	public boolean hasWord(String word) {
		boolean tmp = false ;
		if(words.find(word.toUpperCase()) != null)
			tmp = true;
		return tmp;
	}

	@Override
	public void addWord(String word) {
		words.insert(word.toUpperCase(), word);
	}
}
