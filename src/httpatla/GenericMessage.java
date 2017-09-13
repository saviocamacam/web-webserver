package httpatla;

public class GenericMessage {
	
	private String startLine = "";
	private String messageBody = "";
	
	public GenericMessage() {
		
	}
	
	public String getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}

	public String getStartLine() {
		return startLine;
	}

	public void setStartLine(String startLine) {
		this.startLine = startLine;
	}
}