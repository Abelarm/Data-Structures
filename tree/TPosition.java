package tree;


import NodeList.PositionList;
import position.Position;

public interface TPosition<E> extends Position<E> {
	
	/**Aggiunge un nodo alla lista dei figli**/
	public void addChildren(TPosition<E> v);
	
	/**Restituisce il padre di un nodo**/
	public TPosition<E> getParent();
	
	/**Restituisce una position list di figli**/
	public PositionList<Position<E>> getChildren();
	
	/**Setta il nuovo elemento e restituisce il vecchio**/
	public E setElement(E e);
	
	/**Setta il nuovo padre � restituisce il vecchio**/
	public TPosition<E> setParent(TPosition<E> p);

}
