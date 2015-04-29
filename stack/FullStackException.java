package stack;

@SuppressWarnings("serial")
public class FullStackException extends RuntimeException {

	public FullStackException(){
		super();
	}
	
	public FullStackException(String x){
		super(x);
	}
}
