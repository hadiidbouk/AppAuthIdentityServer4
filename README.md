# AppAuth with IdentityServer 4 for android

## You can now use the AppAuth library with webview check [this](https://github.com/hadiidbouk/AppAuthWebView-Android) 
Android sample using [AppAuth-Android](https://github.com/openid/AppAuth-Android) with [IdentityServer4](https://github.com/IdentityServer/IdentityServer4)

I will add soon :
* The registration part.
* The logout from the android phone and from the server side.
## Identity Server on the backend 

        new Client
				{
					ClientId = "myClientId",
					ClientName = "myClientName",
					AllowedGrantTypes = GrantTypes.CodeAndClientCredentials,
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

					AllowOfflineAccess = true
				}
        
        
## Edit the data in the gradle.properties file

    clientId= "myClientId"
    clientSecret= "myClientSecret"
    redirectUri= "myRedirectUri://callback"
    scope= "openid offline_access"
    authorizationEndpointUri= "myAuthorizationEndpointUri"
    tokenEndpointUri= "myTokenEndPointUri"
    registrationEndpointUri= "myRegistrationEndPointUri"
    responseType= "code"
