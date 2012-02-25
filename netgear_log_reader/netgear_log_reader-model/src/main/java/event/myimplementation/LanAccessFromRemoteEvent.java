package event.myimplementation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.LogParser;
import event.EventType;
import event.IPAdress;
import event.implementation.AbstractEventWithDestination;
import event.implementation.LogIPAdress;

public class LanAccessFromRemoteEvent extends AbstractEventWithDestination {

	private Integer sourcePort;
	private Integer destinationPort;

	public LanAccessFromRemoteEvent(String s) {
		super(s);
	}

	public Integer getSourcePort() {
		return sourcePort;
	}

	public void setSourcePort(Integer sourcePort) {
		this.sourcePort = sourcePort;
	}

	public Integer getDestinationPort() {
		return destinationPort;
	}

	public void setDestinationPort(Integer destinationPort) {
		this.destinationPort = destinationPort;
	}

	public static final String NAME = "LAN access from remote";

	@Override
	public String getName() {
		return NAME;
	}

	/**
	 * [LAN access from remote] from 178.249.112.201:41742 to 192.168.0.112:445
	 * Wednesday, Jan 01,2003 09:31:16
	 */
	@Override
	protected void parse(String s) {
		String regExp = "^\\[LAN access from remote\\] from "
				+ IPAdress.IP_REG_EXP + ":(\\d+?) to " + IPAdress.IP_REG_EXP
				+ ":(\\d+?) " + EventType.GLOBAL_DATE_REG_EXP;

		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(s);
		if (m.find()) {
			source = new LogIPAdress(m.group(1));
			sourcePort = new Integer(m.group(2));
			destination = new LogIPAdress(m.group(3));
			destinationPort = new Integer(m.group(4));
			dateText = m.group(5);
			dateOfEvent = LogParser.parseDate(dateText);
		}
	}
}
