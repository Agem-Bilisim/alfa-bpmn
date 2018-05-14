package tr.com.agem.alfa.bpmn.html;

import org.activiti.engine.form.FormProperty;

import tr.com.agem.alfa.bpmn.types.AbstractIntboxFormType;

/**
 * @author <a href="mailto:ali.ozeren@agem.com.tr">Ali Ozkan Ozeren</a>
 *
 */
public class HtmlLongFormType extends AbstractIntboxFormType  
{

	private static final long serialVersionUID = 3868249214623992954L;

	public String renderInputForType(FormProperty property) 
	{
		StringBuffer str = new StringBuffer("<input type='number' ");
		str.append(" id='")
			.append(super.getId())
			.append("' name='")
			.append(super.getId())
			.append("' ");
		
		if (property.getValue() != null) {
			str.append(" value='").append(property.getValue()).append("' ");
		}
		
		str.append(HTMLInputUtils.getInstance().prepareAttributes(super.getMap()));
		
		str.append(" />");
		
		return HTMLInputUtils.getInstance().createStandartInputDiv(super.getLabel(), str.toString());

	}

}
