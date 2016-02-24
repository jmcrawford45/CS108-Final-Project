package messages;

public class TextMessage extends Message {
	private final String body;
	public TextMessage(String from, String to, String body) {
		super(from, to);
		this.body = body;
	}
	public String getBody() {
		return body;
	}
	@Override
	public String toString(){
		return  "From: " + getFrom() + "\n" + body;
	}

}
