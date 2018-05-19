//Che-Chi Jack Liu
//V00850558


public class MedListRefBased implements List<Medication> {
	
	private int count;        
    private MedicationNode head;     

	public MedListRefBased() {
        head  = new MedicationNode(null);
		count = 0;
    }

	 
	public void add(Medication item, int index) {
		
		if (index < 0 || index > count) {
			throw new ListIndexOutOfBoundsException("The index "+index+" is out of bounds.");
		}
		
		if (head == null) {
			throw new ListIndexOutOfBoundsException("There exits no linked list");
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
		}
		
		else {
			MedicationNode newLink = new MedicationNode(item, curr.prev, curr);
			curr.prev.next = newLink;
			curr.prev = newLink;
		}
		count++;
	}	
	 
	public void remove(int index) {
		
		if (index < 0 || index > count-1) {
			throw new ListIndexOutOfBoundsException("The index "+index+" is out of bounds.");
		}
		
		if (head == null) {
			throw new ListIndexOutOfBoundsException("There exits no linked list");
		}
		
		MedicationNode curr = head;
		int i = 0;
		
		while (i < index) {
			curr = curr.next;
			i++;
		}
		
		if(curr.next == null) {
			curr.prev.next = null;
		}
		
		else if (curr.prev == null) {
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

		
	public Medication get (int index) {
		if (index < 0 || index > count-1) {
			throw new ListIndexOutOfBoundsException("The index "+index+" is out of bounds.");
		}
		MedicationNode curr = head;
		int i= 0;
		while(i<index){
			curr = curr.next;
		    i++;
		}
		return curr.item;
	}
	

	public boolean isEmpty() {
		return head == null;
	}
	
	
	public int size() {
		return count;
	}

	
	public int find(Medication item) {
		MedicationNode curr = head;
		for(int i = 0; i < count; i++){
			if(curr.item.equals(item)){
			return i;
			}
			curr = curr.next;
		}
		return -1;
	}
	
	
	public void removeAll() {
		head = null;
		count = 0;
	}


	public void remove(Medication item) {
		MedicationNode curr = head;
		while (curr != null) {
			if (curr.item.equals(item)) {
				
				if(curr.next == null) {
					curr.prev.next = null;
				}
				else if (curr.prev == null) {
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


	
	public String toString() {
		MedicationNode curr = head;
		String medString = "";
		while(curr != null){
			medString += "\t"+curr.item+"\n";
			curr = curr.next;
		}
		return ("list: {\n"+medString);
	}
	
	
	public static void main(String[] args) {
		MedListRefBased list = new MedListRefBased();
		list.add(new Medication("acetominophen",100),0);
		list.add(new Medication("acetylsalicylic acid",325),0);
		list.add(new Medication("acetominophen",100),0);
		list.add(new Medication("X",1),2);
		list.add(new Medication("meperidine",100),0);
		list.add(new Medication("cimetidine", 5800),3);
		
		System.out.println("The list should be {meperidine,acetominophen,asa,meperidine,cimetidine}");
		System.out.println(list);
		
		System.out.println("Removing X");
		list.remove(new Medication("X",1));
		System.out.println(list);
		System.out.println("removing item!");
		list.remove(0);
		list.remove(3);
		System.out.println(list);
		
		System.out.println("removing all items from list \n");
		list.removeAll();
		
		System.out.println("is the list empty?\n");
		System.out.println(list.isEmpty());
		
		
		System.out.println("adding more medication");
		list.add(new Medication("meperidine",100),0);
		list.add(new Medication("meperidine",100),0);
		list.add(new Medication("meperidine",100),0);
		list.add(new Medication("meperidine",100),0);
		list.add(new Medication("meperidine",100),0);
		list.add(new Medication("X",100),0);
		list.add(new Medication("meperidine",100),0);
		list.add(new Medication("meperidine",100),0);
		list.add(new Medication("meperidine",100),0);
		list.add(new Medication("meperidine",100),0);
		list.add(new Medication("meperidine",100),0);
		list.add(new Medication("meperidine",100),0);
		list.add(new Medication("cimetidine",100),0);
		list.add(new Medication("meperidine",100),0);
		list.add(new Medication("meperidine",100),0);
		list.add(new Medication("meperidine",100),0);
		
		System.out.println(list);
		
		System.out.println("Testing find method, looking for X.");
		System.out.println("The index find method should return a index.");
		
		Medication x = new Medication("X", 100);
		System.out.println("The index returned was "+list.find(x));
		
		
		System.out.println("Removing all of the meperidine at once");
		list.remove(new Medication("meperidine",100));
		System.out.println(list);

		System.out.println("Number of nodes: "+ (list.size()));
		
	}
}	