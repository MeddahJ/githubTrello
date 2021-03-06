package com.sfeir.githubTrello.wrapper;

import java.util.Map;

import javax.ws.rs.core.Response.Status.Family;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource.Builder;

import static com.sfeir.githubTrello.wrapper.Json.*;
import static java.lang.String.*;
import static javax.ws.rs.core.MediaType.*;
import static org.apache.commons.lang3.StringUtils.*;

public class RestClient {

	public RestClient(String apiUrl, String authenticationQuery) {
		this.apiUrl = apiUrl;
		this.authenticationQuery = authenticationQuery;//TODO authentication query format
	}

	public RestClientUrl url(String pathFormat, String... pathParameters) {//FIXME: Clean
		String url = apiUrl
				+ format(pathFormat.replace(apiUrl, ""), (Object[]) pathParameters)
				+ (containsNone(pathFormat, "?") ? "?" : "")
				+ authenticationQuery;
		return new RestClientUrl(url);
	}

	public static class RestClientUrl {
		public String get() {
			return getResponseEntity(initRequest().get(ClientResponse.class));
		}

		public String post(Map<String, ?> input) {
			return getResponseEntity(initRequest().post(ClientResponse.class, json.toString(input)));
		}

		public String put() {
			return getResponseEntity(initRequest().put(ClientResponse.class));
		}

		public String delete() {
			initRequest().delete(ClientResponse.class);
			return "";
		}

		private Builder initRequest() {
			return Client.create().resource(url).accept(APPLICATION_JSON);
		}

		private String getResponseEntity(ClientResponse clientResponse) {
			if (clientResponse.getClientResponseStatus() == null ||
					clientResponse.getClientResponseStatus().getFamily() != Family.SUCCESSFUL) {
				logger.warn(format("Failed : HTTP error code : %s\nUrl: %s", clientResponse.getStatus(), url), new Exception());
				return "";
			}
			return clientResponse.getEntity(String.class);
		}

		private RestClientUrl(String url) {
			this.url = url;
		}

		private String url;
	}

	private final String apiUrl;
	private final String authenticationQuery;

	private static final Log logger = LogFactory.getLog(RestClient.class);

	private static Json json = jsonBuilder().build();
}
