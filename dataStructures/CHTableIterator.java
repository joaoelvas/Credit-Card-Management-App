// This is an Academic Project, and was published after finishing the lecture.
// @author Joao Elvas @ FCT/UNL
// @author Rodolfo Simoes @ FCT/UNL

package dataStructures;

@SuppressWarnings("serial")
public class CHTableIterator<K, V> implements Iterator<Entry<K,V>> {

	private Dictionary<K,V>[] table;
	private int counter;
	private Iterator<Entry<K,V>> currentIterator;
	
	public CHTableIterator(Dictionary<K,V>[] table) {
		this.table = table;
		rewind();
		searchForNextIterator();
	}
	
	@Override
	public boolean hasNext() {
		return currentIterator.hasNext();
	}

	@Override
	public Entry<K,V> next() throws NoSuchElementException {
		if(currentIterator.hasNext() == false) {
			throw new NoSuchElementException();
		}	
		
		Entry<K,V> entry = currentIterator.next();
		
		if(currentIterator.hasNext() == false) {
			searchForNextIterator();
		}
		
		return entry;
	}

	@Override
	public void rewind() {
		counter = 0;
	}
	
	private void searchForNextIterator() {
		while(table[counter].isEmpty()) {
			counter++;
		}
		currentIterator = table[counter].iterator();
	}
}
