package net.guto.jenkins.plugin;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.PostMethod;

public class GitHubIssueHelper {

	// TODO: UGLY UGLY UGLY, please make it preatty
	public void openIssue(Issue issue) {
		HttpClient client = new HttpClient();
		client.getState().setAuthenticationPreemptive(true);
		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("gutomaia", "ehehehehhe... :D");

		client.getState().setCredentials(AuthScope.ANY, credentials);
		PostMethod post = new PostMethod("https://api.github.com/repos/"
				+ issue.user + "/" + issue.project + "/issues");
		post.setRequestHeader("Content-Type", "application/json");
		post.setRequestBody(issue.toJson());

		try {
			System.out.println(post.getURI());
			int returnCode = client.executeMethod(post);
						System.out.println(returnCode);
			for(Header h :post.getRequestHeaders()){
				System.out.println(h.getName() + ": " + h.getValue());
			}
			//System.out.println()
			System.out.println(post.getResponseBodyAsString());
		} catch (Exception e) {
		}
	}

	public static void main(String[] args) {
		Issue issue = new Issue();
		issue.user = "gUTOnET";
		issue.project = "test-git-issue";
		issue.title = "teste do plugin do jenkins";
		issue.body = "teste do plugin do jenkins epa";
		issue.assignee = "gUTOnET";
		System.out.println(issue.toJson());
		new GitHubIssueHelper().openIssue(issue);
	}

}