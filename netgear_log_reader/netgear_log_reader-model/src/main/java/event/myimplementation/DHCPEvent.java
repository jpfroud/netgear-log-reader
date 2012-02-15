package event.myimplementation;

import event.MacAdress;
import event.implementation.AbstractEventWithSource;

public class DHCPEvent extends AbstractEventWithSource {

	private MacAdress macAdress;

	public DHCPEvent(String s) {
		// TODO Auto-generated constructor stub
	}

	public MacAdress getMacAdress() {
		return macAdress;
	}

	public void setMacAdress(MacAdress macAdress) {
		this.macAdress = macAdress;
	}

	public static final String NAME = "DHCP IP";

	@Override
	public String getName() {
		return NAME;
	}
}
