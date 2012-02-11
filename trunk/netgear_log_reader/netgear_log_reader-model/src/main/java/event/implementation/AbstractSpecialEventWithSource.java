package event.implementation;

import event.EventWithSource;
import event.IPAdress;

public class AbstractSpecialEventWithSource extends AbstractSpecialEvent
		implements EventWithSource {

	protected IPAdress source;

	@Override
	public IPAdress getSource() {
		return source;
	}

}
