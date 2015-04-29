package ArrayList;

import java.util.Iterator;

import NodeList.NoSuchElementException;



public class ElementIterator<E> implements Iterator<E>{
	protected ArrayIndexList<E> V;
	protected int cur=0;
	
	
	public ElementIterator(ArrayIndexList<E> L){
		V=L;
	}
	
	public boolean hasNext(){
		return cur<V.size();
	}
	
	public E next() throws NoSuchElementException{
		E el;
		if (!hasNext())
			throw new NoSuchElementException("non c'� un prossimo elemento");
		else{
			el = V.get(cur);
			cur++;
			return el;
		}
	}

	public void remove(){
	}
}
