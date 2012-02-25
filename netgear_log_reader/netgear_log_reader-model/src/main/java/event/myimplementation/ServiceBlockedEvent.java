package event.myimplementation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.LogParser;
import event.EventType;
import event.IPAdress;
import event.implementation.AbstractSpecialEventWithSource;
import event.implementation.LogIPAdress;
import event.implementation.LogParameter;

public class ServiceBlockedEvent extends AbstractSpecialEventWithSource {
	public static final String NAME = "Service blocked";

	public ServiceBlockedEvent(String s) {
		super(s);
	}

	@Override
	public String getName() {
		return NAME;
	}

	/**
	 * [Service blocked: ICMP_echo_req] from source 178.250.209.42, Friday, Jan
	 * 27,2012 19:50:27
	 */
	@Override
	protected void parse(String s) {
		String regExp = "\\[Service blocked: (.+?)\\] from source "
				+ IPAdress.IP_REG_EXP + ", " + EventType.GLOBAL_DATE_REG_EXP;

		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(s);
		if (m.find()) {
			parameter = new LogParameter(m.group(1));
			source = new LogIPAdress(m.group(2));
			dateText = m.group(3);
			dateOfEvent = LogParser.parseDate(dateText);
		}
	}
}
