package tr.com.agem.alfa.bpmn.listener;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.activiti.bpmn.model.FieldExtension;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.bpmn.model.ServiceTask;
import org.activiti.engine.impl.bpmn.parser.BpmnParse;
import org.activiti.engine.impl.bpmn.parser.handler.ServiceTaskParseHandler;
import org.apache.commons.lang3.StringUtils;


/**
 * 
 * if the service task is the implementation of AlfaRedirectServiceTask;
 * 	 Parser adds a flow to the the task with the id "redirectTo" (service task field)
 * 	 with the given condition (if any) with the field "redirectCondition" from the service task
 * 
 * @author <a href="mailto:ali.ozeren@agem.com.tr">Ali Ozkan Ozeren</a>
 *
 */
public class AlfaServiceTaskBpmnParseHandler extends ServiceTaskParseHandler {

	protected String serviceTaskName = "tr.com.agem.alfa.bpmn.servicetask.AlfaRedirectServiceTask";

	protected String taskFieldName = "redirectTo";

	protected String conditionFieldName = "redirectCondition";


	private static String rx = "(\\$\\{([^}]+)\\})";
	private static Pattern p = Pattern.compile(rx);

	private static String notCondition(String condition, String currentCondition) 
	{
		StringBuffer sb = new StringBuffer();
		Matcher m = p.matcher(condition);
		sb.append("${");
		if (m.find()) {
			sb.append("!(").append(m.group(2)).append(")");
		}

		if (currentCondition != null) {
			m = p.matcher(currentCondition);
			if (m.find()) {
				sb.append(" && (").append(m.group(2)).append(")");
			}
		}
		return sb.append("}").toString();
	}

	protected void executeParse(BpmnParse bpmnParse, ServiceTask serviceTask) 
	{

		assert( this.serviceTaskName != null && this.taskFieldName != null && this.conditionFieldName != null );

		if (this.serviceTaskName.equals(serviceTask.getImplementation())) {

			List<FieldExtension> fext = serviceTask.getFieldExtensions();

			String redirectTo = null; 
			String redirectCondition = null;

			for ( FieldExtension fe: fext) {
				if (this.taskFieldName.equals(fe.getFieldName())) {
					redirectTo = fe.getStringValue();
				}
				if (this.conditionFieldName.equals(fe.getFieldName())) {
					redirectCondition = fe.getExpression();
				}
			}

			if (redirectTo != null) {

				String transitionId = serviceTask.getId() + "_" + redirectTo;

				// check if the transition is created before 
				if (serviceTask.getOutgoingFlows() == null || !serviceTask.getOutgoingFlows().contains(transitionId)) {

					// create the transition to the task
					SequenceFlow transition= new SequenceFlow();
					transition.setId(transitionId);
					transition.setSourceFlowElement(serviceTask);
					transition.setTargetFlowElement(bpmnParse.getBpmnModel().getFlowElement(redirectTo));
					// set the transition condition if given 
					if (redirectCondition != null) {
						transition.setConditionExpression(redirectCondition);
						for ( SequenceFlow fe : serviceTask.getOutgoingFlows()) {
							fe.setConditionExpression(notCondition(redirectCondition,StringUtils.trimToNull(fe.getConditionExpression())));
						} 
					}
					serviceTask.getOutgoingFlows().add(transition);
				}

			}
		}

	}


	public void setServiceTaskName(String serviceTaskName) 
	{
		this.serviceTaskName = StringUtils.trimToNull(serviceTaskName);
	}


	public void setTaskFieldName(String taskFieldName) 
	{
		this.taskFieldName = StringUtils.trimToNull(taskFieldName);
	}


	public void setConditionFieldName(String conditionFieldName) 
	{
		this.conditionFieldName = StringUtils.trimToNull(conditionFieldName);
	}

}