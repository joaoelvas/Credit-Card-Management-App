// This is an Academic Project, and was published after finishing the lecture.
// @author Joao Elvas @ FCT/UNL
// @author Rodolfo Simoes @ FCT/UNL

package spelling;

import dataStructures.AVLTree;
import dataStructures.ChainedHashTable;
import dataStructures.Dictionary;
import dataStructures.Entry;
import dataStructures.Iterator;
import dataStructures.OrderedDictionary;
import exceptionPackage.NoFrequencyOnTextException;

/**
 * @author Joao Elvas (41934) <j.elvas@campus.fct.unl.pt>
 * @author Rodolfo Ferreira (41654) <rsi.ferreira@campus.fct.unl.pt>
 */

@SuppressWarnings("serial")
public class TextClass implements Text {

	private static String TYPE_WORD = "P";
	private static String TYPE_ERROR = "E";
	private static String TYPE_CORRECT = "C";
	
	private String textId;
	
	private MainDictionary dictionary;
	
	private Dictionary<Integer,OrderedDictionary<String,Word>> frequencyErrors; 
	private Dictionary<Integer,OrderedDictionary<String,Word>> frequencyCorrect;
	private Dictionary<Integer,OrderedDictionary<String,Word>> frequencyWords;
	
	private OrderedDictionary<String, Word> errors;
	private OrderedDictionary<String, Word> correct;
	
	private String[] lines;

	
	public TextClass(String textId, String[] text, MainDictionary dictionary) {
		lines = text;
		
		errors = new AVLTree<String, Word>();
		correct = new AVLTree<String, Word>();
		
		frequencyErrors = new ChainedHashTable<Integer,OrderedDictionary<String,Word>>();
		frequencyCorrect = new ChainedHashTable<Integer,OrderedDictionary<String,Word>>();
		frequencyWords = new ChainedHashTable<Integer,OrderedDictionary<String,Word>>();
		
		this.textId = textId;
		this.dictionary = dictionary;
		checkErrors();
	}

	@Override
	public boolean hasErrors() {
		return !errors.isEmpty();
	}

	@Override
	public String getTextId() {
		return textId;
	}

	private void checkErrors() {
		
		for(int l = 0; l < lines.length; l++) {
			String[] tmp = lines[l].split("\\s+");
			for(int i = 0; i < tmp.length; i++) {
				if(!tmp[i].isEmpty()) {
					if(dictionary.hasWord(tmp[i].toUpperCase())) {  
						Word word = correct.find(tmp[i].toUpperCase());
						if(word == null) {
							correct.insert(tmp[i].toUpperCase(), new WordClass(tmp[i].toLowerCase(),l+1));
						} else word.addFrequency(l+1);
					} else {
						Word word = errors.find(tmp[i].toUpperCase());
						if(word == null) {
							errors.insert(tmp[i].toUpperCase(), new WordClass(tmp[i].toLowerCase(),l+1));
						} else word.addFrequency(l+1);
					}
				}
			}
		}
		
		Iterator<Entry<String, Word>> a = errors.iterator();
		while(a.hasNext()) {
			Entry<String, Word> b = a.next();
			Word c = b.getValue();
			int d = c.getFrequency();
			if(frequencyErrors.find(d) == null) {
				frequencyErrors.insert(d, new AVLTree<String,Word>());
				frequencyErrors.find(d).insert(c.getWord(), c);
			} else frequencyErrors.find(d).insert(c.getWord(), c);   
			if(frequencyWords.find(d) == null) {
				frequencyWords.insert(d, new AVLTree<String,Word>());
				frequencyWords.find(d).insert(c.getWord(), c);
			} else frequencyWords.find(d).insert(c.getWord(), c);  
		}
		
		a = correct.iterator();
		while(a.hasNext()) {
			Entry<String, Word> b = a.next();
			Word c = b.getValue();
			int d = c.getFrequency();
			if(frequencyCorrect.find(d) == null) {
				frequencyCorrect.insert(d, new AVLTree<String,Word>());
				frequencyCorrect.find(d).insert(c.getWord(), c);
			} else frequencyCorrect.find(d).insert(c.getWord(), c);
			if(frequencyWords.find(d) == null) {
				frequencyWords.insert(d, new AVLTree<String,Word>());
				frequencyWords.find(d).insert(c.getWord(), c);
			} else frequencyWords.find(d).insert(c.getWord(), c);
		}
		
		
	}

	@Override
	public int getTextLenght() {
		return lines.length;
	}

	@Override
	public int wordFrequency(String word) {
		Word we = errors.find(word.toUpperCase()); 
		Word wc = correct.find(word.toUpperCase());
		if(we == null && wc ==null) {
			return 0;
			
		} else if(we == null) 
			return correct.find(word.toUpperCase()).getFrequency(); // troquei
		else 
			return we.getFrequency();
	}

	@Override
	public String getLine(int lineNr) {
		return lines[lineNr - 1];
	}

	@Override
	public Iterator<String> listText() {
		Iterator<String> it = new ArrayIterator<String>(lines, lines.length);
		
		return it;
	}

	@Override
	public Iterator<String> listText(int min, int max) {
		
		if(max == 0) {
			max = lines.length;
		}
		Iterator<String> it = new ArrayIterator<String>(lines,min,max);
		
		return it;
	}

	@Override
	public Iterator<Entry<String, Word>> listSpellingErrors() {  //TODO getValue
		return errors.iterator();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((textId == null) ? 0 : textId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TextClass other = (TextClass) obj;
		if (textId == null) {
			if (other.textId != null)
				return false;
		} else if (!textId.equalsIgnoreCase(other.textId))
			return false;
		return true;
	}

	@Override
	public Iterator<Entry<String,Word>> wordsWithTypeAndFrequency(String type, int frequency) throws NoFrequencyOnTextException {
		Iterator<Entry<String,Word>> it = null;
		
		if(type.equalsIgnoreCase(TYPE_CORRECT) && frequencyCorrect.find(frequency) == null) {
			throw new NoFrequencyOnTextException();
		} else if(type.equalsIgnoreCase(TYPE_ERROR) && frequencyErrors.find(frequency) == null) {
			throw new NoFrequencyOnTextException();
		} else if(type.equalsIgnoreCase(TYPE_WORD) && frequencyWords.find(frequency) == null) {
			throw new NoFrequencyOnTextException();
		}
		
		if(type.equalsIgnoreCase(TYPE_CORRECT)) {
			it = frequencyCorrect.find(frequency).iterator();
		} else if(type.equalsIgnoreCase(TYPE_ERROR)) {
			it = frequencyErrors.find(frequency).iterator();
		} else if(type.equalsIgnoreCase(TYPE_WORD)) {
			it = frequencyWords.find(frequency).iterator();
		}
		return it;
	}
	
	

}
