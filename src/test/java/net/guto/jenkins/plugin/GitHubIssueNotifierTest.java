package net.guto.jenkins.plugin;

import junit.framework.TestCase;

public class GitHubIssueNotifierTest extends TestCase {

	private String projectUrls[] = { "http://github.com/gutomaia/github-issue", "https://github.com/gutomaia/github-issue",
			"git://github.com/gutomaia/github-issue.git", "git@github.com:gutomaia/github-issue.git", "http://github.com/gutomaia/hellow_php",
			"https://github.com/gutomaia/hellow_php", "git@github.com:gutomaia/github-issue.git",
	};

	public GitHubIssueNotifierTest() {
	}

	public void testIsProjectUrlValid() {
		for (String projectUrl : projectUrls) {
			assertTrue(GitHubIssueNotifier.isProjectUrlValid(projectUrl));
		}
	}

	public void testParseProjectUrl() {
		String urls[][] = new String[5][3];
		urls[0] = new String[] { "http://github.com/gutomaia/github-issue", "gutomaia", "github-issue" };
		urls[1] = new String[] { "https://github.com/gutomaia/github-issue", "gutomaia", "github-issue" };
		urls[2] = new String[] { "git@github.com:gutomaia/github-issue.git", "gutomaia", "github-issue" };
		urls[3] = new String[] { "git@github.com:gutomaia/github-issue", "gutomaia", "github-issue" };
		urls[4] = new String[] { "git://github.com/gutomaia/github-issue.git", "gutomaia", "github-issue" };

		for (String[] expected : urls) {
			assertEquals(GitHubIssueNotifier.parseGithubUsername(expected[0]), expected[1]);
			assertEquals(GitHubIssueNotifier.parseGithubProjectName(expected[0]), expected[2]);
		}
	}
}
