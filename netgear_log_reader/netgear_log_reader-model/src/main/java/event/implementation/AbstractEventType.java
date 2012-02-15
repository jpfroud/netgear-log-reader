package event.implementation;

import java.util.Date;

import event.EventType;

public abstract class AbstractEventType implements EventType {

	protected String description;

	protected Date dateOfEvent;

	@Override
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public Date getDateOfEvent() {
		return dateOfEvent;
	}

	@Override
	public String getName() {
		return null;
	}

}
