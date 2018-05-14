package tr.com.agem.alfa.bpmn.utils;

import java.util.Map;

/**
 * @author <a href="mailto:ali.ozeren@agem.com.tr">Ali Ozkan Ozeren</a>
 *
 */
public interface AlfaFormToMapConverterInterface 
{
	public Map<String, String> formToMap(Object formProperties);
}
