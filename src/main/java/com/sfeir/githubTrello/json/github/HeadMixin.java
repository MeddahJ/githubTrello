package com.sfeir.githubTrello.json.github;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public interface HeadMixin {
	@JsonProperty("ref")
	public String getName();

	@JsonProperty("sha")
	public String getSha();
}