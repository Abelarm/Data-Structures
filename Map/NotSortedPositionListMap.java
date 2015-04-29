package Map;

import java.util.Iterator;

import position.Position;
import NodeList.NodePositionList;
import NodeList.PositionList;
import PriorityQueue.Entry;
import PriorityQueue.InvalidKeyException;
import PriorityQueue.MyEntry;
/**Implementazione attraverso una lista doppiamente linkata tutti i metodi vengono fatti in O(n)**/
public class NotSortedPositionListMap<K,V> implements Map<K, V> {
	NodePositionList<Entry<K,V>> lista;
	
	
	/**Costruttore senza paremtri**/
	public NotSortedPositionListMap(){
		lista=new NodePositionList<Entry<K,V>>();
	}
	
	
	/**Restituisce la dimensione della mappa**/
	public int size() {
		return lista.size();
	}

	/**Restituisce true se la mappa ï¿½ vuota altrimenti false**/
	public boolean isEmpty() {
		if(this.size()!=0)
			return false;
		else
			return true;
	}
	
	/**Controlla se la key è valida e può essere comparata**/
	private void checkKey(K key)throws InvalidKeyException{
	if(key.equals(null))											//se la key ï¿½ uguale a nulla allora lancia l'eccezione
		throw new InvalidKeyException("Invalid key");
	}

	/**Inserisce lï¿½entry (k, v) nella mappa M. Se la chiave k non ï¿½ giï¿½ presente in M, restituisce null; altrimenti restituisce il vecchio valore associato a k [sostituisce]**/
	public V put(K key, V value) throws InvalidKeyException {
		this.checkKey(key);									//Controllo se la posizione ï¿½ valida
		V toreturn=null;									//Creo una variabile d'appoggio di tipo V ï¿½ la setto a null
		for(Entry<K,V> x: lista){							//per ogni Entry<K,V> appartenten a lista(for generico)
			if(x.getKey().equals(key)){					//se la chiave della Entry corrente ï¿½ uguale alla chiave passata in input
				toreturn=x.getValue();								//Nella variabile d'appoggio inserisco la value dell'elemento corrente
				x= new MyEntry<K,V>(key,value);						//Alla Entry corrente assegno una nuova entry aventi come parametri quelli passati in input 
			}
		}
		lista.addLast(new MyEntry<K,V>(key,value));			//altrimenti inserisco alla fine la mia entry creata con i parametri passati in input
		return toreturn;									//restituisco tu return
	}

	/**Se la mappa M ha lï¿½entry con chiave k, restituisce il valore associato, altrimenti null.**/
	public V get(K key) throws InvalidKeyException {			
		this.checkKey(key);								//Controllo se la posizione ï¿½ valida
		for(Entry<K,V> x: lista){						//per ogni Entry<K,V> appartenten a lista(for generico)
			if(x.getKey().equals(key))				//se la chiave della Entry corrente ï¿½ uguale alla chiave passata in input
				return x.getValue();							//restituisco il valore della Entry corrente(cioï¿½ quella con la key uguale a quella passata in input
			}
		return null;									//altrimenti restituisco null
	}

	/**Se nella mappa M cï¿½ï¿½ un entry con chiave k, rimuove lï¿½entry con chiave k e restituisce il valore associato. Altrimenti restituisce null.**/
	public V remove(K key) throws InvalidKeyException {
		this.checkKey(key);								//Controllo se la posizione ï¿½ valida
		V toreturn=null;								//Creo una variabile d'appoggio di tipo V ï¿½ la setto a null
		Iterable<Position<Entry<K, V>>> prova=lista.positions();
		for(Position<Entry<K, V>> x: prova){						//per ogni Entry<K,V> appartenten a lista(for generico)
			if(x.element().getKey().equals(key)){					//se la chiave della Entry corrente ï¿½ uguale alla chiave passata in input
				toreturn=x.element().getValue();							//Nella variabile d'appoggio inserisco la value dell'elemento corrente
				Position<Entry<K,V>> temp=x;
				lista.remove(temp);				//rimuovo l'elemento corrente castato a Position<Entry<K,V>> perchï¿½ la lista contiene position di entry
			}
		}
		return toreturn;							//restituisco tu return
	}

	/**restituisce una collezione iterabile delle chiavi della Map**/
	public Iterable<K> keys() {
		PositionList<K> iterabile = new NodePositionList<K>();			//Creo una NodePositionList<K> di keys che sara la mia lista iterable
		for(Entry<K,V> x: lista){										//per ogni Entry<K,V> appartenten a lista(for generico)
			iterabile.addLast(x.getKey());									//alla lista(iterable) aggiungo la key(key) dell'Entry corrente
		}
		return iterabile;												//ritorno la lista cosi creata
	}

	/**restituisce una collezione iterabile dei valori della Map**/
	public Iterable<V> values() {
		PositionList<V> iterabile = new NodePositionList<V>();			//Creo una NodePositionList<K> di keys che sara la mia lista iterable
		for(Entry<K,V> x: lista){										//per ogni Entry<K,V> appartenten a lista(for generico)
			iterabile.addLast(x.getValue());									//alla lista(iterable) aggiungo il valore(value) dell'Entry corrente
		}
		return iterabile;												//ritorno la lista cosi creata
	}

	/**restituisce una collezione iterabile delle entry (k,v) della Map**/
	public Iterable<Entry<K, V>> entries() {					
		return lista;													//restituisco la mia lista che ï¿½ gia un iterable essendo una NodePositionList<Entry<K,V> cioï¿½ di entry
	}
	
	public String toStringKey(){
		String stringa="";
		if(this.isEmpty())
			stringa=null;
		else{
			Iterator<K> stampa=this.keys().iterator();
			while(stampa.hasNext())
				stringa=stringa +" " +  stampa.hasNext();
		}
		return stringa;
	}
	
	public String toStringValuey(){
		String stringa="";
		if(this.isEmpty())
			stringa=null;
		else{
			Iterator<V> stampa=this.values().iterator();
			while(stampa.hasNext())
				stringa=stringa +" " +  stampa.hasNext();
		}
		return stringa;
	}
	
	public String toStringPair(){
			String stringa="";
			if(this.isEmpty())
				stringa=null;
			else{
				Iterator<Entry<K,V>> stampa=this.entries().iterator();
				while(stampa.hasNext()){
					Entry<K,V> temp=stampa.next();
					stringa=stringa+ " " + "key: " + temp.getKey() + " valore:" + temp.getValue();
				}
			}
			return stringa;
	}

}
