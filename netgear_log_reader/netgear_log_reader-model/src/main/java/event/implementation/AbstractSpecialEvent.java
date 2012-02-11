package event.implementation;

import event.Parameter;
import event.SpecialEvent;

public abstract class AbstractSpecialEvent extends AbstractEventType implements
		SpecialEvent {

	protected Parameter parameter;

	@Override
	public Parameter getParameter() {
		return parameter;
	}

}
