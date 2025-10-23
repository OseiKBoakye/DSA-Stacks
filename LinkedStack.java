import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Vector;

public class LinkedStack<E> implements StackInterface<E> {
	private int count;
	private Node<E> top;
	private Node<E> bottom;
	
	
	public LinkedStack() {
		this.top = null;
		this.bottom = null;
		this.count = 0;
	}

	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			private Node<E> current = bottom;

			@Override
			public boolean hasNext() {
				return current != null;
				
			}

			@Override
			public E next() {
				if (!hasNext()) {
					throw new NoSuchElementException();
				}
				E data = current.getElement();
				current = current.getNext();
				return data;
			}
		};
		
		
		//used when the previous node was set;
//		Vector<E> element = new Vector<E>();
//		
//		Node<E> current = this.top;
//		Node<E> previous;
//		
//		while(current != null) {
//			element.add(current.getElement());
//			current = current.getPrevious();
//		}
//		
//		return new ElementIterator<>(element);
		
	}

	@Override
	public boolean isEmpty() {
		
		return (this.top == null);
	}

	@Override
	public int size() {
		
		return this.count;
	}

	@Override
	public StackInterface<E> copy() {
		LinkedStack<E> newStack = new LinkedStack<E>();
		
		if (this.bottom == null) {
			return newStack;
		}
		
		Object[] array = new Object[this.count];
		Node<E> current = this.bottom;
		int index=0;
		
		while (current != null) {
			array[index++] = current.getElement();
			current = current.getNext();
			
		}
		
		for (int i = 0; i < this.count; i++) {
			newStack.push((E) array[i]);
		}
		
		
		return newStack;
	}

	@Override
	public void push(E e) throws IllegalStateException, NullPointerException {
		if (e == null) {
			throw new NullPointerException("Element can not be null");
		}
		
		Node<E> node = new Node<E>(e);
		
		if (isEmpty()) {
			this.top = node;
			this.bottom = node;
		}
		else {
			this.top.setNext(node);
			this.top = node;
		}
		this.count++;
		
	}

	@Override
	public E peek() {
		if (this.top == null) {
			return null;
		}
		else {
			return this.top.getElement();
		}
		
	}

	@Override
	public E pop() {
		
		if (this.top == null) {
			return null;
		}
		
		
		
		if (this.count == 1) {
			E poppedElement = this.top.getElement();
			clear();
			return poppedElement;
		}
		
		Node<E> previous = null;
		Node<E> current = this.bottom;
		int index = 0;
		
		while(index < this.count-1) {
			previous = current;
			current = current.getNext();
			index++;
		}
		
		E poppedElement =  current.getElement();
		previous.setNext(null);
		this.top = previous;
		this.count--;
		return poppedElement;
	}

	@Override
	public void clear() {
		this.top = null;
		this.bottom = null;
		this.count= 0;
		
	}

}
