package Sequence;

import java.util.Iterator;

import NodeList.DNode;

import position.Position;


public class SequenceTest {
	public static void main(String[] args) {
		
		NodeSequence<Integer> S = new NodeSequence<Integer>();
		
		System.out.println(S.isEmpty());
		
		S.addFirst(5);
		S.addFirst(4);
		S.addFirst(3);
		S.addFirst(2);
		S.add(2, 0);
		
		S.set(4, 15);
		
		System.out.println(S.get(4));
		
		System.out.println(S.isEmpty());
		System.out.println(S);
		System.out.println(S.size());

		
		Position<Integer> p5=((DNode<Integer>)S.last()).getPrev();
		
		if(p5==null)
			System.out.println("� vuota cazzo");
		
		S.remove(p5);
		
		
		
		Iterator<Integer> iter= S.iterator();
		
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
		
		System.out.println(S);
		System.out.println(S.size());
		
		
		

	}

}
