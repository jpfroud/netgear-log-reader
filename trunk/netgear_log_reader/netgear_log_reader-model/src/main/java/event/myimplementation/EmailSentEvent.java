package event.myimplementation;

import event.implementation.AbstractSpecialEventWithSource;

public class EmailSentEvent extends AbstractSpecialEventWithSource {

	public static final String NAME = "email sent to";

	public EmailSentEvent(String s) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		return NAME;
	}
}
