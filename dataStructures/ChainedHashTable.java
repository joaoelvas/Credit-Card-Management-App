// This is an Academic Project, and was published after finishing the lecture.
// @author Joao Elvas @ FCT/UNL
// @author Rodolfo Simoes @ FCT/UNL

package dataStructures;  

public class ChainedHashTable<K extends Comparable<K>, V> 
    extends HashTable<K,V> 
{ 

    static final long serialVersionUID = 0L;


    // The array of dictionaries.
    protected Dictionary<K,V>[] table;


    @SuppressWarnings("unchecked")
	public ChainedHashTable( int capacity )
    {
        int arraySize = HashTable.nextPrime((int) (1.1 * capacity));
        // Compiler gives a warning.
        table = (Dictionary<K,V>[]) new Dictionary[arraySize];
        for ( int i = 0; i < arraySize; i++ )
        table[i] = new OrderedDoubleList<K,V>();
        maxSize = capacity;
        currentSize = 0;
    }                                      


    public ChainedHashTable( )
    {
        this(DEFAULT_CAPACITY);
    }                                                                


    // Returns the hash value of the specified key.
    protected int hash( K key )
    {
        return Math.abs( key.hashCode() ) % table.length;
    }


    // If there is an entry in the dictionary whose key is the specified key,
    // returns its value; otherwise, returns null.
    public V find( K key )
    {
        return table[ this.hash(key) ].find(key);
    }


    // If there is an entry in the dictionary whose key is the specified key,
    // replaces its value by the specified value and returns the old value;
    // otherwise, inserts the entry (key, value) and returns null.
    public V insert( K key, V value )
    {
        if ( this.isFull() ){
            this.rehash();
            return null;
        }
        V vale = table[ this.hash(key) ].insert(key, value);
        if(vale == null)
        	currentSize ++;
        return vale;
    }


    // If there is an entry in the dictionary whose key is the specified key,
    // removes it from the dictionary and returns its value;
    // otherwise, returns null.
    public V remove( K key )
    {
    	V vale = table[ this.hash(key) ].remove(key);
    	if(vale != null)
        	currentSize --;
        return vale;
    }


    // Returns an iterator of the entries in the dictionary.
    public Iterator<Entry<K,V>> iterator( )
    {
        return new CHTableIterator<>(table);
    } 
    
    @SuppressWarnings("unchecked")
	private void rehash() {
    	maxSize = maxSize*2;
    	Dictionary<K, V> tmpTable[];
    	Iterator<Entry<K, V>> it = new CHTableIterator<>(table);
    	Entry<K, V> entry;
    	int arraySize = HashTable.nextPrime((int) (1.1 * maxSize));
    	tmpTable = (Dictionary<K,V>[]) new Dictionary[arraySize];
    	for(int i = 0 ; i< tmpTable.length ; i++ )
    		tmpTable[i] = new OrderedDoubleList<K,V>();
    	while(it.hasNext()) {
    		entry = it.next();
    		K key = entry.getKey();
    		V value = entry.getValue();
    		tmpTable[hash(key)].insert(key, value);
    	}
    	table = tmpTable;
    }
}