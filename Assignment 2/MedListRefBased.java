//Che-Chi (Jack) Liu
//V00850558

public class MedListRefBased implements List<Medication> {
	private int count;        
    	private MedicationNode head;     
	
	//Creates and initializes an empty List of Medication objects.
	public MedListRefBased() {
        	head  = new MedicationNode(null);
		count = 0;
    	}
	
	//Inserts an item into the list at postion index.
	public void add(Medication item, int index) {
		if(index < 0 || index > count) {
			throw new ListIndexOutOfBoundsException("The index "+index+" is out of bounds.");
		}
		
		MedicationNode curr = head;
		int i = 0;

		while(i < index) {
			curr = curr.next;
			i++;
		}
		
		if(curr.prev == null) {
			MedicationNode newLink = new MedicationNode(item, null, curr);
			curr.prev = newLink;
			head = newLink;
		}else {
			MedicationNode newLink = new MedicationNode(item, curr.prev, curr);
			curr.prev.next = newLink;
			curr.prev = newLink;
		}
		count++;
	}	
	
	//Removes all matching items from the list.
	public void remove(int index) {
		if(index < 0 || index > count-1) {
			throw new ListIndexOutOfBoundsException("The index "+index+" is out of bounds.");
		}
		
		if(head == null) {
			throw new ListIndexOutOfBoundsException("The list is empty.");
		}
		
		MedicationNode curr = head;
		int i = 0;
		
		while(i < index) {
			curr = curr.next;
			i++;
		}
		
		if(curr.next == null) {
			curr.prev.next = null;
		}
		else if(curr.prev == null) {
			curr = curr.next;
			curr.prev = null;
			head = curr;
		}
		else {
			curr.prev.next = curr.next;
			curr.next.prev = curr.prev;
		}
		count--;
	}
	
	//Accesses the item by its position in the list.
	public Medication get(int index) {
		if(index < 0 || index > count-1) {
			throw new ListIndexOutOfBoundsException("The index "+index+" is out of bounds.");
		}
		
		MedicationNode curr = head;
		int i = 0;
		
		while(i < index) {
			curr = curr.next;
		    	i++;
		}
		
		return curr.item;
	}

	//Returns true if the list is empty, false otherwise.
	public boolean isEmpty() {
		return head == null;
	}
	
	//Returns the number of items in the list.
	public int size() {
		return count;
	}

	//Determines if the equivalent item is in the list. 
	//If it is in the list, then the index location is returned. If it is not, then -1 is returned.
	public int find(Medication item) {
		MedicationNode curr = head;
		for(int i = 0; i < count; i++) {
			if(curr.item.equals(item)) {
				return i;
			}
			curr = curr.next;
		}
		return -1;
	}
	
	//Removes all the items from the list, resulting in an empty list.
	public void removeAll() {
		head = null;
		count = 0;
	}
	
	//Removes all matching items from the list.
	public void remove(Medication item) {
		MedicationNode curr = head;
		while(curr != null) {
			if(curr.item.equals(item)) {
				if(curr.next == null) {
					curr.prev.next = null;
				}
				else if(curr.prev == null) {
					curr = curr.next;
					curr.prev = null;
					head = curr;
				}
				else{
					curr.prev.next = curr.next;
					curr.next.prev = curr.prev;
					curr.prev = null;
					curr.next = null;
				}
				count--;
			}
			curr = curr.next;
		}
	}
	
	//A printout of the list
	public String toString() {
		MedicationNode curr = head;
		String medString = "";
		while(curr != null) {
			medString += "\t"+curr.item+"\n";
			curr = curr.next;
		}
		return ("list: {\n"+medString);
	}
	
	public static void main(String[] args) {
		MedListRefBased list = new MedListRefBased();
		list.add(new Medication("meperidine",100),0);
		list.add(new Medication("acetylsalicylic acid",325),0);
		list.add(new Medication("acetominophen",100),0);
		list.add(new Medication("cimetidine",150),3);
		list.add(new Medication("meperidine",100),0);
		System.out.println("The list should be {meperidine,acetominophen,asa,meperidine,cimetidine}");
		System.out.println(list);
		list.printArray();
		// check to make sure the private shift methods work:
		System.out.println("After sliding everything right from the second spot:");
		list.slideRightFrom(1);
		list.printArray();
		// The list thinks its longer now.	
		list.count++;
		System.out.println("list version: "+list);
		System.out.println("After sliding them back again:");
		list.slideLeftTo(1);
		list.count--;
		list.printArray();
		System.out.println("list version: "+list);

		list.remove(new Medication("meperidine",100));
		System.out.println("After removing meperidine:");
		System.out.println(list);
		System.out.println("The number of elements is "+list.size());
		list.removeAll();
		System.out.println("After removing all the elements:");
		System.out.println(list);
		System.out.println("The number of elements is now "+list.size());
	}
}
