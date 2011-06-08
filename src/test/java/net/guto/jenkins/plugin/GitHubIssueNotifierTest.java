package net.guto.jenkins.plugin;

import junit.framework.TestCase;

public class GitHubIssueNotifierTest extends TestCase {

	private String projectUrls[] = { "http://github.com/gutomaia/github-issue", "https://github.com/gutomaia/github-issue",
			"git://github.com/gutomaia/github-issue.git", "git@github.com:gutomaia/github-issue.git", "http://github.com/gutomaia/hellow_php",
			"https://github.com/gutomaia/hellow_php", "git@github.com:gutomaia/github-issue.git",

	};

	public void testIsProjectUrlValid() {
		GitHubIssueNotifier notifier = new GitHubIssueNotifier();
		for (String projectUrl : projectUrls) {
			assertTrue(notifier.isProjectUrlValid(projectUrl));
		}
	}

	public void testParseProjectUrl() {

	}
}
