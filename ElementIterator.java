import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Vector;

public class ElementIterator<E> implements Iterator<E> {
	private Vector<E> elements;
	private int currentIndex;
	
	public ElementIterator(Vector<E> elements){
		this.elements = elements;
		this.currentIndex = 0;
		
	}

	@Override
	public boolean hasNext() {
		
		return currentIndex < elements.size();
	}

	@Override
	public E next() {
		if (!hasNext()) {
			throw new NoSuchElementException("No more elements left");
		}
		return this.elements.get(currentIndex++);
	}
	

	

}
