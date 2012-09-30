package parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import utils.EmlFileFilter;
import utils.IoUtils;
import event.EventType;
import event.implementation.EventFactory;

public class LogParser {

	private static final Map<String, Integer> MONTH_MAP = new HashMap<String, Integer>();
	static {
		int i = 0;
		MONTH_MAP.put("Jan", i++);
		MONTH_MAP.put("Feb", i++);
		MONTH_MAP.put("Mar", i++);
		MONTH_MAP.put("Apr", i++);
		MONTH_MAP.put("May", i++);
		MONTH_MAP.put("Jun", i++);
		MONTH_MAP.put("Jul", i++);
		MONTH_MAP.put("Aug", i++);
		MONTH_MAP.put("Sep", i++);
		MONTH_MAP.put("Oct", i++);
		MONTH_MAP.put("Nov", i++);
		MONTH_MAP.put("Dec", i++);
	}

	public static List<EventType> parse(String toParse) {
		List<EventType> listEvents = new ArrayList<EventType>();

		try {
			List<String> linesOfLog = IoUtils.convert(toParse);
			for (String s : linesOfLog) {
				EventType event = EventFactory.textLineToEvent(s);
				if (null != event) {
					listEvents.add(event);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return listEvents;
		}

		return listEvents;
	}

	public static List<EventType> parse(File input) throws IOException {
		if (input.isDirectory()) {
			return parseDirectory(input);
		}
		return parse(IoUtils.fileToString(input));
	}

	public static List<EventType> parseDirectory(File input)
			throws FileNotFoundException, IOException {
		List<EventType> result = new ArrayList<EventType>();
		File[] logFiles = input.listFiles(new EmlFileFilter());
		for (File f : logFiles) {
			result.addAll(parse(IoUtils.fileToString(f)));
		}
		return result;
	}

	/**
	 * Text to date ex : Friday, Jan 27,2012 13:01:01
	 * 
	 * @param dateText
	 * @return
	 */
	public static Date parseDate(String dateText) {
		if (null != dateText) {
			Pattern p = Pattern.compile(EventType.GROUP_DATE_REG_EXP);
			Matcher m = p.matcher(dateText);
			if (m.find()) {
				int i = 1;
				String month = m.group(i++);
				String dayOfMonth = m.group(i++);
				String year = m.group(i++);
				String hour = m.group(i++);
				String min = m.group(i++);
				String sec = m.group(i++);
				// to int
				int monthI = MONTH_MAP.get(month);
				int dayOfMonthI = Integer.parseInt(dayOfMonth);
				int yearI = Integer.parseInt(year);
				int hourI = Integer.parseInt(hour);
				int minI = Integer.parseInt(min);
				int secI = Integer.parseInt(sec);
				Calendar c = Calendar.getInstance();
				c.set(yearI, monthI, dayOfMonthI, hourI, minI, secI);
				return c.getTime();
			}
		}
		return null;
	}

	public static List<EventType> filter(List<EventType> listEvents,
			Class<?> classType) {
		List<EventType> result = new ArrayList<EventType>();
		for (EventType e : listEvents) {
			if (e.getClass().equals(classType)) {
				result.add(e);
			}
		}
		return result;
	}
}
