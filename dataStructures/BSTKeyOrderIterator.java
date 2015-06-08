// This is an Academic Project, and was published after finishing the lecture.
// @author Joao Elvas @ FCT/UNL
// @author Rodolfo Simoes @ FCT/UNL

package dataStructures;

public class BSTKeyOrderIterator<K, V> implements Iterator<Entry<K,V>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Stack<BSTNode<K,V>> stack ;
	private BSTNode<K,V> node ;
	
	public BSTKeyOrderIterator(BSTNode<K,V> root) {
		node = root;
		rewind();
	}
	
	@Override
	public boolean hasNext() {
		// * devolve false se a pilha vazia
		return !stack.isEmpty();
	}

	@Override
	public Entry<K, V> next() throws NoSuchElementException {
		// TODO XXXXXXX 
		// * retira o elemento d pilha (top)
		// * comecando no elemento a direita do top. inserir na pilha todos os elementos no percurso a esquerda
		// * devolve o elemento top
		BSTNode<K, V> topNode = stack.pop();
		BSTNode<K, V> rightNode =topNode.getRight();
		
			if(rightNode != null)
				pushLeftSide(rightNode);
		
		return topNode.getEntry();
		
	}

	@Override
	public void rewind() {
		//comecando na raiz , insere na pilha todos os elementos no percurso a esquerda 
		stack = new StackInList<BSTNode<K, V>>();
		pushLeftSide(node);
	}

	// metodo que empilha na pilha tudo o que esta a esquerda
	private void pushLeftSide (BSTNode<K, V> node){
		while(node != null){
		stack.push(node);
		node = node.getLeft();
		}
	}

}
