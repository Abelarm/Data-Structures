package ArrayList;
/**
 * TDA per gestire un insieme di oggetti attraverso una lista con indice
 * dove è possibile inserire in qualsiasi posizione della lista (sempre rispettando le dimensioni della lista stessa)**/
public interface IndexList<E> extends Iterable<E>{
	
	/**Restituisce la dimensione della lista**/
	public int size();
	
	/**Restituisce true se la lista è vuota false altrimenti**/
	public boolean isEmpty();
	
	/**Aggiunge in posizione i l'elmeneto e**/
	public void add(int i, E e) throws IndexOutOfBoundsException;
	
	/**Restituisce l'elemento in posizione i**/
	public E get(int i) throws IndexOutOfBoundsException;
	
	/**Cancella e restituisce l'elemento contenuto nella posizione i**/
	public E remove(int i) throws IndexOutOfBoundsException;
	
	/**Setta il precedente elemento in posizione i con il nuovo elemento, ritornando il vecchio**/
	public E set(int i, E e) throws IndexOutOfBoundsException;
	

}