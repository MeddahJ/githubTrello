package com.sfeir.githubTrello.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sfeir.githubTrello.domain.github.Branch;
import com.sfeir.githubTrello.domain.github.Branch.Commit;
import com.sfeir.githubTrello.domain.github.PullRequest;
import com.sfeir.githubTrello.domain.github.PullRequest.Head;
import com.sfeir.githubTrello.domain.github.Repository;
import com.sfeir.githubTrello.json.github.BranchMixin;
import com.sfeir.githubTrello.json.github.CommitMixin;
import com.sfeir.githubTrello.json.github.HeadMixin;
import com.sfeir.githubTrello.json.github.PullRequestMixin;
import com.sfeir.githubTrello.wrapper.Json;
import com.sfeir.githubTrello.wrapper.RestClient;

import static ch.lambdaj.Lambda.*;
import static com.google.common.collect.ImmutableMap.*;
import static java.lang.String.*;
import static org.apache.commons.lang3.StringUtils.*;
import static org.hamcrest.Matchers.*;

public class GithubService {

	public GithubService(Repository repository, String token) {
		this.restClient = new RestClient(API_URL, format("&access_token=%s", token));
		this.user = repository.getUser();
		this.repositoryName = repository.getName();
		baseBranch = getBranch(repository.getBaseBranchName());
		if (!baseBranch.exists()) {
			logger.warn(format("Originating commit for branch %s not found", repository.getBaseBranchName()));
		}
	}

	public final Branch getBranch(String branchName) {
		String branch = restClient.url("/repos/%s/%s/git/refs/heads/%s", user, repositoryName, branchName).get();
		return json.toObject(branch, Branch.class);
	}

	public final Branch createFeatureBranch(String branchName) {
		Map<String, String> input = of("ref", "refs/heads/" + branchName, "sha", baseBranch.getCommit().getSha());
		String branch = restClient.url("/repos/%s/%s/git/refs", user, repositoryName).post(input);
		if (isNotEmpty(branch)) {
			logger.info(format("Feature branch %s created with output %s", branchName, branch));
		}
		return json.toObject(branch, Branch.class);
	}

	public final PullRequest createPullRequest(String title, String body, Branch branch) {
		String head = format("%s:%s", user, branch.getName());
		Map<String, String> input = of("title", title, "body", body, "head", head, "base", baseBranch.getName());
		String pullRequest = restClient.url("/repos/%s/%s/pulls", user, repositoryName).post(input);
		if (isNotEmpty(pullRequest)) {
			logger.info(format("Pull request for branch %s created, output %s", branch.getName(), pullRequest));
		}
		return json.toObject(pullRequest, PullRequest.class);
	}

	public final PullRequest updatePullRequestDescription(PullRequest pullRequest, String description) {
		String updatedPullRequest = restClient.url("/repos/%s/%s/pulls/%s", user, repositoryName, pullRequest.getId()).post(of("body", description));
		return json.toObject(updatedPullRequest, PullRequest.class);
	}

	public final boolean hasCommitsOnFeatureBranch(Branch branch) {
		return !baseBranch.getCommit().getSha().equals(branch.getCommit().getSha());
	}

	public final boolean hasNoPullRequestForBranch(Branch featureBranch) {
		List<PullRequest> pullRequestsForBranch = select(getOpenedPullRequests(),
				having(on(PullRequest.class).getHead().getName(), equalTo(featureBranch.getName())));
		return pullRequestsForBranch.isEmpty();
	}

	protected final Collection<PullRequest> getOpenedPullRequests() {
		return json.toObjects(restClient.url("/repos/%s/%s/pulls", user, repositoryName).get(), PullRequest.class);
	}

	protected RestClient restClient;
	protected String user;
	protected String repositoryName;
	private Branch baseBranch;

	private static final Log logger = LogFactory.getLog(GithubService.class);
	private static final String API_URL = "https://api.github.com";

	protected static Json json = Json.jsonBuilder()
		.withMixin(Branch.class, BranchMixin.class)
		.withMixin(Commit.class, CommitMixin.class)
		.withMixin(PullRequest.class, PullRequestMixin.class)
		.withMixin(Head.class, HeadMixin.class)
		.build();
}