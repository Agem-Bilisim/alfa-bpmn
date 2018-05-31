package tr.com.agem.alfa.bpmn.types;

import org.activiti.bpmn.model.FormProperty;
import org.activiti.engine.form.AbstractFormType;

/**
 * @author <a href="mailto:ali.ozeren@agem.com.tr">Ali Ozkan Ozeren</a>
 *
 */
public abstract class AbstractCheckboxFormType extends AbstractCommonFormType
{
	private static final long serialVersionUID = 3868249214123992954L;
	
	public static final String NAME = "checkbox";
	
	private String checkedValue; 
	
	public String getName() 
	{
		return AbstractCheckboxFormType.NAME;
	}
	
	@Override
	public AbstractFormType parseInput(FormProperty property) 
	{
		super.parseInput(property);
		
		this.checkedValue= super.getMap().get("checkedValue");

		return this;
	}

	public String getCheckedValue() {
		return this.checkedValue;
	}
}
