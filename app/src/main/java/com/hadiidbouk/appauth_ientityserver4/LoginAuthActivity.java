package com.hadiidbouk.appauth_ientityserver4;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

import net.openid.appauth.AuthorizationException;
import net.openid.appauth.AuthorizationResponse;
import net.openid.appauth.AuthorizationService;
import net.openid.appauth.ClientSecretPost;
import net.openid.appauth.TokenRequest;
import net.openid.appauth.TokenResponse;

public class LoginAuthActivity extends Activity {

    private AuthorizationService mAuthService;

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);

        setContentView(R.layout.activity_login_auth);


        final AuthorizationResponse resp = AuthorizationResponse.fromIntent(getIntent());
        AuthorizationException ex = AuthorizationException.fromIntent(getIntent());

        final AuthManager authManager = AuthManager.getInstance(this);
        authManager.setAuthState(resp,ex);

        if (resp != null) {

            ClientSecretPost clientSecretPost = new ClientSecretPost(authManager.getAuth().getClientSecret());
            TokenRequest tokenRequest = new TokenRequest
				.Builder(authManager.getAuthConfig(), authManager.getAuth().getClientId())
				.setAuthorizationCode(resp.authorizationCode)
				.setRedirectUri(Uri.parse(authManager.getAuth().getRedirectUri()))
                .setCodeVerifier(new SharedPreferencesRepository(this).getCodeVerifier())
				.build();

            mAuthService = authManager.getAuthService();

            mAuthService.performTokenRequest(tokenRequest, clientSecretPost, new AuthorizationService.TokenResponseCallback() {
                @Override public void onTokenRequestCompleted(@Nullable TokenResponse response, @Nullable AuthorizationException ex) {
					if(ex == null) {
						authManager.updateAuthState(response,ex);
                        MyApp.Token = authManager.getAuthState().getIdToken();
                        startService(new Intent(LoginAuthActivity.this, TokenService.class));
                        Intent mainIntent = new Intent(LoginAuthActivity.this, MainActivity.class);
                        startActivity(mainIntent);
                        finish();
					}
					else{
                        Intent loginIntent = new Intent(LoginAuthActivity.this, LoginActivity.class);
                        startActivity(loginIntent);
                        finish();
                    }
                }
            });

            // authorization completed
        } else {
            // authorization failed, check ex for more details
            Intent loginIntent = new Intent(LoginAuthActivity.this, LoginActivity.class);
            startActivity(loginIntent);
            finish();
        }
    }

}
