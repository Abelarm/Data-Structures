package tree;

public class UndeletableNodeException extends RuntimeException {

	
	public UndeletableNodeException(){
		super();
	}
	
	public UndeletableNodeException(String x){
		super(x);
	}
}
