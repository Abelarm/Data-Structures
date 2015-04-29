
package stack;

/**
 TDA per immagazinare oggetti arbitrari, 
 ogni nuovo elemento viene inserito in cima "top" allo stack
 l'elemento cancellato è sempre quello in cima allo stack
 
 */
public interface Stack<E> {
	
	/** Restituisce la dimensione dello stack */
	public int size();
	
	/**Restituisce true/false se lo stack è pieno o vuoto */
	public boolean isEmpty();
	
	/** Restituisce l'elemento in cima allo stack*/
	public E top() throws EmptyStackException;
	
	/**Restituisce l'elemento al top e lo elimina*/
	public E pop()throws EmptyStackException;
	
	/**Inserisci l'elemento nello stack */
	public void push(E o);
}
