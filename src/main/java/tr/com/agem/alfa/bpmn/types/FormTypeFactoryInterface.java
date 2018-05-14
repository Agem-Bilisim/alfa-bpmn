package tr.com.agem.alfa.bpmn.types;


/**
 * @author <a href="mailto:ali.ozeren@agem.com.tr">Ali Ozkan Ozeren</a>
 *
 */
public interface FormTypeFactoryInterface 
{
	public ActivitiFormTypeInterface getFormTypeInstance(String name);
}
