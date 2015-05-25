package main;
import java.util.Date;

public class LogEntry {

	private Object host;

	public LogEntry() {
		// TODO Автоматически созданная заглушка конструктора
	}

	public Object getHost() {
		return host;
	}

	public void setHost(Object host) {
		this.host = host;
	}

	private Date timestamp;

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	private String request;

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	private int HTTPReplyCode;

	public int getHTTPReplyCode() {
		return HTTPReplyCode;
	}

	public void setHTTPReplyCode(int hTTPReplyCode) {
		HTTPReplyCode = hTTPReplyCode;
	}

	private int replyBytes;

	public int getReplyBytes() {
		return replyBytes;
	}

	public void setReplyBytes(int replyBytes) {
		this.replyBytes = replyBytes;
	}

	@Override
	public String toString() {
		return "host=" + host + ", timestamp=" + timestamp + ", request="
				+ request + ", HTTPReplyCode=" + HTTPReplyCode
				+ ", replyBytes=" + replyBytes;
	}

}
