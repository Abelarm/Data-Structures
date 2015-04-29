package Dizionario;

import java.util.Comparator;

import defaultComparator.DefaultComparator;

import ArrayList.ArrayIndexList;
import ArrayList.IndexList;
import Map.HashEntry;
import NodeList.NodePositionList;
import PriorityQueue.Entry;
import PriorityQueue.InvalidKeyException;

/**Implementazione del dizionario ordinato attraverso un IndexList
 * per avere la ricerca di una entry in O(log n) attraverso la ricerca binaria**/
public class OrderedSearchTable<K, V> implements OrderedDictionary<K, V> {
	IndexList<Entry<K,V>> lista;
	Comparator<K> C;
	int size;
	
	public OrderedSearchTable(){
		lista=new ArrayIndexList();
		C=new DefaultComparator();
	}
	

	/**Restituisce la dimensione dell dizionario**/
	public int size() {
		return size;
	}

	/**Restituisce vero se il dizionario è vuoto false altrimenti**/
	public boolean isEmpty() {
		return this.size()==0;
	}

	@Override
	public Entry<K, V> find(K key) throws InvalidKeyException {
		return this.BinarySearch(0, key, 0, size);
	}

	@Override
	public Iterable<Entry<K, V>> findAll(K key) throws InvalidKeyException {
		int index=this.BinarySearchIndex(0, key, 0, size);
		Entry<K,V> temp=this.find(key);
		
		if(index==-1)
			throw new InvalidKeyException("Entry non trovata");
		
		NodePositionList<Entry<K,V>> list= new NodePositionList<Entry<K,V>>();
		boolean f=true;
		int i=index;
		while(f){
			if(C.compare(temp.getKey(), lista.get(i).getKey())==0){
				list.addLast(lista.get(i));
				i++;
			}
			else
				f=false;
		}
		i=index-1;
		f=true;
		while(f){
			if(C.compare(temp.getKey(), lista.get(i).getKey())==0){
				list.addLast(lista.get(i));
				i--;
			}
			else
				f=false;
		}
		return list;
		
	}

	@Override
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		Entry<K,V> toinsert=new HashEntry<K,V>(key,value);
		if(this.size()==0){
			lista.add(0, toinsert);
			size++;
			return toinsert;
		}
		for(int i=0;i<size;i++){
			if(!(C.compare(lista.get(i).getKey(), toinsert.getKey())<=0)){
				lista.add(i, toinsert);
				size++;
				return toinsert;
			}
		}
		lista.add(size, toinsert);
		size++;
		return toinsert;
	
}

	@Override
	public Entry<K, V> remove(Entry<K, V> e) throws InvalidEntryException {
		for(int i=0;i<this.size();i++){
			if(C.compare(e.getKey(), lista.get(i).getKey())==0){
				lista.remove(i);
				size--;
				return e;
				}
			
		}
		return null;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		return lista;
	}

	@Override
	public Entry<K, V> first() {
		return lista.get(0);
	}

	@Override
	public Entry<K, V> last() {
		return lista.get(this.size()-1);
	}

	@Override
	public Iterable<Entry<K, V>> predecessors(K key) {
		NodePositionList<Entry<K,V>> list= new NodePositionList<Entry<K,V>>();
		Entry<K,V> finded=this.find(key);
		int index=this.BinarySearchIndex(0, key, 0, size);
		for(int i=index;i>0;i--){
			list.addLast(lista.get(i));
		}
		return list;
	}

	@Override
	public Iterable<Entry<K, V>> successors(K key) {
		NodePositionList<Entry<K,V>> list= new NodePositionList<Entry<K,V>>();
		Entry<K,V> finded=this.find(key);
		int index=this.BinarySearchIndex(0, key, 0, size);
		for(int i=index;i<size;i++){
			list.addLast(lista.get(i));
		}
		return list;
	}
	
	private Entry<K,V> BinarySearch(int start,K key,int low, int high){
		if(low > high)
			return null;
		else{
			int mid= (low+high)/2;
			Entry<K,V> e= lista.get(mid);
			if(C.compare(key, e.getKey())==0)
				return e;
			else{
				if(C.compare(key, e.getKey())<=0)
					return BinarySearch(start,key,low,mid-1);
				else
					return BinarySearch(start,key,mid+1,high);
		
			}
			
		}
		
	}
	
	private int BinarySearchIndex(int start,K key,int low, int high){
		if(low > high)
			return -1;
		else{
			int mid= (low+high)/2;
			Entry<K,V> e= lista.get(mid);
			if(C.compare(key, e.getKey())==0)
				return mid;
			else{
				if(C.compare(key, e.getKey())<=0)
					return BinarySearchIndex(start,key,low,mid-1);
				else
					return BinarySearchIndex(start,key,mid+1,high);
		
			}
			
		}
		
	}

}
