package tr.com.agem.alfa.bpmn.test.samples;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;

import tr.com.agem.alfa.bpmn.AlfaBpmnProcessEngine;

public class GetDiagramForProcessInstanceApp 
{

	private final static Logger logger = Logger.getLogger(GetDiagramForProcessInstanceApp.class);

    public static void main( String[] args ) throws IOException
    {
    	
    	FileOutputStream f = new FileOutputStream(new File("/home/alio/test2.png"));
    	
   	
    	AlfaBpmnProcessEngine.getInstance().getProcessDiagramForInstance("3067401", f, Color.BLUE, null, false);

    	f.close();
    	
    	AlfaBpmnProcessEngine.destroy();
    }

}
