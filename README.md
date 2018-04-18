# AppAuth with IdentityServer 4 for android

## You can now use the AppAuth library with **WebView** check [this](https://github.com/hadiidbouk/AppAuthWebView-Android) 
Android sample using [AppAuth-Android](https://github.com/openid/AppAuth-Android) with [IdentityServer4](https://github.com/IdentityServer/IdentityServer4)

## Identity Server on the backend 
```csharp
new Client
{
    	ClientId = _configuration["Clients:Mobile:Id"],
 	ClientName = "Mobile Application",
        ClientSecrets = { new Secret(_configuration["Clients:Mobile:Secret"].Sha256())},
        RedirectUris = {_configuration["Clients:Mobile:RedirectUrl"]},
        PostLogoutRedirectUris = { _configuration["Clients:Mobile:PostLogoutRedirectUrl"] },
	
        AllowedGrantTypes = GrantTypes.Code,
        AllowAccessTokensViaBrowser = true,
        RequireConsent = false,

        AllowOfflineAccess = true,
        RefreshTokenUsage = TokenUsage.ReUse,

        AllowedScopes = {
             IdentityServerConstants.StandardScopes.OpenId,
             IdentityServerConstants.StandardScopes.Profile
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
                'appAuthRedirectScheme': 'myRedirectUri' //Without ://callback
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
