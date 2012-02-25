package event.myimplementation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.LogParser;
import event.EventType;
import event.IPAdress;
import event.implementation.AbstractSpecialEventWithSource;
import event.implementation.LogIPAdress;
import event.implementation.LogParameter;

public class DosAttackEvent extends AbstractSpecialEventWithSource {

	public static final String NAME = "DoS attack";

	public DosAttackEvent(String s) {
		super(s);
	}

	@Override
	public String getName() {
		return NAME;
	}

	/**
	 * [DoS attack: ACK Scan] attack packets in last 20 sec from ip
	 * [209.85.148.193], Wednesday, Feb 01,2012 17:56:33
	 */
	@Override
	protected void parse(String s) {
		String regExp = "^\\[DoS attack: (.+?)\\] attack packets in last 20 sec from ip \\["
				+ IPAdress.IP_REG_EXP + "\\], " + EventType.GLOBAL_DATE_REG_EXP;

		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(s);
		if (m.find()) {
			source = new LogIPAdress(m.group(2));
			dateText = m.group(3);
			dateOfEvent = LogParser.parseDate(dateText);
			parameter = new LogParameter(m.group(1));
		}
	}
}
