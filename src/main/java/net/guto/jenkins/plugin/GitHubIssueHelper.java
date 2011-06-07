package net.guto.jenkins.plugin;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.codec.binary.Base64;

public class GitHubIssueHelper {

	// TODO: UGLY UGLY UGLY, please make it preatty
	public static void openIssue(Issue issue, String username, String password) throws IOException {
		String apiPage = "https://api.github.com/repos/" + issue.user + "/" + issue.project + "/issues";

		String authString = username + ":" + password;
		byte[] authEncoded = Base64.encodeBase64(authString.getBytes());
		URL url = new URL(apiPage);
		URLConnection con = url.openConnection();

		con.setRequestProperty("Authorization", "Basic " + new String(authEncoded));
		con.setRequestProperty("Content-Type", "application/json");

		// it's a post;
		con.setDoOutput(true);

		OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
		wr.write(issue.toJson());
		wr.flush();

		InputStream is = con.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);

		int numCharsRead;
		char[] charArray = new char[1024];
		StringBuffer sb = new StringBuffer();
		while ((numCharsRead = isr.read(charArray)) > 0) {
			sb.append(charArray, 0, numCharsRead);
		}
		String result = sb.toString();
		System.out.println("*** BEGIN ***");
		System.out.println(result);
		System.out.println("*** END ***");
	}

	public static void main(String[] args) throws IOException {
		Issue issue = new Issue();
		issue.user = "gUTOnET";
		issue.project = "test-git-issue";
		issue.title = "teste do plugin do jenkins 2";
		issue.body = "teste do plugin do jenkins epa 2";
		issue.assignee = "gUTOnET";
		new GitHubIssueHelper().openIssue(issue, args[1], args[2]);
	}

}