package event.myimplementation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.LogParser;
import event.EventType;
import event.implementation.AbstractSpecialEvent;
import event.implementation.LogParameter;

public class InitializedEvent extends AbstractSpecialEvent {

	public static final String NAME = "Initialized, firmware version";

	public InitializedEvent(String s) {
		super(s);
	}

	@Override
	public String getName() {
		return NAME;
	}

	/**
	 * [Initialized, firmware version: V1.0.0.70_1.0.18] Wednesday, Jan 01,2003
	 * 00:00:04
	 */
	@Override
	protected void parse(String s) {
		String regExp = "^\\[Initialized, (.+?)\\] "
				+ EventType.GLOBAL_DATE_REG_EXP;

		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(s);
		if (m.find()) {
			dateText = m.group(2);
			dateOfEvent = LogParser.parseDate(dateText);
			parameter = new LogParameter(m.group(1));
		}
	}
}
