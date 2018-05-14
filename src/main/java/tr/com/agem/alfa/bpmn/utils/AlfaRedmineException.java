package tr.com.agem.alfa.bpmn.utils;


public class AlfaRedmineException extends RuntimeException 
{
	private static final long serialVersionUID = 295585069041299882L;

	public AlfaRedmineException()
	{
		super();
	}
	
	public AlfaRedmineException(Throwable exception)
	{
		super(exception);
	}
	
	public AlfaRedmineException(String message)
	{
		super(message);
	}
	
	
	public AlfaRedmineException(String message, Throwable exception)
	{
		super(message, exception);
	}

}
