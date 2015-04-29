package Partition;

import java.util.Comparator;
import java.util.Iterator;

import defaultComparator.DefaultComparator;
import position.Position;
import NodeList.NodePositionList;
import NodeList.PositionList;
import Set.ListSet;
import Set.Merge;
import Set.Set;
import Set.UnionMerge;


/**Implementata attraverso una LIsta doppiamente linkata di Set questi set pero non conterranno elementi
 * ma bensi delle position particolari che sono consapevoli della posizione all'interno di un set**/
public class PartitionList<E> implements Partition<E> {
	NodePositionList<Set<SetNode<E>>> lista;						//Lista dei Set contenuti nella partition(però set di SetNode<E>)
	protected Comparator<E> comp = new DefaultComparator<E>();
	
	/**Costruttore senza parametri**/
	public PartitionList(){
		lista= new NodePositionList<Set<SetNode<E>>>();
	}
	
	/**Controlla se il set è non nullo e se è un istanza della classe Set<SetNode<E>> **/
	public ListSet<SetNode<E>> checkSet(Set<E> x){
		try{
			if(x.equals(null))									//se è uguale a null
				throw new InvalidSetException();					//lancia l'eccezione 
			ListSet<SetNode<E>> temp=(ListSet<SetNode<E>>)x;	//Casta il set passato in input a ListSet<SetNode<E>>(potrebbe lanciare class cast exeception)	
			return temp;										//e restituisce il set cosi creato
		}
		catch(Exception e){										//cattura l'eventuale eccezione(classcastException)
			throw new InvalidSetException();						//e lancia quella da noi creata
		}
	}

	/**Restituisce la dimensione della lista(cioÃ¨ quanti insiemi ci sono)**/
	public int size() {
		return lista.size();
	}

	/**Controlla se Ã¨ vuoto**/
	public boolean isEmpty() {
		return lista.size()==0;
	}

	/**Crea un nuovo set avente come elemento quello passato in input**/
	public Position<E> makeSet(E x) {
		SetNode<E> elem= new SetNode<E>(x);							//Crea un SetNode con l'elemento passato in input
		ListSet<SetNode<E>> temp= new ListSet<SetNode<E>>(elem);	//Crea una nuovo ListSet con il SetNode creato sopra
		if(this.ElemIsIn((Set<E>) temp))							//Se l'elemento che abbiamo passato in inptu(cioÃ¨ quello dell'insieme appena creato) Ã¨ gia presente nella partition
			return null;												//ritorna null e non aggiunge il nuovo set nella partizione(per via della natura della partizione stessa)
		lista.addLast(temp);										//altrimenti aggiunge alla fine della lista di set il nostro set creato
		elem.setPos(lista.last());									//Setta la position dell elemento contenuto nel Set(cioÃ¨ il nostro SetNode con la position del set cui appartiene)
		return (Position<E>) lista.last();							//ritorna la position del nostro set(però castata a Position<E>) per via della firma del metodo
		
	}

	/**Metodo che crea un nuovo Set(unione dei due passati in input) e lo aggiunge alla partition, eliminando i due di partenza**/
	public Set<E> union(Set<E> A, Set<E> B) {
		Set<E> C = new ListSet<E>();										//Crea un insieme vuoto
		PositionList<E> a1=(PositionList<E>)(this.checkSet(A)).elements();	//Casta il set a ListSet(attraverso checkSet) ne fa l'elements() quindi viene restituita
		PositionList<E> b1=(PositionList<E>)(this.checkSet(B)).elements();	//una PositionList<SetNode<E>(una positionlist di SetNode) quindi la castiamo a 
		PositionList<E> c1=(PositionList<E>)(this.checkSet(C)).elements();	//PositionList<E>(PositionList per via del metodo merge che useremo)
	    
	    Merge<E> op;														//Crea un oggetto merge
	    op = new UnionMerge<E>();											//e la istanzia come UnionMerge
	    op.merge(a1,b1, comp, c1);											//invoca il merge sulle liste create sopra(sarà  un mergeUnion per la natura della classe op)
	    
	    //operazioni per cancellare i due Set, qualora fossero presenti all'interno della partition
	    
	    lista.remove(((SetNode<E>)a1.last().element()).getPos());			//Legge l'ultima position della lista, ne prende l'elemento(che è  una SetNode) quindi lo casta a SetNode
	    lista.remove(((SetNode<E>)b1.last().element()).getPos());			//per poter leggere in che position si trova il set( nella PositionList dei set), per potergli invocare la remove
	    																	//visto che la remove necessita di: Position<Set<SetNode<E>>> (la position del set)
	    
	    lista.addLast(this.checkSet(C));									//Aggiunge il set C (risultato dell'unione di A e B) come ultimo(però castandolo, per la natura della lista)
	    
	    Iterator<E> iter= c1.iterator();									//crea l'iteratore della lista dei SetNode<E> di C
	    
	    while(iter.hasNext()){												//finche l'iteratore ha un elemento
	    	((SetNode<E>)iter.next()).setPos(lista.last());						//prende il successivo elemento(che è un SetNode) e lo casta a SetNode e gli invoca il metodo
	    																		//setPos passandogli come parametro la position di C nella partizione(lista.last()) in quanto aggiunto con add last
	    }
	    
	    iter= a1.iterator();												//crea l'iteratore della lista dei SetNode<E> di A
	    
	    while(iter.hasNext()){												//finche l'iteratore ha un elemento
	    	((SetNode<E>)iter.next()).setPos(null);								//prende il successivo elemento(che è un SetNode) e lo casta a SetNode e gli invoca il metodo
	    }																		//setPos passandogli come parametro null, perché deve sapere di non appartenere alla stessa partition
	    
	    iter= b1.iterator();												//crea l'iteratore della lista dei SetNode<E> di B
	    
	    while(iter.hasNext()){												//finche l'iteratore ha un elemento
	    	((SetNode<E>)iter.next()).setPos(null);								//prende il successivo elemento(che è un SetNode) e lo casta a SetNode e gli invoca il metodo
	    }																		//setPos passandogli come parametro null, perché deve sapere di non appartenere alla stessa partition
	    
	    return C;															//ritorna il Set creato
	}

	/**Data una position(che in realÃ  Ã¨ un SetNode) restituisce il set in cui si trova**/
	public Set<E> find(Position<E> p) {
		SetNode<E> temp= (SetNode<E>)p;						//Casta la position a setNode
		return (Set<E>) temp.getPos().element();			//ne legge la position(riferimento alla position in cui si trova nella partition(Position<Set<Node<E>>>))
															//di quesa position ne fa il .element() per otterenere il set cui si riferisce ==>  Position<Set<Node<E>>>.element() == Set<SetNode<E>
															//il set viene castato a Set<E> per la firma del metodo
	}
	
	/**Metodo ausiliario, per controllare se il set passato in input, ha qualche elemento giÃ  presente nella partizione**/
	public boolean ElemIsIn(Set<E> A){ 
		Set<E> C = new ListSet<E>();							//crea un nuovo Set vuoto(appoggio)
		ListSet<SetNode<E>> temp=this.checkSet(A);				//Invoca il checkSet per controllarne la validità  e castarlo a ListSet, mettendolo poi in una variabile d'appoggio
		Iterator<Set<SetNode<E>>> iter= lista.iterator();		//crea un iteratore dei Set presenti nella partizione
		while(iter.hasNext()){									//finche l'iteratore ha un successivo(c'è ancora un Set)
			C=A.intersect((Set<E>) iter.next());					//In C  inserisce l'intersezione tra l'insieme dato in input e quello attualmente considerato
			if(!C.equals(null))										//Se tale insieme non Ã¨ vuoto(ci sono elementi in comune)
				return true;											//restituisce true;
		}														//arrivati qui, abbiamo scorso tutti i set presenti nella partizione e non abbiamo trovati elementi in comune
		return false;											//quindi possiamo ritornare null	
			
	}

}
