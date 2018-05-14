package tr.com.agem.alfa.bpmn.types;

/**
 * @author <a href="mailto:ali.ozeren@agem.com.tr">Ali Ozkan Ozeren</a>
 *
 */
public class AlfaFormTypeException extends RuntimeException 
{

	private static final long serialVersionUID = 295511169041299882L;

	public AlfaFormTypeException()
	{
		super();
	}
	
	public AlfaFormTypeException(Throwable exception)
	{
		super(exception);
	}
	
	public AlfaFormTypeException(String message)
	{
		super(message);
	}
	
}
