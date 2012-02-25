package event.myimplementation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.LogParser;
import event.EventType;
import event.IPAdress;
import event.implementation.AbstractEventWithSource;
import event.implementation.LogIPAdress;

public class RemoteLoginSuccessEvent extends AbstractEventWithSource {

	public static final String NAME = "Remote login";

	public RemoteLoginSuccessEvent(String s) {
		super(s);
	}

	@Override
	public String getName() {
		return NAME;
	}

	/**
	 * [Remote login] from source 160.53.250.119, Wednesday, Jan 01,2003
	 * 00:00:36
	 */
	@Override
	protected void parse(String s) {
		String regExp = "^\\[Remote login\\] from source "
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
