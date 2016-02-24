package messages;

public class FriendRequest extends Message {
	private boolean accepted;
	private String body;
	public FriendRequest(String from, String to){
		super(from, to);
		this.accepted = false;
		this.body = from + " would like to be your friend!";
	}
	public String getBody() {
		return body;
	}
	public void accept(){
		this.accepted = true;
	}
	public boolean isAccepted(){
		return this.accepted;
	}
	@Override
	public String toString(){
		return  "From: " + getFrom() + "\n" + body;
	}
}
