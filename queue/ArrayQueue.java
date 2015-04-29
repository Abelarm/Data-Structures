package queue;

import stack.ArrayStack;

/**la coda è implementata attraverso un array circolare teniamo traccia di due indici f=indice del primo elemento r= indice del primo elemento vuoto**/
public class ArrayQueue<E> implements Queue<E> {
	private E Q[ ]; // contiene gli elementi della coda
	protected int capacity; // la dimensione attuale della coda
	public static final int CAPACITY = 1000; // dimensione fissata per default
	private int f;// indice della testa
	private int r; // indice del primo elemento vuoto
	
	
	/**Costruttore senza parametro**/
	public ArrayQueue(){
		this(CAPACITY); //invoco il costruttore di sotto, passando CAPACITY come dimensione
	}
	
	/**Costruttore paramentrico, secondo la dimensione**/
	@SuppressWarnings("unchecked")
	public ArrayQueue(int cap){
		capacity=cap; //Costruttore con argomenti, ad Q viene passato un indrizzo di un Array di object di dimensione capacity
		Q=(E[])new Object[capacity];
	}

	
	/** Restituisce l'elemento in testa, e lo cancella */
	public E dequeue() throws EmptyQueueException { 
		if(isEmpty()) throw new EmptyQueueException("coda vuota, non puoi cancellare");  //Lancia l'eccezione se la coda è vuoto
		E x= Q[f];
		Q[f]=null;
		f=(f+1)% capacity; //il nuovo front è (f+1)% capacity per via dell'utillizzo circolare dell'array che stiamo facendo
		return x;
	}

	
	/** Inserisce all'interno della coda un elemento */
	public void enqueue(E elemento) {    
		if(size()==capacity-1) 			//scriviamo capacity-1 per via della convenzione che la coda è vuota solo quando f=r
			expand();
		Q[r]=elemento;
		r=(r+1)% capacity;  

	}
	
	/**Metodo crea un nuovo array con le dimensioni doppie e vi copia gli elementi del vecchio array**/
	@SuppressWarnings("unchecked")
	public void expand(){  
		capacity=capacity*2;
		E X[]=(E[])new Object[capacity];
		int j=this.size();
		for(int i=0;i<=j;i++){
	    X[i]=this.front();
	    enqueue(dequeue());
		}
	    Q=X;
	    f=0;
	    r=size();
		}


	/** Restituisce l'elemento in testa, ma non lo cancella */
	public E front() throws EmptyQueueException { 
		return Q[f];
	}

	/** Controlla se la coda è piena o vuota */
	public boolean isEmpty() {   
		if(f==r)		// Utilizziamo la notazione che la coda è vuota solo quando testa e indice dell'ultimo elemento coincidono
			return true;
		else
			return false;
	}

	/** Restituisce la dimensione della coda */
	public int size() {   //Restituisce la dimensione della coda
		return (capacity-f+r)%capacity;
	}
	
	
	//ESERCIZI
	/**Metodo per la stampa della coda**/
	public String toString(){
		String stringa="";
		int j=this.size();
		for(int i=0;i<j;i=(i+1)% capacity){  //faccio girare il for al massimo fino alla dimensione dello stack, ma l'incremento di i lo eseguo come se fosse f o r per via della circolarità della coda
			stringa=stringa+" "+ this.front().toString();
			this.enqueue(this.dequeue());  //utilizzo la circolarità della coda sper scorrermi tutto la coda stessa, rimuovendo il primo elemento e rimettendolo in coda, 
										 	//cosi alla fine del for la coda sarà nella situazione iniziale
			}                              
		return stringa;
	} 
	
	/**Metodo per controllare se due code sono uguali**/
	public boolean equals(ArrayQueue<E> X){
		boolean f=true; //flag
		
		if(this.size()!=X.size())
			return false;
		
		if(this.isEmpty() && X.isEmpty()) //se entrambi sono vuoti allora le code sono uguali
			return true;
		
		else
			if(this.isEmpty()||X.isEmpty()){ //visto che non sono tutte e due vuote chiedo se una delle due è vuota, se è cosi allora ritorno false(utilizzo questo procedimento per evitare le eccezzioni)
				return false;
			}
			else{
				int j=this.size();
				for(int i=0;i<j;i=(i+1)% capacity){ //faccio girare il for al massimo fino alla dimensione dello stack, ma l'incremento di i lo eseguo come se fosse f o r per via della circolarità della coda
					if(this.front()!=X.front())  //confronto i due elementi in testa se sono diversi setto la flag a false
						f=false;
					this.enqueue(this.dequeue());   //utilizzo la circolarità della coda sper scorrermi tutto la coda stessa, rimuovendo il primo elemento e rimettendolo in coda(cosi da avere alla fine del ciclo la coda nello stato inziale)
					X.enqueue(X.dequeue());  	
				}
			}
		if(!f)									//se la flag è false(vi è almeno un elemento diverso)
			return false;							//restituisco false
		else									//altrimenti
			return true;							//restituisco true
	}
	
	
	
	/**Metodo per clonare la coda su cui è invocata, restituendone una identica**/
	public ArrayQueue<E> clone(){
		if(this.isEmpty())
			throw new EmptyQueueException("Stack vuoto: impossibile eseguire metodo clone");	//Lancia un eccezione se la coda è vuota
		ArrayQueue<E> X= new ArrayQueue<E>();
		E appoggio;
		int j=this.size();
		for(int i=0;i<j;i=(i+1)% capacity){ //faccio girare il for al massimo fino alla dimensione dello stack, ma l'incremento di i lo eseguo come se fosse f o r per via della circolarità della coda
			appoggio=this.dequeue();  // mi faccio restituire il primo elemento
			this.enqueue(appoggio);   // lo reinsierisco in coda così sfrutto la circolarità dell'array(cosi da avere alla fien del ciclo la coda nello stato inziale)
			X.enqueue(appoggio);
			
		}
		return X;
	}
	
	/**Metodo per avere l'ultimo elemento inserito nella coda**/
	public E Last(){
		int j=this.size()-1;
		for(int i=0;i<j;i=(i+1)% capacity){  //faccio girare il for al massimo fino alla dimensione dello stack (però meno 1), ma l'incremento di i lo eseguo come se fosse f o r per via della circolarità della coda
			this.enqueue(this.dequeue());  //utilizzo la circolarità della coda per scorrermi tutto la coda stessa, rimuovendo il primo elemento e rimettendolo in coda
		}
		E toreturn=  this.front(); 		//Dentro una variabile d'appoggio inserisco il front corrisponde all'elemento inserito più di recente visto che ho fatto size-1 iterazioni
		this.enqueue(this.dequeue());	//riporto la coda allo stato inziale
		return toreturn;
	}
	
	/**Metodo per invertire la coda**/
	public void Reverse() {
		ArrayStack<E> P = new ArrayStack<E>();							//mi creo uno stack d'appoggio
		while (!isEmpty())												//finchè la mia coda non è vuota
			P.push(dequeue());												//inserisco i  miei elementi nello stack
		while (!P.isEmpty())											//finche lo stack non è vuoto
			enqueue(P.pop());												//insiersco gli elementi all'interno dello stack nella coda(ma per la natura dello stack LIFO si troveranno invertiti)
		}
	
	
	public ArrayQueue<E> Collapse(){
		ArrayQueue<E> X= new ArrayQueue<E>();
		X.enqueue(this.front());
		this.enqueue(this.dequeue());
		E last=X.front();
		
		int j=this.size();
		for(int i=1;i<j;i=(i+1)% capacity){
			if(this.front()==last)
				this.enqueue(this.dequeue());
			else{
				last= this.front();
				X.enqueue(last);
				this.enqueue(this.dequeue());
			}
		}
		return X;
	}
}
