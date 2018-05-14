package tr.com.agem.alfa.bpmn.utils;

import javax.el.ELContext;
import javax.el.ValueExpression;

import org.activiti.engine.impl.el.ExpressionFactoryResolver;

/**
 * @author <a href="mailto:ali.ozeren@agem.com.tr">Ali Ozkan Ozeren</a>
 *
 */
public class ELResolver implements ELResolverInterface 
{

	private ELContext context= null;
	
	public ELResolver(ELContext context) 
	{
		this.context = context;
	}

	public Object resolve(String expression) 
	{
		
		ValueExpression ex = ExpressionFactoryResolver.resolveExpressionFactory().createValueExpression(context, expression, Object.class);
		
        return ex.getValue(context);
	}
	
	
}
