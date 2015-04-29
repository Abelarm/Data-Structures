package Deque;

public class NodeDeque_Test {

	public static void main(String[] args) {
		NodeDeque<Integer> X= new NodeDeque<Integer>();
		X.addFirst(1);
		X.addFirst(0);
		X.addLast(2);
		System.out.println(X);
		NodeDeque<Integer> Z= new NodeDeque<Integer>();
		Z.addFirst(1);
		Z.addFirst(0);
		Z.addLast(2);
		System.out.println(X.equals(Z));
		while(Z.size()>0){
		System.out.println(Z.removeFirst());
		}	
		}

}
