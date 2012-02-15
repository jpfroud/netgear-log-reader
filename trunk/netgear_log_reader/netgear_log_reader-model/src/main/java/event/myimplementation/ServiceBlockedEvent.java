package event.myimplementation;

import event.implementation.AbstractSpecialEventWithSource;

public class ServiceBlockedEvent extends AbstractSpecialEventWithSource {
	public static final String NAME = "Service blocked";

	public ServiceBlockedEvent(String s) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		return NAME;
	}
}
