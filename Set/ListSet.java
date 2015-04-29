package Set;

import java.util.Comparator;
import java.util.Iterator;

import position.Position;

import NodeList.NodePositionList;
import NodeList.PositionList;

import defaultComparator.DefaultComparator;

/**Implementazione attraverso una lista ordinata per rendere più efficienti le operazioni**/
public class ListSet<E> implements Set<E>{
	protected PositionList<E> A;     //la lista che contiene gli elementi dell'insieme
	protected Comparator<E> comp = new DefaultComparator<E>();

	
	/**Costruttore che crea un insieme vuoto**/
	public ListSet() {
    A = new NodePositionList<E>();
    }
     
    /**Costruttore che crea un insieme vuoto, e assegna il comparatore passato come argomento**/
    public ListSet(Comparator<E> z) {
    	  A = new NodePositionList<E>();
    	  comp=z;
      }
      
      /**Costruttore che crea un insieme con un elemento e il comparatore di deafult*/
      public ListSet(E elem) {
    	  A = new NodePositionList<E>();
    	  A.addLast(elem);
     }
      
      /**Costruttore che crea un insieme con un elemento e il comparatore passato*/
      public ListSet(E elem, Comparator<E> z) {
    	  A = new NodePositionList<E>();
    	  A.addLast(elem);
    	  comp=z;
      }
     
      /**Costruttore che crea un insieme con la lista passata e il comparatore passato*/
      @SuppressWarnings("unused")
	private ListSet(NodePositionList<E> M, Comparator<E> z) {
    	  A = M;
    	  comp=z;
      }
	
      
     /**Restituisce la dimensione dell'insieme**/
	public int size() {
		return A.size();
	}
	
	/**Restituisce true se è vuoto false altrimenti**/
	public boolean isEmpty() {
		return this.size()==0;
	}
	
	/**Restituisce la collezione iterabile degli elementi presenti nell'insieme**/
	public Iterable<E> elements() { 
		return A;
	}
	
	/**Crea un iteratore degli elementi dell'insieme**/
	public Iterator<E> iterator() { //metodo utile
		return elements().iterator();
	}
	
	
	public E insert(E e){
		Position<E> pos= A.first();
	for(int i=0;i<this.size();i++){
		if(comp.compare(pos.element(), e)==0)
			throw new InvalidInsert();
		if(comp.compare(pos.element(), e)>0){
			A.addBefore(pos, e);
			return e;
		}
	}
	A.addLast(e);
	return e;
	}
	
	/**Crea un nuovo insieme, risultato dell'unione tra quello su cui è invocato e quello parametrico**/
	public Set<E> union(Set<E> B) {
		Set<E> C = new ListSet<E>();		//Crea un insieme vuoto
	    Merge<E> op;						//Crea un oggetto merge
	    op = new UnionMerge<E>();			//e la istanzia come UnionMerge
	    op.merge(A,(PositionList<E>)((ListSet<E>) B).elements(),comp,(PositionList<E>)((ListSet<E>) C).elements());		//Merge prende come input, due positionList un comparatore, e la position list dell'insieme da riempire
	    //il secondo e il terzo insieme vengono castati a ListSet(nostra classe) per potergli invocare elements che restituisce un iterable(quindi questo lo castiamo a PositionList, per via della firma del metodo merge
	    return (Set<E>)C;					//ritorniamo l'insieme C unione dei due insiemi A e B
	}
	
	/**Crea un nuovo insieme, risultato dell'intersezione tra quello su cui è invocato e quello parametrico**/
	public Set<E> intersect(Set<E> B) {
		Set<E> C = new ListSet<E>();		//Crea un insieme vuoto
	    Merge<E> op;						//Crea un oggetto merge
	    op = new IntersectMerge<E>();			//e la istanzia come IntersectMerge
	    op.merge(A,(PositionList<E>)((ListSet<E>) B).elements(),comp,(PositionList<E>)((ListSet<E>) C).elements());		//Merge prende come input, due positionList un comparatore, e la position list dell'insieme da riempire
	    //il secondo e il terzo insieme vengono castati a ListSet(nostra classe) per potergli invocare elements che restituisce un iterable(quindi questo lo castiamo a PositionList, per via della firma del metodo merge
	    return (Set<E>)C;					//ritorniamo l'insieme C intersezione dei due insiemi A e B
	}
	
	
	/**Crea un nuovo insieme, risultato della sottrazione tra quello su cui è invocato e quello parametrico**/
	public Set<E> subtract(Set<E> B) {
		Set<E> C = new ListSet<E>();		//Crea un insieme vuoto
	    Merge<E> op;						//Crea un oggetto merge
	    op = new SubtractMerge<E>();			//e la istanzia come SubtractMerge
	    op.merge(A,(PositionList<E>)((ListSet<E>) B).elements(),comp,(PositionList<E>)((ListSet<E>) C).elements());		//Merge prende come input, due positionList un comparatore, e la position list dell'insieme da riempire
	    //il secondo e il terzo insieme vengono castati a ListSet(nostra classe) per potergli invocare elements che restituisce un iterable(quindi questo lo castiamo a PositionList, per via della firma del metodo merge
	    return (Set<E>)C;					//ritorniamo l'insieme C sottrazione di B da A
	}
}