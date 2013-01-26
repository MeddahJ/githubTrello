package com.sfeir.githubTrello.domain.github;

import static org.apache.commons.lang3.StringUtils.*;

public class PullRequest {

	public String getHtmlUrl() {
		return htmlUrl;
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Head getHead() {
		return head;
	}

	public boolean isOpen() {
		return "open".equals(state);
	}

	public boolean isClosed() {
		return "closed".equals(state);
	}

	public boolean isValid() {
		return isNotEmpty(id) && isNotEmpty(title) && head != null;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		if (!"open".equals(state) && !"closed".equals(state)) {
			throw new IllegalArgumentException("State must be 'open' or 'closed': " + state);
		}
		this.state = state;
	}

	private String title;
	private String description;
	private String htmlUrl;
	private String state;
	private String id;
	private Head head;

	public static class Head {

		public String getName() {
			return name;
		}

		public String getSha() {
			return sha;
		}

		private String name;
		private String sha;
	}
}
