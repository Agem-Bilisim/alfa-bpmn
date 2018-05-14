package tr.com.agem.alfa.bpmn.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.log4j.Logger;

import com.taskadapter.redmineapi.Include;
import com.taskadapter.redmineapi.IssueManager;
import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.RedmineManagerFactory;
import com.taskadapter.redmineapi.bean.Attachment;
import com.taskadapter.redmineapi.bean.CustomField;
import com.taskadapter.redmineapi.bean.CustomFieldDefinition;
import com.taskadapter.redmineapi.bean.CustomFieldFactory;
import com.taskadapter.redmineapi.bean.Issue;
import com.taskadapter.redmineapi.bean.IssueFactory;
import com.taskadapter.redmineapi.bean.Journal;
import com.taskadapter.redmineapi.bean.JournalDetail;
import com.taskadapter.redmineapi.bean.Membership;
import com.taskadapter.redmineapi.bean.Project;
import com.taskadapter.redmineapi.bean.Tracker;
import com.taskadapter.redmineapi.bean.User;

public class AlfaRedmineUtils 
{
	
	private final static Logger logger = Logger.getLogger(AlfaRedmineUtils.class);

	private static final String URI_KEY = "repository.uri";
	private static final String API_ACCESS_KEY = "repository.apiAccessKey";
	private static final String PROJECT_KEY = "repository.projectKey";
	private static final String FILE_NAME = "redmine.properties";
	
	private static final char PROJECTS_DEFINITION_DELIMITER = ',';
	
	public static class JOURNAL_DETAILS 
	{
		public static final int STATUS_NAME = 1;
		public static final int TRACKER_NAME = 2;
		public static final int USER_FULLNAME = 3;
		public static final int PRIORTY = 4;
	}
	
	
	private static Map<String,AlfaRedmineUtils> instances;
	private static Map<String, String[]> projects; 
	

	private RedmineManager redmineManager;
	private Project project;
	private String projectKey;
	
	private AlfaRedmineUtils()
	{
	}
	
	public static AlfaRedmineUtils getInstance(String projectKey) 
	{
		AlfaRedmineUtils instance;
		try {
			if (instances == null) {
				instances = new HashMap<String, AlfaRedmineUtils>();
				AlfaRedmineUtils.readProperties();
			}
			
			instance = instances.get(projectKey);
			if ( instance == null) {
				String defs[]= projects.get(projectKey);
				
				if (defs == null) {
					throw new AlfaRedmineException("No uri and api access key are defined for the project with key :" + projectKey);
				}
				
				instance = new AlfaRedmineUtils();
				instance.redmineManager= RedmineManagerFactory.createWithApiKey(defs[0], defs[1]);
				instance.project= instance.redmineManager.getProjectManager().getProjectByKey(projectKey);
				instance.projectKey = projectKey;
				
				instances.put(projectKey, instance);
				
				
			}
		} catch (Exception e) {
			Utils.getInstance().logError(logger,e);
			throw new AlfaRedmineException("Error in creating AlfaRedmineUtils Object...", e);
		}
		
		return instance;
	}

	
	/**
	 * Attaches a file to an issue
	 * @param fileName
	 * @param content
	 * @return
	 */
	public Attachment attachFile(String fileName, byte[] content) 
	{
		Attachment attachment = null;
	    try {
	    	attachment = redmineManager.getAttachmentManager().uploadAttachment(fileName, "", content);
	    } catch (RedmineException ex) {
	    	throw new AlfaRedmineException("Can't attach file to a Redmine issue", ex);
	    } catch (IOException ex) {
	    	throw new AlfaRedmineException("Can't attach file to a Redmine issue", ex);
	    }
	    return attachment;
	}
	
	/**
	 * Read redmine connection properties from resource
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws RedmineException
	 */
	private static void readProperties() throws IOException,FileNotFoundException, RedmineException
	{
		
		Parameters params = new Parameters();
		FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
		    new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
		    	.configure( params.fileBased()
		    			.setFileName(AlfaRedmineUtils.FILE_NAME)
		    			.setListDelimiterHandler(new DefaultListDelimiterHandler(AlfaRedmineUtils.PROJECTS_DEFINITION_DELIMITER)));
		try
		{
		    Configuration config = builder.getConfiguration();

			String project[] = config.getStringArray(PROJECT_KEY);		    
			String uri[] = config.getStringArray(URI_KEY);
			String apiAccessKey[] = config.getStringArray(API_ACCESS_KEY);
			
			if (project == null || uri == null || apiAccessKey == null || project.length != uri.length || project.length != apiAccessKey.length) {
				throw new AlfaRedmineException("No projects have been defined for redmine entegration");
			}
			
			AlfaRedmineUtils.projects= new HashMap<String, String[]>();
			for (int i=0; i < project.length; i++) {
				projects.put(project[i], new String[]{uri[i] , apiAccessKey[i]});
			}
			
		}
		catch(ConfigurationException e)
		{
			throw new AlfaRedmineException("loading of the configuration file failed", e);
		}
	}
	
	
	/**
	 * Sent issue to redmine using issue manager
	 * @param issue
	 * @return
	 */
	public Issue sentIssue(Issue issue) 
	{
		IssueManager issueManager = redmineManager.getIssueManager();
		try {
			return issueManager.createIssue(issue);

		} catch (RedmineException e) {
			Utils.getInstance().logError(logger,e);
			throw new AlfaRedmineException("Error in sending issue...", e);
		}
	}

	/**
	 * Creates an issue for redmine
	 * @param issue
	 * @return
	 */
	public Issue createIssue(String subject) 
	{
		try {
			Issue issue = IssueFactory.create( project.getId(), subject);
			return issue;

		} catch (Exception e) {
			Utils.getInstance().logError(logger,e);
			throw new AlfaRedmineException("Error in creating issue...", e);
		}
	}
	
	/**
	 * Deletes the given issue from redmine
	 * @param issueId
	 */
	public void deleteIssue(String issueId)
	{
		IssueManager issueManager = redmineManager.getIssueManager();
		
		try{
			issueManager.deleteIssue(new Integer(issueId));
		}catch(RedmineException e){
			Utils.getInstance().logError(logger,e);
			throw new AlfaRedmineException("Error in deleting issue..", e);
		}
	}

	/**
	 * creates custom fields for redmine issue
	 * @param id
	 * @param name
	 * @param value
	 * @return
	 */
	public CustomField createCustomField( int id,String name,String value)
	{
		return CustomFieldFactory.create(id, name, value);
	}
	
	public List<CustomFieldDefinition> getCustomFields() 
	{
		try 
		{
			return redmineManager.getCustomFieldManager().getCustomFieldDefinitions();
		} catch (RedmineException e) 
		{
			e.printStackTrace();
			throw new AlfaRedmineException(e);
		}
	}

	public RedmineManager getRedmineManager() 
	{
		return redmineManager;
	}

	public Collection<Tracker> getTrackers() 
	{
		try 
		{
			return redmineManager.getProjectManager().getProjectByKey(projectKey).getTrackers();
			
		} catch (RedmineException e) 
		{
			e.printStackTrace();
			throw new AlfaRedmineException(e);
		}
	}

	public Collection<CustomField> getAllCustomFields(int id)
	{
		Collection<CustomField> list = null;
		try {
			list = redmineManager.getIssueManager().getIssues(this.projectKey, null,Include.journals, Include.relations, Include.attachments, Include.changesets,Include.watchers).get(id).getCustomFields();
			
			for (CustomField customField : list) {
				customField.setValue("");
			}
		} catch (RedmineException e) {
			Utils.getInstance().logError(logger,e);
			throw new AlfaRedmineException("Error in getting custom fields..", e);
		}
		return list;
	}

	/**
	 * retrieves the issue by ID
	 * @param id
	 * @return
	 */
	public Issue getIssueById(int id) 
	{
		Issue issue = null;
		try {
			issue = redmineManager.getIssueManager().getIssueById(id);
		} catch (RedmineException e) {
			Utils.getInstance().logError(logger,e);
		}
		return issue;
	}

	/**
	 * gets the journal of an issue by ID (what has happened to the issue)
	 * @param issueId
	 * @return
	 */
	public Collection<Journal> getJournals(int issueId)
	{
		Collection<Journal> list = null;
		try {
			Issue issue = redmineManager.getIssueManager().getIssueById(issueId,Include.journals);
			list = issue.getJournals();
		} catch (RedmineException e) {
			Utils.getInstance().logError(logger,e);
			throw new AlfaRedmineException("Error in getting journals..",e);
		}
		return list;
	}
	
	/**
	 * gets the assigned list by projectKey
	 * 
	 * @return
	 * 
	 */
	public List<User> getAssignedList() {
		List<User> listUser = new ArrayList<>();

		try {
			List<Membership> member = redmineManager.getMembershipManager().getMemberships(projectKey);

			for (Membership membership : member) {

				if (membership.getUserId() != null)
					listUser.add(redmineManager.getUserManager().getUserById(membership.getUserId()));
				else if (membership.getGroupId() != null) {
					User user = new User(membership.getGroupId());
					user.setFirstName(membership.getGroupName());
					user.setLastName("");
					user.setFullName(membership.getGroupName());
					listUser.add(user);
				}
			}

		} catch (Exception e) {
			Utils.getInstance().logError(logger, e);
			throw new AlfaRedmineException("Error in assigned list", e);
		}

		return listUser;
	}
	/**
	 * Gets the journal details 
	 * @param issueId
	 * @param journalId
	 * @return
	 */
	public List<JournalDetail> getJournalDetails(int issueId,int journalId) 
	{

		List<JournalDetail> list = null;
		try {
				Issue issue = redmineManager.getIssueManager().getIssueById(issueId,Include.journals);
				for (Journal journal : issue.getJournals()) {
					if(journal.getId() == journalId) {
						list = journal.getDetails();
					}
				}
		} catch (RedmineException e) {
			Utils.getInstance().logError(logger,e);
			throw new AlfaRedmineException("Error in getting journal details..",e);
		}
		return list;
	}
	
	
	/**
	 * @param index
	 * @param detail
	 * 		 any values of AlfaRedmineUtils.JOURNAL_DETAILS
	 * @return
	 */
	public String getJournalDetailsByIndexId(int index,int detail)
	{
		try {
			switch (detail) {
			
			case AlfaRedmineUtils.JOURNAL_DETAILS.STATUS_NAME: return redmineManager.getIssueManager().getStatuses().get(index).getName(); 
			case AlfaRedmineUtils.JOURNAL_DETAILS.TRACKER_NAME: return redmineManager.getIssueManager().getTrackers().get(index).getName(); 
			case AlfaRedmineUtils.JOURNAL_DETAILS.USER_FULLNAME: return redmineManager.getUserManager().getUserById(index+1).getFullName(); 
			case AlfaRedmineUtils.JOURNAL_DETAILS.PRIORTY: return redmineManager.getIssueManager().getIssuePriorities().get(index).getName(); 
			
			default : return "";
			}
		} catch (RedmineException e) {
			Utils.getInstance().logError(logger,e);
			throw new AlfaRedmineException("Error in getting journals..",e);
		}
	}
}
