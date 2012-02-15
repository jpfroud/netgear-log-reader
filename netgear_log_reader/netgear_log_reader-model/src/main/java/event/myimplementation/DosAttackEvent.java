package event.myimplementation;

import event.implementation.AbstractSpecialEventWithSource;

public class DosAttackEvent extends AbstractSpecialEventWithSource {

	public static final String NAME = "DoS attack";

	public DosAttackEvent(String s) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		return NAME;
	}
}
