package event.myimplementation;

import event.implementation.AbstractEventWithSource;

public class AdminLoginSuccessEvent extends AbstractEventWithSource {

	public AdminLoginSuccessEvent(String s) {
		// TODO Auto-generated constructor stub
	}

	public static final String NAME = "Admin login";

	@Override
	public String getName() {
		return NAME;
	}
}
