import edu.princeton.cs.algs4.*;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class NodeStack<Item> implements Iterable<Item> {

	private Node first;
	//private Item item;
	private int N;
	private class Node {
		Item item;
		Node next;
	}

	public NodeStack() {
		first = null;
		N = 0;
	}

	public Item peek() {
		if(isEmpty()) {
			throw new NoSuchElementException("Stack Overflow.");
		}
		return first.item;
	}

	public boolean isEmpty() {
		return first == null;
	}
	public int size() {
		return N;
	}

	public void push(Item item) {
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
		N++;
	}
	public Item pop() {
		if(isEmpty()) {
			throw new NoSuchElementException("Stack Overflow.");
		}
		Item item = first.item;
		first = first.next;
		N--;
		return item;
	}

	public Iterator<Item> iterator() {
		return new ListIterator();
	}

	private class ListIterator implements Iterator<Item> {
		private Node current = first;
		public boolean hasNext() {
			return current != null;
		}
		public Item next() {
			Item item = current.item;
			current = current.next;
			return item;
		}
		public void remove(){ }
	}

}