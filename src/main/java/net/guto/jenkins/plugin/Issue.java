package net.guto.jenkins.plugin;

public class Issue {
	
	public String user;
	public String project;
	public String title;
	public String body;
	public String assignee;
	public String milestone;
	public String labes;

	public String toJson() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"title\":\"" + title + "\",");
		sb.append("\"body\":\"" + body + "\",");
		sb.append("\"assignee\":\"" + assignee + "\"");
		sb.append("}");
		return sb.toString();
	}

}
