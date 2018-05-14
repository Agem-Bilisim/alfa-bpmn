package tr.com.agem.alfa.bpmn.html;


import org.activiti.engine.form.FormProperty;

import tr.com.agem.alfa.bpmn.types.AbstractDateFormType;

/**
 * @author <a href="mailto:ali.ozeren@agem.com.tr">Ali Ozkan Ozeren</a>
 *
 */
public class HtmlDateFormType extends AbstractDateFormType 
{
	private static final long serialVersionUID = 3868249214623992114L;


	public Object renderInputForType(FormProperty property) {
		StringBuffer str = new StringBuffer("<input type='date' ");
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
