package Sequence;

import position.Position;
import ArrayList.IndexList;
import NodeList.BoundaryViolationException;
import NodeList.InvalidPositionException;
import NodeList.PositionList;
/**TDA per gestire una position lista attraverso indici estendendo PositionList e IndexList, con l'aggiunta di alcuni metodi**/
public interface Sequence<E> extends IndexList<E>, PositionList<E> {
	// Metodi ponte (aggiuntivi)
	
	/**Metodo che passato un indice restituisce la position in quell'indice**/
	public Position<E> atIndex(int r) throws BoundaryViolationException;
	
	
	/**Metodo che passato una position restituisce l'indice all'interno della lista**/
	public int indexOf(Position<E> p) throws InvalidPositionException;
	}
