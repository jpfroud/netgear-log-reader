package event.implementation;

import event.IPAdress;

public class LogIPAdress implements IPAdress {

	protected String stringValue;

	public LogIPAdress(String group) {
		stringValue = group;
	}

	@Override
	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

}
