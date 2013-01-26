package com.sfeir.githubTrello.json.github;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import com.sfeir.githubTrello.domain.github.PullRequest.Head;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class HeadMixin extends Head {

	@Override
	@JsonProperty("ref")
	public abstract String getName();

	@Override
	@JsonProperty("sha")
	public abstract String getSha();
}