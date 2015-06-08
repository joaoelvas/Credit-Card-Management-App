// This is an Academic Project, and was published after finishing the lecture.
// @author Joao Elvas @ FCT/UNL
// @author Rodolfo Simoes @ FCT/UNL

package dataStructures;

public class EntryClass<K, V> implements Entry<K, V> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private K key ;
	
	private V value;
	
	public EntryClass (K key , V value ){
		this.key = key ;
		this.value = value ;
		
	}

	@Override
	public K getKey() {
		return key;
	}

	@Override
	public V getValue() {
		return value;
	}
	
	public void setKey(K key){
		this.key = key;
	}
	
	public void setValue(V value){
		this.value = value;
	}

}
