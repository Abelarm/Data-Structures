package Deque;




public class NodeDeque<E> implements Deque<E>{
	protected DLNode<E> header,trailer; 			//nodi sentinella,(che saranno nodi vuoti dove header è sempre prima del primo elemento e trailer sempre dopo l'ultimo elemento
	protected int size;								//numero di elementi
	
	public NodeDeque(){  					//Costruttore
		header= new DLNode<E>();			//Costruttore di un DLNode vuoto
		trailer= new DLNode<E>();			//  "          " "    "     "
		header.setNext(trailer);			//Si fa puntare il next di header a trailer, e il prev di trailer a header
		trailer.setPrev(header);
		size=0;
		}

	/**Aggiunge un elemento all'inizio della struttura**/
	public void addFirst(E element) {				
		DLNode<E> oldfirst = header.getNext();			//Crea un DLNode di appoggio e gli assegna header.getNext() cioè il vecchio primo elemento
		DLNode<E> first = new DLNode<E>(element,header,oldfirst); //Si crea un DLNode che sarà il nostro primo elemento e come parametri gli diamo: l'elemento, il suo prev punterà ad header, il next punterà al vecchio First 
		header.setNext(first);		//Al puntatore al successivo di Header gli facciamo puntare il nuovo DLNode creato (Ossia il nuovo first)
		oldfirst.setPrev(first);	//Nel puntatore al precendete del vecchio-first gli assegnamo il nuovo first
		size++;						//Aumenta la dimensione della struttura
	}
	
	/**Aggiune un elemento alla fine della struttura**/
	public void addLast(E element) throws EmptyDequeException {   
		DLNode<E> secondtolast = trailer.getPrev();	//Crea un DLNode di appoggio e gli assegna trailer.getPrev() cioè il vecchio ultimo elemento
		DLNode<E> last = new DLNode<E>(element, secondtolast, trailer); //Si crea un DLNode che sarà il nuovo-ultimo elemento e come parametri gli diamo: l'elemento, il suo prev punterà al vecchio ultimo, il next punterà a trailer
		secondtolast.setNext(last);		//Nel puntatore al successivo del vecchio-last gli assegnamo il nuovo last
		trailer.setPrev(last);		//nel puntatore al precendete di trailer gli facciamo puntare il nuovo DLNode creato last
		size++;						//Aumenta la dimensione della struttura
		
		
	}
	
	/**Ritorna il primo elemento ma non lo cancella**/
	public E getFirst() throws EmptyDequeException {  
		return header.getNext().getElement();		//Utilizziamo il getElement richiamato su header.getNext()
	}

	/**Ritorna l'ultimo elemento ma non lo cancella**/
	public E getLast() throws EmptyDequeException {
		return trailer.getPrev().getElement();		//Utilizziamo il getElement richiamato su trailer.getPrev()
	}

	/**Ritorna un boolean true se è vuota false altrimenti**/
	public boolean isEmpty() {		
		if(size==0)			
			return true;
		return false;
	}

	/**Rimuove il primo elemento della struttura e lo restituisce**/
	public E removeFirst() throws EmptyDequeException {  //Rimuove il primo elemento della strttura
		if(this.isEmpty())										//Se la strttura è vuota lancia l'eccezione
			throw new EmptyDequeException("Deque is empty");		
		DLNode<E> oldfirst= header.getNext();			//Creiamo un DLNode che punti al primo elemento
		header.setNext(oldfirst.getNext());			//Settiamo il next di header con il next del vecchio-primo(cioè facciamo puntare header al secondo)
		header.getNext().setPrev(header);			//Nel nuovo-primo(getNext()) settiamo il prev a header cosi facendo abbiamo tolto ogni riferimento al vecchio-primo 
		size--;									//Diminuiamo la dimensione della struttura
		return oldfirst.getElement();			//Restituiamo l'elemento contenuto nel vecchio-primo
	}

	/**Rimuove l'ultimo elemento della struttura e lo restituisce**/
	public E removeLast() throws EmptyDequeException {		//Rimuove l'ultimo elemento della struttura
		if(this.isEmpty())										//Se la strttura è vuota lancia l'eccezione
			throw new EmptyDequeException("Deque is empty");
		DLNode<E> oldlast= trailer.getPrev();			//Creiamo un DLNode che punti all'ultimo elemento
		trailer.setPrev(oldlast.getPrev());			//Settiamo il prev di trailer con il prev del vecchio-ultimo(cioè facciamo puntare trailer al penultimo)
		oldlast.getPrev().setNext(trailer);			//Nel nuovo ultimo(getPrev()) settiamo come next il trailer cosi facendo abbiamo tolo ogni riferimento al vecchio-ultimo
		size--;										//Diminuiamo la dimensione della struttura
		return oldlast.getElement();			   //Restituiamo l'elemento contenuto nel vecchio-ultimo
	}

	/**Restituisce la dimensione della Deque**/
	public int size() {		
		return size;
	}
	
	//ESERCIZI
	
	/**Metodo per stampare gli elementi all'interno della deque!**/
	public String toString(){					
		String ris = "primo elemento: -> ";
		int taglia = size();
		for (int i = 0; i < taglia; i++) {			//aggiungo alla fine l'elemento in coda, per size sfruttando la circolarità
		ris = ris + getFirst() + " ";
		addLast(removeFirst());  //Aggiungiamo come ultimo il primo elemento rimosso, facendo questo per size volte ci scorriamo tutta la struttura(e a fine ciclo è nello stato iniziale)
		}
	return ris;
 }
	
	/**Metodo per controllare se due Dequeue sono uguali**/
	public boolean equals(NodeDeque<E> X){
		boolean f=true;	
		if(this.size()!=X.size())
				return false;
			
			if(this.isEmpty() && X.isEmpty()) //se entrambi sono vuoti allora le strutture sono uguali
				return true;
			
			else
				if(this.isEmpty()||X.isEmpty()){ //visto che non sono tutte e due vuote chiedo se una delle due è vuota, se è cosi allora ritorno false(utilizzo questo procedimento per evitare le eccezzioni)
					return false;
				}
				else{
					int taglia = size();
					for (int i = 0; i < taglia; i++) {
						if(this.getFirst()!= X.getFirst())			//Appena trova due elementi diversi assegna la flag a false
							f=false;
						this.addLast(this.removeFirst());  //Aggiungiamo come ultimo il primo elemento rimosso, facendo questo per size volte ci scorriamo tutta la struttura(e a fine ciclo è nello stato iniziale)
						X.addLast(X.removeFirst());		
					}
				}
			if(!f)				     // se la flag è uguale a false vuol dire che abbiamo trovato almeno un elemento diverso
				return false;			//restituisco false
			return true;			//restituisco true
	}
	
	/**Metodo per restituire gli elementi all'interno della dequeue che sono multipli del intero passato in input**/
	public Deque<Integer> restituisciMultipli(int x){
	Deque<Integer> deque= new NodeDeque<Integer>();					//crea un coda d'appoggio
		for(int i=0;i<this.size();i++){									//mi scorro tutto la deque
			E element= this.removeFirst();								//rimuovo il primo elemento 
			int y=(Integer)element;										//lo casto a intero e lo inserisco in una variabile d'appoggio
			this.addLast(element);										//rimetto all'interno della nostra deque(come last) il primo elemento che abbiamo cancellato
			if(y%x==0)													//se questo l'elemento è multiplo del numero passato in input
				deque.addLast(y);												//lo insiersco nella dequeue da restituire
			}
		return deque;													//la restituisco
	}
}