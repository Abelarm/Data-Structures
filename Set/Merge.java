package Set;

import java.util.Comparator;
import java.util.Iterator;

import NodeList.PositionList;

/**Classe astratta che contiene operazione generica di merge questa classe ha un metodo principale merge
 * che utilizza al suo interno 3 metodi che devono essere specializzati a secondo dell'operazione da compiere
 * questa tecnica viene chiamata Template method pattern**/
public abstract class Merge<E> {
	
	private E a, b; 												// elemento corrente di A e di B
	
	private Iterator<E> iterA, iterB; 								// iteratore per A e B
	
	
	
	/** Metodo che deve essere modificato a seconda della classe */
	public void merge(PositionList<E> A, PositionList<E> B, Comparator<E> comp, PositionList<E> C) {
		iterA = A.iterator();										//Crea l'iteratore di A
		iterB = B.iterator();										//Crea l'iteratore di B
		boolean aExists = advanceA(); 								//Test booleano per vedere se ci sono elementi in A, e per assegnare in a il prossimo elemento
		boolean bExists = advanceB(); 								//Test booleano per vedere se ci sono elementi in B, e per assegnare in b il prossimo elemento
		
	while (aExists && bExists) { 									// Ciclo per l'operazione
			int x = comp.compare(a, b);										//Ad x assegnamo ci� che viene restituito dal metodo compare tra (a,b)
			if (x < 0) { 													//se x<0 (cio� se a � minore di b)
				aIsLess(a, C);													//chiamiamo il metodo asIsLess(a,C) che dovr� essere implementato a seconda della classe
				aExists = advanceA(); 											//richiamiamo il metodo advanceA per vedere se ci sono altri elementi in A, e per assegnare ad a il prossimo elemento
			}
			else 															//altrimenti
				if (x == 0) {													//Se x � uguale a 0(cio� a � uguale a b)
					bothAreEqual(a, b, C);											//chiamiamo il metodo bothAreEqual(a, b, C) che dovr� essere implementato a seconda della classe
					//facciamo avanzare a e b in contemporanea
					aExists = advanceA();											//richiamiamo il metodo advanceA per vedere se ci sono altri elementi in A, e per assegnare ad a il prossimo elemento
					bExists = advanceB(); 											//richiamiamo il metodo advanceB per vedere se ci sono altri elementi in B, e per assegnare ad b il prossimo elemento
					}
				else { 															//altrimenti(sar� sicuramente b<a)
					bIsLess(b, C);													//chiamiamo il metodo bsIsLess(b,C) che dovr� essere implementato a seconda della classe
					bExists = advanceB(); 											//richiamiamo il metodo advanceB per vedere se ci sono altri elementi in B, e per asseganre ad b il prossimo elemento
				}
					
	}																//Finde del while
	// while per esaminare gli elementi di A in pi� visto che il while che scorreva entrambi � finito(cio� non ci sono  pi� elementi in B o in A)
	while (aExists) { 												//Finch� c'� un elemento in a
		aIsLess(a, C); 													//chiamiamo il metodo asIsLess(a,C) che dovr� essere implementato a seconda della classe
		aExists = advanceA(); 											//richiamiamo il metodo advanceA per vedere se ci sono altri elementi in A, e per assegnare ad a il prossimo elemento
		}															//fine while

	// while per esaminare gli elementi di B in pi� visto che il while che scorreva entrambi � finito(cio� non ci sono  pi� elementi in A o in B)
	while (bExists) { 												//Finch� c'� un elemento in b
		bIsLess(b, C); 													//chiamiamo il metodo asIsLess(b,C) che dovr� essere implementato a seconda della classe
		bExists = advanceB(); 											//richiamiamo il metodo advanceA per vedere se ci sono altri elementi in B, e per assegnare ad b il prossimo elemento
        }															//fine while

}
	
	
	// Metodi ausiliari da riscrivere a seconda della classe
	
	
	protected void aIsLess(E a, PositionList<E> C) { }
	
	protected void bothAreEqual(E a, E b, PositionList<E> C) { }
	
	protected void bIsLess(E b, PositionList<E> C) { }
	
	// helper methods
	
	/**Avanza con A**/
	private boolean advanceA() {
		if (iterA.hasNext()) {									//finch� l'iteratore di A ha un elemento
			a = iterA.next(); 										//ad a(elemento corrente) assegnamo il successivo
			return true; 											//ritorniamo true
			}
		return false; 											//ritorniamo false
	}
	
	/**Avanza con B**/
	private boolean advanceB() {								
		if (iterB.hasNext()) {									//finch� l'iteratore di B ha un elemento
			b = iterB.next(); 										//ad b(elemento corrente) assegnamo il successivo
			return true;											//ritorniamo true
			}
	return false; 												//ritorniamo false
	}
	
}
