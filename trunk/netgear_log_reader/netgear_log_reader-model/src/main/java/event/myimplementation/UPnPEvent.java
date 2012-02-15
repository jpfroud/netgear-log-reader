package event.myimplementation;

import event.implementation.AbstractSpecialEventWithSource;

public class UPnPEvent extends AbstractSpecialEventWithSource {
	public static final String NAME = "UPnP set event";

	public UPnPEvent(String s) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		return NAME;
	}
}
