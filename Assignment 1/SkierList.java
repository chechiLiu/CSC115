//Che-Chi (Jack) Liu
//V00850558

/*
 * Class SkierList is a list of Skiers in no particular order.
 */

public class SkierList {
	private Skier[] skiers; // array storage for skiers
	private int count; // the number of skiers in the list
	
	// the following is the initial size of the empty skiers array.
	private static final int INITIAL_CAP = 3;
	
	//Creates a SkierList that is empty.
	public SkierList() {
		skiers = new Skier[INITIAL_CAP];
		count = 0;
	}
	
	//The number of Skiers in the list.
	public int size() {
		return count;
	}
	
	//Accesses the skier in the list by their position index.
	//If the index is out of range of the list, then null is returned.
	public Skier get(int index) {
		if(index >= skiers.length || index < 0) {
			return null;
		}else {
			return skiers[index];
		}
	}
	
	//Removes the skier at the index posiiton from the list. 
	//If the index is out of range, then nothing is removed.
	public void remove(int index) {
		if(index >= skiers.length || index < 0) {
			System.out.println("The input index is out of range, nothing is removed.");
		}else {
			Skier[] temp = new Skier[skiers.length-1];
			for(int i = 0, k = 0; i < skiers.length; i++) {
            			if(i == index) { 
                			continue; 
            			}
            			temp[k++] = skiers[i];
			}
			skiers = temp;
			count = count-1;
		}
	}
	
	//Adds a skier to the list, in no particular position in the list.
	public void add(Skier skier) {
		if(count < skiers.length) {
			skiers[count] = skier;
		}else {
			Skier[] temp = new Skier[skiers.length*2];
			
			for(int i = 0; i < skiers.length; i++) {
				temp[i] = skiers[i];
			}
			
			temp[count] = skier;
			skiers = temp;
		}
	    	count = count+1;
	}
	
	//Determines if the skier is in the list. If they are in the list, then the index location is returned. 
	//If they are not, then -1 is returned.
	public int findSkier(Skier skier) {
		for(int i = 0; i < skiers.length; i++) {
			if(skiers[i].getName().equals(skier.getName())) {
				return i;
		    	}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		System.out.println("Testing the SkierList class.");
		SkierList list = null;
		try {
			list = new SkierList();
		} catch (Exception e) {
			System.out.println("Constructor not working.");
			e.printStackTrace();
			return;
		}
		// Add some skiers.
		Skier s1 = new Skier("Daffy Duck", 0);
		list.add(s1);
		if (list.size() != 1) {
			System.out.println("Failed at test one.");
			return;
		}
		if (!list.get(0).equals(s1)) {
			System.out.println("Failed at test two.");
			System.out.println("The first skier in the list needs to be in index position 0");
			return;
		}
		if (list.findSkier(s1) == -1) {
			System.out.println("Failed at test three.");
			return;
		}
		Skier s2 = new Skier("Bugs Bunny", 4);
		list.add(s2);
		if (s2.getLevel() != 4) {
			System.out.println("Failed at test four.");
			return;
		}
		if (list.size() != 2) {
			System.out.println("Failed at test five.");
			return;
		}
		Skier tmp1 = list.get(0);
		Skier remaining;
		if (tmp1.equals(s1)) {
			remaining = s2;
		} else {
			remaining = s1;
		}
		list.remove(0);
		if (list.findSkier(remaining) == -1) {
			System.out.println("Failed at test six.");
			return;
		}
		if (list.size() != 1) {
			System.out.println("Failed at test seven.");
			return;
		}
		if (!list.get(0).equals(remaining)) {
			System.out.println("Failed at test eight.");
			return;
		}
		list.remove(0);
		if (list.size() != 0) {
			System.out.println("Failed at test nine.");
			return;
		}
		System.out.println("Testing for expansion.");
		// note that the array has to expand in this test.
		// Creating an initial array with a length >= max is a failure.
		String prefix = "Skier";
		int max = 1000;
		try {
			for (int i=0; i<max; i++) {
				list.add(new Skier(prefix+i));
			}
		} catch (Exception e) {
			System.out.println("Failed at test 10.");
			e.printStackTrace();
			return;
		}
		for (int i=max-1; i>=0; i--) {
			if (list.findSkier(new Skier(prefix+i)) == -1) {
				System.out.println("Failed at test 11.");
				System.out.println("Unable to find skier: "+(prefix+i));
				return;
			}
		}
		System.out.println("All tests passed");
	}
}
