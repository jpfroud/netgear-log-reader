package event.myimplementation;

import event.implementation.AbstractEventType;

public class Self2WANEvent extends AbstractEventType {
	public static final String NAME = "Self2WAN ICMP type b Detected!";

	public Self2WANEvent(String s) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		return NAME;
	}
}