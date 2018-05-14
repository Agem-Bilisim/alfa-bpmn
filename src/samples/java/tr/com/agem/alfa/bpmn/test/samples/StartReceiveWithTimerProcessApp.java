package tr.com.agem.alfa.bpmn.test.samples;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import tr.com.agem.alfa.bpmn.AlfaBpmnProcessEngine;

public class StartReceiveWithTimerProcessApp 
{
	private final static Logger logger = Logger.getLogger(StartReceiveWithTimerProcessApp.class);

    public static void main( String[] args )
    {
    	Map<String, Object> vars = new HashMap<String, Object>();
    	vars.put("timerP", "T1M");
    	AlfaBpmnProcessEngine.getInstance().startProcessInstance("testTimer", vars);
    	
    	AlfaBpmnProcessEngine.destroy();

    }
}
