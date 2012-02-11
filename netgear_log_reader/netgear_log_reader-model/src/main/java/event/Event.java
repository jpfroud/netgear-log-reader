package event;

import java.util.Date;

public interface Event {

	Date getDateOfEvent();
	
	EventType getTypeOfEvent();
	
}
