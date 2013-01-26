package com.sfeir.githubTrello.json.github;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import com.sfeir.githubTrello.domain.github.Branch;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class BranchMixin extends Branch {

	@Override
	@JsonProperty("name")
	public abstract String getName();

	@Override
	@JsonProperty("object")
	public abstract Commit getCommit();

	@Override
	@JsonProperty("ref")
	public abstract String getRef();
}