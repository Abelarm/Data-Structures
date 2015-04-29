package stack;

import LinkedList.Node;

public class LinkedStack<E> implements Stack<E> {
	private Node<E> top;
	private int size;
	
	public LinkedStack(){
		top=null;
		size=0;
	}
	
	
	/**Restituisce true/false se lo stack è pieno o vuoto */
	public boolean isEmpty() {
		if(top==null)
			return true;
		return false;
	}

	/**Restituisce l'elemento al top e lo elimina*/
	public E pop() throws EmptyStackException {
		if(this.isEmpty()) 
			throw new EmptyStackException("Lo stack è vuoto");
		E temp= top.getElement();
		top=top.getNext();
		size--;
		return temp;
	}

	/**Inserisci l'elemento nello stack */
	public void push(E o) {
		top=new Node<E>(o,top);
		size++;
	}

	
	/** Restituisce la dimensione dello stack */
	public int size() {
		return size;
	}

	
	/** Restituisce l'elemento in cima allo stack*/
	public E top() throws EmptyStackException {
		if(this.isEmpty()) 
			throw new EmptyStackException("Lo stack è vuoto");
		return top.getElement();
	}
	
	//ESERCIZI
	
	/**Metodo che clona il nostro stack**/
	public LinkedStack<E> clone() throws EmptyStackException {
		if(this.isEmpty())
			throw new EmptyStackException("Stack vuoto: impossibile eseguire metodo clone");				//Lancia un eccezione se lo stack è vuoto
		else{
			E appoggio;
			LinkedStack<E> X=new LinkedStack<E>();														//Stack d'appoggio
			LinkedStack<E> A=new LinkedStack<E>();														//stack d'appoggio
			int j=this.size();																			//dentro j inserisce la dimensione dello stack
			for(int i=0;i<j;i++){																		//Inserisce tutti gli elementi del nostro stack all'interno di uno stack d'appoggio, 
																											//usando i push e i pop si nel nostro stack d'appogigo saranno con l'ordine inverso
				appoggio= this.pop();
				A.push(appoggio);
			}
			for(int i = 0;i<j;i++){																		//ripristina allo stato "normale" il nostro stack facendo push e pop dallo stack d'appoggio
				appoggio=A.pop();																		//però il push non lo fa solo nello stack iniziale ma anche nel clone
				this.push(appoggio);
				X.push(appoggio);
			}
			
			return X;
		}
	}
	
	/**Metodo per controllare se due stack sono uguali**/
	public boolean equals(LinkedStack<E> X){
		boolean f=true; 						//flag
		LinkedStack<E> V=new LinkedStack<E>();														//Stack d'appoggio
		E appoggio=null;
		
		
		if(this.size()!=X.size())
			return false;
		
		if(this.isEmpty() && X.isEmpty()) 		//se entrambi sono vuoti allora gli stack sono uguali
			return true;
		
		else
			if(this.isEmpty()||X.isEmpty()){ 	//visto che non sono tutti e due vuoti chiedo se uno dei due è vuoto, se è cosi allora ritorno false(utilizzo questo procedimento per evitare le eccezzioni)
				return false;
			}
			else{
				int j=this.size()-1;
				for(int i=0;i<=j;i++){ 						//Inserisce all'interno di uno stack d'appoggio l'elemento in cima  ad X solo se è uguale a quello in cima al nostro stack
															//(l'altro stack elimina l'elemento senza salvarlo visto che è uguale al quello del nostro stack;
				   if(this.top()==X.top()){					// appena trova un elemento diverso si blocca e pone la flag uguale a false;
					   V.push(this.pop());
					   X.pop();
				   }
				   else
					   f=false;
				}
			}
															//blocco di codice per riportare entrambi gli stack allo stato iniziale
		int j=V.size();											//dentro una variabile metto la dimensione dello stack d'appoggio
		for(int i=0;i<=j;i++){									//faccio un for che si scorre tutto lo stack d'appoggio(V) che contiene gli elementi uguali
			appoggio=V.pop();										//dentro una variabile d'appoggio inserisco l'elemento totlo dallo stack d'appoggio
			this.push(appoggio);									//questo elemento lo metto nello stack su cui è invocato il metodo
			X.push(appoggio);										// e su quello passato in input
		}
		
		if(f)				//Se f è rimasta true per tutta la durata del for di confronto vuol dire che tutti gli elementi sono uguali, altrimenti vuol dire che ha trovato un elemento diverso
			return true;
		else
			return false;
		
	}
	
	/**Metodo per la stampa dello stack**/
	public String toString(){
		ArrayStack<E> A=new ArrayStack<E>();//Creo e inizializzo uno stack d'appoggio
		String stringa="(";
		E appoggio;
		if(this.isEmpty())  						//Se lo stack è vuoto ritorna stack vuoto
			return ("Stack vuoto");
			
		int j=this.size();	
		int i=0;
		for(i=0;i<j;i++){                  //Inverte gli elementi nello stack mettondoli nello stack d'appoggio
				A.push(this.pop());
			}
			for(i=0;i<j;i++){     //Riporta allo stato iniziale lo stack, e nel metre crea la stringa da restituire, tranne l'ultimo elemento che verra fatto fuori dal for
				appoggio=A.pop();
				stringa=stringa+appoggio.toString()+", ";
				this.push(appoggio);
			}
			appoggio=A.pop();										//faccio l'ultimo push-pop fuori dal for per inserire nella stringa da restituire l'aggiunta top
			stringa=stringa + appoggio.toString()+" :top)";
			this.push(appoggio);
			return stringa;
	}
	
	
}