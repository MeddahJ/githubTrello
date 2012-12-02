package com.sfeir.githubTrello.json.github;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import com.sfeir.githubTrello.domain.github.Branch.Commit;

@JsonIgnoreProperties(ignoreUnknown = true)
public interface BranchMixin {

	@JsonProperty("name")
	public String getName();

	@JsonProperty("object")
	public Commit getCommit();

	@JsonProperty("ref")
	public String getRef();
}