package ArrayList;

public class TestArrayList {
	
	public static void main(String[] args) {
		
		ArrayIndexList<String> A = new ArrayIndexList<String>();
		
		A.add(1, "b");
		A.add(2, "c");
		A.add(3, "d");
		A.set(2, "cc");
		System.out.println(A.get(2));
		
		
		System.out.println(A);
	
	}
}