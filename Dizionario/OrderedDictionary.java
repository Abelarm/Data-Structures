package Dizionario;

import PriorityQueue.Entry;

/**Estensione del dizionario che oltre ad avere i metodi de dizionario stesso ha alcuni metodi
 * che riguardano l'ordinamento**/
public interface OrderedDictionary<K, V> extends Dictionary<K, V> {
	
	/**Restituisce la entry con la chiave più piccola**/
	public Entry<K,V> first();
	
	/**Restituisce la entry con la chiave più grande**/
	public Entry<K,V> last();
	
	/**Restituisce una lista iterabile di tutte le entry con chiave più piccola**/
	public Iterable<Entry<K,V>> predecessors(K key);
	
	/**Restituisce una lista iterabile di tutte le entry con chiave più grande**/
	public Iterable<Entry<K,V>> successors(K key);
	
	

}
