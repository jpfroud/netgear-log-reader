package event;

public interface IPAdress {

	/**
	 * 1 group : ip adress text. (ex : 192.168.1.1)
	 */
	public static final String IP_REG_EXP = "(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})";

	String getStringValue();

}
