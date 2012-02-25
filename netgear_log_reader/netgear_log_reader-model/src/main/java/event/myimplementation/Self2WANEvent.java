package event.myimplementation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.LogParser;
import event.EventType;
import event.implementation.AbstractEventType;

public class Self2WANEvent extends AbstractEventType {
	public static final String NAME = "Self2WAN ICMP type b Detected!";

	public Self2WANEvent(String s) {
		super(s);
	}

	@Override
	public String getName() {
		return NAME;
	}

	/**
	 * [Self2WAN ICMP type b Detected!] To prevent from revealing router's
	 * activity, this packet is dropped! Friday, Jan 27,2012 22:05:11
	 */
	@Override
	protected void parse(String s) {
		String regExp = "\\[Self2WAN ICMP type b Detected!\\] To prevent from revealing router's activity, this packet is dropped! "
				+ EventType.GLOBAL_DATE_REG_EXP;

		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(s);
		if (m.find()) {
			dateText = m.group(1);
			dateOfEvent = LogParser.parseDate(dateText);
		}
	}
}
