package queue;

import stack.EmptyStackException;
import stack.Stack;

public class QueueStack<E> implements Stack<E> {	
		ArrayQueue<E> stack;

		@Override
		public int size() {
			return stack.size();
		}

		@Override
		public boolean isEmpty() {
			return stack.size()==0;
		}

		@Override
		public E top() throws EmptyStackException {
			for(int i=0;i<this.size();i++){
				stack.enqueue(stack.dequeue());
			}
			E toreturn = stack.front();
			stack.enqueue(stack.dequeue());
			return toreturn;
			
		}

		@Override
		public E pop() throws EmptyStackException {
			for(int i=0;i<this.size();i++){
				stack.enqueue(stack.dequeue());
			}
			E toreturn= stack.front();
			stack.dequeue();
			return toreturn;
		}

		@Override
		public void push(E o) {
			stack.enqueue(o);
			
		}

	

}
