package event.implementation;

import java.util.Date;

import event.Event;
import event.EventType;

public abstract class LogEvent implements Event {

	protected Date dateOfEvent;
	protected EventType typeOfEvent;

	@Override
	public Date getDateOfEvent() {
		return dateOfEvent;
	}

	@Override
	public EventType getTypeOfEvent() {
		return typeOfEvent;
	}

	public void setDateOfEvent(Date dateOfEvent) {
		this.dateOfEvent = dateOfEvent;
	}

	public void setTypeOfEvent(EventType typeOfEvent) {
		this.typeOfEvent = typeOfEvent;
	}

}
