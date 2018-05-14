package tr.com.agem.alfa.bpmn.test.samples;

import org.apache.log4j.Logger;

import tr.com.agem.alfa.bpmn.AlfaBpmnFormService;
import tr.com.agem.alfa.bpmn.AlfaBpmnProcessEngine;

public class GetStartFormDataAppRendered 
{

	private final static Logger logger = Logger.getLogger(GetStartFormDataAppRendered.class);

    public static void main( String[] args )
    {
    	Object startForm = AlfaBpmnFormService.getInstance().getRenderedTaskForm("7508");

    	logger.info(startForm);
    	
    	AlfaBpmnProcessEngine.destroy();
    }
    
}
