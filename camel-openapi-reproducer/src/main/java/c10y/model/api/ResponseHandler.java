/**
 * 
 */
package c10y.model.api;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_INTERNAL_ERROR;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.apache.camel.Exchange.CONTENT_TYPE;
import static org.apache.camel.Exchange.EXCEPTION_CAUGHT;
import static org.apache.camel.Exchange.HTTP_RESPONSE_CODE;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import c10y.model.api.ValidationException;

import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.springframework.stereotype.Component;

/**
 * Set up success or failure responses.
 * 
 * @author Jean-Marc Reynaud
 *
 */
@Component
public class ResponseHandler {
	
	/**
	 * 
	 * @param xchg
	 * @return a failure response
	 */
	public Response failure(Exchange xchg) {
		var cause = xchg.getProperty(EXCEPTION_CAUGHT, Throwable.class);
        var msg = xchg.getMessage();
        Response resp = null;
        if (cause instanceof ValidationException) {
            resp = new Response(Integer.valueOf(HTTP_BAD_REQUEST), cause.getMessage());
		} else {
	        resp = new Response(Integer.valueOf(HTTP_INTERNAL_ERROR), cause);
		}
        msg.setHeader(CONTENT_TYPE, APPLICATION_JSON);
        msg.setHeader(HTTP_RESPONSE_CODE, resp.getResultCode());
        return resp;
	}
	
	/**
	 * 
	 * @return a success response
	 */
	@Handler
	public Response success() {
		return new Response(Integer.valueOf(HTTP_OK), "Success");
	}
	

}
