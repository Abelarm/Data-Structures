package NodeList;

import defaultComparator.DefaultComparator;

public class TestMergeSort {
	public static void main(String[] args) {
		DefaultComparator<Integer> comp = new DefaultComparator<Integer>();
		NodePositionList<Integer> S = new NodePositionList<Integer>();
		S.addFirst(1);
		S.addLast(2);
		S.addFirst(3);
		S.addLast(1);
		S.addLast(4);
		S.addLast(5);
		System.out.print("Ecco gli elementi nella lista input");
		System.out.println(S.toString());
		System.out.println("Facciamo il mergesort");
		@SuppressWarnings("unused")
		NodePositionList<Integer> output = new NodePositionList<Integer>();
		NodePositionList.mergeSort(S, comp);
		System.out.print("Ecco gli elementi nella lista ordinata ");
		System.out.println(S.toString());
	}
}