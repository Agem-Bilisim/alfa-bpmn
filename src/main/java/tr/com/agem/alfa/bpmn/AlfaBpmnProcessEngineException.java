package tr.com.agem.alfa.bpmn;

/**
 * @author <a href="mailto:ali.ozeren@agem.com.tr">Ali Ozkan Ozeren</a>
 *
 */
public class AlfaBpmnProcessEngineException extends RuntimeException 
{

	private static final long serialVersionUID = 295585069041299882L;

	public AlfaBpmnProcessEngineException()
	{
		super();
	}
	
	public AlfaBpmnProcessEngineException(Throwable exception)
	{
		super(exception);
	}
	
	public AlfaBpmnProcessEngineException(String message)
	{
		super(message);
	}
	
	public AlfaBpmnProcessEngineException(String message, Throwable exception)
	{
		super(message, exception);
	}
}
