package com.hadiidbouk.appauth_ientityserver4;

public class Auth {

	private String clientId;
	private String clientSecret;
	private String redirectUri;
	private String scope;
	private String authorizationEndpointUri;
	private String tokenEndpointUri;
	private String registrationEndpointUri;
	private String responseType;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getRedirectUri() {
		return redirectUri;
	}

	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getAuthorizationEndpointUri() {
		return authorizationEndpointUri;
	}

	public void setAuthorizationEndpointUri(String authorizationEndpointUri) {
		this.authorizationEndpointUri = authorizationEndpointUri;
	}

	public String getTokenEndpointUri() {
		return tokenEndpointUri;
	}

	public void setTokenEndpointUri(String tokenEndpointUri) {
		this.tokenEndpointUri = tokenEndpointUri;
	}

	public String getRegistrationEndpointUri() {
		return registrationEndpointUri;
	}

	public void setRegistrationEndpointUri(String registrationEndpointUri) {
		this.registrationEndpointUri = registrationEndpointUri;
	}

	public String getResponseType() {
		return responseType;
	}

	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
}
