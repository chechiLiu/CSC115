//Che-Chi Jack Liu
//V00850558


import java.util.NoSuchElementException;

/*
 * The shell of the class, to be completed as part of the
 * CSC115 Assignment 5 : Emergency Room
 */


public class PriorityQueue {
	
	private Heap heap;

	//This method creates an empty priority queue.
	public PriorityQueue() {
 		heap = new Heap();
	}

    //This method inserts an item into the queue
	public void enqueue(java.lang.Comparable item) {
		heap.insert(item);
	}

	//This method removes the highest priority item from the queue.
	//It returns the item.
	public java.lang.Comparable dequeue() {
        if (heap.size() == 0) {
            throw new java.util.NoSuchElementException("The heap is empty.");
        }
        return heap.removeRootItem();		
	}

	//This method retrieves, but does not remove the next item out of the queue.
	//It returns the item with the highest priority in the queue.
	public java.lang.Comparable peek() {
		 if (heap.size() == 0) {
            throw new java.util.NoSuchElementException("The heap is empty.");
         }
         return heap.getRootItem();
	}

	//This method return true if the queue is empty, false if it is not.
	public boolean isEmpty() {
		if(heap.size() > 0) {
            return false;
        }else {
            return true;
        }
	}


	public static void main (java.lang.String[] args) {
        PriorityQueue patientQueue = new PriorityQueue();
        ER_Patient[] patients = new ER_Patient[6];
        String[] complaints = {"Walk-in", "Life-threatening", "Chronic", "Major fracture", "Chronic", "Life-threatening"};
        for (int i = 0; i < complaints.length; i++) {
            patients[i] = new ER_Patient(complaints[i]);
            patientQueue.enqueue(patients[i]);
            // spread out the admission times by 1 second
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("sleep interrupted");
                return;
            }
        }

        System.out.println("Main Testing");
        
        System.out.println("Patients waiting:\n");
        for (int i = 0; i < complaints.length; i++) {
            System.out.println(patients[i]);
        }
        System.out.println("Testing...");
        
        System.out.println("Visited Patient:" + patientQueue.dequeue().toString());
        
        System.out.println("Visited Patient:" + patientQueue.dequeue().toString());
        
        System.out.println("Visited Patient:" + patientQueue.dequeue().toString());
        
        System.out.println("Visited Patient:" + patientQueue.dequeue().toString());
        
        System.out.println("Visited Patient:" + patientQueue.dequeue().toString());
        //heap.printHeap();
        System.out.println("Visited Patient:" + patientQueue.dequeue().toString());
    }

}
	
