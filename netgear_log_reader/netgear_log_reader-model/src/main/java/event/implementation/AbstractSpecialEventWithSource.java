package event.implementation;

import event.EventWithSource;
import event.IPAdress;

public abstract class AbstractSpecialEventWithSource extends
		AbstractSpecialEvent implements EventWithSource {

	protected IPAdress source;

	public AbstractSpecialEventWithSource(String s) {
		super(s);
	}

	@Override
	public IPAdress getSource() {
		return source;
	}

}
