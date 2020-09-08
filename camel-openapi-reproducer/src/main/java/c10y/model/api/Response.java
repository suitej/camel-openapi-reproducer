/**
 * 
 */
package c10y.model.api;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 
 * @author Jean-Marc Reynaud
 *
 */
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "resultCode",
    "message",
    "additionalMessage",
    "stackTrace"
})
public class Response {

	@JsonProperty("resultCode")
	private Integer resultCode;
	
	@JsonProperty("message")
	private String message;
	
	private String additionalMessage;
	
	@JsonIgnore
	private Throwable cause;

	/**
	 * 
	 */
	public Response() {
		super();
	}

	/**
	 * @param resultCode
	 * @param message
	 */
	public Response(Integer resultCode, String message) {
		super();
		this.resultCode = resultCode;
		this.message = message;
	}

	/**
	 * @param resultCode
	 * @param cause
	 */
	public Response(Integer resultCode, Throwable cause) {
		super();
		this.resultCode = resultCode;
		this.cause = cause;
		this.message = cause.getMessage();
	}

	/**
	 * @param resultCode
	 * @param message
	 * @param cause
	 */
	public Response(Integer resultCode, String message, Throwable cause) {
		super();
		this.resultCode = resultCode;
		this.message = message;
		this.cause = cause;
	}

	/**
	 * @param resultCode
	 * @param message
	 * @param additionalMessage
	 * @param cause
	 */
	public Response(Integer resultCode, String message, String additionalMessage, Throwable cause) {
		super();
		this.resultCode = resultCode;
		this.message = message;
		this.additionalMessage = additionalMessage;
		this.cause = cause;
	}

	/**
	 * @return the resultCode
	 */
	public Integer getResultCode() {
		return resultCode;
	}

	/**
	 * @param resultCode the resultCode to set
	 */
	public void setResultCode(Integer resultCode) {
		this.resultCode = resultCode;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the {@code additionalMessage}, if it exists, otherwise {@code cause.cause.message}, if it exists.
	 */
	@JsonProperty("additionalMessage")
	public String getAdditionalMessage() {
		if (additionalMessage != null) {
			return additionalMessage;
		}
		if ((cause == null) || (cause.getCause() == null)) {
			return null;
		}
		return cause.getCause().getMessage();
	}

	/**
	 * @param additionalMessage the additionalMessage to set
	 */
	@JsonProperty("additionalMessage")
	public void setAdditionalMessage(String additionalMessage) {
		this.additionalMessage = additionalMessage;
	}

	/**
	 * @return the cause
	 */
	public Throwable getCause() {
		return cause;
	}

	/**
	 * @param cause the cause to set
	 */
	public void setCause(Throwable cause) {
		this.cause = cause;
	}

	/**
	 * 
	 * @return the stack trace, if {@code cause} is not null.
	 */
	@JsonProperty("stackTrace")
	public String stackTrace() {
		if (cause == null) {
			return null;
		}
		var sw = new StringWriter();
		var pw = new PrintWriter(sw);
		cause.printStackTrace(pw);
		return sw.toString();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Response [resultCode=");
		builder.append(resultCode);
		builder.append(", message=");
		builder.append(message);
		builder.append(", additionalMessage=");
		builder.append(additionalMessage);
		builder.append(", cause=");
		builder.append(cause);
		builder.append("]");
		return builder.toString();
	}


}
