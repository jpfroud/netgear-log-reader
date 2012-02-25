package event.myimplementation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.LogParser;
import event.EventType;
import event.IPAdress;
import event.implementation.AbstractEventWithSource;
import event.implementation.LogIPAdress;

public class InternetConnectedEvent extends AbstractEventWithSource {

	public static final String NAME = "Internet connected";

	public InternetConnectedEvent(String s) {
		super(s);
	}

	@Override
	public String getName() {
		return NAME;
	}

	/**
	 * [Internet connected] IP address: 178.250.210.109, Wednesday, Jan 01,2003
	 * 09:30:32
	 */
	@Override
	protected void parse(String s) {
		String regExp = "^\\[Internet connected\\] IP address: "
				+ IPAdress.IP_REG_EXP + ", " + EventType.GLOBAL_DATE_REG_EXP;

		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(s);
		if (m.find()) {
			source = new LogIPAdress(m.group(1));
			dateText = m.group(2);
			dateOfEvent = LogParser.parseDate(dateText);
		}
	}
}
