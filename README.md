# AppAuth with IdentityServer 4 for android

## You can now use the AppAuth library with **WebView** check [this](https://github.com/hadiidbouk/AppAuthWebView-Android) 
Android sample using [AppAuth-Android](https://github.com/openid/AppAuth-Android) with [IdentityServer4](https://github.com/IdentityServer/IdentityServer4)

## Identity Server on the backend 
```csharp
new Client
{
    ClientId = "myClientId",
    ClientName = "myClientName",
    RequireConsent = false,

    ClientSecrets =
    {
        new Secret("myClientSecret".Sha256())
    },

    RedirectUris = { "myRedirectUri://callback" },

    AllowedScopes =
    {
        IdentityServerConstants.StandardScopes.OpenId,
        IdentityServerConstants.StandardScopes.Profile,
        IdentityServerConstants.StandardScopes.Email,
        IdentityServerConstants.StandardScopes.Phone,
        IdentityServerConstants.StandardScopes.OfflineAccess //to get the refresh token
    },

    AllowOfflineAccess = true,
    AllowedGrantTypes = GrantTypes.Code,
    AllowAccessTokensViaBrowser = true,
    RequireConsent = false,
    RefreshTokenUsage = TokenUsage.ReUse,
    RequirePkce = true,
    
    AllowedScopes = {
                      IdentityServerConstants.StandardScopes.OpenId,
                      IdentityServerConstants.StandardScopes.Profile,
                    }
  }
```	
	
	
## Edit the data in the gradle.properties file

    clientId= "myClientId"
    clientSecret= "myClientSecret"
    redirectUri= "myRedirectUri://callback"
    scope= "openid offline_access"
    authorizationEndpointUri= "myAuthorizationEndpointUri"
    tokenEndpointUri= "myTokenEndPointUri"
    registrationEndpointUri= "myRegistrationEndPointUri"
    responseType= "code"




## Add the redirect uri to gradle.app : 


```
android {
    compileSdkVersion 25
    buildToolsVersion "25.0.3"
    defaultConfig {
       ...
        manifestPlaceholders = [
                'appAuthRedirectScheme': 'myRedirectUri'
        ]
    }
}
```

## Add the redirect uri to the RedirectUriReceiverActivity in manifest :


```xml
<activity
	android:name="net.openid.appauth.RedirectUriReceiverActivity"
	android:theme="@style/Theme.AppCompat.NoActionBar">
		<intent-filter>
			<action android:name="android.intent.action.VIEW"/>

			<category android:name="android.intent.category.DEFAULT"/>
			<category android:name="android.intent.category.BROWSABLE"/>

			<data android:scheme="myRedirectUri"/>
		</intent-filter>
</activity>
```
