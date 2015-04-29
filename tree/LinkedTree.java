package tree;

import java.util.Iterator;

import NodeList.BoundaryViolationException;
import NodeList.InvalidPositionException;
import NodeList.NodePositionList;
import NodeList.PositionList;

import position.Position;

public class LinkedTree<E> implements Tree<E> {
	protected TNode<E> root;
	protected int size;
	
	/**Costruttore senza parametri**/
	public LinkedTree(){
		root=null;
		size=0;
	}

	/**Restituisce quanti elementi ci sono nell'albero**/
	public int size() {
		return size;
	}

	/**Restiusce false se l'albero è pieno true se è vuoto **/
	public boolean isEmpty() {
		return size==0;
	}

	/**controlla la position*/
	private TNode<E> checkposition(Position<E> v) throws InvalidPositionException{
		try{
			TNode<E> temp = (TNode<E>) v;						//Cast necessario per controllare se la position appartiene al tipo di dato LinkedTree(cié controlla se la position passata è una TPosition)
			return temp;
	}
		catch(ClassCastException x){									//Se il cast da errore cattura l'eccezione e ne lancia una diversa InvalidPositionException
			throw new InvalidPositionException("Position di tipo sbagliato per questo tipo di lista");
		}
	}
	
	/**restituisce l'iteratore degli elementi contenuti nei nodi*/
	public Iterator<E> iterator() {
		Iterable<Position<E>> ListaP=this.positions();				//Crea un iterable di Position, a quest iterable gli assegna il metodo position() che restituisce un iteratore di position
		PositionList<E> elements=new NodePositionList<E>();			//Crea una position list di elementi
		for(Position<E>pos: ListaP)									//per ogni position esistente nel iterable
			elements.addLast(pos.element());							//aggiunge l'elemento all'intero della position list
		return elements.iterator();									//ritorna l'iteratore della lista sopra riempita.
	}

	/**restituisce una lista di posizioni iterabili*/
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> ListaPositions=new NodePositionList<Position<E>>();			//Crea una postion list di position 
		if(size!=0)																				//Se l'albero non è vuoto
			preorderPositions(root(),ListaPositions);											//richiama preorder(che ricorsivamente, aggiunge nell'albero tutti gli elementi)
		return ListaPositions;	
	}

	/**Sostituisce l'elemento passato alla posizione passata in input**/
	public E replace(Position<E> v, E e) throws InvalidPositionException {		
		TNode<E> temp=this.checkposition(v);			//Controlla se è una Position valida
		E appoggio=temp.element();
		temp.setElement(e);
		return appoggio;
	}

	/**Restituisce la radice dell'albero**/
	public Position<E> root() throws EmptyTreeException {
		if(this.isEmpty())
			throw new EmptyTreeException("L'albero e vuoto");
		else return root;
	}

	/**Restituisce il padre dell'alemento passato in input**/
	public Position<E> parent(Position<E> v) throws InvalidPositionException,BoundaryViolationException {
		TNode<E> temp=this.checkposition(v);									//Controlla se è una Position valida
		if(this.isRoot(temp))													//Se la Position passta in input è la radice lancia l'eccezione 
			throw new BoundaryViolationException("Non esiste il padre della radice");
		else
			return temp.getParent();
	}
	
	/**Restituisce una positionList delle TPosition dei  figli di un nodo passato in input**/
	public PositionList<Position<E>> figli(Position<E> v)throws InvalidPositionException{
		TNode<E> temp=this.checkposition(v);								//Controlla se è una Position valida
		return temp.getChildren();						
	}
	
	/**Restituisce una lista iterable dei figli di un nodo passato in input**/
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {
		TNode<E> temp=this.checkposition(v);										//Controlla se è una Position valida
		PositionList<Position<E>> ListaPositions=new NodePositionList<Position<E>>();	//Crea una postion list di position
		ListaPositions=this.figli(temp);
		return ListaPositions;
	}

	/**Controlla se la position passata in input e un nodo**/
	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		TNode<E> temp=this.checkposition(v);		//Controlla se è una Position valida
		if(temp.getChildren().size()!=0)			//Controlla la dimensione della lista di figli, se e diverso da 0 e uno nodo
			return true;
		else 
			return false;
	}

	/**Controlla se la position passata in input e una foglia**/
	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		TNode<E> temp=this.checkposition(v);			//Controlla se e una Position valida
		if(temp.getChildren().size()==0)			//Controlla la dimensione dell'array di figli, se e uguale a 0 e una foglia
			return true;
		else 
			return false;
	}

	/**Controlla se la Position passata in input e la radice**/
	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		TNode<E> temp=this.checkposition(v);			//Controlla se e una Position valida
		return(temp.equals(root));
	}
	
	/**Aggiunge alla lista dei figli della position passata in input una nuova TNode creata con l'elemento passato in input**/
	public Position<E> addChild(E e, Position<E> parent) throws InvalidPositionException{
		TNode<E> temp=this.checkposition(parent);			//Controlla se e una Position valida
		TNode<E> toReturn=new TNode<E>(e,temp);				//Crea una TNode con l'elemento passato in input avente come padre la TNode passata in input(castata)
		temp.addChildren(toReturn);							//Aggiunge la TNode sopra creata alla TNode passata in input
		size++;
		return toReturn;
	}
	
	/**metodo che aggiunge ad un padre una lista di figli**/
	public void addChildren(Position <E> v, PositionList<Position<E>> childrenList){
		TNode<E> temp=this.checkposition(v);			
		for(Position<E> x : childrenList){
			temp.addChildren(new TNode<E>(x.element(),temp));
			size++;
		}
		
	}
	
	/**Aggiunge la radice solo se l'albero è vuoto**/
	public Position<E> addRoot(E e)throws BoundaryViolationException{
		if(this.size()!=0) 
			throw new BoundaryViolationException("Albero non vuoto, sostituzione impossibile!!");
	
		TNode<E> toReturn=new TNode<E>(e,null);			//crea una nuova TNode radice
		root=toReturn;									//sostituisce la radice
			size++;
	return toReturn;		
	}
	
	/**Rimuove la Position passata in input e i figli della position diventano figli del padre della postion passata in input**/
	public E remove(Position<E> p)throws InvalidPositionException,BoundaryViolationException {
		TNode<E> temp=this.checkposition(p);			//Controlla se e una Position valida
		if(this.isRoot(temp))								//Se la Position passata in input e una radice lancio l'eccezione
			throw new BoundaryViolationException("Non si pue eliminare la radice");
		
		PositionList<Position<E>> figli=temp.getChildren();				//In una variabile d'appoggio inserisco i figli della position passta in Input
		TNode<E> father=this.checkposition(temp.getParent());			//In una variabile d'appoggio inserisco il padre della Position passata in input
		for(Position<E> x: figli){										//Per ogni Position appartente a figli(for generico)(cioe per tutti i figli del nodo) 
			temp=this.checkposition(x);										//casto la position per poterci lavorare
			temp.setParent(father);											//modifico il puntatore a padre di goni figlio
			father.addChildren(temp);										//Aggiungo alla lista dei figli del padre del padre(nonno) tale figlio
		}
		return temp.element();
	}
	
	
	
	public E removeExternalChild(Position<E> v){
		PositionList<Position<E>> figli=this.figli(v);
		if(this.isExternal(figli.first().element())){
			Position<Position<E>> primo=figli.first();
			figli.remove(primo);
			return primo.element().element();
			}
		else
			throw new UndeletableNodeException();
	}
	
	
	/**Aggiunge alla lista dei figli della Position passata in input l'albero passato in input(radice)**/
	public void attach(Position<E> p,LinkedTree<E> t)throws InvalidPositionException{
		TNode<E> temp=this.checkposition(p);			//Controlla se e una Position valida
		TNode<E> temp1=this.checkposition(t.root());		//Ad una variabile d'appoggio assegna la radice dell'albero passato in input(castato)
		temp1.setParent(temp);								//Assegna come padre della radice dell' albero passato in input la Position passata in input
		temp.addChildren(temp1);							//aggiunge alla lista dei figli, la radice dell'albero passato in input
		
	}
	
	/**crea una lista delle position partendo dall'alto*/ 
	public void preorderPositions(Position<E> v, PositionList<Position<E>> pos) throws InvalidPositionException {
		TNode<E> temp=this.checkposition(v);			//Controlla se è una Position valida
		pos.addLast(v);										//aggiunge alla positionlist passata in input, la position passata in input
		if(this.isInternal(v)){
			PositionList<Position<E>> figli=temp.getChildren();			//In una variabile d'appoggio inserisco i figli della position passta in Input
			for(Position<E> x: figli){							//Per ogni TNode appartente a figli(for generico)(cioè per tutti i figli del nodo)
				preorderPositions(x,pos);						//Richiama ricorsivamente su ogni figlio preorderPositions
			}
		}
	}
	
	/**crea una lista delle position partendo dal basso*/
	public void postorderPositions(Position<E> v, PositionList<Position<E>> pos)throws InvalidPositionException {
		TNode<E> temp=this.checkposition(v);			//Controlla se e una Position valida
		if(this.isInternal(v)){
			PositionList<Position<E>> figli=temp.getChildren();			//In una variabile d'appoggio inserisco i figli della position passta in Input
			for(Position<E> x: figli){							//Per ogni Position appartente a figli(for generico)(cioe per tutti i figli del nodo)
				preorderPositions(x,pos);						//Richiama ricorsivamente su ogni figlio preorderPositions
			}
		}
		pos.addLast(v);										//aggiunge alla positionlist passata in input, la position passata in input

	}
	
	/**Restituisce la profondite di un nodo**/
	public int depth(Position<E> p)throws InvalidPositionException,BoundaryViolationException{
		TNode<E> temp=this.checkposition(p);			//Controlla se e una Position valida
		if(this.isRoot(p))								//caso base: se e la radice restituisci 1
			return 1;
		else
			return 1+ this.depth(temp.getParent());		//richiama ricorsivamente depth sul padre pere aggiungendo 1;
	}
	
	/**Restituisce l'altezza dell'albero**/
	public int height()throws InvalidPositionException{
		if(this.isEmpty())												//Se e vuoto ritorna 0
			return 0;
		int altezza = 1;								//inizializzo la variabile d'appoggio, 
		TNode<E> temp=this.checkposition(this.root());		//ad una variabile d'appoggio temp assegno la root castata(checkposition)
		while(!this.isExternal(temp)){						//finche temp non e esterno
			temp=this.checkposition(this.figli(temp).first().element());				//a temp assegno l'elemento della prima position contenuta nella lista dei figli di temp stesso(cioe il suo primo figlio)
			altezza++;																		//faccio ciò perchè il metodo figli restituisce una positionList di TPosition
		}
		return altezza;
	}
	
	

}
