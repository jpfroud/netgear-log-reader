package event.implementation;

import event.Parameter;
import event.SpecialEvent;

public abstract class AbstractSpecialEvent extends AbstractEventType implements
		SpecialEvent {

	protected Parameter parameter;

	public AbstractSpecialEvent(String s) {
		super(s);
	}

	@Override
	public Parameter getParameter() {
		return parameter;
	}

}
