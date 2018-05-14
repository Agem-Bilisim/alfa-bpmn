package tr.com.agem.alfa.bpmn.servicetask;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.log4j.Logger;

import tr.com.agem.alfa.bpmn.AlfaBpmnFormService;

/**
 * The class is a ready-to-use service task which redirects the execution to the specified 
 * task (redirectTo) if the the given condition (redirectCondition) is true   
 * See :AlfaServiceTaskBpmnParseHandler class for the implementation
 *  
 * @author <a href="mailto:ali.ozeren@agem.com.tr">Ali Ozkan Ozeren</a>
 *
 */
public class AlfaRedirectServiceTask implements JavaDelegate 
{
	
	private final static Logger logger = Logger.getLogger(AlfaBpmnFormService.class);

	private Expression redirectTo;

	private Expression redirectCondition;


	@Override
	public void execute(DelegateExecution execution) 
	{
		Boolean redirectConditionLocal = (Boolean) redirectCondition.getValue(execution);

		if (redirectConditionLocal) {
			String redirectToLocal = (String) redirectTo.getValue(execution);
			logger.info("Redirecting the process to " + redirectToLocal );
		}
	}

}
