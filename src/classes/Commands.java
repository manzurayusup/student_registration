package classes;

import java.util.HashMap;

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
	
	static {
        for (Commands command : Commands.values()) {
            map.put(command.value, command);
        }
    }
	
	public static Commands valueOf(int value) {
        return (Commands) map.get(value);
    }

}
