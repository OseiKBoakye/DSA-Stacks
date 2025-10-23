
public class Node<E> {
	private E value;
	private Node<E> next;
	private Node<E> previous;
	
	
	public Node(E value) {
		this.value = value;
		this.next = null;
	}
	
	public Node(Node<E> previous, E element, Node<E> next) {
		this.previous = previous;
		this.value = element;
		this.next = next;
	}
	
	public void setData(E value) {
		this.value = value;
		
	}
	
	public void setNext(Node<E> next) {
		this.next = next;
	}
	public void setPrevious(Node<E> previous) {
		this.previous = previous;
	}
	
	public E getElement() {
		return this.value;
	}
	
	public Node<E> getNext() {
		return this.next;
	}
	public Node<E> getPrevious() {
		return this.previous;
	}

}
