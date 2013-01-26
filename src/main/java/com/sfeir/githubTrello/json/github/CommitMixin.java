package com.sfeir.githubTrello.json.github;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import com.sfeir.githubTrello.domain.github.Branch.Commit;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class CommitMixin extends Commit {

	@Override
	@JsonProperty("sha")
	public abstract String getSha();

	@Override
	@JsonProperty("url")
	public abstract String getUrl();
}