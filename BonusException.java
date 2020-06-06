
public class BonusException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// An exception class - In case of a high owner bonus.
	public BonusException() {
		super("!! Owner bonus is too high !!");
	}
}
