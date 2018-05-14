package tr.com.agem.alfa.bpmn.test.samples;

import tr.com.agem.alfa.bpmn.AlfaBpmnProcessEngine;



public class DeployModelApp 
{
	public static void main( String[] args )
	{
		AlfaBpmnProcessEngine.getInstance().deployModel("Test Prop","tr/com/agem/alfa/bpmn/test/samplebpm/formPropTest.bpmn20.xml");
//		AlfaBpmnProcessEngine.getInstance().deleteProcessInstances("testRedirect_2", null, "test");
		
		
//		List<Deployment> x = AlfaBpmnProcessEngine.getInstance().getProcessEngine().getRepositoryService().createDeploymentQuery().processDefinitionKey("testRedirect_2").list();
//		
//		for (Deployment d : x) {
//			List<ProcessInstance> z = AlfaBpmnProcessEngine.getInstance().getProcessEngine().getRuntimeService().createProcessInstanceQuery().processDefinitionKey("testRedirect_2").list();
//			for (ProcessInstance e : z) {
//				AlfaBpmnProcessEngine.getInstance().getProcessEngine().getRuntimeService().deleteProcessInstance(e.getProcessInstanceId(), null);
//			}
//			AlfaBpmnProcessEngine.getInstance().getProcessEngine().getRepositoryService().deleteDeployment(d.getId());
//		}
		
		
		AlfaBpmnProcessEngine.destroy();
	}
}
