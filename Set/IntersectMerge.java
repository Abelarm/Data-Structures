package Set;

import NodeList.PositionList;

/** Classe che specializza il genericMerge per l�INTERSEZIONE di 2 insiemi **/
public class IntersectMerge<E> extends Merge<E> {
	
	//i metodi aIsLess, e bIsLess non li implementiamo(quindi li lasciamo vuoti), perch� nell'intersezione ci interessano solo quando sono uguali
	
	/**Metodo che viene invocato quando a e b sono uguali**/
	protected void bothAreEqual(E a, E b, PositionList<E> C) { 
		C.addLast(a); 	// aggiungiamo solamente a(per via dell'intersezione, ma avremmo potuto aggiungere anche b, basta che si aggiungeva SOLO uno dei due)
	}

}
