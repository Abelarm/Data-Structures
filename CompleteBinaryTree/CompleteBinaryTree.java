package CompleteBinaryTree;

import position.Position;
import BinaryTree.BinaryTree;
/**Interfaccia del complete binary tree che estende binary tree
 * 
 *la differenza e che il complete binary tree ha esattamente due figli
 */
public interface CompleteBinaryTree<E> extends BinaryTree<E> {

	/**Inserisce una foglia che contiene elem, come ultimo elemento dell’albero. 
	 * Quindi, la nuova foglia ha come padre il primo nodo dell’albero che ha meno di 2 figli (mantiene la proprietà di completezza).**/
	public Position<E> add(E elem); 
	
	/**rimuove l’ultimo nodo z**/
	public E remove();

}
