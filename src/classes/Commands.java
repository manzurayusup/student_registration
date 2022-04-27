package classes;

import java.util.HashMap;
/**
 * Enums corresponding with the numbered menu options.
 * @author manzura, khushi, irtaza, yab
 *
 */
public enum Commands {
	NON_COMMAND(0),
	REGISTER_FOR_COURSE(1),
	PRINT_COURSE(2),
	PRINT_COURSES_ALL(3),
	PRINT_COURSES_ENROLLED(4),
	PRINT_COURSES_WAITLISTED(5),
	QUIT(6);

	final int value;
	private static HashMap<Integer, Commands> map = new HashMap<Integer, Commands>();
	
	Commands(int i) {
		this.value = i;
	}
	/**
	 * This static method maps the enum values to the enums. 
	 */
	static {
        for (Commands command : Commands.values()) {
            map.put(command.value, command);
        }
    }
	/**
	 * Returns the enum with value "value". 
	 * @param value
	 * @return
	 */
	public static Commands valueOf(int value) {
        return (Commands) map.get(value);
    }

}
