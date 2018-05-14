package tr.com.agem.alfa.bpmn.listener;

import java.util.List;

import org.activiti.bpmn.model.ActivitiListener;
import org.activiti.bpmn.model.BaseElement;
import org.activiti.bpmn.model.ImplementationType;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.impl.bpmn.parser.BpmnParse;
import org.activiti.engine.impl.bpmn.parser.handler.AbstractBpmnParseHandler;
import org.apache.log4j.Logger;

import tr.com.agem.alfa.bpmn.AlfaBpmnProcessEngineException;
import tr.com.agem.alfa.bpmn.utils.Utils;

/**
 * 
 * @author <a href="mailto:ali.ozeren@agem.com.tr">Ali Ozkan Ozeren</a>
 *
 */
public class AlfaUserTaskBpmnParseHandler extends AbstractBpmnParseHandler<UserTask> 
{

	private final static Logger logger = Logger.getLogger(AlfaUserTaskBpmnParseHandler.class);
	
	protected List<String> customTaskListeners; 

	public AlfaUserTaskBpmnParseHandler() 
	{
	}

	@Override
	protected Class< ? extends BaseElement> getHandledType()
	{
		return UserTask.class;
	}

	/* (non-Javadoc)
	 * @see org.activiti.engine.impl.bpmn.parser.handler.AbstractBpmnParseHandler#executeParse(org.activiti.engine.impl.bpmn.parser.BpmnParse, org.activiti.bpmn.model.BaseElement)
	 */
	@Override
	protected void executeParse(BpmnParse bpmnParse, UserTask element) 
	{
		 
		if (this.customTaskListeners != null && this.customTaskListeners.size() > 0) {
			
			String taskDefinitionKey = element.getId();
			
			String processDefinitionKey= bpmnParse.getCurrentProcessDefinition().getKey();

			
			for (String taskListenerClass : this.customTaskListeners) {
				
				try {
					
					Object obj= Class.forName(taskListenerClass).newInstance();
					
					if (obj instanceof AlfaAbstractTaskListener) {
						
						AlfaAbstractTaskListener listener = (AlfaAbstractTaskListener) obj;
						
						// if parsed process is not related to task listener, ignore it (empty set means add listener to all processes)
						if (listener.getProcessDefinitionKeys() != null 
								&& listener.getProcessDefinitionKeys().size() > 0 
								&& !listener.getProcessDefinitionKeys().contains(processDefinitionKey)) {
							continue;
						}
						
						// if parsed task is not related to task listener, ignore it (empty set means add listener to all tasks)
						if (listener.getTaskDefinitionKeys() != null 
								&& listener.getTaskDefinitionKeys().size() > 0 
								&& !listener.getTaskDefinitionKeys().contains(taskDefinitionKey)) {
							continue;
						}

						for (String type : listener.getEventTypes()) {
							ActivitiListener activitiListener = new ActivitiListener();
							activitiListener.setEvent(type);
							activitiListener.setImplementationType(ImplementationType.IMPLEMENTATION_TYPE_INSTANCE);
							activitiListener.setInstance(listener);
					        element.getTaskListeners().add(activitiListener);
					        
						}
						
					} else {
						throw new AlfaBpmnProcessEngineException("Listener class should be subclass of  AlfaAbstractTaskListener : " + taskListenerClass);
					}
					
				} catch (InstantiationException|IllegalAccessException|ClassNotFoundException e) {
					Utils.getInstance().logError(logger, e);
					throw new AlfaBpmnProcessEngineException("Cannot create object for class named : " + taskListenerClass);
				}
				
				
			}
		}
	}


	public void setCustomTaskListeners(List<String> customTaskListeners) 
	{
		this.customTaskListeners = customTaskListeners;
	}
}