public class Hello {
	public static void main(String[] args) {
	LinkedList1 myList = new LinkedList1();
	//if(!myList.isEmpty())
	//	System.out.println("The size of my list is " + myList.size);
	myList.add("James");
	myList.add("John");
	myList.add("Aaron");
	myList.add("Zach");
	myList.add("Jason");
	myList.add("Kyle");
	myList.delete("Aaron");
	myList.display();
	myList.displayReverse();
	}
}