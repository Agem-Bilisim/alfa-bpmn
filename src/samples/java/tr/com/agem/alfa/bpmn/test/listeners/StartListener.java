package tr.com.agem.alfa.bpmn.test.listeners;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.apache.log4j.Logger;

public class StartListener implements ExecutionListener
{

	private static final long serialVersionUID = -6131745264843448379L;
	
	private final static Logger logger = Logger.getLogger(StartListener.class);


	public void notify(DelegateExecution execution) 
	{
		logger.debug("Start Listener....");
		System.out.println("Start Listener. sysout...");
	}

}
