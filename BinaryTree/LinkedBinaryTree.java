package BinaryTree;

import java.util.Comparator;
import java.util.Iterator;

import position.Position;
import tree.EmptyTreeException;
import NodeList.BoundaryViolationException;
import NodeList.InvalidPositionException;
import NodeList.NodePositionList;
import NodeList.PositionList;

public class LinkedBinaryTree<E> implements BinaryTree<E> {
	
	protected BTNode<E> root; 		//riferimento alla radice
	protected int size; 				//numero di nodi

	/** Crea un albero binario vuoto*/
	public LinkedBinaryTree() { 
	root = null; 
	size = 0; 
	} 
	
	/**restituisce il numero dei nodi*/
	public int size() {
		return size;
	}

	/**restituisce true se vuoto*/
	public boolean isEmpty() {
		return size==0;
	}
	
	/**controlla la position*/
	public BTNode<E> checkposition(Position<E> v) throws InvalidPositionException{
		try{
			BTNode<E> temp = (BTNode<E>) v;						//Cast necessario per controllare se la position appartiene al tipo di dato LinkedBinaryTree(ciè controlla se la position passata  è una BTPos)
			return temp;
	}
		catch(ClassCastException x){									//Se il cast da errore cattura l'eccezione e ne lancia una diversa InvalidPositionException
			throw new InvalidPositionException("Position di tipo sbagliato per questo tipo di lista");
		}
	}

	/**restituisce l'iteratore degli elementi contenuti nei nodi*/
	public Iterator<E> iterator() {							
		Iterable<Position<E>> ListaP=positions();					//Crea un iterable di Position, a quest iterable gli assegna il metodo position() che restituisce un iteratore di position
		PositionList<E> elements=new NodePositionList<E>();			//Crea una position list di elementi
		for(Position<E>pos: ListaP)									//per ogni position esistente nel iterable
			elements.addLast(pos.element());						//aggiunge l'elemento all'intero della position list
		return elements.iterator();									//ritorna l'iteratore della lista sopra riempita.
	}

	/**restituisce una lista di posizioni iterabili*/
	public Iterable<Position<E>> positions() {									
		PositionList<Position<E>> ListaPositions=new NodePositionList<Position<E>>();	//Crea una postion list di position 
		if(size!=0)																		//Se l'albero non è vuoto
			preorderPositions(root(),ListaPositions);									//richiama preorder(che ricorsivamente, aggiunge nell'albero tutti gli elementi)
		return ListaPositions;																//Restituisce la lista sopra creata
	}
	
	/**crea una lista delle position partendo dall'alto*/
	public void preorderPositions(Position<E> v, PositionList<Position<E>> pos) {
		pos.addLast(v);										//aggiunge alla positionlist passata in input, la position passata in input
		if(hasLeft(v))										//se ha figlio sinistro
			preorderPositions(left(v), pos);					//richiama ricorsivamente preorder sul figlio sinistro del nodo passato a sinistra, e sulla lista 
		if(hasRight(v))										//se ha figlio destro
			preorderPositions(right(v),pos);					//richiama ricorsivamente preorder sul figlio destro del nodo passato a sinistra, e sulla lista
	}
	
	/**crea una lista delle position partendo dal basso*/ 
	public void postorderPositions(Position<E> v, PositionList<Position<E>> pos) {				
		if(hasLeft(v))												//se ha figlio sinistro
			postorderPositions(left(v), pos);							//richiama ricorsivamente preorder sul figlio sinistro del nodo passato a sinistra, e sulla lista 
		if(hasRight(v))												//se ha figlio destro
			postorderPositions(right(v),pos);							//richiama ricorsivamente preorder sul figlio destro del nodo passato a sinistra, e sulla lista
		pos.addLast(v);												//poi aggiunge l'elemento passato in input, nella lista passata in input
	}
	
	/**crea una lista delle position ordinatamente(sinistro-padre-destro)*/ 
	public void inorderPositions(Position<E> v, PositionList<Position<E>> pos) {				
		if(hasLeft(v))												//se ha figlio sinistro
			inorderPositions(left(v), pos);							//richiama ricorsivamente preorder sul figlio sinistro del nodo passato a sinistra, e sulla lista 
		
		pos.addLast(v);												//poi aggiunge l'elemento passato in input, nella lista passata in input
		
		if(hasRight(v))												//se ha figlio destro
			inorderPositions(right(v),pos);							//richiama ricorsivamente preorder sul figlio destro del nodo passato a sinistra, e sulla lista
		
	}

	/**cambia elemento di un nodo preesistente*/
	public E replace(Position<E> v, E e) throws InvalidPositionException {
		BTNode<E> temp=this.checkposition(v);						//controlla se la position è valida, e restiutisce il cast a BTNode
		E returnable = temp.element() ;								//dentro una variabile d'appoggio restiutisce l'elemnto contenuto nella position passata
		temp.setElement(e);											//setta il nuovo elemento
		return returnable;
	}

	/**restituisce la radice*/
	public Position<E> root() throws EmptyTreeException {
		return root;
		
	}

	/**restituisce il padre*/
	public Position<E> parent(Position<E> v) throws InvalidPositionException,BoundaryViolationException {
		BTNode<E> vparent=checkposition(v);														//controlla se la position è valida, e restiutisce il cast a BTNode
		Position<E> parentPos=vparent.getParent();												//nella variabile d'appoggio inserisce il padre dell'elemento passato in input
		if(parentPos==null)throw new BoundaryViolationException("Il nodo non ha padre");		//se la variabile d'appoggio è null vuol dire che l'elemento non ha padre, quindi lancia l'eccezione
		return parentPos;
	}

	/**restituisce una collezione iterabile dei figli*/
	public Iterable<Position<E>> children(Position<E> v)throws InvalidPositionException {
		PositionList<Position<E>> ListaFigli=new NodePositionList<Position<E>>();					//Crea una lista di position 
		if(hasLeft(v))																				//Se ha il figlio sinistro
			ListaFigli.addLast(left(v));																//aggiunge il figlio sinistro alla lista
		if(hasRight(v))																				//Se ha il figlio destro
			ListaFigli.addLast(right(v));																//aggiunge il figlio destro alla lista
		return ListaFigli;		
	}

	/**controlla se il nodo è interno*/
	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		checkposition(v);
		return (hasLeft(v)||hasRight(v));					//Ritorna vero se ha un figlio destro o sinistro
	}

	/**controlla se il nodo è una foglia*/
	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		checkposition(v);
		return(!(hasLeft(v)||hasRight(v)));				//Ritorna false se non ha figli
	}

	/**controlla se il nodo è la radice*/
	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		checkposition(v);
		return (v.equals(root));
	}

	/**restituisce il figlio sinistro del nodo*/
	public Position<E> left(Position<E> v) throws InvalidPositionException,BoundaryViolationException {
		BTNode<E> vLeft=checkposition(v);						//controlla se la position è valida, e restiutisce il cast a BTNode
		Position<E> leftPos=vLeft.getLeft();						//nella variabile d'appoggio inserisce il figlio sinistro dell'elemento passato in input
		if(leftPos==null)											//se la variabile d'appoggio è null vuol dire che l'elemento non ha figlio sinitro, quindi lancia l'eccezione
			throw new BoundaryViolationException("Il nodo è privo di figlio sinistro");
		return leftPos;
	}

	/**restituisce il figlio destro del nodo*/
	public Position<E> right(Position<E> v) throws InvalidPositionException,BoundaryViolationException {
		BTNode<E> vRight=checkposition(v);						//controlla se la position è valida, e restiutisce il cast a BTNode
		Position<E> rightPos=vRight.getRight();						//nella variabile d'appoggio inserisce il figlio destro dell'elemento passato in input
		if(rightPos==null)											//se la variabile d'appoggio è null vuol dire che l'elemento non ha figlio destro, quindi lancia l'eccezione
			throw new BoundaryViolationException("Il nodo è privo di figlio destro");
		return rightPos;
	}

	/**controlla se il nodo ha un figlio sinistro*/
	public boolean hasLeft(Position<E> v) throws InvalidPositionException {
		BTNode<E> vLeft=checkposition(v);						//controlla se la position è valida, e restiutisce il cast a BTNode
		return(vLeft.getLeft()!=null);								//restituisce true se esiste il figlio sinistro, false altrimenti
	}

	/**controlla se il nodo ha un figlio destro*/
	public boolean hasRight(Position<E> v) throws InvalidPositionException {
		BTNode<E> vRight=checkposition(v);						//controlla se la position è valida, e restiutisce il cast a BTNode
		return(vRight.getRight()!=null);							//restituisce true se esiste il figlio destro, false altrimenti
	}
	
	//METODI UTILI PER IL BINARY SEARCH TREE
	
	/**Metodo che trasforma v in un nodo interno, aggiungendo l e r che sono nodi sentinella**/
	public void expandExternal(Position<E> v, E l, E r) throws InvalidPositionException {
	if (!isExternal(v))			//se il nodo passato in input non è foglia
		throw new InvalidPositionException("Nodo non è foglia");	//lancia l'eccezione 
	insertLeft(v, l);					//inserisce come figlio sinistro del nodo passato in input una Entry avente come valore l
	insertRight(v, r);					//inserisce come figlio destro del nodo passato in input una Entry avente come valore r
	}
	
	
	/**Cancella la foglia v e suo padre (il remove del BST lo sostituisce con il fratello di v)**/
		public void removeAboveExternal(Position<E> v)  throws InvalidPositionException {
		if (!isExternal(v)) 										//se la position non è esterna
			throw new InvalidPositionException("v non è foglia!");		//lancia l'eccezione
		if (isRoot(v)) 												//se è root
			remove(v);													//cancellala
		else {  													//altrimenti
			Position<E> u = parent(v);								//in una position salva il padre
			remove(v); 												//rimuove il figlio
			remove(u);    											//rimuove il padre
			}
		}
	

	//ESERCIZI
	/**trova il lowest common ancestor di due nodi*/
	public Position<E> lca(Position<E>  v, Position<E> w){ 				//trova l'antenato comune di due nodi
		checkposition(v);											//controlla se la position v è valida
		checkposition(w);											//controlla se la position w è valida
		if(v.equals(w))												//controlla se v e w sono uguali, se si restituisce v
			return v;
		if(isRoot(v))												//controlla se v è radice, se si restituisce v
			return v;
		if(isRoot(w))												//controlla se w è radice, se si restituisce w
			return w;
		int prof_v=depth(v);										//assegno a prof_v la profonditè del nodo v
		int prof_w=depth(w);										//assegno a prof_w la profonditè del nodo w
		if(prof_v==prof_w)											//se prof_v e prof_w sono uguali 
			return lca(parent(v), parent(w));							//chiamo la funzione lca sui nodi genitori di v e w
		if(prof_v>prof_w)											//se prof_v è piè grande di prof_w 
			return lca(parent(v), w);									//chiamo la funzione lca sul nodo genitore di v e su w
		else														//altrimenti
			return lca(v, parent(w));									//chiamo la funzione lca su  v e sul nodo genitore di w
		}
	
	
	/**restituisce la profondità di un nodo*/
	public int depth(Position<E> p) throws InvalidPositionException{
		BTNode<E> vPos=checkposition(p);						//controlla se la position e valida, e restiutisce il cast a BTNode
		if(isRoot(vPos))											//se vPos è radice
			return 0;													//restiruisce 0
		int profondita=1;											//assegna 1 a profondità
		BTNode<E> parentPos=this.checkposition(vPos.getParent());					//assegna a parentPos il genitore di vPos
		while(!isRoot(parentPos)){									//finchè parentPos non è radice
			parentPos=this.checkposition(parentPos.getParent());							//assegna a parentPos il genitore di parentPos 
			profondita++;												//incrementa profondità di 1
		}
		return profondita;											//restituisce profondità
	}
	
	/**controlla se due nodi hanno sottoalberi uguali*/
	public boolean equal(Position<E> p,BinaryTree<E> W, Position<E> q)throws InvalidPositionException{
		
		BTNode<E> temp=this.checkposition(p);												//controlla se la position è valida, e restiutisce il cast a BTNode	
		BTNode<E> temp1=checkposition(q);													//controlla se la position è valida, e restiutisce il cast a BTNode

		if(p.element().equals(q.element())){													//se l'elemento di p è uguale all'elemento di q
				if(this.hasLeft(p)&& W.hasLeft(q)&& this.hasRight(p)&& W.hasRight(q)){				//se l'albero radicato in p ha figlio sinistro e destro e l'albero W radicato in q ha figlio sinistro e destro
				equal(temp.getLeft(),W,temp1.getLeft());												//chiama la funzione equal per confrontare il figlio sinistro dell'albero e il figlio sinistro di W
				equal(temp.getRight(),W,temp1.getRight());												//chiama la funzione equal per confrontare il figlio destro dell'albero e il figlio destro di W
			}
				else																				
					if(!this.hasLeft(p)&& !W.hasLeft(q)&& !this.hasRight(p)&& !W.hasRight(q))		//altrimenti se l'albero radicato in p NON ha figlio sinistro e destro e l'albero W radicato in q NON ha figlio sinistro e destro
						return true;																	//restituisci true
					else 
						if(this.hasLeft(p)&& this.hasLeft(q)&&!this.hasRight(p)&& !W.hasRight(q))	//altrimenti se l'albero radicato in p ha figlio sinistro e destro e l'albero W radicato in q NON ha figlio sinistro e destro
							equal(temp.getLeft(),W,temp1.getLeft());									//chiama la funzione equal per confrontare il figlio sinistro dell'albero e il figlio sinistro di W
						else
							if(!this.hasLeft(p)&& !this.hasLeft(q)&&this.hasRight(p)&& W.hasRight(q)) //altrimenti se l'albero radicato in p NON ha figlio sinistro e destro e l'albero W radicato in q NON ha figlio sinistro e destro
								equal(temp.getRight(),W,temp1.getRight());									//chiama la funzione equal per confrontare il figlio destro dell'albero e il figlio destro di W
							else
								return false;
				return false;
		}
		else 
			return false;
}
	/**Aggiunge una radice all'albero, solo se non è vuoto**/
	public Position<E> addRoot(E e) throws EmptyTreeException{
		if(this.size()!=0) 
					throw new EmptyTreeException("Albero non vuoto, sostituzione impossibile!!");
			
		BTNode<E> toReturn=new BTNode<E>(e,null,null,null);			//crea una nuova BTNode radice
		root=toReturn;			//sostituisce la radice
		size++;
		return toReturn;		
		
	}
	/** Aggiunge un figlio sinistro al nodo passato in input, solo se non lo ha giè**/
	public Position<E> insertLeft(Position<E> v, E e)throws InvalidPositionException, BoundaryViolationException{
		BTNode<E> temp=this.checkposition(v);					//controlla se la position è valida
		if(this.hasLeft(temp))											//se il nodo ha giè figlio sinistro lancia l'eccezione
			throw new BoundaryViolationException("il nodo ha già nodo sinistro");	
		BTNode<E> toReturn=new BTNode<E>(e,temp,null,null);			//crea un BTNode avente come padre il nodo passato in input
		temp.setLeft(toReturn);			//setta come figlio sinistro del nodo passato in input quello sopra creato
		size++;
		return toReturn;
		}
	
	/** Aggiunge un figlio destro al nodo passato in input, solo se non lo ha giè**/
	public Position<E> insertRight(Position<E> v, E e)throws InvalidPositionException, BoundaryViolationException{
		BTNode<E> temp=this.checkposition(v);					//controlla se la position è valida
		if(this.hasRight(temp))											//se il nodo ha giè figlio destro lancia l'eccezione
			throw new BoundaryViolationException("il nodo ha giè nodo sinistro");	
		BTNode<E> toReturn=new BTNode<E>(e,temp,null,null);			//crea un BTNode avente come padre il nodo passato in input
		temp.setRight(toReturn);			//setta come figlio destro del nodo passato in input quello sopra creato
		size++;
		return toReturn;
	}
	
	/**Rimuove il nodo passato in input(solo se ha un solo figlio) e ne restituisce l'elemento**/
	public E remove(Position<E> v)throws InvalidPositionException, BoundaryViolationException{
		BTNode<E> temp=this.checkposition(v);					//controlla se la position è valida
		
		if(this.hasLeft(v)&&this.hasRight(v))											//Se il nodo passato in input ha sia figlio destro che sinistro è impossibile rimuoverlo
			throw new BoundaryViolationException("Il nodo ha due figli, impossibile rimuoverlo");
		
		BTNode<E> padre=this.checkposition(this.parent(temp));				//Ad una variabile assegnamo il padre del nodo passato in input
		
		if(!this.hasLeft(v)&& !this.hasRight(v)){			//Se il nodo passato in input non ha ne figlio destro ne figlio sinistro
			if(padre.getLeft().equals(temp)){					//controlliamo se il nodo passato in input è il figlio sinistro del padre
				padre.setLeft(null);							
				size--;
			}
			else{											//controlliamo se il nodo passato in input è il figlio destro del padre
				padre.setRight(null);
				size--;
			}
		}
		else{													//Se il nodo passato in input ha figlio destro O figlio sinistro
		if(padre.getLeft().equals(temp)){							//se il nodo pasato in input è figlio sinistro 
				if(this.hasLeft(temp)){									//se il nodo passato in input HA figlio sinistro	
					padre.setLeft(temp.getLeft());							//assegna al figlio sinistro del padre il figlio sinistro del nodo passato in input
					size--;
				}
				else{													//se il nodo passato in input HA figlio destro
					padre.setLeft(temp.getRight());							//assegna al figlio sinistro del padre il figlio destro del nodo passato in input
					size--;
				}
			}
		else{														//se il nodo passato in input è figlio destro
				if(this.hasLeft(temp)){									//se il nodo passato in input HA figlio sinistro
					padre.setRight(temp.getLeft());							//assegna al figlio destro del padre il figlio sinistro del nodo passato in input
					size--;
				}
				else{													//se il nodo passato in input HA figlio destro
					padre.setRight(temp.getRight());						//assegna al figlio deastro del padre il figlio destro del nodo passato in input
					size--;
				}
			}
		}
	
	return temp.element();
	}
	
	/**Attacca come figlio sinstro o al figlio destro i binary tree T1 e T2 **/
	public void attach(Position <E> v, BinaryTree<E> T1, BinaryTree<E> T2)throws InvalidPositionException, BoundaryViolationException{
		BTNode<E> temp=this.checkposition(v);					//controlla se la position è valida
			if(this.isInternal(temp))								//Se il nodo è interno(ha almeno un figlio) lancia l'eccezione perchè è impossibile eseguire attach
				throw new BoundaryViolationException("Il nodo è interno, impossibile richiamare il metodo attach");
			temp.setLeft(this.checkposition(T1.root()));		//Assegna al figlio sinistro del nodo passato in input la radice di T1 castata attraverso checkposition
			temp.setRight(this.checkposition(T2.root()));		//Assegna al figlio destro del nodo passato in input la radice di T1 castata attraverso checkposition
				
		
	}
	
	public Iterable<E> contenti(Position<E> p, PositionList<E> pos)throws InvalidPositionException, BoundaryViolationException{
		BTNode<E> temp=this.checkposition(p);					//controlla se la position è valida
		if(this.isExternal(temp)|| !this.hasLeft(temp) || !this.hasRight(temp))
			throw new BoundaryViolationException("impossibile controllare su di una foglia");
		else
			if(this.equal(this.left(temp), this, this.right(temp))){
				pos.addLast(temp.element());
				this.contenti(this.left(temp), pos);
				this.contenti(this.right(temp), pos);
			}
			
		return pos;
	}
	
	public Position<E> sibling(Position<E> v){
		BTNode<E> temp=this.checkposition(v);					//controlla se la position è valida
		if(temp.getParent().getLeft().equals(temp))				//si confronta temp (la posizione passata) con il figlio sinistro del padre, per sapere se è lui
				return temp.getParent().getRight();				//se lo è, il fratello di temp è il figlio destro del padre di temp
			else{
				if(temp.getParent().getRight().equals(temp))	//si confronta temp (la posizione passata) con il figlio destro del padre, per sapere se è lui
					return temp.getParent().getLeft();			//se lo è, il fratello di temp è il figlio sinistro del padre di temp
		    }
		return null;											//altrimenti temp non ha fratello
		
	}
	
	/**Un nodo v si dice RightLarger se per ogni nodo del sottoalbero radicato in v (v compreso)
	 * vale che il sottoalbero destro di questo nodo ha dimensione maggiore o uguale
	 * del sottoalbero sinistro. Se è vero si restituisce il numero di nodi che formano il sottoalbero in v
	 * altrimenti il valore sentinella -1*/
	public static int isRightLarger(BinaryTree T, Position v){
		int right=0;														//contatore figli destri
		int left=0;															//contatore figli sinistri
		BTNode temp=((LinkedBinaryTree)T).checkposition(v);					//controlla se la position è valida
		if(T.isExternal(temp))												//se temp (la position passata) è esterna soddisfa la proprietà (0 figli destri=0 figli sinistri)
			return 1;														//e il sottoalbero radicato contiene solo lui
		else{
			if(T.hasRight(temp))											//se temp ha figlio destro
				right= isRightLarger(T,T.right(temp));						//a right assegna l'output di isRightLarger sul figlio destro di temp
			if(T.hasLeft(temp))												//se temp ha figlio sinistro
				left=  isRightLarger(T,T.left(temp));						//a left assegna l'output di isRightLarger sul figlio sinistro
			if(right>=left && left>=0 && right>=0)							//se soddisfa la proprietà, ovvero right è maggiore di left e entrambi >=0 (ovvero la proprietà è valida per tutti i nodi del sottoalbero)
				return right+left+1;										//restituisci la somma dei figli destri e sinistri + 1 (la radice del sottoalbero)
			else 															//altrimenti
				return -1;													//restituisci -1 (valore sentinella)
		}
	}
	
	
	
	public int Property(BinaryTree<E> T, Position<E> v, Comparator<E> C){
		int right=0;														//contatore figli destri
		int left=0;	
		if(T.isExternal(v))
			return 1;
		if(T.hasLeft(v) && T.hasRight(v) && (C.compare(T.left(v).element(), T.right(v).element())<=0)){
				right= this.Property(T, T.right(v), C);
				left=  this.Property(T, T.left(v), C);
		}
		else{
			if(T.hasLeft(v) && !T.hasRight(v))
				left=  -(this.Property(T, T.left(v), C));
			else{
				if(!T.hasLeft(v) && T.hasRight(v))
					right= -(this.Property(T, T.right(v), C));
				else {
					right=  -(this.Property(T, T.right(v), C));
					left=  -(this.Property(T, T.left(v), C));
			   }
		   }
		}
		
		if(right >= 0 && left>=0)
			return right+left+1;
		else
			if(right>=0 && left< 0)
				return -right+left-1;
			else
				if(right<0 && left>=0)
					return right-left-1;
				else
					return right+left-1;
		}
	
	
	
	public boolean equalsSottoAlberi(Position<E> p){
		if(!this.hasLeft(p) && !this.hasRight(p))
			return true;
		if(this.hasLeft(p) && !this.hasRight(p))
			return false;
		if(!this.hasLeft(p) && this.hasRight(p))
			return false;
		return this.equal(this.left(p), this, this.right(p));
	
		
	}
	
	
	
	public Iterator<E> Ricco(Position<E> p){
		BTNode<E> temp=this.checkposition(p);				//controlla se la position è valida
		PositionList<E> lista = new NodePositionList<E>();
		this.inorderPositions(p, (PositionList<Position<E>>) lista);
		Iterable<Position<E>> iter=lista.positions();
		lista = new NodePositionList<E>();
		for(Position<E> x: iter){
			if(!this.equalsSottoAlberi(x))
				lista.addLast(x.element());
		}
		return lista.iterator();
		
	}
	
	public Iterator<E> NodiLivello(Position<E> p, int k ,NodePositionList<E> iter){
		if(k==0)
			 iter.addLast(p.element());
		else{
			if(this.hasLeft(p))
				this.NodiLivello(this.left(p), k-1, iter);
			if(this.hasRight(p))
				this.NodiLivello(this.right(p), k-1, iter);
		}
		return iter.iterator();
			
		
	}
	
	

	
}
