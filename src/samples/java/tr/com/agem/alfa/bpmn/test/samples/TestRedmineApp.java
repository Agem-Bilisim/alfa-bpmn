package tr.com.agem.alfa.bpmn.test.samples;

import org.apache.log4j.Logger;

import tr.com.agem.alfa.bpmn.utils.AlfaRedmineUtils;

public class TestRedmineApp 
{
	private final static Logger logger = Logger.getLogger(TestRedmineApp.class);

    public static void main( String[] args ) throws InterruptedException
    {
    	
//    	Issue x = AlfaRedmineUtils.getInstance("alfatest").createIssue("test 1 2");
//    	x.setTracker(TrackerFactory.create(1));
    	
//    	Collection<CustomField> x = AlfaRedmineUtils.getInstance("alfatest").getAllCustomFields(0);
//    	logger.info(x);
    	AlfaRedmineUtils.getInstance("rcop-mis").getIssueById(761);
    	    	
    
    }
}