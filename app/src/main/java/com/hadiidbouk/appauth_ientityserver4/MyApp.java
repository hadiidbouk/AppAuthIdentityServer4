package com.hadiidbouk.appauth_ientityserver4;

import android.app.Application;

import net.openid.appauth.AuthState;

public class MyApp extends Application {

	public static String Token;

	private SharedPreferencesRepository mSharedPrefRep;

	@Override
	public void onCreate() {
		super.onCreate();

		mSharedPrefRep = new SharedPreferencesRepository(this);

		AuthState authState = mSharedPrefRep.getAuthState();
		if(authState != null)
			Token = authState.getIdToken();

	}



}
