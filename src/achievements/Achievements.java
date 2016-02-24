package achievements;
/*
 * Defines public constants representing
 * each of the achievements. Supplies method
 * for checking if a user has an achievement,
 * and allows for updating the achievement list.
 */
public class Achievements {
public static final int AMATEUR = 1;
public static final int PROLIFIC = 2;
public static final int PRODIGIOUS = 4;
public static final int MACHINE = 8;
public static final int GREATEST = 16;
public static final int PRACTICE = 32;
public static final int[] ACHIEVEMENTS = 
{AMATEUR,PROLIFIC,PRODIGIOUS,MACHINE,GREATEST,PRACTICE};
	
private int achieved;
	public Achievements(){
		this.achieved = 0;
	}
	public boolean hasAchieved(int a){
		return (this.achieved & a) != 0;
	}
	public void achieved(int a){
		this.achieved |= a;
	}
}
