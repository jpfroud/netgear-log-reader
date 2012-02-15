package event.myimplementation;

import event.implementation.AbstractEventWithSource;

public class RemoteLoginFailureEvent extends AbstractEventWithSource {

	public static final String NAME = "Remote login failure";

	public RemoteLoginFailureEvent(String s) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		return NAME;
	}
}
