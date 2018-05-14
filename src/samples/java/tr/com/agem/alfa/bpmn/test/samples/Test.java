package tr.com.agem.alfa.bpmn.test.samples;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

	public static void main(String[] args) {

		System.out.println(notCondition("${goBack == 1}", "${xyz == 5}"));
	}
	
	private static String rx = "(\\$\\{([^}]+)\\})";
	private static Pattern p = Pattern.compile(rx);
	
	private static String notCondition(String condition, String currentCondition) 
	{
		StringBuffer sb = new StringBuffer();
		Matcher m = p.matcher(condition);
		sb.append("${");
		if (m.find()) {
			sb.append("!(").append(m.group(2)).append(")");
		}

		if (currentCondition != null) {
			m = p.matcher(currentCondition);
			if (m.find()) {
				sb.append(" && (").append(m.group(2)).append(")");
			}
		}
		return sb.append("}").toString();
	}

}
