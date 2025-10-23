import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Vector;

public class ArrayBasedStack<E> implements StackInterface<E> {
	private final int SIZE = 5;
	private Object[] stack;
	private int count;
	private int maxSize = 10000;
	
	public ArrayBasedStack() {
		this.stack = new Object[SIZE];
		this.count = 0;
		
	}
	
	public ArrayBasedStack(int size) {
		this.stack = new Object[size];
		this.count = 0;
		
	}
	

	@Override
	public Iterator<E> iterator() {
//		return new Iterator<E>() {
//			private int currentIndex = count-1;
//			
//
//			@Override
//			public boolean hasNext() {
//				return (currentIndex >= 0);
//			}
//
//			@Override
//			public E next() {
//				if (!hasNext()) {
//					throw new NoSuchElementException();
//				}
//				return (E) stack[currentIndex--];
//			}
//			
//		};
//		
		Vector<E> element = new Vector<E>();
		
		
		for (int i =this.count-1; i >= 0; i--) {
			element.add((E)stack[i]);
		}
		
		return new ElementIterator<E>(element);
		
	}

	@Override
	public boolean isEmpty() {
		
		return (this.count == 0);
	}

	@Override
	public int size() {
		
		return this.count;
	}

	@Override
	public StackInterface<E> copy() {
		ArrayBasedStack<E> copy = new ArrayBasedStack<E>();
		
		
		if (isEmpty()) {
			return copy;
		}
		
		for (int i = 0; i < this.count; i++) {
			copy.push((E) stack[i]);
		}
		copy.count = this.count;
		return copy;
		
	}

	@Override
	public void push(E e) throws IllegalStateException, NullPointerException {
		if (e == null) {
			throw new NullPointerException("Null exception: " + e);
		}
		
		if (isFull()) {
			resize();
		}
		
		if (this.count < maxSize ) {
			this.stack[this.count] = e;
			this.count++;
		}
		else {
			throw new IllegalStateException("This stack is full");
		}
		
		
	}
	
	public boolean isFull() {
		return (this.count == this.stack.length);
	}
	
	public void resize() {
		int length = this.stack.length * 2;
		
		if (maxSize > 0) {
			length = Math.min(length, maxSize);
		}
		
		Object[] biggerSize = new Object[length];
		
		for (int i = 0; i < this.count; i++) {
			biggerSize[i] = stack[i];
		}
		
		stack = biggerSize;
	}

	@Override
	public E peek() {
		if (isEmpty()) {
			return null;
		}
		E peekElement = (E) this.stack[this.count-1];
		return peekElement;	
	}

	@Override
	public E pop() {
		if (isEmpty()) {
			return null;
		}
		else {
			E poppedElement = (E)this.stack[this.count-1];
			this.stack[this.count-1] = null;
			this.count--;
			return poppedElement;
		}
	}

	@Override
	public void clear() {
		this.count =0;
		this.stack = new Object[SIZE];
		
	}

}
