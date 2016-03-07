package setUp;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import messages.ChallengeMessage;
import messages.FriendRequest;
import messages.Message;
import messages.TextMessage;
import setUp.AccountManager;
import user.User;

/**
 * Application Lifecycle Listener implementation class Initialize
 *
 */
@WebListener
public class Initialize implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public Initialize() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
    	System.out.println("NEW ACCOUNT MADE");
    	User testUser = new User("Guy","password");
    	testUser.addFriend("Ben");
    	testUser.addTakenQuiz("US History");
    	testUser.addTakenQuiz("Harry Potter Trivia");
    	testUser.addTakenQuiz("Baseball Trivia");
    	testUser.setBio("Taking quizzes is my thing!");  
    	Message msg1 = new TextMessage("Ben","Guy","Hey, hows the quizzing?");
    	Message msg2 = new TextMessage("Ben","Guy","Let's get lunch!");
    	Message fr1 = new FriendRequest("Ben","Guy");
    	Message ch1 = new ChallengeMessage("Ben","Guy","TestLink",50);
    	testUser.addMessageToInbox(msg1);
    	testUser.addMessageToInbox(msg2);
    	testUser.addMessageToInbox(fr1);  
    	testUser.addMessageToInbox(ch1);  

    	
    	User testUser2 = new User("Ben","1234");
    	testUser2.addFriend("Guy");
    	testUser2.addTakenQuiz("Harry Potter Trivia");
    	testUser2.setBio("FL->CA");
    	
    	AccountManager manager = new AccountManager();  
    	manager.createAccount("Guy", testUser);
    	manager.createAccount("Ben", testUser2);
        ServletContext context = arg0.getServletContext();
        context.setAttribute("manager", manager);
        context.setAttribute("defaultUser", testUser);
    }
	
}