package event.myimplementation;

import event.implementation.AbstractEventWithSource;

public class AdminLoginFailureEvent extends AbstractEventWithSource {

	public AdminLoginFailureEvent(String s) {
		// TODO Auto-generated constructor stub
	}

	public static final String NAME = "Admin login failure";

	@Override
	public String getName() {
		return NAME;
	}

}
