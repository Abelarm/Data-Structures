package CompleteBinaryTree;


import java.lang.Iterable;
import java.util.Iterator;

import ArrayList.ArrayIndexList;
import NodeList.BoundaryViolationException;
import NodeList.InvalidPositionException;


import position.Position;

import tree.EmptyTreeException;


public class ArrayListCompleteBinaryTree<E> implements CompleteBinaryTree<E> {
	private ArrayIndexList<BTPos<E>> T; // contiene gli elementi dell'albero		
	protected int size=0; // la dimensione attuale dell'albero
	public static final int CAPACITY = 1000; // dimensione fissata per defaul
	
	
	public BTPos<E> checkposition(Position<E> v) throws InvalidPositionException{
		try{
			BTPos<E> temp = (BTPos<E>) v;//Cast necessario per controllare se la position appartiene al tipo di dato ArraTree(cià² controlla se la position passata è una BTPos)
			return temp;
	}
		catch(ClassCastException x){		//Se il cast da errore cattura l'eccezione e ne lancia una diversa InvalidPositionException
			throw new InvalidPositionException("Position di tipo sbagliato per questo tipo di lista");
		}
	}
	
	public ArrayListCompleteBinaryTree(){
		T=new ArrayIndexList<BTPos<E>>(CAPACITY); //invoco il costruttore con la capacità  di default
		T.add(0, null);
	}
	
	public ArrayListCompleteBinaryTree(int x){
		T=new ArrayIndexList<BTPos<E>>(x); //invoco il costruttore parametrico
		T.add(0, null);
	}
	
	/**restituisce il numero di elementi dell'arryIndexlist**/
	public int size() {         
		return T.size()-1;
	}

	public boolean isEmpty() { 
		if(this.size()!=0)
			return false;
		else 
			return true;
	}

	public Iterator<E> iterator() {                    // restituisce un iteratore degli elementi contenuti nel nostro albero (ArrayIndexlist)
		ArrayIndexList<E> list = new ArrayIndexList<E>();   // crea un ArrayIndexList di elementi
		Iterator<BTPos<E>> iter = T.iterator();         //assegna ad un iteratore di BTPos l'iteratore del nostro albero
		iter.next();    // salta il primo elemento        
		int i=0; 
		while (iter.hasNext())         				//finchè ci sono elementi nell'iteratore 
			list.add(i++,iter.next().element());   //aggiungi alla lista di elementi all'indice i l'elemento contenuto nell'iteratore
		return list.iterator();						
	}

	public Iterable<Position<E>> positions() { 		//restituisce un iteratore delle position contenuti nel nostro albero (ArrayIndexlist)
		ArrayIndexList<Position<E>> P = new ArrayIndexList<Position<E>>(); 		//crea un ArrayIndexList di Position
		Iterator<BTPos<E>> iter = T.iterator(); 					//assegna ad un iteratore di BTPos l'iteratore del nostro albero
		iter.next(); 							//salta il primo elemento
		int i=0; 
		while (iter.hasNext()) 						//finchà© ci sono elementi nell'iteratore
			P.add(i++,iter.next());				//aggiungi alla lista di position all'indice i la position contenuta nell'iteratore
		return P;
	}

	public E replace(Position<E> v, E e) throws InvalidPositionException { //sostituisce l'elemento passato in input 'e' nella position 'v' 
		BTPos<E> temp=this.checkposition(v);               			//controlla se la position è valida 
		E returner=T.get(temp.get_index()).element();	// prende l'indice della position passata in input e lo utilizza per accedere all'array, e prende l'elemento contnuto nella BTposition in quell' indice
		temp.setElement(e);
		T.add(temp.get_index(),temp); //inserisce nell'array alla posizione di temp.getIndex()(l'indice della position passata in input) la vecchia position perà² con l'elemento cambiato
		return returner;
	}

	public Position<E> root() throws EmptyTreeException {    //restiruisce la radice dell'albero, nel caso in cui è vuoto lancia un'eccezione
		if(this.isEmpty())                             
			throw new EmptyTreeException("Lista vuota!");
		else 
			return T.get(1);
		
	}


	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		BTPos<E> temp = this.checkposition(v);  				//controlla se la position è valida 
		if(this.isRoot(v))									//controlla indice della position passata in input è uguale a 1 (cioà© la radice dell'albero)
			throw new BoundaryViolationException("Non esiste il padre della root.");   	//lancia l'eccezione
		int padre= (int) temp.get_index()/2;					//ogni padre ha due figli in posizione 2*(indice padre) e 2*(indice padre)+1, percò per risalire al padre prendiamo la parte intera della divisione (indice figlio)/2 
		return T.get(padre);						//ritorna la position indicata dell'indice padre (ottenuto dalla divisione di sopra)
	}

	
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {		//restituisce un iteraable dei figlio del nodo passato in input
		BTPos<E> temp=checkposition(v);														//controlla se la posizione è valida
		ArrayIndexList<Position<E>> L=new ArrayIndexList<Position<E>>();			//crea un ArrayIndexList di Position 
		L.add(0, null);
		L.add(1, T.get(temp.get_index()*2));										//Aggiunge in posizione 1 il primo figlio di temp
		L.add(2, T.get(temp.get_index()*2+1));										//Aggiunge in posizione 2 il primo figlio di temp
		return L;
		}


	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		BTPos<E> temp = this.checkposition(v);  				//controlla se la position è valida 
		try{								
			T.get(temp.get_index()*2);		//controllo se accedendo all'indice di position passata in input *2 lancia l'eccezione IndexOutOfBoundsException (cioè al di fuori dell'array)
			return true;					//se non lancia l'eccezione vuol dire che ha almeno un figlio e quindi è interno e restituisce true
		}		
		catch(IndexOutOfBoundsException e ){    //se l'elemento non esiste lancia l'eccezione e la catturiamo per restituire false
			return false;
		} 
	}

	@Override
	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		BTPos<E> temp = this.checkposition(v);  				//controlla se la position è valida 
		try{
			T.get(temp.get_index()*2);				//controllo se accedendo all'indice di position passata in input *2 lancia l'eccezione IndexOutOfBoundsException (cioè al di fuori dell'array)
			return false;							//se non lancia l'eccezione vuol dire che ha figli e quindi è interno e restituisce false
		}		
		catch(IndexOutOfBoundsException e ){ 		//se l'elemento non esiste lancia l'eccezione e la catturiamo per restituire true
			return true;
		} 
	}

	@Override
	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		BTPos<E> temp=this.checkposition(v); 	//controlliamo se la position passata in input è una BTPos altrimenti lancia l'eccezione InvalidPositionException
		if(temp.get_index()==1)           //controlla se l'indice della position è 1 (indice della root), se si si restituisce true
			return true;
		else
			return false;
	}

	/**Inserisce una foglia che contiene elem, come ultimo elemento dell’albero. 
	 * Quindi, la nuova foglia ha come padre il primo nodo dell’albero che ha meno di 2 figli (mantiene la proprietà di completezza).**/
	public Position<E> add(E elem) {				
		int i = size() + 1; 					
		BTPos<E> p = new BTPos<E>(elem,i); 			
		T.add(i, p); 
		return p; 
	}

	/**rimuove l’ultimo nodo z**/
	public E remove() {																	//Rimuove l'ultima position dell'arraylist
		if(isEmpty()) throw new EmptyTreeException("Tree is empty"); 	
		return T.remove(size()).element();												//Restituisce l'elemento rimosso
	}

	
	public Position<E> left(Position<E> v) throws InvalidPositionException,BoundaryViolationException {				//Restituisce il figlio sinistro della position passata in input
		BTPos<E> temp= checkposition(v);																		//Controlla se la position è valida
		if(!this.hasLeft(v))																				//se la position passata in input non ha figlio sinistro lancia l'eccezione altrimenti lo restituisce
			throw new BoundaryViolationException("NO left child");	
		else
			return T.get(temp.get_index()*2);
	}

	
	public Position<E> right(Position<E> v) throws InvalidPositionException,BoundaryViolationException {				//Restituisce il figlio destro della position passata in input
		BTPos<E> temp= checkposition(v);																				//Controlla se la position è valida
		if(!this.hasRight(v))																						//Se la position passata in input non ha figlio destro lancia l'eccezione altrimenti lo restituisce
			throw new BoundaryViolationException("NO right child");											
		else
			return T.get(temp.get_index()*2+1);
	}

	
	public boolean hasLeft(Position<E> v) throws InvalidPositionException {					//Restituisce true se ha un figlio sinistro o false se non lo ha
		BTPos<E> temp=checkposition(v);									//Controlla se la position è valida
		return temp.get_index()*2<=this.size();								//Se l'indice dell'eventuale figlio sinistro è minore di size allora esiste e restituisce true;
	}

	
	public boolean hasRight(Position<E> v) throws InvalidPositionException {				//Restituisce true se ha un figlio destro o false se non lo ha	
		BTPos<E> temp=checkposition(v);														//Controlla se la position è valida
		return temp.get_index()*2+1<=this.size();													//Se l'indice dell'eventuale figlio destro è minore di size allora esiste e restituisce true;
	}
	
	public String toString(){
		String stringa=" ";
		Iterator<E> iter=this.iterator();
		while(iter.hasNext())
			stringa=stringa + " " + iter.next().toString();
		return stringa;
	}

} 
