package com.ank.authservice;

import com.ank.authservice.security.repositories.JpaRegisteredClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;

import java.util.UUID;

@SpringBootTest
class AuthServiceApplicationTests {

	@Autowired
	JpaRegisteredClientRepository jpaRegisteredClientRepository;
	@Test
	void contextLoads() {
	}

//	@Test
//	void testCase(){
//
//		RegisteredClient oidcClient = RegisteredClient.withId(UUID.randomUUID().toString())
//				.clientId("oidc-client")
//				.clientSecret("$2a$12$e8dx1/jN8cajLbFHvlH3SuFXM3cCQI67GnWysrPBqBJYKUAQe59JC")
//				.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//				.authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
//				.redirectUri("https://oauth.pstmn.io/v1/callback")
//				.postLogoutRedirectUri("http://127.0.0.1:8080/")
//				.scope("ADMIN")
////				.scope(OidcScopes.OPENID)
////				.scope(OidcScopes.PROFILE)
//				.clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
//				.build();
//
//		jpaRegisteredClientRepository.save(oidcClient);
//	}
	}
