package tr.com.agem.alfa.bpmn.types;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.activiti.bpmn.model.FormProperty;
import org.activiti.bpmn.model.FormValue;
import org.activiti.engine.form.AbstractFormType;

import tr.com.agem.alfa.bpmn.utils.ELResolverInterface;

/**
 * @author <a href="mailto:ali.ozeren@agem.com.tr">Ali Ozkan Ozeren</a>
 *
 */
public abstract class AbstractCommonFormType extends AbstractFormType implements ActivitiFormTypeInterface 
{
	private static final long serialVersionUID = 9174362796522080281L;
	
	
	private String variable;
	
	private String id;	
	private String label;

	private boolean required;
	private boolean writeable;
	private boolean readable;
	
	private Map<String,String> map;
	
	public void setVariable(String variable) 
	{
		this.variable= variable;
	}
	
	public String getVariable() 
	{
		return variable;
	}
	
	@Override
	public Object convertFormValueToModelValue(String propertyValue) 
	{
		return propertyValue;
	}

	@Override
	public String convertModelValueToFormValue(Object modelValue) 
	{
		return modelValue != null ? modelValue.toString() : null;
	}
	
	public String getMimeType() 
	{
		return "plain/text";
	}

	public AbstractFormType parseInput(FormProperty property) 
	{
		map = new HashMap<String,String>();
		
		for (FormValue fv : property.getFormValues()) {
			map.put(fv.getId(), fv.getName());
		}
		
		this.id= property.getId();
		this.label= property.getName();
		
		this.readable = property.isReadable();
		this.writeable = property.isWriteable();
		this.required = property.isRequired();

		return this;
	}

	public String getLabel() {
		return label;
	}

	public String getId() {
		return id;
	}

	public boolean isRequired() {
		return required;
	}

	public boolean isWriteable() {
		return writeable;
	}

	public boolean isReadable() {
		return readable;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Map<String, String> getMap() {
		return map;
	}

	/* (non-Javadoc)
	 * @see tr.com.agem.alfa.bpmn.types.ActivitiFormTypeInterface#renderInput(org.activiti.engine.form.FormProperty, tr.com.agem.alfa.bpmn.utils.ELResolverInterface)
	 */
	public Object renderInput(org.activiti.engine.form.FormProperty property, ELResolverInterface elResolver) 
	{
		if (elResolver != null) {
			Map<String, String> resolvedMap = new HashMap<String, String>();
			for (Entry<String, String> entry : map.entrySet()) {
				resolvedMap.put(elResolver.resolve(entry.getKey()).toString(), elResolver.resolve(entry.getValue()).toString());
			}
			map= resolvedMap;
		}
		
		return this.renderInputForType(property);
	}
	
	public abstract Object renderInputForType(org.activiti.engine.form.FormProperty property);


}
