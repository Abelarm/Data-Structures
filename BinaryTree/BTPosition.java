package BinaryTree;

import position.Position;
/**interfaccia di BTPosition che astrae il concetto di Nodo contiene tre riferimenti 1.Padre 2.Elemento 3.Figlio destro 4.Figlio sinistro**/
public interface BTPosition<E> extends Position<E> {			
	
	/**Restituisce il figlio sinistro **/
	public BTPosition<E> getLeft() ;						
	
	/**Restituisce il figlio destro **/
	public BTPosition<E> getRight() ;						
	
	/**Restiruisce il Padre**/
	public BTPosition<E> getParent();						 

	/**Setta l'elemento all'interno della BTPosition**/
	public void setElement(E newElem);						

	/**Modifica il riferimento del figlio sinistro della BTPosition su cui è invocato**/
	public void setLeft(BTPosition<E> newLeft);				

	/**Modifica il riferimento del figlio destro della BTPosition su cui è invocato**/
	public void setRight(BTPosition<E> newRight);			
	
	/**Modifica il riferimento al padre della BTPosition su cui è invocato**/
	public void setParent(BTPosition<E> newParent);			
	

}