package CompleteBinaryTree;

import position.Position;


public class ArrayListCompleteBinaryTree__Test {

	
	public static void main(String[] args) {
		ArrayListCompleteBinaryTree<Integer> X = new ArrayListCompleteBinaryTree<Integer>();

		
		System.out.println(X.isEmpty());
		

		X.add(1);
		X.add(2);
		X.add(3);
		Position <Integer> caso=X.add(4);
		X.add(5);
		X.add(6);
		X.add(7);
		
		System.out.println(X.isEmpty());
		System.out.println(X.size());
		
		Position<Integer> temp = X.root();
		
		System.out.println(X.isRoot(temp));
		System.out.println(X.isRoot(caso));

		System.out.println(X.isExternal(temp));
		System.out.println(X.isInternal(temp));
		
		System.out.println(X.parent(caso).element());
		System.out.println(X.children(temp));
		
		System.out.println(X.left(temp).element());
		System.out.println(X.right(temp).element());
		
		System.out.println(X.toString());
	}

}
