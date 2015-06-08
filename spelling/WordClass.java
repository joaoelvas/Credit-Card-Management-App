// This is an Academic Project, and was published after finishing the lecture.
// @author Joao Elvas @ FCT/UNL
// @author Rodolfo Simoes @ FCT/UNL

package spelling;
 
/**
 * @author Joao Elvas (41934) <j.elvas@campus.fct.unl.pt>
 * @author Rodolfo Ferreira (41654) <rsi.ferreira@campus.fct.unl.pt>
 */

@SuppressWarnings("serial")
public class WordClass implements WordSetters, Word {

	private static final int DEFAULT_SIZE = 100;
	
	private String word;
	private int frequency, counter;
	private int[] lines;
	
	public WordClass(String word, int line) {
		this.word = word;
		frequency = 1;
		counter = 1;
		lines = new int[DEFAULT_SIZE]; 
		lines[0] = line;
	}
	
	@Override
	public String getWord() {
		return word;
	}

	@Override
	public int getFrequency() {
		return frequency;
	}

	@Override
	public int[] getLines() {
		return lines;
	}
	
	public int getCounter() {
		return counter;
	}

	@Override
	public void addFrequency(int line) {
		frequency++;
		if(!hasLine(line)) {
			lines[counter++] = line;
		}
	}
	
	private boolean hasLine(int line) {
		boolean bool = false;
		for(int i = 0; i < counter; i++) {
			if(lines[i] == line) {
				bool = true;
			}
		}
		return bool;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((word == null) ? 0 : word.hashCode());
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
		WordClass other = (WordClass) obj;
		if (word == null) {
			if (other.word != null)
				return false;
		} else if (!word.equalsIgnoreCase(other.word))
			return false;
		return true;
	}

}
