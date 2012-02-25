package event.implementation;

import java.util.Date;

import event.EventType;

public abstract class AbstractEventType implements EventType {

	protected String description;

	protected Date dateOfEvent;

	protected String line;

	protected String dateText;

	public AbstractEventType(String line) {
		this.line = line;
		parse(line);
	}

	public String getDateText() {
		return dateText;
	}

	public void setDateText(String dateText) {
		this.dateText = dateText;
	}

	@Override
	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public void setDateOfEvent(Date dateOfEvent) {
		this.dateOfEvent = dateOfEvent;
	}

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

	protected abstract void parse(String s);

}
