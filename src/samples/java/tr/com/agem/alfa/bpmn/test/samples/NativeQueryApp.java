package tr.com.agem.alfa.bpmn.test.samples;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.impl.persistence.entity.VariableInstanceEntity;
import org.activiti.engine.runtime.Execution;
import org.apache.log4j.Logger;

import tr.com.agem.alfa.bpmn.AlfaBpmnProcessEngine;

public class NativeQueryApp 
{
	private final static Logger logger = Logger.getLogger(NativeQueryApp.class);

    public static void main( String[] args )
    {
    	ProcessEngine engine = AlfaBpmnProcessEngine.getInstance().getProcessEngine();

    	long count = engine.getRuntimeService().createNativeExecutionQuery()
    			  .sql("SELECT count(*) FROM " + engine.getManagementService().getTableName(Execution.class) + " T1, "
    			    + engine.getManagementService().getTableName(VariableInstanceEntity.class) + " V1 WHERE V1.EXECUTION_ID_ = T1.ID_").count();
    	
    	logger.info("Task count :" + count);
    	
    	AlfaBpmnProcessEngine.destroy();
    }
}
