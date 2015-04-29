package Sequence;



import java.util.Iterator;

import NodeList.BoundaryViolationException;
import NodeList.DNode;
import NodeList.InvalidPositionException;
import NodeList.NodePositionList;

import position.Position;

/**E' una positionList ma che pu� essere gestita attraverso gli indici  **/
public class NodeSequence<E> extends NodePositionList<E> implements Sequence<E>{

	/**Controlla se l'indice passato in input � giusto**/
	public void checkIndex(int r, int n)throws IndexOutOfBoundsException {
		if(r<0 || r>= n) throw new IndexOutOfBoundsException("Indice Scorretto");
	}
	
	
	/**Restituisce la position che si trova in posizione r(intero passato in input)**/
	public Position<E> atIndex(int r) throws BoundaryViolationException {
		DNode<E> node;												//creo un DNode d'appoggio
		checkIndex(r, size());										//Controlla se l'indice passato � giusto
		if (r <= size()/2) {										//se tale indice si trova a sinistra della met�
			node = header.getNext();									// nel nostro nodo d'appoggio inseriamo header(la position prima del primo elemento)
			for (int i=0; i < r; i++)									// facciamo scorrere un for da 0(inizio) fino a r(position che vogliamo trovare)
				node = node.getNext(); 										//a node assegnamo il successivo cosi da far si che il node si trovi la position in posizione i(quando arriviamo all'ultima iterazione si trovera la position con indice r)
			}
		else {														//altrimenti vuol dire che si trova nella parte destra della lista
			node = trailer.getPrev();									//nel nostro node d'appoggio inseriamo il trailer(la position dopo l'ultimo elemento)
			for (int i=1; i < size()-r; i++)							//facciamo un for che parte da 1 e arriva fino a size-r(cio� di quante posizioni deve tornare indietro partendo dall'ultimo) 
				node = node.getPrev(); 										//a node assegnamo il predecende cosi quando arriver� all'ultima iterazione si trover� in posizone r
		}
		return node;												//restituisce node, cio� la position trovata
	}

	/**Restituisce l'indice in cui si trova la position passata in input**/
	public int indexOf(Position<E> p) throws InvalidPositionException {
		DNode<E> node = checkPosition(p); 							//controlla se la position in posizione p � valida e lo casta a DNode
		DNode<E> node1=header;										//in una position d'appoggio inserisco l'header
		
		int indice = 0;												//inizializzo una variabile indice =0
		while (!(node1.getNext().equals(node))) {					//finche la position d'appoggio � diversa dal nodo passato in input(castato) 
			node1 = node1.getNext();										// al node d'appoggio inserisco il successivo
			indice++; 														// incremento i perch� vuol dire che la position non si trova a quell'indice
		}
		return indice;												//restituisco indice che sar� l'indice preciso di dove si trova la mia position
		}

	
	/**Inserisce un nuovo elemento in posizione i**/
	public void add(int i, E e) throws IndexOutOfBoundsException {
		checkIndex(i, size()+1);								//Controllo se l'indice � valido(con size+1 qual'ora volesse inseriro per ultimo
		if (i == size()) 										//se l'indice passato in input � uguale alle size
			addLast(e); 											//inserisco alla fine l'elemento(attraverso il metodo di NodePositionList)
		else 													//altrimenti voglio inserire in un indice all'interno della lista
			addBefore(atIndex(i), e);							//aggiungo prima della position trovata con atIndex(passandogli i)(restituisce la position che si trova in posizione i), l'elemento e
 		
	}

	/**Restituisce l'elemento contenuto nella position in posizione i **/
	public E get(int i) throws IndexOutOfBoundsException {
		checkIndex(i,size());									//controllo se l'indice passato � valido	
		return (atIndex(i)).element(); 							//invoco il metodo element() sulla position di indice i(trovata con atIndex)
	}

	/**Rimuovo l'elemento che si trova in pozione i e lo restituisco**/
	public E remove(int i) throws IndexOutOfBoundsException {
		checkIndex(i,size());									//controllo se l'indice passato � valido	
		return remove(atIndex(i));								//invoco la remove passandogli come imput atIndex(i) cio� la position che si trova in posizione i
	}

	/**Setto nella position che si trova in posizione i l'elemento e passato in input e ne restituisco il vecchio**/
	public E set(int i, E e) throws IndexOutOfBoundsException {
		checkIndex(i,size());									//controllo se l'indice passato � valido	
		return set(atIndex(i), e); 								//invoco il set di NodePositionList(passandogli come paraemtro atIndex(i)(cio� la position che si trova in posizione i) e l'elemento passato in input
	}
	
	/**Crea una collezione iterabile di position**/
	public Iterable<Position<E>> positions(){
		return super.positions();
	}
	
	/**Crea un iteatore di elementi**/
	public Iterator<E> iterator(){
		return super.iterator();
		
	}

	
}