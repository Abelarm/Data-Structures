package tree;

import NodeList.NodePositionList;
import NodeList.PositionList;
import position.Position;
/**Position particolare che corrisponderà al nostro nodo
 * oltre ad avere l'elemento ha un puntatore a TPosition cioè il puntatore al padre
 * e un puntatore ad una PositionList di figli**/
public class TNode<E> implements TPosition<E> {
		protected E element;
		protected TPosition<E> padre;
		protected PositionList<Position<E>> figli;
		
	/**Costruttore parametrico, che passa in input un elemento(l'elemento del TNode che costruiremo) e un TNode(il padre del TNode che costruiremo)**/
	public TNode(E e, TPosition<E> p){			
		element=e;
		padre=p;
		figli=new NodePositionList<Position<E>>();
	}

	
	/**Metodo di position per restituire l'elemento all'interno della position**/
	public E element() {				
		return element;
	}

	/**Aggiunge un nodo alla lista dei figli**/
	public void addChildren(TPosition<E> v) {			
		figli.addLast(v);
	}

	
	/**Restituisce una position list di figli**/
	public PositionList<Position<E>> getChildren() {		 
		return figli;
	}

	/**Setta il nuovo elemento e restituisce il vecchio**/
	public E setElement(E e) {   
	   E appoggio=element;
		element=e;
		return appoggio;
	}


	/**Setta il nuovo padre e restituisce il vecchio**/
	public TPosition<E> setParent(TPosition<E> p) {
		TPosition<E> appoggio=padre;
		padre=p;
		return appoggio;
	}


	/**Restituisce il padre di un nodo**/
	public TPosition<E> getParent() {  
		return padre;
	}



}
