package com.example.securingweb.authentication;

import java.util.ArrayList;

import org.openflexo.pamela.annotations.ImplementationClass;
import org.openflexo.pamela.annotations.Import;
import org.openflexo.pamela.annotations.Imports;
import org.openflexo.pamela.annotations.ModelEntity;
import org.openflexo.pamela.securitypatterns.authenticator.annotations.AuthenticationInformation;
import org.openflexo.pamela.securitypatterns.authenticator.annotations.Authenticator;
import org.openflexo.pamela.securitypatterns.authenticator.annotations.RequestAuthentication;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@ModelEntity
@ImplementationClass(CustomAuthenticationProvider.CustomAuthenticationProviderImpl.class)
@Authenticator(patternID = SessionInfo.PATTERN_ID)
@Imports(@Import(SessionInfo.class))
public interface CustomAuthenticationProvider extends AuthenticationProvider {
	String ID = "id";

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException;

	@RequestAuthentication(patternID = SessionInfo.PATTERN_ID)
	int request(@AuthenticationInformation(patternID = SessionInfo.PATTERN_ID, paramID = ID) String id);

	abstract class CustomAuthenticationProviderImpl implements CustomAuthenticationProvider {

		@Override
		public Authentication authenticate(Authentication authentication) throws AuthenticationException {

			System.out.println("On utilise bien le CustomAuthenticationProvider pour " + authentication);
			Thread.dumpStack();

			String name = authentication.getName();
			String password = authentication.getCredentials().toString();

			if (shouldAuthenticateAgainstThirdPartySystem()) {

				// use the credentials
				// and authenticate against the third-party system
				return new UsernamePasswordAuthenticationToken(name, password, new ArrayList<>());
			} else {
				return null;
			}
		}

		private boolean shouldAuthenticateAgainstThirdPartySystem() {
			return true;
		}

		@Override
		public boolean supports(Class<?> authentication) {
			return authentication.equals(UsernamePasswordAuthenticationToken.class);
		}

		/*
		 * @Override public int request(String id) { if (this.check(id)) { return
		 * this.generateFromAuthInfo(id); } return this.getDefaultToken(); }
		 * 
		 * private boolean check(String id) { for (String userID : this.getUsers()) { if
		 * (userID.compareTo(id) == 0) return true; } return false; }
		 * 
		 * @Override public int generateFromAuthInfo(String id) { return id.hashCode();
		 * }
		 */

	}

}