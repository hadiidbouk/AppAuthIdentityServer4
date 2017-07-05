package com.hadiidbouk.appauth_ientityserver4;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import net.openid.appauth.AuthState;
import net.openid.appauth.AuthorizationException;
import net.openid.appauth.AuthorizationService;
import net.openid.appauth.ClientSecretPost;
import net.openid.appauth.TokenRequest;
import net.openid.appauth.TokenResponse;


import java.util.Timer;
import java.util.TimerTask;


public class TokenService extends Service {

    public static final int notifyAfter = 30000;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Timer timer = new Timer("TokenTimer",true);
        timer.scheduleAtFixedRate(new TokenTimer(),0,notifyAfter);
    }

    private class TokenTimer extends TimerTask{
        private SharedPreferencesRepository mSharedPrefRep = new SharedPreferencesRepository(TokenService.this);

        @Override
        public void run() {

            if(MyApp.Token == null)
                return;

            final AuthManager authManager = AuthManager.getInstance(TokenService.this);

            final AuthState authState = authManager.getAuthState();


            if(authState.getNeedsTokenRefresh()) {
                //Get New Token

                ClientSecretPost clientSecretPost = new ClientSecretPost(authManager.getAuth().getClientSecret());
                final TokenRequest request = authState.createTokenRefreshRequest();
                final AuthorizationService authService = authManager.getAuthService();

                authService.performTokenRequest(request, clientSecretPost, new AuthorizationService.TokenResponseCallback() {
                    @Override
                    public void onTokenRequestCompleted(@Nullable TokenResponse response, @Nullable AuthorizationException ex) {
                        if(ex != null){
                            ex.printStackTrace();
                            return;
                        }
                        authManager.updateAuthState(response,ex);
                        MyApp.Token = authState.getIdToken();
                    }
                });

            }

        }
    }
}
