package BinaryTree;

import java.util.Iterator;

import defaultComparator.DefaultComparator;

import NodeList.NodePositionList;

import position.Position;

public class LinkedBinaryTree_test {
	public static void main(String[] args) {
		
		LinkedBinaryTree<Integer> X = new LinkedBinaryTree<Integer>();
		Iterable<Integer> pos1=new NodePositionList<Integer>();
		
		System.out.println(X.size());
		Position<Integer> root =X.addRoot(10);
		Position<Integer> figlio=X.insertLeft(root, 9);
		Position<Integer> figlio1=X.insertRight(root, 8);
		
		System.out.println(X.size());
		
		System.out.println(X.hasLeft(root));
		System.out.println(X.hasRight(root));
		
		System.out.println(X.replace(figlio, 99));
		
		System.out.println(figlio.element());
		
		LinkedBinaryTree<Integer> T = new LinkedBinaryTree<Integer>();
		
		
		Position<Integer> root1 =T.addRoot(7);
		Position<Integer> figlio16=T.insertLeft(root1, 6);
		X.insertRight(root1, 5);
		
		LinkedBinaryTree<Integer> Y = new LinkedBinaryTree<Integer>();
		
		
		Position<Integer> root2 =Y.addRoot(4);
		Position<Integer> figlio2=Y.insertLeft(root2, 3);
		X.insertRight(root2, 2);
		
		X.attach(figlio, T, Y);
		
		
		System.out.println(X.equal(figlio1, Y, figlio2));
		
		Iterator<Integer> stampa=X.iterator();
		
		NodePositionList<Position<Integer>> pos= new NodePositionList<Position<Integer>>();
		NodePositionList<Integer> pos2= new NodePositionList<Integer>();
		X.postorderPositions(root, pos);
		
		Iterator<Position<Integer>> stampa1=pos.iterator();
			
		while(stampa1.hasNext())
			System.out.println(stampa1.next().element());
		
		pos1=X.contenti(root, pos2);
		Iterator<Integer> stampa2=pos1.iterator();
		
		while(stampa.hasNext())
			System.out.println(stampa.next());
		T.insertLeft(figlio16, 1);
		
		System.out.println(LinkedBinaryTree.isRightLarger(X, figlio));
		
		DefaultComparator C=new DefaultComparator();
		
		System.out.println(X.Property(X, root, C));
		
		
		System.out.println(X.equalsSottoAlberi(root));
		
		NodePositionList<Integer> perprova= new NodePositionList<Integer>();
		
		stampa=X.NodiLivello(root, 2, perprova);
		
		while(stampa.hasNext())
			System.out.println(stampa.next());
 }
}
