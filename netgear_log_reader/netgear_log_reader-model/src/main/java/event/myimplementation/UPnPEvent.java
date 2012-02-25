package event.myimplementation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.LogParser;
import event.EventType;
import event.IPAdress;
import event.implementation.AbstractSpecialEventWithSource;
import event.implementation.LogIPAdress;
import event.implementation.LogParameter;

public class UPnPEvent extends AbstractSpecialEventWithSource {
	public static final String NAME = "UPnP set event";

	public UPnPEvent(String s) {
		super(s);
	}

	@Override
	public String getName() {
		return NAME;
	}

	/**
	 * [UPnP set event: Public_UPNP_C3] from source 192.168.0.101, Wednesday,
	 * Jan 01,2003 09:40:29
	 */
	@Override
	protected void parse(String s) {
		String regExp = "^\\[UPnP set event: (.+?)\\] from source "
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
