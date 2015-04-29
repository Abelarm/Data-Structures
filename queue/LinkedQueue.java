package queue;

import LinkedList.Node;
/**Implemenetata attraverso oggetti particolari che oltre ad avere l'elemento hanno un puntare al successivo**/
public class LinkedQueue<E> implements Queue<E> {
	private Node<E> front,rear;								//utilizzo due nodi "sentinella" front punter� al primo elemento rear all'ultimo
	private int size;						

	/**Costruttore senza parametri**/
	public LinkedQueue(){
		front=null;
		rear=null;
		size=0;
	}
	
	/** Restituisce l'elemento in testa, e lo cancella */
	public E dequeue() throws EmptyQueueException {
		if(this.isEmpty()) 	
			throw new EmptyQueueException("La coda � vuota");					//lancia eccezione se la coda � vuota
		E temp= front.getElement();												//dentro una variabile d'appoggio di tipo generico inserisco l'elemento contenuto nella front
		front=front.getNext();													//faccio puntare front al successivo di front(cos� da perdere ogni riferimento al vecchio front)
		size--;																	//diminuisco la dimensione
		return temp;															//restituisco l'elemento contenuto nel vecchio front
	}

	/** Inserisce all'interno della coda un elemento */
	public void enqueue(E elemento) {
		Node<E> temp=new Node<E>(elemento,null);								//creo una nuova position avente l'elemento e il puntatore a successivo uguale a null
		if(this.isEmpty()){														//se la coda � vuota
			front=temp;																//faccio corrispondere sia il front che il rear al node prima creato
			rear=temp;
		}
		else{																	//altirmenti
		rear.setNext(temp);															//setto il puntatore al successivo del rear con il mio nuovo node
		rear=temp;																	//assegno a rear il mio elemento che sar� il nuovo rear
		}
		size++;																	//in tutti i casi aumento la dimensione
		}

	
	/** Restituisce l'elemento in testa, ma non lo cancella */
	public E front() throws EmptyQueueException {
		if(this.isEmpty()) 
			throw new EmptyQueueException("La coda � vuota");
		return front.getElement();
	}

	
	/** Controlla se la coda � piena o vuota */
	public boolean isEmpty() {
		if(this.size()==0)
		 return true;
		return false;
	}

	/** Restituisce la dimensione della coda */
	public int size() {
		return size;
	}
	
	//ESERCIZI
	
	/**Metodo per controllare se due Code sono uguali**/ 
	public boolean equals(LinkedQueue<E> X){
		boolean f=true; //flag
		
		if(this.size()!=X.size())
			return false;
		
		if(this.isEmpty() && X.isEmpty()) //se entrambi sono vuoti allora le code sono uguali
			return true;
		
		else
			if(this.isEmpty()||X.isEmpty()){ //visto che non sono tutte e due vuote chiedo se una delle due � vuota, se � cosi allora ritorno false(utilizzo questo procedimento per evitare le eccezzioni)
				return false;
			}
			else{
				int j=this.size();
				for(int i=0;i<j;i++){ //faccio girare il for al massimo fino alla dimensione dello stack, 
					if(this.front()!=X.front())  //confronto i due elementi in testa se sono diversi setto la flag a false
						f=false;
					this.enqueue(this.dequeue());   //utilizzo la circolarit� della coda sper scorrermi tutto la coda stessa, rimuovendo il primo elemento e rimettendolo in coda
													//(cos� alla fine del for avr� la coda nella situazione di partenza)
					X.enqueue(X.dequeue());  	
				}
			}
		if(!f) 							//se la flag � uguale a false (almento un elemento � diverso)
			return false;					//restituisco false;
		else							//altrimenti
			return true;					//true;
	}
	
	/**Metodo per restituire una coda che � il clone della coda sui cui il metodo � invocato**/
	public LinkedQueue<E> clone(){
		if(this.isEmpty())
			throw new EmptyQueueException("La coda � vuota: impossibile eseguire metodo clone");//Lancia un eccezione se la coda � vuota
		LinkedQueue<E> X= new LinkedQueue<E>();
		E appoggio;
		int j=this.size();
		for(int i=0;i<j;i++){ //faccio girare il for al massimo fino alla dimensione dello stack,
			appoggio=this.dequeue();  // mi faccio restituire il primo elemento
			this.enqueue(appoggio);   // lo reinsierisco in coda cos� sfrutto la circolarit� della coda(cos� alla fine del for avr� la coda nella situazione di partenza)
			X.enqueue(appoggio);
			
		}
		return X;
	}
	
	/**Metodo per stampare l'ultimo elemento all'interno della coda**/
	public void Last(){
		int j=this.size()-1;
		for(int i=0;i<j;i++){  					//faccio girare il for al massimo fino alla dimensione dello stack (per� meno 1)
			this.enqueue(this.dequeue());  			//utilizzo la circolarit� della coda per scorrermi tutta la coda( rimuovendo il primo elemento e rimettendolo in coda)
		}
		System.out.println(this.front()); 		//Stampo l'elemento in front che corrisponde all'elemento inserito pi� di recente visto che ho fatto size-1 iterazioni
		this.enqueue(this.dequeue());			//riporto la coda allo stato inziale
	}

	
	
	public String toString()
	{
		String w = ("Contenuto della coda dal Front ---> ");
	    int taglia = size();
	    
	    for (int i=0; i < taglia; i++)
	    {
	    	E tmp= dequeue();  /* Lo cancello dalla coda rear */
	    	w=w+tmp+" ";  
	    	enqueue(tmp);      /* Lo metto dal front */
	  }
	    
	    return w;
	    
	}
	
	

}