package tr.com.agem.alfa.bpmn.test.samples;

import java.util.List;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.apache.log4j.Logger;

import tr.com.agem.alfa.bpmn.AlfaBpmnProcessEngine;

public class ListActiveTaskApp 
{

	private final static Logger logger = Logger.getLogger(ListActiveTaskApp.class);

	public static void main( String[] args )
	{

		TaskService taskService = AlfaBpmnProcessEngine.getInstance().getProcessEngine().getTaskService();
		
		List<Task> tasks = AlfaBpmnProcessEngine.getInstance().getUserTasks("alio");
		
		for (Task t : tasks) {
			logger.info("Task Owner:" + t.getAssignee() + ", Task ID:" + t.getId() + ", Task Name:" + t.getName()+ ", Execution Id:" + t.getExecutionId() + " Process Id:" + t.getProcessInstanceId());
//	    	Map<String, Object> vars = new HashMap<String, Object>();
//	    	vars.put("goBack", "0");
//    		taskService.complete(t.getId(),vars);
		}
		

		AlfaBpmnProcessEngine.destroy();
	}

}
