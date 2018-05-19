//Che-Chi Jack Liu
//V00850558


/*
 * Class Skier consists of the name of a skier and the current level of expertise, 
 * for the purposes of a Ski school that holds courses for different levels. 
 * In this version, the name of the skier is used as the unique identifier; in other words, 
 * we assume that no two skiers have the same name.  
 */

public class Skier {

	// Programmer note: Do not alter the following instance variables.
	private String name; // the unique name of the skier
	private int level; // level of skill

/*
 * Programmer note:  Each of the methods below are not complete and
 * need to be implemented by you.
 * Make sure you provide method header comments and provide
 * the implementation code. 
 * Make sure you test regularly for compilation and errors,
 * It is recommended that you reference the completed main method,
 * where each of your methods is tested;
 * follow that ordering so lyou can monitor your progress.
 */
	
	//Creates a level 0 Skier.
	public Skier(String name) {
		this.name = name;
	}
	
	//Creates a Skier at the given level.
	public Skier(String name, int level) {
		this.name = name;
		if (level < 0 || level > 4) {
			this.level = 0;
		}else {
			this.level = level;
		}
	}

	//Updates the name of the skier.
	public void setName(String name) {
		this.name = name;
	}
	
	//The skier's name.
	public String getName() {
		// programmer note, the return statement is only a placeholder so compilation works.
		return name;
	}
	
	//Sets the level for the skier.
	public void setLevel(int level) {
		if (level < 0 || level > 4) {
				//Error
		}else {
			this.level = level;
		}
	}

	//The current level of the skier.
	public int getLevel() {
		// programmer note: The return statement is only a placeholder.
		return level;
	}
	
	//Determines if two skier objects are equivalent. For our purposes, 
	//they are equal if they have the same name.
	public boolean equals(Skier other) {
		// programmer note: The return statement is only a placeholder.
		return this.name == other.name;
	}
	
	//A String representation of the skier.
	public String toString() {
		// programmer note: The return statement is only a placeholder.
		return this.name + "(level "+this.level+ ")" ;
	}

	public static void main(String[] args) {
		System.out.println("Testing the Skier class.");
		Skier s1 = null;
		try {
			s1 = new Skier("Howie SnowSkier", 4);
		} catch(Exception e) {
			System.out.println("Constructor not working.");
			e.printStackTrace();
			return;
		}
		if (!s1.getName().equals("Howie SnowSkier")) {
			System.out.println("Failed at test one.");
			return;
		}
		if (s1.getLevel() != 4) {
			System.out.println("Failed at test two.");
			return;
		}
		Skier s2 = new Skier("Baby Skier");
		if (!s2.getName().equals("Baby Skier")) {
			System.out.println("Failed at test three.");
			return;
		}
		if (s2.getLevel() != 0) {
			System.out.println("Failed at test four.");
			return;
		}
		s2.setName("Better Skier");
		s2.setLevel(1);
		if (!s2.getName().equals("Better Skier") || s2.getLevel() != 1) {
			System.out.println("Failed at test five.");
			return;
		}
		if (s1.equals(s2)) {
			System.out.println("Failed at test six.");
			return;
		}
		if (!s1.equals(new Skier("Howie SnowSkier",4))) {
			System.out.println("Failed at test seven.");
			return;
		}
		if (s1.toString().equals("Howie SnowSkier (level 0)")) {
			System.out.println("Failed at test eight.");
			System.out.println("Expected: Howie SnowSkier (level 0)");
			System.out.println("Got:      "+s1.toString());
			return;
		}
		System.out.println("All tests passed.");
	}
}
	
