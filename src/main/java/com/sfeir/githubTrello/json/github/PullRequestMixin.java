package com.sfeir.githubTrello.json.github;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import com.sfeir.githubTrello.domain.github.PullRequest;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class PullRequestMixin extends PullRequest {

	@Override
	@JsonProperty("html_url")
	public abstract String getHtmlUrl();

	@Override
	@JsonProperty("number")
	public abstract String getId();

	@Override
	@JsonProperty("title")
	public abstract String getTitle();

	@Override
	@JsonProperty("body")
	public abstract String getDescription();

	@Override
	@JsonProperty("head")
	public abstract Head getHead();

	@Override
	@JsonProperty("state")
	public abstract String getState();

	@Override
	@JsonProperty("state")
	public abstract void setState(String state);


}