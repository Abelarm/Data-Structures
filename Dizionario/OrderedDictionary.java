package Dizionario;

import PriorityQueue.Entry;

/**Estensione del dizionario che oltre ad avere i metodi de dizionario stesso ha alcuni metodi
 * che riguardano l'ordinamento**/
public interface OrderedDictionary<K, V> extends Dictionary<K, V> {
	
	/**Restituisce la entry con la chiave pi� piccola**/
	public Entry<K,V> first();
	
	/**Restituisce la entry con la chiave pi� grande**/
	public Entry<K,V> last();
	
	/**Restituisce una lista iterabile di tutte le entry con chiave pi� piccola**/
	public Iterable<Entry<K,V>> predecessors(K key);
	
	/**Restituisce una lista iterabile di tutte le entry con chiave pi� grande**/
	public Iterable<Entry<K,V>> successors(K key);
	
	

}
