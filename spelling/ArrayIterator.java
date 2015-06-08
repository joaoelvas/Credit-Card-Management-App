// This is an Academic Project, and was published after finishing the lecture.
// @author Joao Elvas @ FCT/UNL
// @author Rodolfo Simoes @ FCT/UNL

package spelling;

import dataStructures.*;


@SuppressWarnings("serial")
public class ArrayIterator<E> implements Iterator<E> {

	private E[] array;
	private int counter;
	private int begin;
	private int end;
	
	public ArrayIterator(E[] array, int counter) {
		this.array = array;
		end = counter;
		rewind();
	}
	
	public ArrayIterator(E[] array, int begin, int end) {
		this.array = array;
		rewind(begin,end);
	}
	
	@Override
	public void rewind() {
		counter = begin;
	}
	
	private void rewind(int begin, int end) {
		counter = begin - 1;
		this.end = end;
	}

	@Override
	public boolean hasNext() {
		return counter < end;
	}

	@Override
	public E next() {
		return array[counter++];
	}

}
