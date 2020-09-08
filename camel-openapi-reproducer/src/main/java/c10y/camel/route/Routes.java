package c10y.camel.route;

/**
 * Constants for <b>public</b> routes.
 * 
 * @author Jean-Marc Reynaud
 *
 */
public class Routes {

	/**
	 * Direct, synchronous
	 */
	public static final String IEP_QUERY_BY_ORG_ID = "direct:FetchEndpointsByOrgId";

	/**
	 * Direct, synchronous
	 */
	public static final String IEP_QUERY_BY_ORG_TAG = "direct:FetchEndpointsByOrgTag";

	/**
	 * Direct, synchronous
	 */
	public static final String IEP_QUERY_BY_REMOTE_TAG = "direct:FetchEndpointsByRemoteTag";

	/**
	 * SEDA, asynchronous
	 */
	public static final String IEP_REFRESH_TOKENS = "seda:RefreshEndpointsTokens";

	/**
	 * SEDA, asynchronous
	 */
	public static final String IEP_PERSIST_DB = "seda:PersistRemoteEndpoint";

	/**
	 * SEDA, asynchronous, 2 threads, block when full.
	 */
	public static final String C10Y_REST_API = "seda:C10yAPI?concurrentConsumers=2&blockWhenFull=true";
	
	/**
	 * Dead-letter queue, JMS, asynchronous
	 */
	public static final String C10Y_REST_API_DLQ = "jms:C10yAPI_DLQ";
	
	/**
	 * Direct, synchronous
	 */
	public static final String JOB_DELTA_START = "direct:JobDeltaStart";

	/**
	 * Direct, synchronous
	 */
	public static final String BFO_REST_DELTA = "direct:BfoRestDelta";

	/**
	 * Direct, synchronous
	 */
	public static final String BFO_OAUTH_LOGIN = "direct:BfoOauthLogin";

	/**
	 * Direct, synchronous
	 */
	public static final String QB_REST_DELTA = "direct:QbRestDelta";

	/**
	 * Direct, synchronous (was seda:qb.refresh.tokens)
	 */
	public static final String QB_OAUTH_REFRESH = "direct:QbOauthRefresh";

	/**
	 * Direct, synchronous
	 */
	public static final String RMW_OPP_DELTA = "direct:RmwOppDelta";

	/**
	 * Direct, synchronous
	 */
	public static final String SAYVA_OPP_DELTA = "direct:SayvaOppDelta";

	/**
	 * Direct, synchronous
	 */
	public static final String PARASOFT_OPP_CDC = "direct:ParasoftOppCDC";

	/**
	 * Direct, synchronous
	 */
	public static final String PARASOFT_OPP_SPLIT_CDC = "direct:ParasoftOppSplitCDC";

	/**
	 * Direct, synchronous
	 */
	public static final String PARASOFT_OPP_LINE_CDC = "direct:ParasoftOppLineCDC";

	/**
	 * Direct, synchronous
	 */
	public static final String PARASOFT_OPP_MAIN = "direct:ParasoftOppMain";

	/**
	 * SEDA, asynchronous
	 */
	public static final String JOB_DELTA_SUCCESS = "seda:JobDeltaSuccess";

	/**
	 * SEDA, asynchronous
	 */
	public static final String JOB_DELTA_FAILURE = "seda:JobDeltaFailure";

	/**
	 * The abbreviated package name for route log names
	 */
	public static final String LOG_PREFIX = "c.c.r.";
	
	/**
	 * Direct, synchronous
	 */
	//public static final String NOTIFICATION_BY_EMAIL = "direct:EmailNotificationPoint";
	public static final String NOTIFICATION_BY_EMAIL = "direct:start";
	

	/*
	 * prevent instantiation 
	 */
	private Routes() {
		super();
	}

}
