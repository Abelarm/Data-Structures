package Deque;


/**Tda che immagazina oggetti arbitrari,
 * si possono fare inserimenti/rimozioni sia in testa che in coda**/
public interface Deque<E> {
	
	
	/**Restituisce la dimensione della Deque**/
	public int size(); 
	 
	/**Ritorna un boolean true se è vuota false altrimenti**/
	public boolean isEmpty(); 
	
	/**Ritorna il primo elemento ma non lo cancella**/
	public E getFirst() throws EmptyDequeException;  
	
	/**Ritorna l'ultimo elemento ma non lo cancella**/
	public E getLast() throws EmptyDequeException; 
	
	/**Aggiunge un elemento all'inizio della struttura**/
	public void addFirst(E element);  
	
	/**Aggiune un elemento alla fine della struttura**/
	public void addLast(E element);	
	
	/**Rimuove il primo elemento della struttura e lo restituisce**/
	public E removeFirst() throws EmptyDequeException;
	
	/**Rimuove l'ultimo elemento della struttura e lo restituisce**/
	public E removeLast() throws EmptyDequeException;  
	
}
