// This is an Academic Project, and was published after finishing the lecture.
// @author Joao Elvas @ FCT/UNL
// @author Rodolfo Simoes @ FCT/UNL

package spelling;

import dataStructures.*;
import exceptionPackage.*;

/**
 * @author Joao Elvas (41934) <j.elvas@campus.fct.unl.pt>
 * @author Rodolfo Ferreira (41654) <rsi.ferreira@campus.fct.unl.pt>
 */

@SuppressWarnings("serial")
public class SpellingClass implements Spelling {

	private HashTable<String, Text> texts;
	private MainDictionary dictionary;
	
	public SpellingClass() {
		texts = new ChainedHashTable<>();
		dictionary = new MainDictionaryClass();
	}
	
	@Override
	public boolean hasWord(String word) {
		return dictionary.hasWord(word);
	}

	@Override
	public boolean hasText(String textId) {
		boolean bool = false;
		if(texts.find(textId) == null)
			return bool;
		else bool = true;
		return bool;
	}

	@Override
	public boolean hasErrors(String textId) {
		return texts.find(textId).hasErrors();
	}

	@Override
	public void addWord(String word)  {
		texts = new ChainedHashTable<>();
		dictionary.addWord(word);
	}

	@Override
	public void createText(String textId, String[] text) throws TextAlreadyExistException {
		if(!hasText(textId)){
		texts.insert(textId, new TextClass(textId, text, dictionary));
		}else
		throw new TextAlreadyExistException();
	}

	@Override
	public void remText(String textId) throws TextNotFoundException {
		if(!hasText(textId))
			throw new TextNotFoundException();
		
		texts.remove(textId);
	}

	@Override
	public int getTextLenght(String textId) {
		return texts.find(textId).getTextLenght();
	}

	@Override
	public int wordFrequencyOnText(String textId, String word)throws TextNotFoundException {
		if(!hasText(textId))
			throw new TextNotFoundException ();
		
		int freq = texts.find(textId).wordFrequency(word) ;
		if(freq == -1){
			return 0;
		}
		return freq;
	}

	@Override
	public String getLineFromText(String textId, int lineNr) {
		return texts.find(textId).getLine(lineNr);
	}


	@Override
	public Iterator<String> listText(String textId) {
		return texts.find(textId).listText();
	}


	@Override
	public Iterator<String> listTextSection(String textId, int minNumLine,
			int maxNumLine) {
		return texts.find(textId).listText(minNumLine, maxNumLine);
	}

	@Override
	public Iterator<Entry<String, Word>> listSpelingErrors(String textId) {
		return texts.find(textId).listSpellingErrors();
	}

	@Override
	public Iterator<Entry<String,Word>> wordsWithTypeAndFrequency(String textId, String type, int frequency) throws TextNotFoundException, NoFrequencyOnTextException {
		if(texts.find(textId) == null) throw new TextNotFoundException();
		return texts.find(textId).wordsWithTypeAndFrequency(type, frequency);
	}

}
