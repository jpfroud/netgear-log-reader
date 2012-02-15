package event.myimplementation;

import event.implementation.AbstractEventType;

public class TimeSynchroEvent extends AbstractEventType {
	public static final String NAME = "Time synchronized with NTP server";

	public TimeSynchroEvent(String s) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		return NAME;
	}
}
