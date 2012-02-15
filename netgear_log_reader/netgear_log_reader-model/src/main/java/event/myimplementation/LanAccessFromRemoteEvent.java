package event.myimplementation;

import event.implementation.AbstractEventWithDestination;

public class LanAccessFromRemoteEvent extends AbstractEventWithDestination {

	private Integer sourcePort;
	private Integer destinationPort;

	public LanAccessFromRemoteEvent(String s) {
		// TODO Auto-generated constructor stub
	}

	public Integer getSourcePort() {
		return sourcePort;
	}

	public void setSourcePort(Integer sourcePort) {
		this.sourcePort = sourcePort;
	}

	public Integer getDestinationPort() {
		return destinationPort;
	}

	public void setDestinationPort(Integer destinationPort) {
		this.destinationPort = destinationPort;
	}

	public static final String NAME = "LAN access from remote";

	@Override
	public String getName() {
		return NAME;
	}
}
