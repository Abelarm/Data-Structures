package Dizionario;

import PriorityQueue.Entry;
import position.Position;

/**Estensione di Entry che estende entry, e in più ha un puntatore a Position<Entry<K,V>> cioè è consapevole della propria posizione**/
public class BSTEntry<K,V> implements Entry<K,V> { 
	protected K key; 
	protected V value; 
	protected Position<Entry<K,V>> pos; 
	

/**Costruttore di default**/
 public BSTEntry() { 
	super();	 
} 

/**Costruttore parametrico**/
public BSTEntry(K k, V v, Position<Entry<K,V>> p) { 
		key = k; 
		value = v; 
        pos = p; 
} 

/**Restituisce la chiave dell'Entry**/
public K getKey() { 
	return key; 
} 

/**Restituisce il valore dell'Entry**/
public V getValue() { 
	return value; 
} 

/**Restituisce la sua position**/
public Position<Entry<K,V>> position() { 
	return pos;
}
}
