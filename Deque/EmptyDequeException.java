package Deque;

public class EmptyDequeException extends RuntimeException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmptyDequeException(String message){
		super(message);
	}
	
	public EmptyDequeException(){
		super();
	}
}
