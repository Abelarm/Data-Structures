package tree;

import java.util.Iterator;
import java.lang.Iterable;

import NodeList.BoundaryViolationException;
import NodeList.InvalidPositionException;

import position.Position;

/**TDA contentitore di oggetti ordinati gerarchicamente, le elemento in cima è detto radice 
 * ad eccezione della radice ogni elemento ha un solo padre e zero o più figli
 * NodoInterno: Nodo con almeno un figlio
 * Foglia: nodo senza figli**/
public interface Tree<E> extends Iterable<E>{ 
	
	/**Restituisce la dimensione dell'albero**/
	public int size();
	
	/**Restituisce true se l'albero è vuoto**/
	public boolean isEmpty();	
	
	/**Restituisce un iteratore degli elementi contenuti nell'albero**/
	public Iterator<E> iterator(); 
	
	/** Restituisce una collezione iterabile dei nodi (positions) dell'albero. */ 
	public Iterable<Position<E>> positions(); 
	
	/**Sostituisce l'elemento contenuto nella Position p con quello passato in input, e restituisce il vecchio**/
	public E replace(Position<E> v, E e) throws InvalidPositionException; 
	
	/**Restituisce la root dell'albero**/
	public Position<E> root() throws EmptyTreeException; 
	
	/**Restituisce il padre del noodo passato in input**/
	public Position<E> parent(Position<E> v) throws InvalidPositionException,  BoundaryViolationException;
	
	/** Restituisce una collezione iterabile dei figli (positions) di un dato nodo (position) */
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException; 
	
	/**Restituisce true se v è un nodo interno, false altrimenti**/
	public boolean isInternal(Position<E> v) throws InvalidPositionException; 
	
	/**Restituisce true se v è una foglia, false altrimenti**/
	public boolean isExternal(Position<E> v) throws InvalidPositionException; 
	
	/**Restituisce true se la position passata è la root**/
	public boolean isRoot(Position<E> v) throws InvalidPositionException; 
	
	
	}


