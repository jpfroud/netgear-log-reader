package event;

import java.util.Date;

public interface EventType {

	/**
	 * 6 groups :
	 * <ul>
	 * <li>1 : Month (ex: Jan)</li>
	 * <li>2 : day of month (ex: 07)</li>
	 * <li>3 : year (ex: 2012)</li>
	 * <li>4 : hour (ex : 13)</li>
	 * <li>5 : min (ex : 15)</li>
	 * <li>6 : sec (ex : 03)</li>
	 * </ul>
	 */
	public static final String GROUP_DATE_REG_EXP = "(\\w{3}) (\\d{2}),(\\d{4}) (\\d{2}):(\\d{2}):(\\d{2})";

	/**
	 * 1 group : ex : Friday, Jan 27,2012 19:31:50
	 */
	public static final String GLOBAL_DATE_REG_EXP = ".+?\\, (\\w{3} \\d{2},\\d{4} \\d{2}:\\d{2}:\\d{2})";

	String getName();

	Date getDateOfEvent();

	String getDescription();

	String getLine();

}
