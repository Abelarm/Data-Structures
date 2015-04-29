package ArrayList;

import java.util.Iterator;

import defaultComparator.DefaultComparator;

/**Implementiamo il tda IndexList attraverso un array**/
public class ArrayIndexList<E> implements IndexList<E>  {
	private E[] A;								//array che svolgerà il compito della nostra lista
	private int capacity=16;
	private int size;

	/**Costruttore senza parametro**/
	@SuppressWarnings("unchecked")
	public ArrayIndexList(){
		A=(E[])new Object[capacity];
	}
	
	/**Costruttore parametrico**/
	@SuppressWarnings("unchecked")
	public ArrayIndexList(int cap){
		A=(E[])new Object[cap];
	}
	
	/**Metodo ausiliario per controllare se l'indice passato è compreso tra 0 e l'altro indice passato**/
	public void checkIndex(int r, int n)throws IndexOutOfBoundsException {
		if(r<0 || r>n) throw new IndexOutOfBoundsException("Indice Scorretto");
	}
	
	/**Aggiunge in posizione i l'elmeneto e**/
	public void add(int i, E e) throws IndexOutOfBoundsException {
		checkIndex(i, size()+1);								//controlla l'indice passato
		
		//Spezzone di codice per ampliare l'index list se la size è uguale alla capacità massima dell'array
		if(size == capacity){	
			capacity*=2;
			@SuppressWarnings("unchecked")
			E[] B=(E[])new Object[capacity];
			for(int j=0; j<size;j++)
				B[j]=A[j];
			A=B;
		}
		
		for (int j=size-1;j>=i;j--)								//a j assegna la size-1 finchè j è maggiore uguale di i (decrementa i)
			A[j+1]=A[j];											//nella posizione successiva inserisco l'elemento precendete (se i è maggiore della size non succede nulla)
		
		A[i]=e;													//inserisco in posizione i
		size++;													//aumento la dimensione
	}

	/**Restituisce l'elemento in posizione i**/
	public E get(int i) throws IndexOutOfBoundsException {
		checkIndex(i, size()+1); 					//controllo se l'indice passato è valido
		return A[i];									//restituisco l'elemento in posizione i
	}

	/**Restituisce true se la lista è vuota false altrimenti**/
	public boolean isEmpty() {
		return size==0;
	}

	/**Cancella e restituisce l'elemento contenuto nella posizione i**/
	public E remove(int i) throws IndexOutOfBoundsException {
		checkIndex(i, size());						//controllo se l'indice è corretto
		E temp=A[i];								//dentro una variabile d'appoggio insiersco l'elemento in posizione i
		for(int j=i;j<size();j++)					//faccio un for che parte da i e arriva fino alla size
			A[j]=A[j+1];								//inserisco dentro il precedente l'indice sucessivo(così perdiamo i)
		size--;										//diminuisco la dimensione
		return temp;								//restituisco l'elemento
	}

	/**Setta il precedente elemento in posizione i con il nuovo elemento, ritornando il vecchio**/
	public E set(int i, E e) throws IndexOutOfBoundsException {
		checkIndex(i, size());						//controllo se l'indice è valido
		E temp = A[i];								//dentro una variabile d'appoggio insiersco l'elemento in posizione i
		A[i] = e;									//in posizione i inserisco il nuovo elemento
		return temp;								//restituisco il vecchio
	}
	
	/**Restituisce la dimensione della lista**/
	public int size() {
		return size;
	}
	
	/**Restituisce un iteratore della IndexList**/
	public Iterator<E> iterator() {
		return new ElementIterator<E>(this);
	}

	
	//ESERCIZI?
	
	public void ShiftAvanti(int i){
		
		for(int j=this.size();j>=i+1;j--){
			this.add(j+1, this.get(j));
		}
	}
	
	public void ShiftIndietro(int i){
		
		for(int j=i+1;j<=this.size();j++){
			this.add(j-1, this.get(j));
		}
	}
	
	/**Metodo per invertire la nostra IndexList, fino all'indice r**/
	public void InvertTO(int r){
		this.checkIndex(r, size);						//controllo se l'indice passato è valido			
		for(int i=0;i<=r;i++){							//faccio un for che scorre fino a r
			E first=this.remove(i);							//in una variabile chiamata first inserisco l'elemento che ho tolto dall'inzio
			E last=this.remove(r);							//in una variabile chiamata last inserisco l'elemento che ho tolto dalla fine
			this.add(i, last);								//aggiungo in posizione i(da dove ho tolto first) last e in posizione r(dove ho tolto last) first
			this.add(r, first);
			r--;
			
		}
	}
	
	/**Metodo per stampare gli elementi contenuti nella stringa**/
	public String toString(){
		String stringa="Contenuto del vettore";
		for(int i=0;i<this.size();i++){
			stringa= stringa+ " " + this.get(i);
		}
		return stringa;
	}
	
	
	/**Metodo per l'ordinamento utilizzando la tecnica bubblesort**/
	public void BubbleSort(){
		DefaultComparator<E> C= new DefaultComparator<E>();										//creo un comparatore 
		int scambi = 0;																			//inizializzo una variabile che conterà gli scambi
		do{																						//do
			scambi=0;																				//come prima cosa inizializzo scambi a 0		
			for(int i=0;i<size;i++){																//for che si scorre tutto l'indexList
				if(C.compare(this.get(i), this.get(i+1))<0){											//se l'elemento in posizione i è minore dell'elemento in posizione i+
					E first= this.get(i);
					E last=	 this.get(i+1);
					this.set(i, last);																		//li scambio
					this.set(i+1, first);								
					scambi++;																				//incremento il contatore degli scambi
				}
					
			}
			
		}while(scambi!=0);																	//finche scambi è diverso da 0
	}
	

	
}
