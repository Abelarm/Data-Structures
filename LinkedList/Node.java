package LinkedList;

/**Tipo di dato particolare che oltre ad avere l'elemento al suo interno ha anche il puntatore a Node che corrisponde al successivo**/
public class Node<E> {
	private E element;  //elemento generico
	private Node<E> next; //puntatore al prossimo nodo
	
	/**Costruttore con parametri**/
	public Node(E e,Node<E> n){ 
		element=e;
		next=n;
	}
	
	/**Costruttore senza parametri che si rifà al costruttore con i parametri passando null,null**/
	public Node(){ 
		this(null,null);
	}
	
	/**Restituisce l'elemento **/
	public E getElement(){ 
		return element;
	}
	
	/**Restituisce il puntatore al prossimo nodo**/
	public Node<E> getNext(){ 
		return next;
	}
	
	/**Modifica l'elemento all'interno del nodo**/
	public void setElement(E newElem){ 
		element=newElem;
	}
	
	/**Modifica il puntatore al successivo**/
	public void setNext(Node<E> newNext){ //
		next=newNext;
	}
}