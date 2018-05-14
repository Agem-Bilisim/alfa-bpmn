package tr.com.agem.alfa.bpmn.test.samples;

import java.util.List;

import org.activiti.engine.task.Task;
import org.apache.log4j.Logger;

import tr.com.agem.alfa.bpmn.AlfaBpmnProcessEngine;

public class ListTaskApp 
{

	private final static Logger logger = Logger.getLogger(ListTaskApp.class);

    public static void main( String[] args )
    {
    	List<Task> tasks = AlfaBpmnProcessEngine.getInstance().getUserTasks("alio");

    	for (Task t : tasks) {
    		logger.info("Task ID:" + t.getId() + ", Task Name:" + t.getName());
//    		AlfaBpmnProcessEngine.getInstance().completeTask(t.getId(),null,false,null);
    	}
    	
    	AlfaBpmnProcessEngine.destroy();
    } 
    

}
