package tr.com.agem.alfa.bpmn.test.samples;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;

import tr.com.agem.alfa.bpmn.AlfaBpmnProcessEngine;

public class GetDiagramForProcessApp 
{

	private final static Logger logger = Logger.getLogger(GetDiagramForProcessApp.class);

    public static void main( String[] args ) throws IOException
    {
    	
    	FileOutputStream f = new FileOutputStream(new File("/home/alio/test.png"));
    	AlfaBpmnProcessEngine.getInstance().getProcessDiagram("testRedirect_2",f);

    	f.close();
    	
    	AlfaBpmnProcessEngine.destroy();
    }

}
