package event.implementation;

import event.MacAdress;

public class LogMacAdress implements MacAdress {

	protected String stringValue;

	public LogMacAdress(String stringValue) {
		this.stringValue = stringValue;
	}

	@Override
	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

}
