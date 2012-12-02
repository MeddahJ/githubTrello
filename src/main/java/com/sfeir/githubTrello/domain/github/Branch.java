package com.sfeir.githubTrello.domain.github;


public class Branch {

	public String getName() {
		return ref.replace("refs/heads/", "");
	}

	public Commit getCommit() {
		return commit;
	}

	public String getRef() {
		return ref;
	}

	public boolean exists() {
		return ref != null && commit != null;
	}

	public Branch() {}

	private Commit commit;
	private String ref;


	public class Commit {
		public String getSha() {
			return sha;
		}

		public String getUrl() {
			return url;
		}

		public Commit() {}

		private String sha;
		private String url;
	}
}