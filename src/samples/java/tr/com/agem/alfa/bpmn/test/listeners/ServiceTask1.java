package tr.com.agem.alfa.bpmn.test.listeners;

import org.activiti.engine.delegate.BpmnError;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class ServiceTask1 implements JavaDelegate {

	public void execute(DelegateExecution execution)
	{
		System.out.println("Service Task 1 completed...");
		
		 throw new BpmnError("serviceTask1");
	}

}
