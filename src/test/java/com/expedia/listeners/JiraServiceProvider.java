package com.expedia.listeners;

import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.Field;
import net.rcarz.jiraclient.Issue;
import net.rcarz.jiraclient.Issue.FluentCreate;
import net.rcarz.jiraclient.JiraClient;
import net.rcarz.jiraclient.JiraException;

public class JiraServiceProvider {

	public JiraClient jira;
	public String project;

	public JiraServiceProvider(String jiraUrl, String userName, String password, String project) {
		BasicCredentials creds = new BasicCredentials(userName, password);
		jira = new JiraClient(jiraUrl, creds);
		this.project = project;
	}

	public void createJiraTicket(String issueType, String summary, String description, String reporter) {
		try {
			FluentCreate fluentCreate = jira.createIssue(project, issueType);
			fluentCreate.field(Field.DESCRIPTION, description);
			fluentCreate.field(Field.SUMMARY, summary);
			Issue issue = fluentCreate.execute();
			System.out.println("New JIRA Issue created");
		} catch (JiraException e) {
			e.printStackTrace();
		}

	}

}
