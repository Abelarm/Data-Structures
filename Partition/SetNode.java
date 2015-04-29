package Partition;
import Set.Set;
import position.Position;

/**Classe che rappresenta una position consapevole della position del set in cui si trova**/
public class SetNode<E> implements Position<E>, Comparable<E> {
	E elemento;							//elemento
	Position<Set<SetNode<E>>> pos;		//Position del set in cui si trova
	
	/**Costruttore parametrico rispetto all'elemento**/
	public SetNode(E x){
		elemento=x;
		pos=null;
	}
	
	/**Restutisce l'elemento**/
	public E element() {
		return elemento;
	}
	
	/**Aggiorna la position con una nuova passata in input**/
	public void setPos(Position<Set<SetNode<E>>> x){
		pos=x;
	}
	
	/**Metodo riscritto per confrontare due elementi(SetNode<E>)**/
	public int compareTo(Object o) {
		SetNode<E> temp= (SetNode<E>)o;					//castiamo l'oggetto passato in input nel nostro tipo
		Comparable a=((Comparable)this.element());		//Castiamo l'elemento contenuto nella nostra position(SetNode<E>) a comparable per poterla confrontare
		Comparable b=((Comparable)temp.element());		//Castiamo l'elemento contenuto nella a position(SetNode<E>) passata in input a comparable per poterla confrontare
		if(a.compareTo(b)<0)					
			return -1;
			else{
				if(a.compareTo(b)>0)
					return 1;
			}
		return 0;
	}

	/**Restituisce il riferimento alla position del Set**/
	public Position<Set<SetNode<E>>> getPos() {
		return pos;
	}

}
