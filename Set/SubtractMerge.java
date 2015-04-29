package Set;

import NodeList.PositionList;

/** Classe che specializza il genericMerge per la sottrazione tra di 2 insiemi **/
public class SubtractMerge<E> extends Merge<E> {
	
	//I metodo bIsLess, bothAreEqual non li implementiamo(cio� li lasciamo vuoti) perch� per via della natura dell'operazion l'unico metodo che ci interessa � aIsLess
	//poich� quando sono uguali nella differenza non devono essere presenti, poich� nell'operazion A/B devono essere presenti solo gli elementi di A che non sono anche elementi di B
	//bisLess non ci interessa perch� nell'insieme devono essere presenti solo elementi di A che non sono anche elementi di B
	
	/**Metodo che viene invocato quando a � pi� piccolo di b**/
	protected void aIsLess(E a, PositionList<E> C) {
		C.addLast(a); 	// Aggiungiamo perch� � pi� piccolo di b quindi nell'unione � presente (perch� essendo B ordinato, se a � minore di b, negli elementi successivi di b non ci possono essere duplicati di a)
		}
}
