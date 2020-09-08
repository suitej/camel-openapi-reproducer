package c10y.model.oauth;

import c10y.model.api.ValidationException;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Receiver for server response in the form: <br>
 * <code>
 * <br>{
 * <br>"refresh_token": "AB11593091111aWrEWL4NAYDIyPbpumsE31bAVFg4kJV2xkWSE",
 * <br>"access_token": "eyJlbmMiOiJBMTI4Q0JDLUhTMjU2IiwiYWxnIjoiZGlyIn0..o1wDTSky4qaEmme90ZTDCg.54YJCRmuKe",
 * <br>"expires_in": 3600,
 * <br>"x_refresh_token_expires_in": 8726400
 * <br>}
 * </code>
 * 
 * @author Jean-Marc Reynaud
 *
 */
public class OAuthTokens {

	@JsonProperty("token_type")
	@JsonAlias({ "Token Type", "tokenType", "TokenType" })
	private String tokenType = "bearer";

	@JsonProperty("access_token")
	@JsonAlias({ "Access Token", "accessToken", "AccessToken" })
	private String accessToken;

	@JsonProperty("access_token_expires_in")
	@JsonAlias({ "Expires In", "expiresIn", "ExpiresIn", "expires_in" })
	private Integer accessTokenExpiresIn = Integer.valueOf(3600); // 1 hour

	@JsonProperty("refresh_token")
	@JsonAlias({ "Refresh Token", "refreshToken", "RefreshToken" })
	private String refreshToken;

	@JsonProperty("refresh_token_expires_in")
	@JsonAlias({ "x_refresh_token_expires_in" })
	private Integer refreshTokenExpiresIn = Integer.valueOf(8726400); // 101 days

	/**
	 * @return the tokenType
	 */
	public String getTokenType() {
		return tokenType;
	}

	/**
	 * @param tokenType the tokenType to set
	 */
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	/**
	 * @return the accessToken
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * @param accessToken the accessToken to set
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	/**
	 * @return the accessTokenExpiresIn
	 */
	public Integer getAccessTokenExpiresIn() {
		return accessTokenExpiresIn;
	}

	/**
	 * @param accessTokenExpiresIn the accessTokenExpiresIn to set
	 */
	public void setAccessTokenExpiresIn(Integer accessTokenExpiresIn) {
		this.accessTokenExpiresIn = accessTokenExpiresIn;
	}

	/**
	 * @return the refreshToken
	 */
	public String getRefreshToken() {
		return refreshToken;
	}

	/**
	 * @param refreshToken the refreshToken to set
	 */
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	/**
	 * @return the refreshTokenExpiresIn
	 */
	public Integer getRefreshTokenExpiresIn() {
		return refreshTokenExpiresIn;
	}

	/**
	 * @param refreshTokenExpiresIn the refreshTokenExpiresIn to set
	 */
	public void setRefreshTokenExpiresIn(Integer refreshTokenExpiresIn) {
		this.refreshTokenExpiresIn = refreshTokenExpiresIn;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OAuthTokens [tokenType=");
		builder.append(tokenType);
		builder.append(", accessToken=");
		builder.append(accessToken);
		builder.append(", accessTokenExpiresIn=");
		builder.append(accessTokenExpiresIn);
		builder.append(", refreshToken=");
		builder.append(refreshToken);
		builder.append(", refreshTokenExpiresIn=");
		builder.append(refreshTokenExpiresIn);
		builder.append("]");
		return builder.toString();
	}

	/**
	 * Validate the bean.
	 * 
	 * @throws ValidationException
	 */
	public void validate() throws ValidationException {
		if (tokenType == null) {
			tokenType = "bearer";
		}
		if (accessTokenExpiresIn == null) {
			accessTokenExpiresIn = Integer.valueOf(3600);
		}
		if (refreshTokenExpiresIn == null) {
			refreshTokenExpiresIn = Integer.valueOf(8726400);
		}
		if ((accessToken == null) || (refreshToken == null)) {
			throw new ValidationException("Validation exception: accessToken & refreshToken are mandatory.");
		}
	}

}
