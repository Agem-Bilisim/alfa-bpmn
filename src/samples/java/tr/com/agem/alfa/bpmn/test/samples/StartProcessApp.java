package tr.com.agem.alfa.bpmn.test.samples;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.runtime.ProcessInstance;
import org.apache.log4j.Logger;

import tr.com.agem.alfa.bpmn.AlfaBpmnProcessEngine;

public class StartProcessApp 
{
	private final static Logger logger = Logger.getLogger(StartProcessApp.class);

    public static void main( String[] args )
    {
    	Map<String, Object> vars = new HashMap<String, Object>();
    	vars.put("goBack", "1");
    	vars.put("kime", "alio");
    	vars.put("nextInputStyle", "kirmizi")
    	;
    	ProcessInstance x = AlfaBpmnProcessEngine.getInstance().startProcessInstance("testRedirect_2", vars);
    	
    	System.out.println(x.getProcessInstanceId());
    	
    	AlfaBpmnProcessEngine.destroy();
    }
}
