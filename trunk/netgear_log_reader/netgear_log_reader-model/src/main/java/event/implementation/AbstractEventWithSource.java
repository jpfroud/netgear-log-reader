package event.implementation;

import event.EventWithSource;
import event.IPAdress;

public abstract class AbstractEventWithSource extends AbstractEventType
		implements EventWithSource {
	protected IPAdress source;

	public AbstractEventWithSource(String s) {
		super(s);
	}

	@Override
	public IPAdress getSource() {
		return source;
	}

	public void setSource(IPAdress source) {
		this.source = source;
	}

}
