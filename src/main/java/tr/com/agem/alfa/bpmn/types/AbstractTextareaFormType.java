package tr.com.agem.alfa.bpmn.types;

import org.activiti.bpmn.model.FormProperty;
import org.activiti.engine.form.AbstractFormType;

import tr.com.agem.alfa.bpmn.AlfaBpmnProcessEngineException;

/**
 * @author <a href="mailto:ali.ozeren@agem.com.tr">Ali Ozkan Ozeren</a>
 *
 */
public abstract class AbstractTextareaFormType extends AbstractCommonFormType
{

	private static final long serialVersionUID = 3868249214623992954L;
	public static final String NAME = "textarea";
	
	private int rows;
	private int cols;
	
	public AbstractFormType parseInput(FormProperty property)
	{
		super.parseInput(property);
		try {
			rows = Integer.parseInt(super.getMap().get("rows"));
			cols = Integer.parseInt(super.getMap().get("cols"));
		} catch (NumberFormatException e) {
			throw new AlfaBpmnProcessEngineException("rows and cols values for textarea must be numeric", e);
		}
		
		return this;
	}
	
	public String getName() 
	{
		return AbstractTextareaFormType.NAME;
	}

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}
}
