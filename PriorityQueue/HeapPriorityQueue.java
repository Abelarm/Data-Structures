package PriorityQueue;

import java.util.Comparator;

import position.Position;

import defaultComparator.DefaultComparator;

import CompleteBinaryTree.ArrayListCompleteBinaryTree;
import CompleteBinaryTree.CompleteBinaryTree;

/**Implmentata attraverso una albero binario completo
 * e con la priprietà dell'heap order: per ogni noodo interno key(v)>= key(parent(v))
 * min/canellazione/inserimento O(log n)**/
public class HeapPriorityQueue<K, V> implements PriorityQueue<K, V> {
	
	protected CompleteBinaryTree<Entry<K,V>> heap;
	protected Comparator<K> comp;
	

	/** Creates an empty heap with the default comparator */
	public HeapPriorityQueue() {
	//usiamo un indexlist organizzata come heap [alb. Bin.completo + heap-order]
	heap = new ArrayListCompleteBinaryTree<Entry<K,V>>();
	comp = new DefaultComparator<K>();
	}
	
	/**Restituisce il numero di elementi contenuti nella PriorityQueue**/
	public int size() {
		return heap.size();				//Come contantore di elementi utilizzo il metodo .size del completebinaritree
	}

	/**Restituisce true se la PQ è vuota altrimenti false**/
	public boolean isEmpty() {
		if(this.size()==0)
			return true;
		else
			return false;
	}

	/**Restituisce la entry con la key più piccola**/
	public Entry<K, V> min() throws EmptyPriorityQueueException {
		if (isEmpty())											//se la nostra PQ è vuota lancia l'eccezione 
			throw new EmptyPriorityQueueException("Priority queue is empty");
		return heap.root().element();								//per come è strutturato l'heap la root è la entry con la chiave minore quindi sulla root castiamo .element() poichè heap.root() restituisce una position di Entry
	}
	
	/** Swaps the entries of the two given positions */
	protected void swap(Position<Entry<K,V>> x, Position<Entry<K,V>> y) {
		Entry<K,V> temp = x.element();
		heap.replace(x, y.element());
		heap.replace(y, temp);
	}
	
	/**Inserisci all'interno dell'heap una nuova entry**/
	public Entry<K,V> insert(K k, V x) throws InvalidKeyException {
		checkKey(k); 				// metodo per controllare se la chiave è valida
		Entry<K,V> entry = new MyEntry<K,V>(k,x);
		upHeap(heap.add(entry)); 				//upHeap eseguito sul nodo che contiene l’entry appena inserito
		return entry;
		}

	
	/**L’algoritmo “UpHeap” ripristina l’ordine delle key, effettuando degli swap tra
     il nodo che contiene k lungo il cammino che sale verso la root.*/
	protected void upHeap(Position<Entry<K,V>> v) {					
		Position<Entry<K,V>> u;					//crea una Positon di Entry d'appoggio
		while (!heap.isRoot(v)) {			//finche la position passata in input non è la root
			u = heap.parent(v);					//alla position d'appoggio assegna il padre della entry passata in input
			if (comp.compare(u.element().getKey(), v.element().getKey()) <= 0) 		 //se la key del padre è minore di quella del figlio(vuol dire che l'heap order è conservato)
				break;																	//esce dal while
			swap(u, v);							//sostituisce u con v(nodo passato in input)
			v = u;								//v assegnamo u(cioè se stesso, perché abbiamo effettutato lo swap
	}
	}


	/**Controlla se la key è valida e può essere comparata**/
	private void checkKey(K key)throws InvalidKeyException{
	try {
			comp.compare(key,key);				//comprara due volte la key, se questa key non può essere comparata allora lancia l'eccezion e
	}
	catch (Exception e){				//che viene catturata 
			throw new InvalidKeyException("Invalid key");			//e al suo posto viene mandata una eccezione diversa cioè invalidKeyException
		}
	}

	/**Rimuove la Entry più piccola **/
	public Entry<K,V> removeMin() throws EmptyPriorityQueueException {
		if (isEmpty()) 										//se la nostra PQ è vuota lancia l'eccezione 
			throw new EmptyPriorityQueueException("Priority queue is empty");
		Entry<K,V> min = heap.root().element();					//per come è strutturato l'heap il minimo si trova nella radice gli eseguiamo sopra il metodo element perché heap.root() è una Position di Entry
		if (size() == 1) 							//Se la dimensione dell'heap è 1
			heap.remove();								//rimuove l'ultimo elemento(cioè anche il primo)
		else { 
			heap.replace(heap.root(), heap.remove());				//Sostituisci la key della root con la key dell’ultimo nodo 
																		//e cancella questo ultimo nodo(cioè la vecchia radice)
		}
		downHeap(heap.root()); 					//downHeap eseguito a partire dalla root 
		return min;
		}
	
	
	
	/**L’algoritmo “DownHeap” ripristina la proprietà dell’heap-order effettuando lo
	swap tra il nodo che contiene la key k lungo il path dalla root fino alle foglie**/
	protected void downHeap(Position<Entry<K,V>> r) {
		Position<Entry<K,V>> s; 					// Creo una Position di Entry d'appoggio
		while (heap.isInternal(r)){ 				//finchè r(passato in input) non è una foglia
			if (!heap.hasRight(r)) 			//se r non ha figlio dx
				s = heap.left(r); 					//ad s assegno il figlio sinistro di r(passato in input)
			else 						//vuol dire che ha 2 figli: confronto le chiavi dei figli
				if (comp.compare(heap.left(r).element().getKey(),heap.right(r).element().getKey()) <=0) //se il sinistro è minore
						s = heap.left(r);			//ad s assegno il figlio sinistro di r(passato in input)
				else 					//altrimenti il destro è minore
					s = heap.right(r); 				//ad s assegno il figlio destro di r(passato in input)
		//a questo punto se la key di s è minore della key di r, faccio lo swap e ricomincio su s
				if (comp.compare(s.element().getKey(), r.element().getKey()) < 0) {
						swap(r, s);						//sostituisce r(nodo passato in input) con s
							r = s; 						//ad r assegnamo s(cioè se stesso, perché abbiamo effettutato lo swap)
							}
		//altrimenti esco: la proprietà dell’heap-order è ripristinata.
			else 
			break;
		} //end del while
	}

	
	

}
