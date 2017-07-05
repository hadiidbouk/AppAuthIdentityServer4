# AppAuth with IdentityServer 4 for android

Android simple using [AppAuth-Android](https://github.com/openid/AppAuth-Android) with [identity server 4](https://github.com/IdentityServer/IdentityServer4)

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
					},

					AllowOfflineAccess = true
				}
        
        
## Edit the data in the gradle.properties

    clientId= "myClientId"
    clientSecret= "myClientSecret"
    redirectUri= "myRedirectUri://callback"
    scope= "openid offline_access"
    authorizationEndpointUri= "myAuthorizationEndpointUri"
    tokenEndpointUri= "myTokenEndPointUri"
    registrationEndpointUri= "myRegistrationEndPointUri"
    responseType= "code"
