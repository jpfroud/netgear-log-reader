package event.implementation;

import event.EventWithDestination;
import event.IPAdress;

public abstract class AbstractEventWithDestination extends
		AbstractEventWithSource implements EventWithDestination {

	IPAdress destination;

	@Override
	public IPAdress getDestination() {
		return destination;
	}

}
