package BinaryTree;

public class BTNode<E> implements BTPosition<E> { 

	private E element;														// elemento contenuto nel nodo
	private BTPosition<E> parent, left, right;								// riferimenti rispettivamente a padre, figlio destro e figlio sinistro
	
	/**costruttore, setto l'elemento, il padre, il figlio destro e il figlio sinistro	**/
	public BTNode(E element, BTPosition<E> parent, BTPosition<E> left, BTPosition<E> right) { 	
		setElement(element); 																	
		setParent(parent); 
		setLeft(left); 
		setRight(right);
		
	}
	
	/**Restiruisce l'elemento**/
	public E element() {										
		return element;	
	}

	/**Restituise il figlio sinistro**/
	public BTPosition<E> getLeft() {							
		return left;
	}

	/**Restituisce il figlio destro**/
	public BTPosition<E> getRight() {							
		return right;
	}

	/**Restituisce il padre**/
	public BTPosition<E> getParent() {							
		return parent;
	}

	/**Setta l'elemento a newElement**/
	public void setElement(E newElem) {						
		element = newElem;
	}

	/**Modifica la Position del figlio sinistro a newLeft**/
	public void setLeft(BTPosition<E> newLeft) {				
			left = newLeft;
		
	}

	/**Modifica la Position del figlio destro a newRight**/
	public void setRight(BTPosition<E> newRight) {				
		right = newRight;
		
	}

	/**Modifica la Position del genitore a newParent**/
	public void setParent(BTPosition<E> newParent) {			
		parent = newParent;
		
	}

}
