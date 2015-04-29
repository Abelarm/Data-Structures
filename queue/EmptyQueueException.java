package queue;

@SuppressWarnings("serial")
public class EmptyQueueException extends RuntimeException {

	public EmptyQueueException(){
		super();
	}
	
	public EmptyQueueException(String x){
		super(x);
	}
}
