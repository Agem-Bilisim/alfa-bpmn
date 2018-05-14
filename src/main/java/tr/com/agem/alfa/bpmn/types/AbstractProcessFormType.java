package tr.com.agem.alfa.bpmn.types;


/**
 * @author <a href="mailto:ali.ozeren@agem.com.tr">Ali Ozkan Ozeren</a>
 *
 */
public abstract class AbstractProcessFormType extends AbstractCommonFormType
{
	private static final long serialVersionUID = 2262742328986137324L;
	
	public static final String NAME = "process_name";
	
	
	public String getName() 
	{
		return AbstractProcessFormType.NAME;
	}
}
