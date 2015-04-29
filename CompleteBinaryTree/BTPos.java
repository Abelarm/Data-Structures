package CompleteBinaryTree;

import position.Position;
/**Position particolare che implementa Position che oltre ad avere l'elemento ha anche 
 * un indice che corrisponde all'indice in cui si trova nell'array list **/
public class BTPos<E> implements Position<E> {
	
	private	E element;
	private int index;

	/**Costruttore parametrico**/
	public BTPos(E element, int index){
		this.element=element;
		this.index=index;
		}
	
	/**Metodo della position**/
	public E element() {
		return element;
	}
	
	/**Si fa restituire l'intero che corrisponde all'indice**/
	public int get_index(){
		return index;
	}
	
	/**Sette l'elemento con quello passato in input e restituisce il vecchio**/
	public E setElement(E e){
		E temp=element;
		element=e;
		return temp;
	}

}
