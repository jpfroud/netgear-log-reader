package event.myimplementation;

import event.implementation.AbstractEventType;

public class InternetDisconnectedEvent extends AbstractEventType {

	public static final String NAME = "Internet disconnected";

	public InternetDisconnectedEvent(String s) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		return NAME;
	}
}
