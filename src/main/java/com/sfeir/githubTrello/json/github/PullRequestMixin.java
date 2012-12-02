package com.sfeir.githubTrello.json.github;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import com.sfeir.githubTrello.domain.github.PullRequest.Head;

@JsonIgnoreProperties(ignoreUnknown = true)
public interface PullRequestMixin {

	@JsonProperty("html_url")
	public String getHtmlUrl();

	@JsonProperty("number")
	public String getId();

	@JsonProperty("title")
	public String getTitle();

	@JsonProperty("body")
	public String getDescription();

	public Head getHead();

	@JsonProperty("state")
	public String getState();

	@JsonProperty("state")
	public void setState(String state);


}