package event;

public interface MacAdress {

	/**
	 * Mac adress reg exp (1 group)
	 */
	String MAC_REG_EXP = "(([0-9a-fA-F][0-9a-fA-F]:){5}[0-9a-fA-F][0-9a-fA-F])";

	String getStringValue();

}
