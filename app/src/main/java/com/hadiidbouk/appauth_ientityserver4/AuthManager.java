package com.hadiidbouk.appauth_ientityserver4;

import android.content.Context;
import android.net.Uri;
import android.os.Build;

import net.openid.appauth.AppAuthConfiguration;
import net.openid.appauth.AuthState;
import net.openid.appauth.AuthorizationException;
import net.openid.appauth.AuthorizationResponse;
import net.openid.appauth.AuthorizationService;
import net.openid.appauth.AuthorizationServiceConfiguration;
import net.openid.appauth.TokenResponse;

public class AuthManager {
	private static AuthManager instance;
	private AuthState mAuthState;
	private Auth mAuth;
	private AuthorizationServiceConfiguration mAuthConfig;
	private SharedPreferencesRepository mSharedPrefRep;
	private AuthorizationService mAuthService;

	public static AuthManager getInstance(Context context) {
		if (instance == null) {
			instance = new AuthManager(context);
		}
		return instance;
	}

	private AuthManager(Context context){
		mSharedPrefRep = new SharedPreferencesRepository(context);
		setAuthData();

		mAuthConfig = new AuthorizationServiceConfiguration(
				Uri.parse(mAuth.getAuthorizationEndpointUri()),
				Uri.parse(mAuth.getTokenEndpointUri()),
				null);
		mAuthState = mSharedPrefRep.getAuthState();

		AppAuthConfiguration.Builder appAuthConfigBuilder = new AppAuthConfiguration.Builder();

		//To Allow Http in requests in debug mode
		if(BuildConfig.DEBUG)
			appAuthConfigBuilder.setConnectionBuilder(AppAuthConnectionBuilderForTesting.INSTANCE);

		AppAuthConfiguration appAuthConfig = appAuthConfigBuilder.build();
		mAuthService = new AuthorizationService(context, appAuthConfig);
	}



	public AuthorizationServiceConfiguration getAuthConfig() {
		return mAuthConfig;
	}

	public Auth getAuth() {
		if(mAuth == null){
           setAuthData();
        }

        return mAuth;
	}

	public AuthState getAuthState(){
		return mAuthState;
	}

	public void updateAuthState(TokenResponse response, AuthorizationException ex){
		mAuthState.update(response,ex);
		mSharedPrefRep.saveAuthState(mAuthState);
	}

	public void setAuthState(AuthorizationResponse response, AuthorizationException ex){
		if(mAuthState == null)
			mAuthState = new AuthState(response,ex);

        mSharedPrefRep.saveAuthState(mAuthState);
	}

	public AuthorizationService getAuthService(){
		return mAuthService;
	}

	private void setAuthData(){
        mAuth = new Auth();
        mAuth.setClientId(BuildConfig.CLIENT_ID);
        mAuth.setAuthorizationEndpointUri(BuildConfig.AUTHORIZSTION_END_POINT_URI);
        mAuth.setClientSecret(BuildConfig.CLIENT_SECRET);
        mAuth.setRedirectUri(BuildConfig.REDIRECT_URI);
        mAuth.setScope(BuildConfig.SCOPE);
        mAuth.setTokenEndpointUri(BuildConfig.TOKEN_END_POINT_URI);
        mAuth.setResponseType(BuildConfig.RESPONSE_TYPE);
    }
}
