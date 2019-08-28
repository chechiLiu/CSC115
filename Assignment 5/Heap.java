//Che-Chi (Jack) Liu
//V00850558

import java.util.NoSuchElementException;

/*
 * The Heap is a binary tree data structure that has both a structural and ordering property.
 * 	The structural property is that it is a complete tree, meaning that for each level k, except the lowest level, there are exactly 2k-1 nodes at that level. The lowest level has all its nodes as far left as possible.
 * 	The ordering property is that for every node n in the tree, the comparable item contained in that node comes before the items in both the left and right child of n.
 * The elements in the Heap must be of type Comparable. Java generics are not used here; it is up to the user to cast items to their orignal subclass.
 */

@SuppressWarnings({"unchecked"})

public class Heap {
	private Comparable[] heapArray;
	private int size;
	
	public Heap() {
		size = 0;
		heapArray = new Comparable[10];
	}
	
    	//Returns true if the heap is empty, false if it is not.
	public boolean isEmpty() {
		if(size > 0) {
			return false;
		}else {
			return true;
		}
	}

	//Returns the number of items in the heap.
	public int size() {
		return size;
	}
	
	//Inserts an item into the heap.
	public void insert(java.lang.Comparable item) {
      		if(size == heapArray.length) {
            		doubleSize();
        	}
		
        	heapArray[size++] = item;
        	moveUp();
	}
	
	//Removes the item at the root node of the heap and returns the item at the root of the heap.
	public java.lang.Comparable removeRootItem() {
		if(isEmpty()) {
        		throw new java.util.NoSuchElementException("The heap is empty.");
        	}
		
        	Comparable root = heapArray[0];
        	heapArray[0] = heapArray[size - 1];
        	size--;
        	moveDown();
        	
		return root;
	}
	
	//Retrieves, without removing the item in the root.
	//It returns the top item in the tree.
	public java.lang.Comparable getRootItem() {
		if(isEmpty()) {
            		throw new java.util.NoSuchElementException("The heap is empty.");
        	}
		
		return heapArray[0];
	}

    	//Doubles the size of the heap if the current one is full.
    	private void doubleSize() {
        	if(heapArray.length == size) {
            		Comparable[] temp = new Comparable[heapArray.length * 2];
            		System.arraycopy(heapArray, 0, temp, 0, heapArray.length);
            		heapArray = temp;
        	}
    	}
	
    	//Moves the new item up to the root of the correct place.
    	private void moveUp() {
        	int position = size - 1;
        	int parent;
		
        	while(position >= 0 && heapArray[(position - 1) / 2].compareTo(heapArray[position]) > 0) {
            		Comparable temp = heapArray[position];
            		heapArray[position] = heapArray[(position - 1) / 2];
            		heapArray[(position - 1) / 2] = temp;
            		position = (position - 1) / 2;
        	}
    	}
	
   	//Moves the item at root down up to the leaf of the correct place.
    	private void moveDown() {
        	int position = 0;
        	int rightChild;
        	int leftChild;
        	int currPosition;
        	boolean down = false;

        	while((position < size() - 1) && !down) {
            		currPosition = position;
            		Comparable temp = heapArray[currPosition];
            		leftChild = 2 * position + 1;
            		rightChild = 2 * position + 2;
            
            		if(leftChild < size && heapArray[position].compareTo(heapArray[leftChild]) > 0) {
                		position = leftChild;
            		}
            		if(rightChild < size && heapArray[position].compareTo(heapArray[rightChild]) > 0) {
                		position = rightChild;
            		}
            		if(position != currPosition) {
                		heapArray[currPosition] = heapArray[position];
                		heapArray[position] = temp;
            		}else {
                		down = true;
            		}
        	}
    	}
	
    	//Prints out the heap
    	public void printHeap() {
        	System.out.println("");
        	for(int i = 0; i < heapArray.length; i++) {
            		System.out.println(heapArray[i].toString() + " ");
        	}
    	}
	
	public static void main(String[] args) {
        	Heap heap = new Heap();
        	ER_Patient[] patients = new ER_Patient[5];
        	String[] complaints = {"Walk-in", "Life-threatening", "Chronic", "Major fracture", "Chronic"};
        	for(int i = 0; i < 5; i++) {
            		patients[i] = new ER_Patient(complaints[i]);
            		heap.insert(patients[i]);
            		// spread out the admission times by 1 second
            		try {
                		Thread.sleep(1000);
            		}catch (InterruptedException e) {
                		System.out.println("sleep interrupted");
                		return;
            		}
        	}
        		
		System.out.println("Main Testing");
        	System.out.println("Patients waiting:\n");
        	for(int i = 0; i < 5; i++) {
            		System.out.println(patients[i]);
        	}
        	System.out.println("Testing..... ");
        
        	System.out.println("Visited Patient:" + heap.removeRootItem().toString());
        	System.out.println("Visited Patient:" + heap.removeRootItem().toString());
        	System.out.println("Visited Patient:" + heap.removeRootItem().toString());       
        	System.out.println("Visited Patient:" + heap.removeRootItem().toString());
        	System.out.println("Visited Patient:" + heap.removeRootItem().toString());
	}
}
