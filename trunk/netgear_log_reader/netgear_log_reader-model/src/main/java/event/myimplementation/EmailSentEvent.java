package event.myimplementation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.LogParser;
import event.EventType;
import event.implementation.AbstractSpecialEvent;
import event.implementation.LogParameter;

public class EmailSentEvent extends AbstractSpecialEvent {

	public static final String NAME = "email sent to";

	public EmailSentEvent(String s) {
		super(s);
	}

	@Override
	public String getName() {
		return NAME;
	}

	/**
	 * [email sent to: jpfroud@gmail.com] Wednesday, Jan 01,2003 07:25:15
	 */
	@Override
	protected void parse(String s) {
		String regExp = "^\\[email sent to: (.+?)] "
				+ EventType.GLOBAL_DATE_REG_EXP;

		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(s);
		if (m.find()) {
			parameter = new LogParameter(m.group(1));
			dateText = m.group(2);
			dateOfEvent = LogParser.parseDate(dateText);
		}
	}
}
