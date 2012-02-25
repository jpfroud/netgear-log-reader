package event.myimplementation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.LogParser;
import event.EventType;
import event.implementation.AbstractEventType;

public class TimeSynchroEvent extends AbstractEventType {
	public static final String NAME = "Time synchronized with NTP server";

	public TimeSynchroEvent(String s) {
		super(s);
	}

	@Override
	public String getName() {
		return NAME;
	}

	/**
	 * [Time synchronized with NTP server] Friday, Jan 27,2012 12:48:37
	 */
	@Override
	protected void parse(String s) {
		String regExp = "^\\[Time synchronized with NTP server\\] "
				+ EventType.GLOBAL_DATE_REG_EXP;

		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(s);
		if (m.find()) {
			dateText = m.group(1);
			dateOfEvent = LogParser.parseDate(dateText);
		}
	}
}
