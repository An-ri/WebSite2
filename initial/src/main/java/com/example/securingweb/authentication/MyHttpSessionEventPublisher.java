package com.example.securingweb.authentication;

import javax.servlet.http.HttpSessionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class MyHttpSessionEventPublisher extends HttpSessionEventPublisher {

	@Autowired
	private AuthManagerService authManagerService;

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		super.sessionCreated(event);
		System.out.println("Tiens on vient de creer la session " + event.getSession());

		SessionInfo sessionInfo = authManagerService.makeNewSessionInfo();
		System.out.println("New sessionInfo : " + sessionInfo);

		event.getSession().setAttribute(SessionInfo.SESSION_INFO, sessionInfo);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		super.sessionDestroyed(event);
		System.out.println("Tiens on vient de detruire la session " + event.getSession());
	}
}
