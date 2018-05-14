package tr.com.agem.alfa.bpmn.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.impl.el.ActivitiElContext;
import org.activiti.engine.impl.el.VariableScopeElResolver;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.task.Task;

import tr.com.agem.alfa.bpmn.types.ActivitiFormTypeInterface;

/**
 * @author <a href="mailto:ali.ozeren@agem.com.tr">Ali Ozkan Ozeren</a>
 *
 */
public class AlfaFormValuesResolveCmd implements Command<List<Object>>, Serializable 
{

	private static final long serialVersionUID = 1L;
	protected String taskId;
	List<FormProperty> list;

	/**
	 * Command is used to create a context for resolving the expressions used in form values
	 * @param list
	 * @param taskId
	 */
	public AlfaFormValuesResolveCmd(List<FormProperty> list, String taskId) 
	{
		this.taskId = taskId;
		this.list = list;
	}

	public List<Object> execute(CommandContext commandContext) 
	{
		ELResolver elResolver = null;
		
		if (taskId != null) {
			
			TaskEntity task = commandContext
					.getTaskEntityManager().findById(taskId);
			
			if (task == null) {
				throw new ActivitiObjectNotFoundException("No task found for taskId '" + taskId +"'", Task.class);
			}
			
			VariableScopeElResolver varResolver = new VariableScopeElResolver((TaskEntity) task);
			elResolver = new ELResolver(new ActivitiElContext(varResolver));
		}

		List<Object> renderedForm= new ArrayList<Object>();
		if (list != null) {
			for (FormProperty property : list) {
				if (property.getType() instanceof ActivitiFormTypeInterface) {
					ActivitiFormTypeInterface prop= (ActivitiFormTypeInterface) property.getType();
					renderedForm.add(prop.renderInput(property, elResolver));
				}
			}
		}

		return renderedForm;
	}

}