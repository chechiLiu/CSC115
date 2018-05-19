//Che-Chi Jack Liu
//V00850558


/*
 * Class Lesson is a Ski lesson for a certain level of skiers. It may be empty or contain a number of ski students. 
 * In this version of the class, there is no limit to the number of students in the lesson.
 */
 
public class Lesson {

	/*
	 * Programmer note:
	 * the following static final array is a nice lookup array to
	 * match the level numbers to the level names.
	 * Note that the index of a name in the array is exactly the
	 * the level number for that name.
	 * Because this particular lookup array is immutable and
	 * there only needs to be one copy for any number of Lesson objects,
	 * we use the 'final' and 'static'.
	 */
	private static final String[] levelNames = 
		{"Beginner", "Novice", "Snowplower", "Intermediate", "Advanced"};
	// Programmer note: Do not alter the following instance variables.
	private int lessonLevel; // level of the Lesson, not necessarily all the skiers
	private String lessonName; // One of the names in levelNames array above.
	private SkierList students; // The list of skiers registered for this lesson.

/*
 * Programmer note: Each of the methods below are not complete and
 * need to be implemented by you.
 * Make sure you provide method header comments and provide
 * the implementation code.
 * Make sure you test regularly for compilation and errors.
 * It is recommended that you reference teh completed main method,
 * where each of your methods is tested;
 * follow that ordering so you can monitor your progress.
 */
	//Creates a lesson for a given level
	public Lesson(int level) {
		if (level < 0 || level > 4) {
			lessonName = levelNames[0];
			students = new SkierList();
			lessonLevel = 0;
		}else {
			lessonName = levelNames[level];
			students = new SkierList();
			lessonLevel = level;
		}
	}
	
	//Creates a lesson for the given level, and populates the lesson with Skiers.
	public Lesson(int level, SkierList students) {
		if (level < 0 || level > 4) {
			lessonName = levelNames[0];
			lessonLevel = 0;
			this.students = students;
		}else {
			lessonName = levelNames[level];
			lessonLevel = level;
			this.students = students;
		}
	}
	
	//Sets the lesson name based on the level of the students.
	public void setLessonLevel(int level) {
		if (level < 0 || level > 4) {
			
		}else {
			lessonName = levelNames[level];
			lessonLevel = level;
		}
	}
	
	//The group name of the lesson
	public String getName() {
		// Programmer note: The return statement is only a placeholder.
		return lessonName;
	}
	
	//The number of skiing students in the lesson.
	public int numStudents() {
		// Programmer note: The return statement is only a placeholder.
		return students.size();
	}
	
	//Adds a new skier to the list. Before adding the skier to the lesson, the skier's level is updated to fit the level of the lesson.
	public void addSkier(Skier skier) {
		students.add(new Skier(skier.getName(), lessonLevel));
	}
	
	//Removes a skier from the lesson. If the skier is not in the lesson, then nothing is removed.
	public void removeSkier(Skier skier) {
		int index = 0;
			for(int i = 0; i<students.size(); i++){
				if(skier == students.get(i)){
					index = i;
					students.remove(index);
				}
			}
	}
	
	//Determines whether a particular skier is registered for this Lesson.
	public boolean isRegistered(Skier skier) {
		// Programmer note: The return statement is only a placeholder.
		for(int i = 0; i<students.size(); i++){
			if(skier == students.get(i)) {
				return true;
			}
		}
		return false;
	}
	
	//Prints a list of the students
	public String toString() {
		// Programmer note: The return statement is only a placeholder.
		Skier tem;
		String result = "";
		for(int i=0; i<students.size(); i++){
			tem =students.get(i);
			result +=tem.getName()+": "+levelNames[tem.getLevel()]+" ";
		}
		return result;
	}

	/**
	 * Used as a test harness for the class.
	 * @param args Not used.
	 */
	public static void main(String[] args) {
		System.out.println("Testing the Lesson class.");
		Lesson lesson = null;
		String[] group = {"Daffy Duck", "Bugs Bunny", "Betty Boop",
			"Roger Rabbit", "Han Solo", "Chewbacca"};
		try {
			lesson = new Lesson(2);
		} catch (Exception e) {
			System.out.println("Failed to construct a Lesson object.");
			e.printStackTrace();
			return;
		}
		if (!lesson.getName().equals("Snowplower")) {
			System.out.println("Failed at test one.");
			return;
		}
		if (lesson.numStudents() != 0) {
			System.out.println("Failed at test two.");
			return;
		}
		lesson.setLessonLevel(3);
		if (!lesson.getName().equals("Intermediate")) {
			System.out.println("Failed at test three.");
			return;
		}
		for (int i=0; i<group.length; i++) {
			lesson.addSkier(new Skier(group[i]));
		}
		if (lesson.numStudents() != 6) {
			System.out.println("Failed at test four.");
			return;
		}
		System.out.print("Checking the toString: Should see a list of ");
		System.out.println("6 skiers, all with level 3");
		System.out.println(lesson);
		
		System.out.println("Checking constructor that takes a list.");
		int[] levels = {0,3,2};
		int levelIndex = 0;
		SkierList list = new SkierList();
		for (int i=0; i<group.length; i++) {
			list.add(new Skier(group[i],levels[levelIndex]));
			levelIndex = (levelIndex+1)%levels.length;
		}
		try {
			lesson = new Lesson(4,list);
		} catch (Exception e) {
			System.out.println("Constructor not working.");
			e.printStackTrace();
			return;
		}
		if (lesson.numStudents() != 6) {
			System.out.println("Failed at test five.");
			return;
		}
		for (int i=0; i<list.size(); i++) {
			if (!lesson.isRegistered(list.get(i))) {
				System.out.println("Failed at test six.");
				return;
			}
		}
		Skier removed = list.get(3);
		lesson.removeSkier(removed);
		if (lesson.isRegistered(removed)) {
			System.out.println("Failed at test seven.");
			return;
		}
		if (lesson.numStudents() != 5) {
			System.out.println("Failed at test eight.");
			return;
		}
		System.out.print("The following printout should consist of 5 ");
		System.out.println("skiers with varying levels:");
		System.out.println(lesson);
		System.out.println("Testing completed.");
	}
}
