package com.example.securingweb.authentication;

import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;
import static org.springframework.web.context.WebApplicationContext.SCOPE_SESSION;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpSession;

import org.openflexo.pamela.annotations.Getter;
import org.openflexo.pamela.annotations.ImplementationClass;
import org.openflexo.pamela.annotations.ModelEntity;
import org.openflexo.pamela.annotations.Setter;
import org.openflexo.pamela.securitypatterns.authenticator.annotations.AuthenticateMethod;
import org.openflexo.pamela.securitypatterns.authenticator.annotations.AuthenticationInformation;
import org.openflexo.pamela.securitypatterns.authenticator.annotations.AuthenticatorGetter;
import org.openflexo.pamela.securitypatterns.authenticator.annotations.AuthenticatorSubject;
import org.openflexo.pamela.securitypatterns.authenticator.annotations.ProofOfIdentitySetter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@Scope(value = SCOPE_SESSION, proxyMode = TARGET_CLASS)
@ModelEntity
@ImplementationClass(SessionInfo.SessionInfoImpl.class)
@AuthenticatorSubject(patternID = SessionInfo.PATTERN_ID)
public interface SessionInfo {

	String SESSION_INFO = "SESSION_INFO";
	String PATTERN_ID = "AuthenticatorPattern";
	String AUTH_INFO = "auth_info1";
	String MANAGER = "manager";
	String ID_PROOF = "id_proof";

	@Getter(value = AUTH_INFO, defaultValue = AUTH_INFO)
	@AuthenticationInformation(patternID = PATTERN_ID, paramID = CustomAuthenticationProvider.ID)
	String getAuthInfo();

	@Setter(AUTH_INFO)
	void setAuthInfo(String val);

	@Getter(value = ID_PROOF, defaultValue = "-1")
	int getIDProof();

	@Setter(ID_PROOF)
	@ProofOfIdentitySetter(patternID = PATTERN_ID)
	void setIdProof(int val);

	@Getter(MANAGER)
	@AuthenticatorGetter(patternID = PATTERN_ID)
	CustomAuthenticationProvider getManager();

	@Setter(MANAGER)
	void setManager(CustomAuthenticationProvider val);

	@AuthenticateMethod(patternID = PATTERN_ID)
	void authenticate();

	@Override
	public String toString();

	abstract class SessionInfoImpl implements SessionInfo {

		private final String created;

		public SessionInfoImpl() {
			this.created = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
			System.out.println("On cree une nouvelle SessionInfo " + this);
			Thread.dumpStack();
		}

		@Override
		public String toString() {
			return "SessionInfoImpl created on " + created;
		}

	}

	public static SessionInfo getCurrentSessionInfo() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(true);
		return (SessionInfo) session.getAttribute(SessionInfo.SESSION_INFO);
	}

}
