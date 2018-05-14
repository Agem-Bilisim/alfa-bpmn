package tr.com.agem.alfa.bpmn.html;

import org.activiti.engine.form.FormProperty;

import tr.com.agem.alfa.bpmn.types.AbstractPasswordFormType;

/**
 * @author <a href="mailto:ali.ozeren@agem.com.tr">Ali Ozkan Ozeren</a>
 *
 */
public class HtmlBooleanFormType extends AbstractPasswordFormType
{
	private static final long serialVersionUID = 3868249214623992954L;

	public Object renderInputForType(FormProperty property) 
	{
		return "<input type='boolean'/>";
	}
}
