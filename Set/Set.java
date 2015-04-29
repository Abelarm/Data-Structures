package Set;
/**Il TDA set � un contenitore di oggetti 
 * distinti
 * non ordinati
 * deve supportare le seguenti operazioni unuion,intersect,subtract**/
public interface Set<E> {
	
	/**Restituisce la dimensione dell'insieme**/
	public int size();
	
	/**Restituisce true se l'insieme � vuoto false altrimenti**/
	public boolean isEmpty();
	
	/**Crea un nuovo insieme composto da: l'unione dell'insieme su cui � invocato con l'insieme passato in input e lo restituisce **/
	public Set<E> union(Set<E> B);
	
	/**Crea un nuovo insieme composto da: l'intersezione dell'insieme su cui � invocato con l'insieme passato in input e lo restituisce **/
	public Set<E> intersect(Set<E> B);
	
	/**Crea un nuovo insieme composto da: la sottrazione dell'insieme passato in input da quello su cui � invocato e lo restituisce **/
	public Set<E> subtract(Set<E> B);
}
