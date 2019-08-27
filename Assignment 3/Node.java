//Che-Chi (Jack) Liu
//V00850558

//A simple Node class.

public class Node {
	String data;
	Node next;
	
	public Node(String data, Node next) {
		this.data = data;
		this.next = next;
	}
	
	public String getData() {
		return data;
	}
}