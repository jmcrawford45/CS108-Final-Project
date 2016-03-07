package messages;


public abstract class Message {
	private final String from, to, type;
	public Message(String from, String to,String type){
		this.from = from;
		this.to= to;
		this.type = type;
	}
	public String getFrom() {
		return from;
	}
	public String getTo() {
		return to;
	}
	public String getType(){
		return type;
	}
}
