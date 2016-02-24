package messages;
public abstract class Message {
	private final String from, to;
	public Message(String from, String to){
		this.from = from;
		this.to= to;
	}
	public String getFrom() {
		return from;
	}
	public String getTo() {
		return to;
	}
}
