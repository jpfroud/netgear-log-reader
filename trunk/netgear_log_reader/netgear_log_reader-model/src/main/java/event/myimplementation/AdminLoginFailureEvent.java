package event.myimplementation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.LogParser;
import event.EventType;
import event.IPAdress;
import event.implementation.AbstractEventWithSource;
import event.implementation.LogIPAdress;

public class AdminLoginFailureEvent extends AbstractEventWithSource {

	public AdminLoginFailureEvent(String s) {
		super(s);
	}

	/**
	 * ex : [Admin login failure] from source 192.168.0.106, Friday, Jan 27,2012
	 * 19:31:50
	 * 
	 * @param s
	 */
	@Override
	protected void parse(String s) {
		String regExp = "^\\[Admin login failure\\] from source "
				+ IPAdress.IP_REG_EXP + "\\, " + EventType.GLOBAL_DATE_REG_EXP;

		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(s);
		if (m.find()) {
			source = new LogIPAdress(m.group(1));
			dateText = m.group(2);
			dateOfEvent = LogParser.parseDate(dateText);
		}

	}

	public static final String NAME = "Admin login failure";

	@Override
	public String getName() {
		return NAME;
	}

}
