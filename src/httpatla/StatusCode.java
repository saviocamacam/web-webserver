package httpatla;

public enum StatusCode {
	
	S100("Continue"),
	S101("Switching Protocols"),
	S200("OK"),
	S201("Created"),
	S202("Accepted"),
	S203("Non-Authoritative Information"),
	S204("No Content"),
	S205("Reset Content"),
	S206("Partial Content"),
	S300("Multiple Choices"),
	S301("Moved Permanently"),
	S302("Found"),
	S303("See Other"),
	S304("Not Modified"),
	S305("Use Proxy"),
	S307("Temporary Redirect"),
	S400("Bad Request"),
	S401("Unauthorized"),
	S402("Payment Required"),
	S403("Forbidden"),
	S404("Not Found"),
	S405("Method Not Allowed"),
	S406("Not Acceptable"),
	S407("Proxy Authentication Required"),
	S408("Request Time-out"),
	S409("Conflict"),
	S410("Gone"),
	S411("Length Required"),
	S412("Precondition Failed"),
	S413("Request Entity Too Large"),
	S414("Request-URI Too Large"),
	S415("Unsupported Media Type"),
	S416("Requested range not satisfiable"),
	S417("Expectation Failed"),
	S500("Internal Server Error"),
	S501("Not Implemented"),
	S502("Bad Gateway"),
	S503("Service Unavailable"),
	S504("Gateway Time-out"),
	S505("HTTP Version no Supported");
	
	private String reasonPhrase;
	
	StatusCode(String reasonPhrase) {
		this.reasonPhrase = reasonPhrase;
	}
	
	public String reasonPhrase() {
		return reasonPhrase;
	}

}
