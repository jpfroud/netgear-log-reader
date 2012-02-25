package event.myimplementation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.LogParser;
import event.EventType;
import event.implementation.AbstractEventType;

public class InternetDisconnectedEvent extends AbstractEventType {

	public static final String NAME = "Internet disconnected";

	public InternetDisconnectedEvent(String s) {
		super(s);
	}

	@Override
	public String getName() {
		return NAME;
	}

	/**
	 * [Internet disconnected] Wednesday, Jan 01,2003 00:00:11
	 */
	@Override
	protected void parse(String s) {
		String regExp = "^\\[Internet disconnected\\] "
				+ EventType.GLOBAL_DATE_REG_EXP;

		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(s);
		if (m.find()) {
			dateText = m.group(1);
			dateOfEvent = LogParser.parseDate(dateText);
		}
	}
}
