package c10y.camel.microservice;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import c10y.camel.route.Routes;
import c10y.model.api.Response;
import c10y.model.api.ResponseHandler;
import c10y.model.oauth.OAuthTokens;

/**
 * 
 * @author Jean-Marc Reynaud
 *
 */
@Component
public class C10yOAuthRB extends RouteBuilder {
	
	// Route & log IDs.
	private static final String REFRESH_TKNS_RID1 = "oauth.refreshTokens.get";
	private static final String REFRESH_TKNS_LOG1 = Routes.LOG_PREFIX + REFRESH_TKNS_RID1;
	private static final String REFRESH_TKNS_RID2 = "oauth.refreshTokens.post";
	private static final String REFRESH_TKNS_LOG2 = Routes.LOG_PREFIX + REFRESH_TKNS_RID2;
	
	@Autowired
	private ResponseHandler handler;


	@Override
	public void configure() throws Exception {

		// TODO 20 we'll need a global one somewhere, I guess?
		restConfiguration()
			.component("servlet")	 // FIXME 10 "camel-servlet" (when running in Tomcat or WildFly) raises: No bean could be found in the registry for: camel-servlet of type: org.apache.camel.spi.RestApiConsumerFactory
			.bindingMode(RestBindingMode.json)
			.scheme("http") // TODO 10 https scheme - requires cert?
			.contextPath("api")
			.apiComponent("openapi")
			.apiContextPath("api-doc")	// FIXME 10 java.lang.NoSuchMethodError: com.fasterxml.jackson.databind.introspect.AnnotatedMember.getType(Lcom/fasterxml/jackson/databind/type/TypeBindings;)Lcom/fasterxml/jackson/databind/JavaType;
			.dataFormatProperty("prettyPrint", "true")
			.clientRequestValidation(true)
		;
		
		onException(Exception.class)
			.handled(true)
			.to("log://C10yOAuthRB?level=ERROR&showAll=true&showStreams=true")
			// response to the receiver
			.bean(handler, "failure")
			.marshal().json(JsonLibrary.Jackson)
		;
			
		// refresh tokens from, for example, QB oAuth login.
		// Test: http://localhost:8080/camel/oauth/refreshTokens?organizationTag=SAYVA_QB&accessToken=<accessToken>&refreshToken=<refreshToken>
		rest("/oauth")
			.get("/refreshTokens")
				// Swagger / OpenAPI
				.description("Refresh the tokens for the given organizationTag.")
				.param()
					.name("organizationTag")
					.type(RestParamType.query)
					.dataType("string")
					.required(true)
					.description("remote enpoint organization tag")
				.endParam()
				.param()
					.name("accessToken")
					.type(RestParamType.query)
					.dataType("string")
					.required(true)
					.description("oAuth access token")
				.endParam()
				.param()
					.name("accessTokenExpiresIn")
					.type(RestParamType.query)
					.dataType("integer")
					.required(false)
					.defaultValue("3600")
					.description("time in seconds the access token expires in, default: 3600")
				.endParam()
				.param()
					.name("refreshToken")
					.type(RestParamType.query)
					.dataType("string")
					.required(true)
					.description("oAuth refresh token")
				.endParam()
				.param()
					.name("refreshTokenExpiresIn")
					.type(RestParamType.query)
					.dataType("integer")
					.required(false)
					.defaultValue("8726400")
					.description("time in seconds refresh token expires in, default: 8726400 (101 days)")
				.endParam()
				.param()
					.name("tokenType")
					.type(RestParamType.query)
					.dataType("string")
					.required(false)
					.defaultValue("bearer")
					.description("token type, default: bearer")
				.endParam()
				.outType(Response.class)
				
				// process
				.route()
					.routeId(REFRESH_TKNS_RID1)
					.to("log://" + REFRESH_TKNS_LOG1 + "?showHeaders=true&showBody=true")

					// DO NO WORK FOR stackoverflow preproducer

					.process(new Processor() {
						// create & return an OAuthTokens
						@Override
						public void process(Exchange xchg) throws Exception {
							var msg = xchg.getMessage();
							var tokens = new OAuthTokens();
							tokens.setAccessToken(msg.getHeader("accessToken", String.class));
							tokens.setAccessTokenExpiresIn(msg.getHeader("accessTokenExpiresIn", Integer.class));
							tokens.setRefreshToken(msg.getHeader("refreshToken", String.class));
							tokens.setRefreshTokenExpiresIn(msg.getHeader("refreshTokenExpiresIn", Integer.class));
							tokens.setTokenType(msg.getHeader("tokenType", String.class));
							msg.setBody(tokens);
						}
					})
					.to("log://" + REFRESH_TKNS_LOG1 + "?showHeaders=false&showBody=true")

					// DO NO WORK FOR stackoverflow preproducer

					// response to the receiver
					.bean(handler, "success")

		;

		// refresh tokens from, for example, QB oAuth login.
		// Test: http://localhost:8080/camel/oauth/refreshTokens?organizationTag=SAYVA_QB
		rest("/oauth")
			.post("/refreshTokens")
				// Swagger / OpenAPI
				.description("Refresh the oAuth tokens for the given organizationTag.")
				.param()
					.name("organizationTag")
					.type(RestParamType.query)
					.dataType("string")
					.required(true)
					.description("remote enpoint organization tag")
				.endParam()
				.type(OAuthTokens.class)
				.outType(Response.class)
				
				// process
				.route()
					.routeId(REFRESH_TKNS_RID2)
					.to("log://" + REFRESH_TKNS_LOG2 + "?showHeaders=true&showBody=true")
					
					// validate OAuthTokens
					.process(new Processor() {
						@Override
						public void process(Exchange xchg) throws Exception {
							var tokens = xchg.getMessage().getBody(OAuthTokens.class);
							tokens.validate();
						}
					})

					// DO NO WORK FOR stackoverflow preproducer

					// response to the receiver
					.bean(handler, "success")

		;

	}
}
