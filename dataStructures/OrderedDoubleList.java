// This is an Academic Project, and was published after finishing the lecture.
// @author Joao Elvas @ FCT/UNL
// @author Rodolfo Simoes @ FCT/UNL

package dataStructures;

public class OrderedDoubleList<K extends Comparable <K> , V> implements OrderedDictionary<K, V> {

	 static final long serialVersionUID = 0L;


	    // Node at the head of the list.
	    protected DListNode<Entry<K,V>> head;

	    // Node at the tail of the list.
	    protected DListNode<Entry<K,V>> tail;

	    // Number of elements in the list.
	    protected int currentSize;
	    
	public OrderedDoubleList(){
        head = null;
        tail = null;
        currentSize = 0;
	}
	@Override
	public boolean isEmpty() {
		return currentSize == 0;
	}

	@Override
	public int size() {
		return currentSize;
	}

	@Override
	public V find(K key) {
		DListNode<Entry<K,V>> node = findNode(key);
	        if ( node != null && node.getElement().getKey().compareTo(key) == 0 )
	        	return node.getElement().getValue();
	        else
	        	return null;
	}
	
	// Metodo ausiliar que encontra o node de uma determinada Key , caso nao encontre retorna null 
	private DListNode<Entry<K,V>> findNode (K key ){
		DListNode<Entry<K,V>> node = head;
        while ( node != null && node.getElement().getKey().compareTo(key) < 0 )
        {
            node = node.getNext();
        }
        return node;
		
	}

	@Override
	public V insert(K key, V value) {
		DListNode<Entry<K,V>> node = findNode(key);
		 if(node == null){
			//adicionar ao fim 
			DListNode<Entry<K,V>> tmpNode = new DListNode<Entry<K,V>>(new EntryClass<K,V>(key, value), tail, null);
			if(this.isEmpty())
				head = tmpNode;
			else
				tail.setNext(tmpNode);;
			tail= tmpNode;
			currentSize ++;
			return null;
		}
		else if (node.getElement().getKey().compareTo(key) == 0){
			//adicionar e remover o outro
			V oldValue = node.getElement().getValue();
			node.setElement(new EntryClass<K,V>(key , value) ); 
			return oldValue ;
		}
		 else if(node == head ){
			// adicionar a cabeca
			DListNode<Entry<K,V>> tmpNode = new DListNode<Entry<K,V>>(new EntryClass<K,V>(key, value), null, head);
			if(this.isEmpty())
				tail = tmpNode;
			else
				head.setPrevious(tmpNode);
			head = tmpNode;
			currentSize ++;
			return null;
		}
		else{ 
			// inserir numa posicao
			DListNode<Entry<K,V>> prevNode = node.getPrevious();
			DListNode<Entry<K,V>> nextNode = node.getNext();
			DListNode<Entry<K,V>> newNode = new DListNode<Entry<K,V>>(new EntryClass<K,V>(key, value), prevNode, nextNode);
		 	prevNode.setNext(newNode);            
		 	nextNode.setPrevious(newNode);            
		 	currentSize++;
			return null;
		}
	}

	@Override
	public V remove(K key) {
		DListNode<Entry<K,V>> node = findNode(key);
		if (node.getElement().getKey().compareTo(key) == 0){
			if(node == head){
					// remove o primeiro
					V oldValue = node.getElement().getValue();
					head = head.getNext();
					if(head == null)
						tail = null;
					else
					head.setPrevious(null);
					currentSize --;
					return oldValue;
			}
			else if (node == tail){
				// remove o ultimo
				V oldValue = node.getElement().getValue();
				tail =  tail.getPrevious();
				if(tail == null)
					head = null;
				else
				tail.setNext(null);
				currentSize --;
				return oldValue;
			}
			else {
				//remover numa posicao
				V oldValue = node.getElement().getValue();
				DListNode<Entry<K,V>> prevNode = node.getPrevious();
				DListNode<Entry<K,V>> nextNode = node.getNext();
				prevNode.setNext(nextNode);            
				nextNode.setPrevious(prevNode);            
				currentSize--;
				return oldValue;
			}
		}
		return null ;
		
	}

	@Override
	public Iterator<Entry<K, V>> iterator() {
		return new DoublyLLIterator<Entry<K,V>>(head, tail);
	}

	@Override
	public Entry<K, V> minEntry() throws EmptyDictionaryException {
		if (head == null)
			throw new EmptyDictionaryException();
		return head.getElement();
	}

	@Override
	public Entry<K, V> maxEntry() throws EmptyDictionaryException {
		if (tail == null)
			throw new EmptyDictionaryException();
		return tail.getElement();
	}

}
