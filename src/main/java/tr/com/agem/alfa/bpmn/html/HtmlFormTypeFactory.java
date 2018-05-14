package tr.com.agem.alfa.bpmn.html;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import tr.com.agem.alfa.bpmn.types.AbstractCheckboxFormType;
import tr.com.agem.alfa.bpmn.types.AbstractComboboxFormType;
import tr.com.agem.alfa.bpmn.types.AbstractDateFormType;
import tr.com.agem.alfa.bpmn.types.AbstractIntboxFormType;
import tr.com.agem.alfa.bpmn.types.AbstractPasswordFormType;
import tr.com.agem.alfa.bpmn.types.AbstractTextboxFormType;
import tr.com.agem.alfa.bpmn.types.ActivitiFormTypeInterface;
import tr.com.agem.alfa.bpmn.types.FormTypeFactoryInterface;
import tr.com.agem.alfa.bpmn.types.AlfaFormTypeException;
import tr.com.agem.alfa.bpmn.utils.Utils;

/**
 * @author <a href="mailto:ali.ozeren@agem.com.tr">Ali Ozkan Ozeren</a>
 *
 */
public class HtmlFormTypeFactory implements FormTypeFactoryInterface
{
	
	private final static Logger logger = Logger.getLogger(HtmlFormTypeFactory.class);
	
	private static final Map<String, Class<?>> formTypes;
	static {
		formTypes= new HashMap<String, Class<?>>();
		// activiti-explorer form types
		formTypes.put("boolean", HtmlBooleanFormType.class);
		formTypes.put("enum", HtmlEnumFormType.class);
		formTypes.put("string", HtmlStringFormType.class);
		formTypes.put("long", HtmlLongFormType.class);
		
		formTypes.put(AbstractComboboxFormType.NAME, HtmlEnumFormType.class);
		formTypes.put(AbstractIntboxFormType.NAME, HtmlLongFormType.class);
		formTypes.put(AbstractTextboxFormType.NAME, HtmlStringFormType.class);
		formTypes.put(AbstractDateFormType.NAME, HtmlDateFormType.class);
		formTypes.put(AbstractPasswordFormType.NAME, HtmlBooleanFormType.class);
		formTypes.put(AbstractCheckboxFormType.NAME, HtmlCheckboxFormType.class);
	}
	
	public ActivitiFormTypeInterface getFormTypeInstance(String name)
	{
		if (formTypes.containsKey(name)) {
			try {
				return (ActivitiFormTypeInterface) formTypes.get(name).newInstance();
			} catch (Exception e) {
				Utils.getInstance().logError(logger, e);
			} 
		}
		
		throw new AlfaFormTypeException("Error in creating object with the type named " + name );
	}

}
