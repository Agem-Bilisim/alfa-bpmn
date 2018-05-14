package tr.com.agem.alfa.bpmn.types;



/**
 * @author <a href="mailto:ali.ozeren@agem.com.tr">Ali Ozkan Ozeren</a>
 *
 */
public abstract class AbstractButtonFormType extends AbstractCommonFormType
{
	private static final long serialVersionUID = 3868249214123992954L;
	public static final String NAME = "button";
	
	public String getName() 
	{
		return AbstractButtonFormType.NAME;
	}
}
