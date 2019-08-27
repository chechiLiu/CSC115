//Che-Chi (Jack) Liu
//V00850558

/*
 *StringStack is a simple stack that holds String elements.
 */

public class StringStack {
	private Node head;
	
	public StringStack() {
		head = null;
	}
	
	//Returns true if the stack is empty, false if not.
	public boolean isEmpty() {
		return head == null;
	}

	//Returns the top item on the stack, which is removed.
	public String pop() {
		if(isEmpty()) {
			throw new StackEmptyException("The stack is empty!");
		}else {
			String x = head.data;
			head = head.next;
			return x;
		}
	}

	//Returns the top item on the stack, without removing it.
	public String peek() {
		if(isEmpty()) {
			throw new StackEmptyException("The stack is empty!");
		}else {
			return head.data;
		}
	}

	//Pushes a string onto the top of the stack.
	public void push(String item) {
		Node temp = head;
		head = new Node(item, temp);
	}

	//Empties the stack of all its items.
	public void popAll() {
		head = null;
	}
	
	//Prints out all the strings in the stack.
	public String toString() {
		String x = "Stack: \n";
		
		Node temp = head; 
		while(temp != null) { 
            x = x + temp.data +"\n";
			temp = temp.next; 
        }
		
		return x;
	}
	
	public static void main(String[] args) {
		StringStack stack = new StringStack();

		stack.push("1");
		stack.push("2");
		stack.push("3");
		stack.push("4");
		stack.push("5");

		System.out.println("What is entered: "+ stack.toString());

		System.out.println("The first item peeking in the stack is : " + stack.peek());
		System.out.println("After Peeking: "+ stack.toString());
		
		stack.pop();
		
		System.out.println("after one pop, the remaining is: " + stack.toString());
		System.out.println("after one pop, the first item peeking in the stack is : " + stack.peek());

		stack.popAll();
		System.out.println("The remaining of the stack after the popAll function " + stack.toString()); //returns true if it is empty
		System.out.println("Is the stack empty? "+stack.isEmpty()); //check if the stack is empty, returns true if it is empty
		System.out.println("Let's pop one more time.");
		stack.pop();//testing the StackEmptyException, it will have an exception, since the stack is empty
	}
}