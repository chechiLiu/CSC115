//Che-Chi (Jack) Liu
//V00850558

/*
Simple example of a Java class.
*/

public class Time {
	private int hours;
	private int minutes;
	
	//Constructor must be public, can't have return, and its name must be the same as the class name.
	public Time(int hours, int minutes) {
		setHours(hours);
		setMinutes(minutes);
	}
	
	//Another constructor without something to pass in; without parameter.
	public Time() {
		setHours(0);
		setMinutes(0);
	}
	
	//Accessors
	public int getHours() {
		return this.hours;
	}
	
	public int getMinutes() {
		return this.minutes;
	}
	
	//Modifiers
	public void setHours(int hours) {
		if(hours >= 0 && hours < 24) {
			this.hours = hours;
		}
	}
	
	public void setMinutes(int minutes) {
		if(minutes >= 0 && minutes < 60) {
			this.minutes = minutes;
		}
	}
	
	//Other methods
	public String toString() {
		return this.hours + ":" + this.minutes;
	}
	
	public boolean equals(Time other) {
		return this.hours == other.hours && this.minutes == other.minutes;
	}
	
	public void add(Time other) {
		int sumHours = this.hours + other.hours;
		int sumMinutes = this.minutes + other.minutes;
		
		if (sumMinutes >= 60) {
			sumHours += 1;
			sumMinutes -= 60;
		}
		
		this.hours = sumHours%24;
		this.minutes = sumMinutes%60;
	}
	
	//Testing
	public static void main(String[] args) {
		Time t1 = new Time(12, 50);
		System.out.println("Hours is " + t1.getHours());
		System.out.println("Minutes is " + t1.getMinutes());
		System.out.println(t1);
		
		Time t2 = new Time();
		System.out.println(t2);
		
		Time t3 = new Time(12, 30);
		t1.add(t3);
		System.out.println("Sum is "+t1);
	}
}
