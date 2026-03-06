/**
 * This class parses the user input. Validation is performed on the input string and
 * convert it into the corresponding {@code Task} objects - {@code Todo}, {@code Deadline}, {@code Event}.
 */
public class Parser {

    /**
     * Index of the description component of a {@code Todo} object.
     */
    private static final int STARTING_INDEX_OF_TODO_TASK_DESCRIPTION = 5;

    /**
     * Index of the description component of a {@code Event} object.
     */
    private static final int STARTING_INDEX_OF_EVENT_TASK_DESCRIPTION = 6;

    /**
     * Index of the description component of a {@code Deadline} object.
     */
    private static final int STARTING_INDEX_OF_DEADLINE_TASK_DESCRIPTION = 9;

    /**
     * Parses the user input and determines the task type, followed by validating if all
     * the components of the respective task type is present and finally returns
     * the {@code Task} instance.
     *
     * @param userInput The full user input entered by the user.
     * @return A {@code Task} instance (one of the three - {@code Todo}, {@code Deadline}, {@code Event}.
     * @throws TaskHandlerException If the user input is invalid or there are missing components.
     */
    public static Task determineTaskType(String userInput) throws TaskHandlerException {
        if (userInput.startsWith("todo")) {
            handleTodoTaskValidation(userInput);
            return getTodoInstance(userInput);

        } else if (userInput.startsWith("event")) {
            handleEventTaskValidation(userInput);
            return getEventInstance(userInput);

        } else if (userInput.startsWith("deadline")) {
            handleDeadlineTaskValidation(userInput);
            return getDeadlineInstance(userInput);

        } else {
            throw new TaskHandlerException("ERROR: Unknown task type. Valid tasks are 'todo', 'event', 'deadline'");
        }
    }

    /**
     * Validates the input string for a {@code Todo} task.
     * Checks for the existence of the description.
     *
     * @param userInput The input string starting with "todo".
     * @throws TaskHandlerException If {@code userInput} is invalid input or {@code Todo} task has empty description.
     */
    public static void handleTodoTaskValidation(String userInput) throws TaskHandlerException {
        if (userInput.length() >= STARTING_INDEX_OF_TODO_TASK_DESCRIPTION &&
                (userInput.charAt(STARTING_INDEX_OF_TODO_TASK_DESCRIPTION - 1) != ' ')) {
            throw new TaskHandlerException("ERROR: Unknown task type. Valid tasks are 'todo', 'event', 'deadline'");
        }

        if (userInput.trim().length() < STARTING_INDEX_OF_TODO_TASK_DESCRIPTION) {
            throw new TaskHandlerException("ERROR: Description of a 'todo' is empty. Please include it.");
        }
    }

    /**
     * Extracts the description from the validated user input and creates a new {@code Todo} instance.
     *
     * @param userInput The validated input string starting with "todo".
     * @return A {@code Todo} instance.
     */
    public static Todo getTodoInstance(String userInput) {
        return new Todo(userInput.substring(STARTING_INDEX_OF_TODO_TASK_DESCRIPTION));
    }

    /**
     * Validates the input string for a {@code Event} task.
     * Checks for the existence of description, /from time and /to time.
     *
     * @param userInput The input string starting with "event".
     * @throws TaskHandlerException If {@code userInput} is invalid input or {@code Event} task is missing any of the
     * three components - description, /from time or /to time.
     */
    public static void handleEventTaskValidation(String userInput) throws TaskHandlerException{
        if (userInput.length() >= STARTING_INDEX_OF_EVENT_TASK_DESCRIPTION &&
                (userInput.charAt(STARTING_INDEX_OF_EVENT_TASK_DESCRIPTION - 1) != ' ')) {
            throw new TaskHandlerException("ERROR: Unknown task type. Valid tasks are 'todo', 'event', 'deadline'");
        }
        if (userInput.trim().length() < STARTING_INDEX_OF_EVENT_TASK_DESCRIPTION) {
            throw new TaskHandlerException("ERROR: Description, /from and /to timings of an 'event' task are empty. " +
                    "Please include them.");
        }

        if (userInput.indexOf("/from") == STARTING_INDEX_OF_EVENT_TASK_DESCRIPTION) {
            throw new TaskHandlerException("ERROR: Description of an 'event' task is empty. Please include it.");
        }

        if (!userInput.contains(" /from") && !userInput.contains(" /to")) {
            throw new TaskHandlerException("ERROR: '/from' and '/to' time for an 'event' task are empty. " +
                    "Please include them.");
        }

        if (!userInput.contains(" /from")) {
            throw new TaskHandlerException("ERROR: '/from' time for an 'event' task is empty. Please include it.");
        }

        if (!userInput.contains(" /to")) {
            throw new TaskHandlerException("ERROR: '/to' time for an 'event' task is empty. Please include it.");
        }
    }

    /**
     * Extracts the description, /from time and /to time
     * from the validated user input and creates a new {@code Event} instance.
     *
     * @param userInput The validated input string starting with "event".
     * @return A {@code Event} instance.
     */
    public static Event getEventInstance(String userInput) {
        int endIndexOfEventDescription = userInput.indexOf(" /from");
        String eventTaskDescription = userInput.substring(STARTING_INDEX_OF_EVENT_TASK_DESCRIPTION,
                endIndexOfEventDescription);
        int eventFromTimeStartIndex = userInput.indexOf("/from") + ("/from").length();

        int eventFromTimeEndIndex = userInput.indexOf(" /to");
        String eventTaskFromTime = userInput.substring(eventFromTimeStartIndex, eventFromTimeEndIndex);

        int eventToTimeStartIndex = userInput.indexOf("/to") + ("/to").length();
        String eventTaskToTime = userInput.substring(eventToTimeStartIndex);

        return new Event(eventTaskDescription, eventTaskFromTime, eventTaskToTime);
    }

    /**
     * Validates the input string for a {@code Deadline} task.
     * Checks for the existence of description and /by date.
     *
     * @param userInput The input string starting with "deadline".
     * @throws TaskHandlerException If {@code userInput} is invalid input or {@code Deadline} task is missing
     * any of the two components - description or /by date.
     */
    public static void handleDeadlineTaskValidation(String userInput) throws TaskHandlerException{
        if (userInput.length() >= STARTING_INDEX_OF_DEADLINE_TASK_DESCRIPTION &&
                (userInput.charAt(STARTING_INDEX_OF_DEADLINE_TASK_DESCRIPTION - 1) != ' ')) {
            throw new TaskHandlerException("ERROR: Unknown task type. Valid tasks are 'todo', 'event', 'deadline'");
        }
        if (userInput.trim().length() < STARTING_INDEX_OF_DEADLINE_TASK_DESCRIPTION) {
            throw new TaskHandlerException("ERROR: Description and /by date of a 'deadline' task are empty. " +
                    "Please include them.");
        }

        if (userInput.indexOf("/by") == STARTING_INDEX_OF_DEADLINE_TASK_DESCRIPTION) {
            throw new TaskHandlerException("ERROR: Description of a 'deadline' task is empty. Please include it.");
        }

        if (!userInput.contains(" /by")) {
            throw new TaskHandlerException("ERROR: '/by' date of a 'deadline' task is empty. Please include it");
        }
    }

    /**
     * Extracts the description and /by date from the validated user input and creates a new {@code deadline} instance.
     *
     * @param userInput The validated input string starting with "deadline".
     * @return A {@code Deadline} instance.
     */
    public static Deadline getDeadlineInstance(String userInput) {
        int endIndexOfDeadlineDescription = userInput.indexOf(" /by");
        String deadlineTaskDescription = userInput.substring(STARTING_INDEX_OF_DEADLINE_TASK_DESCRIPTION,
                endIndexOfDeadlineDescription);

        int deadlineDateIndex = userInput.indexOf("/by") + ("/by").length();
        String deadlineDate = userInput.substring(deadlineDateIndex);

        return new Deadline(deadlineTaskDescription, deadlineDate);
    }
}