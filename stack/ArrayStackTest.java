package stack;

public class ArrayStackTest {

	
	public static void main(String[] args) {
	
			
		ArrayStack<Integer> X = new ArrayStack<Integer>(5);
		ArrayStack<Integer> Y = new ArrayStack<Integer>(5);
		
		
		X.push(1);
		X.push(2);
		X.push(3);
		X.push(4);
		X.push(5);
		X.push(6);
		X.push(7);
		X.push(8);
		
		System.out.println("la dimensione dello stack è: " +X.size());
		
		System.out.println("Gli elementi sono :" +X.toString());
		
		Y=X.clone();
		
		System.out.println("Gli elementi sono :" +Y.toString());
		
		if(X.equals(Y))
			System.out.println("gli stack sono uguali");
		else
			System.out.println("gli stack sono diversi");
		
		
		System.out.println("la dimensione dello stack è: " +Y.size());
	
		
 }
}