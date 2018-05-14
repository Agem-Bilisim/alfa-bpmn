package tr.com.agem.alfa.bpmn.test.samples;

import java.io.IOException;

import org.apache.log4j.Logger;

import tr.com.agem.alfa.bpmn.AlfaBpmnFormService;
import tr.com.agem.alfa.bpmn.AlfaBpmnProcessEngine;

public class GetStartFormDataApp 
{

	private final static Logger logger = Logger.getLogger(GetStartFormDataApp.class);

    public static void main( String[] args ) throws IOException
    {
    	
		for (Object property : AlfaBpmnFormService.getInstance().getRenderedStartForm("formPropTest")) {
			logger.info(property);
		}
    	
    	AlfaBpmnProcessEngine.destroy();
    }

}
