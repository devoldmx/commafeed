package com.commafeed.frontend.pages;

import java.net.URISyntaxException;

import javax.inject.Inject;

import org.apache.http.client.utils.URIBuilder;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.flow.RedirectToUrlException;
import org.jboss.logging.Logger;

import com.commafeed.backend.model.ApplicationSettings;
import com.commafeed.backend.services.ApplicationSettingsService;

@SuppressWarnings("serial")
public class GoogleImportRedirectPage extends WebPage {

	private static Logger log = Logger
			.getLogger(GoogleImportRedirectPage.class);

	private static final String SCOPE = "https://www.google.com/reader/subscriptions/export";
	private static final String AUTH_URL = "https://accounts.google.com/o/oauth2/auth";

	@Inject
	ApplicationSettingsService applicationSettingsService;

	public GoogleImportRedirectPage() {

		ApplicationSettings settings = applicationSettingsService.get();

		String clientId = settings.getGoogleClientId();

		String redirectUri = GoogleImportCallbackPage.getCallbackUrl();
		try {
			URIBuilder builder = new URIBuilder(AUTH_URL);

			builder.addParameter("redirect_uri", redirectUri);
			builder.addParameter("response_type", "code");
			builder.addParameter("scope", SCOPE);
			builder.addParameter("approval_prompt", "force");
			builder.addParameter("client_id", clientId);

			throw new RedirectToUrlException(builder.build().toString());
		} catch (URISyntaxException e) {
			log.error(e.getMessage(), e);
		}

	}
}
