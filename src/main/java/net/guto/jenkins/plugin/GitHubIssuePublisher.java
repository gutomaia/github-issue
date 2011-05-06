package net.guto.jenkins.plugin;

import hudson.Extension;
import hudson.Launcher;
import hudson.model.BuildListener;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.BuildStepMonitor;
import hudson.tasks.Publisher;
import hudson.tasks.Recorder;
import hudson.tasks.test.AbstractTestResultAction;
import hudson.util.FormValidation;

import java.io.IOException;
import java.util.logging.Logger;

import net.sf.json.JSONObject;

import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;
import org.kohsuke.stapler.StaplerRequest;

public class GitHubIssuePublisher extends Recorder {

	private static final Logger LOGGER = Logger
			.getLogger(GitHubIssuePublisher.class.getName());

	String githubProjectUrl;

	@DataBoundConstructor
	public GitHubIssuePublisher(String githubProjectUrl) {
		LOGGER.info("Construtor: " + githubProjectUrl);

		this.githubProjectUrl = githubProjectUrl;
	}

	public String getGithubProjectUrl() {
		return githubProjectUrl;
	}

	public BuildStepMonitor getRequiredMonitorService() {
		return BuildStepMonitor.BUILD;
	}

	@Override
	public boolean perform(AbstractBuild<?, ?> build, Launcher launcher,
			BuildListener listener) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		AbstractTestResultAction<AbstractTestResultAction> testResultAction = build
				.getTestResultAction();
		if (testResultAction.getFailCount() > 0) {
			testResultAction.getFailedTests();

		}
		LOGGER.info("passou pelo perform");
		return true;
	}

	@Extension
	public static final DescriptorImpl DESCRIPTOR = new DescriptorImpl();

	public static final class DescriptorImpl extends
			BuildStepDescriptor<Publisher> {

		String token;

		DescriptorImpl() {
			super(GitHubIssuePublisher.class);
		}

		@Override
		public String getDisplayName() {
			return "GitHub Issue";
		}

		@Override
		public boolean isApplicable(Class<? extends AbstractProject> jobType) {
			return false;
		}

		@Override
		public boolean configure(StaplerRequest req, JSONObject json)
				throws hudson.model.Descriptor.FormException {
			token = json.getString("token");
			LOGGER.info("Saving Github token: " + token);
			save();
			return super.configure(req, json);
		}

		FormValidation doGitHubProjectUrlCheck(
				@QueryParameter final String githubProjectUrl)
				throws IOException {
			return FormValidation.ok();
		}

		String githubToken;

		public void setGithubToken(String token) {
			this.githubToken = token;
		}

		public String getGithubToken() {
			return githubToken;
		}

	}

}
