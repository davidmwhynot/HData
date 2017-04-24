public class LinkedList1 {
	private class Node {
		String value;
		Node next;
		Node(String val, Node n) {
			value = val;
			next = n;
		}
		Node(String val) {
			this(val, null);
		}
	}
	private Node first;
	private Node last;
	public void add(String name) {
		if(first == null) {
			first = new Node(name);
			last = first;
			}
		else {
			last.next = new Node(name);
			last = last.next;
		}	
	}
	public void display() {
		Node it = first;
		while(it != null) {
			System.out.println(it.value);
			it = it.next;
		}
	}
	public void delete(String name) {
		if(first == null)
			return;
		if(first.value.equals(name))
			first = first.next;
				if(first == null)
					last = null;
		Node it = first;
		while(!it.next.value.equals(name))
			it = it.next;
		it.next = it.next.next;
	}
	public void displayReverse(Node node) {
		if(node.next != null)
			displayReverse(node.next);
		System.out.println(node.value);
	}
}