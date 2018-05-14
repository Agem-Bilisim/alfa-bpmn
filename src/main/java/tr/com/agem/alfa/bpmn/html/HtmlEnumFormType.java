package tr.com.agem.alfa.bpmn.html;

import org.activiti.engine.form.FormProperty;

import tr.com.agem.alfa.bpmn.types.AbstractComboboxFormType;

/**
 * @author <a href="mailto:ali.ozeren@agem.com.tr">Ali Ozkan Ozeren</a>
 *
 */
public class HtmlEnumFormType extends AbstractComboboxFormType  
{
	private static final long serialVersionUID = 3868249214623992954L;

	public String renderInputForType(FormProperty property) 
	{
		StringBuffer str= new StringBuffer();
		
		str.append("<select id='")
			.append(super.getId())
			.append("' name='")
			.append(super.getId())
			.append("' ");
		
		str.append(HTMLInputUtils.getInstance().prepareAttributes(super.getMap(),"options"));
		
		str.append(">");
		
		for ( String key : super.getOptions().keySet()) {
			str.append("<option value='")
				.append(key)
				.append("'");
			if (property.getValue() != null && property.getValue().equals(key)) {
				str.append(" selected ");
			}
				
			str.append(" > ")
				.append(super.getOptions().get(key))
				.append("</option>");
		}
		
		str.append("</select>");
		
		return HTMLInputUtils.getInstance().createStandartInputDiv(super.getLabel(), str.toString());
	}
	
}
