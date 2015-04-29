package queue;
/**
Interfaccia della coda 
TDA che immagazina oggetti arbitrari i cui inserimenti e cancellazione avvengono secondo lo schema FIFO(first in first out)
*/
public interface Queue<E> {
	
	/** Restituisce la dimensione della coda */
	public int size();
	
	/** Controlla se la coda è piena o vuota */
	public boolean isEmpty();
	
	/** Restituisce l'elemento in testa, ma non lo cancella */
	public E front() throws EmptyQueueException;
	
	/** Inserisce all'interno della coda un elemento */
	public void enqueue(E elemento);
	
	/** Restituisce l'elemento in testa, e lo cancella */
	public E dequeue()throws EmptyQueueException;
}
