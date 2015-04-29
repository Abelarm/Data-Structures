package Dizionario;

import java.util.Iterator;

import PriorityQueue.Entry;

public class OrderedSearchTableTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		OrderedSearchTable<Integer,String> X = new OrderedSearchTable<Integer,String>();
		
		X.insert(1,"A");
		X.insert(2,"B");
		X.insert(16,"C");
		X.insert(3, "D");
		
		System.out.println(X.size());
		
		
		Entry<Integer,String> temp=X.find(2);
		X.remove(temp);
		
		Iterator<Entry<Integer,String>> iter= X.entries().iterator();
		
		while(iter.hasNext())
			System.out.println(iter.next().getValue());

	}

}
