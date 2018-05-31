package tr.com.agem.alfa.bpmn.types;


/**
 * @author <a href="mailto:ali.ozeren@agem.com.tr">Ali Ozkan Ozeren</a>
 *
 */
public abstract class AbstractIntegerFormType extends AbstractCommonFormType
{

	private static final long serialVersionUID = 3868249214623992954L;

	public static final String NAME = "integer";
	
	public String getName() 
	{
		return AbstractIntegerFormType.NAME;
	}
}
