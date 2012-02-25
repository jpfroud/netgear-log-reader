package event.implementation;

import event.EventWithDestination;
import event.IPAdress;

public abstract class AbstractEventWithDestination extends
		AbstractEventWithSource implements EventWithDestination {

	protected IPAdress destination;

	public AbstractEventWithDestination(String s) {
		super(s);
	}

	@Override
	public IPAdress getDestination() {
		return destination;
	}

}
