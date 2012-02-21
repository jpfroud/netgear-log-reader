package event.myimplementation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.LogParser;
import event.EventType;
import event.IPAdress;
import event.MacAdress;
import event.implementation.AbstractEventWithSource;
import event.implementation.LogIPAdress;

public class DHCPEvent extends AbstractEventWithSource {

	private MacAdress macAdress;

	public DHCPEvent(String s) {
		line = s;
		parse(s);
	}

	public MacAdress getMacAdress() {
		return macAdress;
	}

	public void setMacAdress(MacAdress macAdress) {
		this.macAdress = macAdress;
	}

	public static final String NAME = "DHCP IP";

	@Override
	public String getName() {
		return NAME;
	}

	/**
	 * [DHCP IP: (192.168.0.100)] to MAC address 70:DE:E2:7F:B0:1E, Wednesday, Jan 01,2003 01:44:43
	 */
	@Override
	protected void parse(String s) {
		String regExp = "^\\[DHCP IP: \\("+IPAdress.IP_REG_EXP+"\\)\\] to MAC address "
				+ IPAdress.IP_REG_EXP + "\\, " + EventType.GLOBAL_DATE_REG_EXP;

		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(s);
		if (m.find()) {
			source = new LogIPAdress(m.group(1));
			dateText = m.group(2);
			dateOfEvent = LogParser.parseDate(dateText);
		}
	}
}
