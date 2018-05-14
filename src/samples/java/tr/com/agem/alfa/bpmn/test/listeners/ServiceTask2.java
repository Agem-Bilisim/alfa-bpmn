package tr.com.agem.alfa.bpmn.test.listeners;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class ServiceTask2 implements JavaDelegate {

	public void execute(DelegateExecution execution)
	{
		System.out.println("Service Task 2 completed...");
	}

}
