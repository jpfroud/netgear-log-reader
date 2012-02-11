package event.myimplementation;

import event.MacAdress;
import event.implementation.AbstractEventWithSource;

public class DHCPEvent extends AbstractEventWithSource {

	private MacAdress macAdress;

	public MacAdress getMacAdress() {
		return macAdress;
	}

	public void setMacAdress(MacAdress macAdress) {
		this.macAdress = macAdress;
	}

}
