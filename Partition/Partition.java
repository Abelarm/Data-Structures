package Partition;


import Set.Set;
import position.Position;
/**TDA che contiene una collezione di insiemi a due a due disgiunti**/
public interface Partition<E> {
	
	/**Restituisce la dimensione della partizione**/
	public int size();
	
	/**Restituisce true se è vero, altrimenti false**/
	public boolean isEmpty();
	
	/**crea l'insieme con il solo elemento x e lo aggiunge alla partizione (restituisce la sua position)**/
	public Position<E> makeSet(E x);
	
	/**Aggiunge alla partizione l'insieme A U B, distruggendo  A e B*/
	public Set<E> union(Set<E> A, Set<E> B);
	
	
	/**restituisce l'insieme che contiene l'elemento in position p**/
	public Set<E> find(Position<E> p);
}
