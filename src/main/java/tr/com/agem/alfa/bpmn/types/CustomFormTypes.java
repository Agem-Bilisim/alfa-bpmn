package tr.com.agem.alfa.bpmn.types;

import org.activiti.bpmn.model.FormProperty;
import org.activiti.engine.form.AbstractFormType;
import org.activiti.engine.impl.form.FormTypes;

/**
 * @author <a href="mailto:ali.ozeren@agem.com.tr">Ali Ozkan Ozeren</a>
 *
 */
public class CustomFormTypes extends FormTypes 
{
	FormTypeFactoryInterface factory;
	
	@Override
	public AbstractFormType parseFormPropertyType(FormProperty formProperty) 
	{
		if (factory == null) {
			throw new AlfaFormTypeException("No Form Type Factory is assigned for CustomFormTypes");
		}
				
		ActivitiFormTypeInterface formObject = factory.getFormTypeInstance(formProperty.getType());
		if (formObject != null) {
			formObject.setVariable(formProperty.getVariable());
			return formObject.parseInput(formProperty);
		} else {
			throw new AlfaFormTypeException("No form type is implemented for " + formProperty.getType()); 
		}
	}

	public FormTypeFactoryInterface getFactory() 
	{
		return factory;
	}

	public void setFactory(FormTypeFactoryInterface factory) 
	{
		this.factory = factory;
	}	
}