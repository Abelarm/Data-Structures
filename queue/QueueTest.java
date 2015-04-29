package queue;

public class QueueTest {
	
	public static void main(String[] args) {
	
	ArrayQueue<Integer> A= new ArrayQueue<Integer>(10);
	ArrayQueue<Integer> B= new ArrayQueue<Integer>(10);
	
	A.enqueue(1);
	A.enqueue(2);
	A.enqueue(3);
	A.enqueue(4);
	A.enqueue(4);
	A.enqueue(4);
	A.enqueue(5);
	A.enqueue(8);
	
	System.out.println(A);
	
	//A.dequeue();
	//A.dequeue();
	//A.dequeue();
	
	System.out.println(A);
	
	B=A.clone();
	
	if(A.equals(B))
		System.out.println("Vero");
	else
		System.out.println("falso");
	
	B=A.Collapse();
	
	System.out.println(B);
	
	}
}
