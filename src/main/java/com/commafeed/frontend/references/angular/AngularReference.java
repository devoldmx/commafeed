package com.commafeed.frontend.references.angular;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.resource.UrlResourceReference;

public class AngularReference extends UrlResourceReference {
	private static final long serialVersionUID = 1L;

	public static final AngularReference INSTANCE = new AngularReference();

	private AngularReference() {
		super(
				Url.parse("https://ajax.googleapis.com/ajax/libs/angularjs/1.1.4/angular.min.js"));
	}

	public static void renderHead(final IHeaderResponse response) {
		response.render(JavaScriptHeaderItem.forReference(INSTANCE));
	}
}