package Set;

import NodeList.PositionList;

/** Classe che specializza il genericMerge per l�UNIONE di 2 insiemi**/
public class UnionMerge<E> extends Merge<E> {
	
	/**Metodo che viene invocato quando a � minore di b**/
	protected void aIsLess(E a, PositionList<E> C) {
		C.addLast(a); 	// Aggiungiamo perch� � pi� piccolo di b quindi nell'unione � presente (perch� essendo B ordinato, se a � minore di b, negli elementi successivi di b non ci possono essere duplicati di a)
	}
	
	/**Metodo che viene invocato quando a e b sono uguali**/
	protected void bothAreEqual(E a, E b, PositionList<E> C) {
		C.addLast(a); 												// aggiungiamo solamente a, perch� non vogliamo duplicati
		}
	
	protected void bIsLess(E b, PositionList<E> C) {
		C.addLast(b); // Aggiungiamo perch� � pi� piccolo di b quindi nell'unione � presente (perch� essendo B ordinato, se a � minore di b, negli elementi successivi di b non ci possono essere duplicati di a)
		}
}
