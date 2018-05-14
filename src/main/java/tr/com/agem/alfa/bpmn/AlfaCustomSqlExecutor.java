package tr.com.agem.alfa.bpmn;

import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.cmd.AbstractCustomSqlExecution;
import org.activiti.engine.impl.cmd.CustomSqlExecution;
import org.apache.log4j.Logger;

import tr.com.agem.alfa.bpmn.utils.CustomSqlMapper;

/**
 * @author <a href="mailto:ali.ozeren@agem.com.tr">Ali Ozkan Ozeren</a>
 *
 */
public class AlfaCustomSqlExecutor 
{
	private final static Logger logger = Logger.getLogger(AlfaCustomSqlExecutor.class);
	
	private static AlfaCustomSqlExecutor instance;
	
	private AlfaCustomSqlExecutor() {
		
	}
	
	public static AlfaCustomSqlExecutor getInstance() 
	{
		if (AlfaCustomSqlExecutor.instance == null) {
			AlfaCustomSqlExecutor.instance = new AlfaCustomSqlExecutor();
			logger.info("Alfa Custom Sql Executor is initialized...");
		}
		
		return AlfaCustomSqlExecutor.instance;
		
	}
	
	public List<Map<String, Object>> selectUserRelatedTasks(final String username) 
	{
		CustomSqlExecution<CustomSqlMapper, List<Map<String, Object>>> customSqls =
				new AbstractCustomSqlExecution<CustomSqlMapper, List<Map<String, Object>>>(CustomSqlMapper.class) 
				{
					public List<Map<String, Object>> execute(CustomSqlMapper customMapper) {
						return customMapper.selectUserRelatedTasks(username);
					}
				};

		return AlfaBpmnProcessEngine.getInstance().getProcessEngine().getManagementService().executeCustomSql(customSqls);
	}
	
	public List<Map<String, Object>> selectGroupRelatedTasks(final String group) 
	{
		CustomSqlExecution<CustomSqlMapper, List<Map<String, Object>>> customSqls =
				new AbstractCustomSqlExecution<CustomSqlMapper, List<Map<String, Object>>>(CustomSqlMapper.class) 
				{
					public List<Map<String, Object>> execute(CustomSqlMapper customMapper) {
						return customMapper.selectGroupRelatedTasks(group);
					}
				};

		return AlfaBpmnProcessEngine.getInstance().getProcessEngine().getManagementService().executeCustomSql(customSqls);
	}


}
