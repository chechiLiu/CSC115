//Che-Chi (Jack) Liu
//V00850558

/*
 * The PriorityQueue is a Queue of Comparable objects, where the item in the queue with the highest priority, 
 * determined by its compareTo method, is always the next item to be removed.
 */

import java.util.NoSuchElementException;

public class PriorityQueue {
	private Heap heap;

	//Creates an empty priority queue.
	public PriorityQueue() {
 		heap = new Heap();
	}

    	//Inserts an item into the queue.
	public void enqueue(java.lang.Comparable item) {
		heap.insert(item);
	}

	//Removes the highest priority item from the queue and returns the item.
	public java.lang.Comparable dequeue() {
        	if(heap.size() == 0) {
            		throw new java.util.NoSuchElementException("The heap is empty.");
        	}
        	
		return heap.removeRootItem();	
	}

	//Retrieves, but does not remove the next item out of the queue.
	//It returns the item with the highest priority in the queue.
	public java.lang.Comparable peek() {
		if(heap.size() == 0) {
            		throw new java.util.NoSuchElementException("The heap is empty.");
         	}
        
		return heap.getRootItem();
	}
	
	//Returns true if the queue is empty, false if it is not.
	public boolean isEmpty() {
		if(heap.size() > 0) {
            		return false;
        	}else {
            		return true;
        	}
	}
	
	public static void main(String[] args) {
        	PriorityQueue patientQueue = new PriorityQueue();
        	ER_Patient[] patients = new ER_Patient[6];
        	String[] complaints = {"Walk-in", "Life-threatening", "Chronic", "Major fracture", "Chronic", "Life-threatening"};
        	for (int i = 0; i < complaints.length; i++) {
            		patients[i] = new ER_Patient(complaints[i]);
            		patientQueue.enqueue(patients[i]);
            		//spread out the admission times by 1 second
            		try {
                		Thread.sleep(1000);
            		} catch (InterruptedException e) {
                		System.out.println("sleep interrupted");
                		return;
            		}
        	}

        	System.out.println("Main Testing");
        
        	System.out.println("Patients waiting:\n");
        	for(int i = 0; i < complaints.length; i++) {
            		System.out.println(patients[i]);
        	}
        	System.out.println("Testing...");
       
        	System.out.println("Visited Patient:" + patientQueue.dequeue().toString());
        	System.out.println("Visited Patient:" + patientQueue.dequeue().toString());
        	System.out.println("Visited Patient:" + patientQueue.dequeue().toString());   
        	System.out.println("Visited Patient:" + patientQueue.dequeue().toString());
               	System.out.println("Visited Patient:" + patientQueue.dequeue().toString());
        	System.out.println("Visited Patient:" + patientQueue.dequeue().toString());
    	}
}
