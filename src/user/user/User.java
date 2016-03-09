package user;

import java.util.ArrayList;

import com.sun.xml.internal.bind.CycleRecoverable.Context;

import messages.Message;
import achievements.Achievements;

public class User implements java.io.Serializable{

	private static final long serialVersionUID = 1L;    
	static int BIO_CHAR_LIMIT = 150;
	static int FRIEND_LIM = 5;              
	//testing 

	private String uniqueUserID;       
	private String hashPassword;
	private String displayName;
	private String profileImageString;    
	private String bio;
	private String currentStatus;
	private ArrayList<String> previousStatuses;
	private ArrayList<String> friends;//display top five//
	private ArrayList<String> topQuizzes;//top 3
	private ArrayList<String> quizHistory;//all quizzes
	private ArrayList<String> authoredQuizzes;
	//authored quizzes// 
   	private int careerScore;    
	private int friendCount;
	private boolean adminStatus = false;
	private ArrayList<Message> messages;
	private ArrayList<String> activityLog;  
	private ArrayList<Achievements> achievements;
	private boolean isPrivate;

	public User(String uniqueUserId,String hashPassword) {
		this.uniqueUserID = uniqueUserId;    
		this.displayName = uniqueUserId;
		this.friendCount = 0;
		this.careerScore = 0;
		friends = new ArrayList<String>();
		previousStatuses = new ArrayList<String>();
		topQuizzes = new ArrayList<String>();
		quizHistory = new ArrayList<String>();
		authoredQuizzes = new ArrayList<String>();
		achievements = new ArrayList<Achievements>();
		profileImageString = "defaultImg.png";
		messages = new ArrayList<Message>();
		activityLog = new ArrayList<String>();
		this.hashPassword = hashPassword;

	}
	
	public void setDisplayName(String newDisplayName){
		this.displayName = newDisplayName;
	}

	public String getDisplayName(){
		return this.displayName;
	}
	public String getPassword(){
		return hashPassword;
	}

	public void setImage(String newImageString){
		this.profileImageString = newImageString;
	}

	public String getImage(){
			return this.profileImageString;
	}

	public void setBio(String str){
		if(str.length() > BIO_CHAR_LIMIT){
			print("error: exceeds character limit!");
		} else {
			this.bio = str;
		}
	}

	public String getBio(){
		if(this.bio != null) {
			return this.bio;
		}
		return "";
	}

	public void setStatus(String newStatus){
		this.currentStatus = newStatus;
		previousStatuses.add(newStatus);  
	}  

	public String getStatus(){
		if(this.currentStatus != null){
			return this.currentStatus;
		}
		return "";
	}

	public void addFriend(String friendUniqueUserID){
		if(!friends.contains(friendUniqueUserID)){
			friends.add(friendUniqueUserID);
			this.friendCount++;
		}
		activityLog.add(this.uniqueUserID + " Added" + friendUniqueUserID + " as a friend!");
	}

	public void removeFriend(String friendUniqueUserID){
		if(friends.contains(friendUniqueUserID)){
			friends.remove(friendUniqueUserID);
			this.friendCount--;
		}
	}

	public void displayFriends(){
		for(int i = 0; i < friends.size(); i++){
			if(i >= FRIEND_LIM) break;
			print(friends.get(i));
		}
	}
	
	public ArrayList<String> getFriends(){ 
		return friends;
	}
	
	
	
	public void addTakenQuiz(String quizname){
		quizHistory.add(quizname);
		activityLog.add(this.uniqueUserID + " Took Quiz: " + quizname);

	}
	
	public ArrayList<String> getQuizzes(){
		return quizHistory;
	}
	
	public ArrayList<String> getActivityLog(){
		return activityLog;
	}
	public void addAuthoredQuiz(String quizname){
		if(!authoredQuizzes.contains(quizname)){
			authoredQuizzes.add(quizname);
			activityLog.add(this.uniqueUserID + "Created Quiz: " + quizname);
		}
	}
	
	public ArrayList<String> getAuthoredQuizzes(){
		return authoredQuizzes;
	}
	
	public void addAchievement(Achievements achievement){
		if(!achievements.contains(achievement)){
			achievements.add(achievement);
			activityLog.add(this.uniqueUserID + "Got a New Achievement!: " + achievement);
		}
	}
	
	public ArrayList<Achievements> getAchievements(){
		return achievements;
	}

	public void setScore(int newScore){
		this.careerScore = newScore;
	}
	

	public int getScore(){
		return this.careerScore;
	}

	public void setFriendCount(){
		this.friendCount = friends.size();
	}

	public int getFriendCount(){
		return this.friendCount;   
	}
	
	public boolean containsFriend(String friendUniqueId){
		for(int i = 0; i < friends.size(); i++){
			if(friends.get(i).equals(friendUniqueId)) return true;
		}
		return false;
	}

	public void promoteToAdmin(int uniqueId){
		if(this.adminStatus){
			
		
		}
	}

	public boolean getAdminStatus(){
		return adminStatus;
	}
	
	
	
	public void addMessageToInbox(Message msg){
		messages.add(msg);
	}
	
	public void deleteMessage(int index){
		if(index >= messages.size()) return;
		messages.remove(index);
	}
	
	public ArrayList<Message> getMessages(){
		return messages;
	}
	
	public int getMessageCount(){
		return messages.size();
	}

	public void viewProfile(){
		print("DisplayName: " + this.getDisplayName());
		print("Friends: ");
		this.displayFriends();
		print("Friend Count: " + this.getFriendCount());
		print("Status: " + this.getStatus());
		print("Bio: " + this.getBio());
	}
	
	public void setPrivate(boolean status){
		this.isPrivate = status;
	}
	
	public boolean isPrivate(){
		return isPrivate;
	}



	private static void print(String str){
		System.out.println(str);
	}
}
