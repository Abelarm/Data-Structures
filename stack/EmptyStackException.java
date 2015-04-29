package stack;

@SuppressWarnings("serial")
public class EmptyStackException extends RuntimeException {

	public EmptyStackException() {
		super();
	}
	
	public EmptyStackException(String x) {
		super(x);
	}

}
