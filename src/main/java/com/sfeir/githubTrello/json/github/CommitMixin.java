package com.sfeir.githubTrello.json.github;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public interface CommitMixin {

	@JsonProperty("sha")
	public String getSha();

	@JsonProperty("url")
	public String getUrl();
}