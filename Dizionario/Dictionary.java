package Dizionario;

import PriorityQueue.Entry;
import PriorityQueue.InvalidKeyException;

/**TDA dizionar � un contenitore di entry<K,V> dove sono consentite entry con la stessa key**/
public interface Dictionary<K,V> {
	

	/**Restituisce il numero di elementi contenuti nel dizionario**/
	public int size();

	/**Restituisce true se il dizionario � vuoto altrimenti false**/
	public boolean isEmpty();

	/** Restituisce un�entry con chiave k; se non c�� restituisce null **/
	public Entry<K,V> find(K key) throws InvalidKeyException;

	/** Restituisce una collez. it. di tutte le entry di chiave key (se non c��, vuota) */
    public Iterable<Entry<K,V>> findAll(K key) throws InvalidKeyException;

    /** Inserisce un�entry nel dizionario e restituisce l�entry creata */
    public Entry<K,V> insert(K key, V value) throws InvalidKeyException;

    /** Cancella e restituisce un�entry dal dizionario */
    public Entry<K,V> remove(Entry<K,V> e) throws InvalidEntryException;

    /** Restituisce una collezione iterabile di tutte le entry del dizionario */
    public Iterable<Entry<K,V>> entries();
}