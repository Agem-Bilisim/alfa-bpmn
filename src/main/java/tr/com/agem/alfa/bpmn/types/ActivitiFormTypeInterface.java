package tr.com.agem.alfa.bpmn.types;

import org.activiti.engine.form.AbstractFormType;

import tr.com.agem.alfa.bpmn.utils.ELResolverInterface;

public interface ActivitiFormTypeInterface 
{
	public AbstractFormType parseInput(org.activiti.bpmn.model.FormProperty property);
	public Object renderInput(org.activiti.engine.form.FormProperty property, ELResolverInterface elResolver);
	public void setVariable(String variable);
	public String getVariable();
}