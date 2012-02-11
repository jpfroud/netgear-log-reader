package event.myimplementation;

import event.IPAdress;
import event.implementation.AbstractEventWithSource;

public class InternetEvent extends AbstractEventWithSource {

	private Boolean connected;

	public Boolean isConnected() {
		return connected;
	}

	public void setConnected(Boolean connected) {
		this.connected = connected;
		if (null != connected && Boolean.FALSE) {
			source = null;
		}
	}

	@Override
	public void setSource(IPAdress source) {
		super.setSource(source);
		if (null != source) {
			connected = Boolean.TRUE;
		}
	}

}
