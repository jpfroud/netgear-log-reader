package event.implementation;

import event.Parameter;

public class LogParameter implements Parameter {

	protected String name;

	@Override
	public String getName() {
		return name;
	}

	public LogParameter(String name) {
		super();
		this.name = name;
	}

}
