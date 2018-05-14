package tr.com.agem.alfa.bpmn.test.samples;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.apache.log4j.Logger;

import tr.com.agem.alfa.bpmn.AlfaBpmnProcessEngine;

public class StartProcessByMessage 
{
	private final static Logger logger = Logger.getLogger(StartProcessByMessage.class);

    public static void main( String[] args )
    {
    	ProcessEngine engine = AlfaBpmnProcessEngine.getInstance().getProcessEngine();
    	
    	Map<String, Object> vars = new HashMap<String, Object>();
    	vars.put("degisken", "evet");
    	AlfaBpmnProcessEngine.getInstance().startProcessInstanceByMessage("evetTaskMessage", vars);
    	
//    	List<Task> tasks = AlfaBpmnProcessEngine.getInstance().getUserTasks("mehmet");
//
//    	TaskService taskService = AlfaBpmnProcessEngine.getInstance().getProcessEngine().getTaskService();
//    	for (Task t : tasks) {
//    		logger.info("Task ID:" + t.getId() + ", Task Name:" + t.getName());
//    		taskService.complete(t.getId());
//    	}
    	
//    	List<Execution> processes = engine.getRuntimeService().createExecutionQuery().orderByProcessInstanceId().desc().list();
//    	for (Execution p : processes) {
//    		System.out.println(p.getId());
//    		engine.getRuntimeService().signal(p.getId());
//    	}
    	
    	
    	AlfaBpmnProcessEngine.destroy();

    }
}
