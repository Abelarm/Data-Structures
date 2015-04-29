package Deque;

/**Oggetti particolari che al loro interno oltre ad avere l'elemento hanno due puntatori a DLNode che saranno il successivo è il precedente**/
public class DLNode<E> {
	private E element;      //Un campo di tipo generico contenente l'elemento 
	private DLNode<E> next, prev;  //due puntatori a DLNode prev punterà al DLNode precendete e next al DLNode successivo

	
	
	/**Costruttore parametrico**/
	public DLNode(E e, DLNode<E> p, DLNode<E> n) {		
		element = e;
		next = n;
		prev = p;
	}
	
	/**Costruttore non parametico**/
	public DLNode() {    					
		this(null, null, null); 
	}


	/**Modifica il campo element**/
	public void setElement(E newElem) {   
		element = newElem; 
	}


	/**Modifica il puntatore a DLNode successivo**/
	public void setNext(DLNode<E> newNext) {   
		next = newNext; 
	}

	/**Modifica il puntatore al DLNode precendete**/
	public void setPrev(DLNode<E> newPrev) {    
		prev = newPrev; 
	}

	/**Restituisce l'elemento**/
	public E getElement() {           
		return element; 
	}


	/**Restituisce il puntatore al DLNode successivo**/
	public DLNode<E> getNext() {    
		return next; 
	}


	/**Restituisce il puntatore al DLNode precendete**/
	public DLNode<E> getPrev(){		
		return prev;
	}



}