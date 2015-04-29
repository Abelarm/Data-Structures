package stack;

public class LinkedStackTest {
	
	public static void main(String[] args) {
	

		LinkedStack<Integer> X = new LinkedStack<Integer>();
		LinkedStack<Integer> Y = new LinkedStack<Integer>();
		
		
		X.push(1);
		X.push(2);
		X.push(3);
		X.push(4);
		X.push(1);
		X.push(2);
		X.push(3);
		X.push(4);
	
	
	
	
	Y=X.clone();
	
	System.out.println("Gli elementi sono :" +X.toString());
	
	if(X.equals(Y))
		System.out.println("gli stack sono uguali");
	else
		System.out.println("gli stack sono diversi");
	
	
	System.out.println("la dimensione dello stack è: " +Y.size());
	
	

 }
}
