package com.hadiidbouk.appauth_ientityserver4;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import net.openid.appauth.AuthorizationRequest;
import net.openid.appauth.AuthorizationService;
import net.openid.appauth.CodeVerifierUtil;

public class LoginActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}

	public void Login(View view) {
		AuthManager authManager = AuthManager.getInstance(this);
		AuthorizationService authService = authManager.getAuthService();
		Auth auth = authManager.getAuth();

		AuthorizationRequest.Builder authRequestBuilder = new AuthorizationRequest
			.Builder(
			authManager.getAuthConfig(),
			auth.getClientId(),
			auth.getResponseType(),
			Uri.parse(auth.getRedirectUri()))
			.setScope(auth.getScope());

		//Generate and save code verifier to be used later
		String codeVerifier = CodeVerifierUtil.generateRandomCodeVerifier();
		SharedPreferencesRepository sharedPreferencesRepository = new SharedPreferencesRepository(this);
		sharedPreferencesRepository.saveCodeVerifier(codeVerifier);

		authRequestBuilder.setCodeVerifier(codeVerifier);

		AuthorizationRequest authRequest = authRequestBuilder.build();

		Intent authIntent = new Intent(this, LoginAuthActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, authRequest.hashCode(), authIntent, 0);

		authService.performAuthorizationRequest(
			authRequest,
			pendingIntent);
	}
}
