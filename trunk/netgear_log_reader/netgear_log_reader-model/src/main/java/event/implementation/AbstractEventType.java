package event.implementation;

import event.EventType;

public abstract class AbstractEventType implements EventType {

	protected String name;
	protected String description;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
