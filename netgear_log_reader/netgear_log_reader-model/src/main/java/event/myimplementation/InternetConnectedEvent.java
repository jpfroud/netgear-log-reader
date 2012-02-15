package event.myimplementation;

import event.implementation.AbstractEventWithSource;

public class InternetConnectedEvent extends AbstractEventWithSource {

	public static final String NAME = "Internet connected";

	public InternetConnectedEvent(String s) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		return NAME;
	}
}
