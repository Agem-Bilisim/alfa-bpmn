package tr.com.agem.alfa.bpmn.test.samples;

import java.util.Map;

import org.activiti.engine.task.Comment;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.apache.log4j.Logger;

import tr.com.agem.alfa.bpmn.AlfaBpmnProcessEngine;
import tr.com.agem.alfa.bpmn.AlfaCustomSqlExecutor;

public class ListTaskIdentityApp 
{

	private final static Logger logger = Logger.getLogger(ListTaskIdentityApp.class);

	public static void main( String[] args )
	{


		AlfaBpmnProcessEngine p = AlfaBpmnProcessEngine.getInstance();

//		p.involveUser("132505", "alio", "koordinator");

		for (IdentityLink i : p.getTaskIdentityLinks("160015")) {

			logger.debug(i.getUserId() + "---" + i.getType());
		}

		for (Map<String, Object> t : AlfaCustomSqlExecutor.getInstance().selectUserRelatedTasks("alio")){
			for ( String k : t.keySet()) {
				logger.debug( k + " = " + t.get(k));
			}
		}

		for (Task t : p.getUserRelatedTasks("alio")) {
			logger.debug(t.getId() + " -- " );
		}
		
		for (Comment e: p.getTaskComments("160015", "uyari")) {
			logger.debug(e.getFullMessage());
		}
		AlfaBpmnProcessEngine.destroy();
	}



}
