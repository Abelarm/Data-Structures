package tree;

import java.util.Iterator;

import position.Position;

public class LinkedTree_Test {
	
	public static void main(String[] args) {
	
		LinkedTree<Integer> X= new LinkedTree<Integer>();
		LinkedTree<Integer> Y= new LinkedTree<Integer>();
		
		
		System.out.println(X.isEmpty());
		
		Position<Integer> root= X.addRoot(5);
		Position<Integer> primofiglio=X.addChild(4, root);
		X.addChild(3, root);
		X.addChild(2, root);
		X.addChild(1, root);
		
		Position<Integer> root1= Y.addRoot(10);
	    Y.addChild(9, root1);
		Y.addChild(8, root1);
		Y.addChild(7, root1);
		Position<Integer> ultimofiglio=Y.addChild(6, root1);
		
		System.out.println(Y.depth(ultimofiglio));
		
		
		
		Y.attach(ultimofiglio, X);
		
		
		
		
		System.out.println(X.isEmpty());
		
		System.out.println(X.size());
		
		X.children(root);
		
		System.out.println(X.isExternal(root));
		
		System.out.println(X.isInternal(root));
		
		System.out.println(X.replace(primofiglio, 45));
		
		
		
		Iterator<Integer> stampa= Y.iterator();
		
		while(stampa.hasNext())
			System.out.println(stampa.next());
		
		System.out.println(Y.depth(ultimofiglio));
		System.out.println(Y.height());
	

 }
}