package tr.com.agem.alfa.bpmn.types;

import org.activiti.bpmn.model.FormProperty;
import org.activiti.engine.form.AbstractFormType;


/**
 * @author <a href="mailto:ali.ozeren@agem.com.tr">Ali Ozkan Ozeren</a>
 *
 */
public abstract class AbstractRedmineFormType extends AbstractCommonFormType
{
	private static final long serialVersionUID = 3868249214123992954L;
	
	public static final String NAME = "redmine";
	
	public static final String PROJECTKEY = "projectKey";

	
	private String projectKey;
	
	public AbstractFormType parseInput(FormProperty property)
	{
		super.parseInput(property);
		this.projectKey= super.getMap().get(AbstractRedmineFormType.PROJECTKEY);

		return this;
	}
	
	
	public String getName() 
	{
		return AbstractRedmineFormType.NAME;
	}


	public String getProjectKey() {
		return projectKey;
	}


	
}
