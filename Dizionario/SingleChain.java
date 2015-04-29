package Dizionario;

import java.util.Comparator;

import position.Position;

import defaultComparator.DefaultComparator;

import Map.HashEntry;
import PriorityQueue.Entry;
import PriorityQueue.InvalidKeyException;
import Sequence.NodeSequence;

/**Implementazione del dizionario attraverso una lista doppiamente linkata inserimenti O(1) cancellazione e find O(n)**/
public class SingleChain<K, V> implements Dictionary<K, V> {
	private NodeSequence<Entry<K,V>> catena;
	protected Comparator<K> c; //serve per la checkKey
	
	/**Costruttore**/
	public SingleChain() {
	catena = new NodeSequence<Entry<K,V>>();
	c = new DefaultComparator<K>();
	}
	
	/** Restituisce un’entry con chiave k; se non c’è restituisce null **/
	public Entry<K,V> find(K key) throws InvalidKeyException {
	if (isEmpty()) 
		return null;
	checkKey(key);
	Position<Entry<K,V>> curr = catena.first();
	Entry<K,V> currEntry = curr.element();
	if (currEntry.getKey().equals(key)) 
		return currEntry;
	for(int i=1; i<catena.size(); i++) {
		curr = catena.next(curr);
		currEntry = curr.element();
	if (currEntry.getKey().equals(key)) 
		return currEntry;
	}
	return null;
}
	/**Controlla se la key è valida **/
	private void checkKey(K key)throws InvalidKeyException{
	if(key.equals(null))											//se la key è uguale a nulla allora lancia l'eccezione
		throw new InvalidKeyException("Invalid key");
	}
	
	
	/** Restituisce una collezione iterabile di tutte le entry del dizionario */
	public Iterable<Entry<K, V>> entries() {
		return catena;
	}
	
	public Iterable<Entry<K,V>> findAll(K key) throws InvalidKeyException {
		checkKey(key);
		NodeSequence<Entry<K,V>> toReturn = new NodeSequence<Entry<K,V>>();
		if(!isEmpty()) {
		Position<Entry<K,V>> curr = catena.first();
		Entry<K,V> currEntry = curr.element();
		for(int i=0;i<size()-1;i++) {
		if (currEntry.getKey().equals(key)) 
			toReturn.addLast(curr.element());
		curr=catena.next(curr);
		currEntry = curr.element();
		}
		if (catena.last().element().getKey().equals(key))
		toReturn.addLast(catena.last().element());
		}
		return toReturn;
	}
	
	
	public Entry<K,V> insert(K key, V value) throws InvalidKeyException
	{
	checkKey(key);
	Entry<K,V> toReturn = new HashEntry<K,V>(key, value);
	//uso HashEntry per usare equals sovrascritto
	catena.addLast(toReturn);
	return toReturn;
	}


	/**Restituisce true se la PQ è vuota altrimenti false**/
	public boolean isEmpty() {
		if(this.size()!=0)
			return false;
		else
			return true;
	}
	
	
	/** Cancella e restituisce un’entry dal dizionario */
	@SuppressWarnings("unchecked")
	public Entry<K, V> remove(Entry<K, V> e) throws InvalidEntryException {
		for(Entry<K,V> x: catena){				//per ogni Entry contenuta nella lista
			if(x.equals(e)){						//chiede la Entry passata è uguale alle Entry corrente
				catena.remove(((BSTEntry<K,V>)x).position());				//la rimuove però castandola a BSTEntry per fargli restituire la position in cui si trva
				return e;
			}
		}
		return null;	
	}
	
	/**Restituisce il numero di elementi contenuti nella PriorityQueue**/
	public int size() {
		return catena.size();				//Come contantore di elementi utilizzo il metodo .size della PositionList
	}
}
