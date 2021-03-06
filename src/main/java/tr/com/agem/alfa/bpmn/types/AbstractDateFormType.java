package tr.com.agem.alfa.bpmn.types;


import org.activiti.bpmn.model.FormProperty;
import org.activiti.engine.form.AbstractFormType;

/**
 * @author <a href="mailto:ali.ozeren@agem.com.tr">Ali Ozkan Ozeren</a>
 *
 */
public abstract class AbstractDateFormType extends AbstractCommonFormType
{
	private static final long serialVersionUID = 1664028556950591530L;
	
	public static final String NAME = "date";
	
	private String datePattern;
	
	public AbstractFormType parseInput(FormProperty property)
	{
		super.parseInput(property);
		this.datePattern= property.getDatePattern();
		
		return this;
	}

	public String getDatePattern() {
		return datePattern;
	}

	public void setDatePattern(String datePattern) 
	{
		this.datePattern = datePattern;
	}
	
	public String getName() 
	{
		return  AbstractDateFormType.NAME;
	}


}
