package PriorityQueue;

import position.Position;
import Dizionario.InvalidEntryException;
/**implementazione della AdaptablePQ estendendo la sortedListPriorityQueue(lista ordinata)**/
public class SortedListAdaptablePQ<K, V> extends SortedListPriorityQueue<K, V> implements AdaptablePriorityQueue<V, K> {

	/**Metodo per controllare la validità di una key e di castarla**/
	private LocationAwareEntry<K,V> checkkey(Entry<K,V> e){
		try{
			LocationAwareEntry<K,V> temp= (LocationAwareEntry<K,V>)e;								//casta a LocationAwareEntry (potrebbe lanciare l'eccezione)	
			return temp;																			//restituisco la entry castata
				}
		catch(Exception ex){																		//catturo l'eventuale eccezione 
			throw new InvalidEntryException();															//lancio la nostra specifica
		}
	}
	
	/**Metodo ausiliario per l'inserimento modificato per settare la position quando viene inserito**/
	protected void insertEntry(Entry<K,V> e) { 
		LocationAwareEntry<K,V> temp= this.checkkey(e);							//controllo se la positon è valida e la casto
		
		if (this.isEmpty()){ 					//se la la nostra PQ è vuota
			entries.addFirst(temp); 					//inseriscel 'elemento all'inzio (perché sicuramente è la più piccola entry,(visto che è vuoto))
			temp.SetPos(entries.first());				//setto la position della LocationAwareEntry
		}
		else 
				if (c.compare(e.getKey(), entries.last().element().getKey()) > 0){ 			//Se la entry è maggiore dell'ultimo
					entries.addLast(temp); 																	// inserisce alla fine della lista
					temp.SetPos(entries.last());															//setto la position della LocationAwareEntry
				}
	else { 
		Position<Entry<K,V>> curr = entries.first(); 								//ad una Entry d'appoggio inserisce il primo elemento della lista
		while (c.compare(temp.getKey(), curr.element().getKey())> 0) { 				//finche la chiave del nostro elemento è maggiore della chiave della Entry d'appoggio
			curr = entries.next(curr); 													// alla nostra entry d'appoggio assegnamo la prossima entry
			} 
		entries.addBefore(curr, temp); 							//arrivati a questo punto la entry d'appoggio sara maggiore della nostra entry passata in input(altrimenti non si fermava il while), quindi la inseriamo prima di quest'ultima
		curr=entries.prev(curr);								//dentro curr metto la position precedente a curr stessa(cioè la position precisa dove ho inserito la mia entry)
		temp.SetPos(curr);										//setto la position della LocationAwareEntry
		}
	}
	
	
	/**sostituisce con k la chiave di e**/
	public void replaceKey(Entry<K, V> entry, K key) {
		LocationAwareEntry<K,V> temp= this.checkkey(entry);										//controllo se la positon è valida e la casto
		Position<Entry<K,V>> prev=entries.prev(temp.getPos());									//dentro una position mi faccio dare la position precendete a alla mi entry
		LocationAwareEntry<K,V> nuova= new LocationAwareEntry<K, V>(key, entry.getValue());		//creo una nuova entry avente come key la nuova key e come valore il vecchio valrore
		this.remove(temp);																		//rimuovo la vecchia entry
		entries.addAfter(prev, nuova);															//aggiungo dopo la position che mi sono conservato prima(quella precendete alla position dell'elemento) la entry nuova
		Position<Entry<K,V>> curr= entries.next(prev);											//dentro una position inserisco la position successiva a prev(cioè la position dove si trova la mia entry appena inserita)
		nuova.SetPos(curr);																		//e ne setto la position con questa position qui
	}

	
	/**sostituisce il valore di e con x**/
	public void replaceValue(Entry<K, V> entry, V x) {
		LocationAwareEntry<K,V> temp= this.checkkey(entry);										//controllo se la positon è valida e la casto
		Position<Entry<K,V>> prev=entries.prev(temp.getPos());									//dentro una position mi faccio dare la pisiton precendete a alla mi entry
		LocationAwareEntry<K,V> nuova= new LocationAwareEntry<K, V>(entry.getKey(), x);			//creo una nuova entry avente come key la vecchia key e come valore quello passato in input
		this.remove(temp);																		//rimuovo la vecchia entry
		entries.addAfter(prev, nuova);															//aggiungo dopo la position che mi sono conservato prima(quella precendete alla position dell'elemento) la entry nuova
		Position<Entry<K,V>> curr= entries.next(prev);											//dentro una position inserisco la position successiva a prev(cioè la position dove si trova la mia entry appena inserita)
		nuova.SetPos(curr);																		//e ne setto la position con questa position qui

	}
	
	/**elimina dalla PQ l’entry e (e la restituisce)**/
	public Entry<K,V> remove(Entry<K,V> e){
		LocationAwareEntry<K,V> temp= this.checkkey(e);											//controllo se la positon è valida e la casto
		return entries.remove(temp.getPos());													//invoco il remove sulla lista passandogli la position da rimuovere per avare il metodo in O(1)
	}
}
