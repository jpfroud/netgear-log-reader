package event.myimplementation;

import event.implementation.AbstractSpecialEvent;

public class InitializedEvent extends AbstractSpecialEvent {

	public static final String NAME = "Initialized, firmware version";

	public InitializedEvent(String s) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		return NAME;
	}
}
