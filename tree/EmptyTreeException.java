package tree;

@SuppressWarnings("serial")
public class EmptyTreeException extends RuntimeException {
	
	public EmptyTreeException() {
		super();
	}
	
	public EmptyTreeException(String x) {
		super(x);
	}
}
