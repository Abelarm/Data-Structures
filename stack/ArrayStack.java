package stack;


public class ArrayStack<E> implements Stack<E> {
	private E S[ ]; // contiene gli elementi dello stack
	protected int capacity; // la dimensione attuale dello stack
	public static final int CAPACITY = 1000; // dimensione fissata per default
	private int top = -1; // indice del top
	
	
	public ArrayStack(){
		this(CAPACITY); //invoco il costruttore di sotto, passando CAPACITY come dimensione
	}
	
	
	@SuppressWarnings("unchecked")
	public ArrayStack(int cap){
		capacity=cap; //Costruttore con argomenti, ad S viene passato un indrizzo di un Array di object di dimensione capacity
		S=(E[])new Object[capacity];
	}
	
	
	/**Restituisce true/false se lo stack è pieno o vuoto */
	public boolean isEmpty() {
		if(top==-1)//se il top è uguale alla condizone di creazione dello stack(cioè quando è vuoto), restituiamo false true altrimenti
			return true;
		else
			return false;
	}

	
	/**Restituisce l'elemento al top e lo elimina*/
	public E pop() throws EmptyStackException {
		if(this.isEmpty())
			throw new EmptyStackException("Stack vuoto: impossibile fare il pop");//Lancia un eccezione se lo stack è vuoto
		else{
		E x=S[top];//ad un variabile d'appoggio si assegna il primo elemento, cioè il top, nella posizione del top si fa corrispondere null, e top si decrementa.
		S[top]=null;
		top--;
		return x;
		}
	}
	
	@SuppressWarnings("unchecked")
	public void Expand(){
		capacity=capacity*2;
		E X[]=(E[])new Object[capacity];//In un array di appoggio(X) di dimensione doppia rispetto a quella dell'array inizale, viene copiato tutto il nostro stack, appena finito si
										  //assegna a S l'indizzo di X affinche S contenga l'indirizzo di un array con dimensione maggiore ma con gli stessi elementi.
		int j=this.size()-1;
		for(int i=0;i<=j;i++){
    	X[i]=S[i];
		}
        S=X;
	}

	/**Inserisci l'elemento nello stack */
	public void push(E o) {
		if(this.size()==capacity){
			Expand();
			}
		top++;//Si incrementa top, e si aggiunge in cima allo stack l'elemento passato come parametro
		S[top]=o;

	}

	
	/** Restituisce la dimensione dello stack */
	public int size() {
		return top+1;//Restituisce top+1 perché  lo stack parte da 0.
	}


	/** Restituisce l'elemento in cima allo stack*/
	public E top() throws EmptyStackException {
		if(this.isEmpty())
			throw new EmptyStackException("Stack vuoto: impossibile restituire top");//Lancia un eccezione se lo stack è vuoto
		else{
			return S[top];//Si restituisce l'elemento in cima, ma non lo si cancella per via della natura dello stack.
		}
	}
	
	//ESERCIZI
	
	/**Metodo che clona il nostro stack**/
	public ArrayStack<E> clone() throws EmptyStackException {
		if(this.isEmpty())
			throw new EmptyStackException("Stack vuoto: impossibile eseguire metodo clone");//Lancia un eccezione se lo stack è vuoto
		else{
			E appoggio;
			ArrayStack<E> A=new ArrayStack<E>();// Creo e inizializzo 2 nuovi Stack di cui uno è di appoggio
			ArrayStack<E> X=new ArrayStack<E>();
			int j=this.size()-1;
			for(int i=0;i<=j;i++){//Inserisce tutti gli elementi del nostro stack all'interno di uno stack d'appoggio, usando i push e i pop si nel nostro stack d'appogigo saranno con l'ordine inverso
				appoggio= this.pop();
				A.push(appoggio);
			}
			j=A.size()-1;;
			for(int i=0;i<=j;i++){  //Ora tutti gli elementi si trovano nello stack d'appoggio però con ordine invertito, quindi attraverso i push e i pop reinseriamo
				appoggio= A.pop();	   //all'interno sia del nostro stack originale sia dello stack clone gli elementi e visto che nello stack d'appoggio si trovavano invertiti
				this.push(appoggio);	  //all'interno dei due stack si troveranno con le posizoni corrette
				X.push(appoggio);
				}
			return X;
		}
	}
	
	/**Metodo per controllare se due stack sono uguali**/
	public boolean equals(ArrayStack<E> X){
		boolean f=true; //flag
		E appoggio;
		ArrayStack<E> A=new ArrayStack<E>();//Creo e inizializzo uno stack d'appoggio
		
		if(this.size()!=X.size())
			return false;
		
		if(this.isEmpty() && X.isEmpty()) //se entrambi sono vuoti allora gli stack sono uguali
			return true;
		
		else
			if(this.isEmpty()||X.isEmpty()){ //visto che non sono tutti e due vuoti chiedo se uno dei due è vuoto, se è cosi allora ritorno false(utilizzo questo procedimento per evitare le eccezzioni)
				return false;
			}
			else{
				int j=this.size()-1;
				for(int i=0;i<=j;i++){ 					//Inserisce all'interno di un array d'appoggio l'elemento in cima X solo se è uguale a quello in cima al nostro stack
				   if(this.top()==X.top()){		// appena trova un elemento diverso si blocca e pone la flag uguale a false;
					A.push(this.pop());
					X.pop();
				   }
				   else
					   f=false;
					   break;
				}
				j=A.size()-1;
				for(int i=0;i<=j;i++){  	 //Riporta allo stato iniziale i due stack, visto che nello stack d'appoggio si trovavano invertiti
					appoggio=A.pop();
					this.push(appoggio);
					X.push(appoggio);
				}
			}
		if(f)				//Se f è rimasta true per tutta la durata del for di confronto vuol dire che tutti gli elementi sono uguali, altrimenti vuol dire che ha trovato un elemento diverso
			return true;
		else return false;
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
