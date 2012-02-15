package parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import utils.IoUtils;
import event.EventFactory;
import event.EventType;

public class LogParser {

	public static List<EventType> parse(String toParse) {
		List<EventType> listEvents = new ArrayList<EventType>();

		try {
			List<String> linesOfLog = IoUtils.convert(toParse);
			for (String s : linesOfLog) {
				listEvents.add(EventFactory.textLineToEvent(s));
			}
		} catch (IOException e) {
			e.printStackTrace();
			return listEvents;
		}

		return listEvents;
	}

	public static List<EventType> parse(File input) throws IOException {
		return parse(IoUtils.fileToString(input));
	}
}
