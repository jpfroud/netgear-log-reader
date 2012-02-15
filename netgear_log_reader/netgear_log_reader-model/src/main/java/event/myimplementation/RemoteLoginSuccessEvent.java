package event.myimplementation;

import event.implementation.AbstractEventWithSource;

public class RemoteLoginSuccessEvent extends AbstractEventWithSource {

	public static final String NAME = "Remote login";

	public RemoteLoginSuccessEvent(String s) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		return NAME;
	}
}
