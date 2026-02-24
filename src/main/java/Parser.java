import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;

public class Parser {
    private static final int STARTING_INDEX_OF_TODO_TASK_DESCRIPTION = 5;
    private static final int STARTING_INDEX_OF_EVENT_TASK_DESCRIPTION = 6;
    private static final int STARTING_INDEX_OF_DEADLINE_TASK_DESCRIPTION = 9;


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

    public static void handleTodoTaskValidation(String userInput) throws TaskHandlerException {
        if (userInput.length() >= STARTING_INDEX_OF_TODO_TASK_DESCRIPTION &&
                (userInput.charAt(STARTING_INDEX_OF_TODO_TASK_DESCRIPTION - 1) != ' ')) {
            throw new TaskHandlerException("ERROR: Unknown task type. Valid tasks are 'todo', 'event', 'deadline'");
        }

        if (userInput.trim().length() < STARTING_INDEX_OF_TODO_TASK_DESCRIPTION) {
            throw new TaskHandlerException("ERROR: Description of a 'todo' is empty. Please include it.");
        }
    }

    public static Todo getTodoInstance(String userInput) {
        return new Todo(userInput.substring(STARTING_INDEX_OF_TODO_TASK_DESCRIPTION));
    }

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
            throw new TaskHandlerException("ERROR: '/from' and '/to' time for an 'event' task are empty. Please include them.");
        }

        if (!userInput.contains(" /from")) {
            throw new TaskHandlerException("ERROR: '/from' time for an 'event' task is empty. Please include it.");
        }

        if (!userInput.contains(" /to")) {
            throw new TaskHandlerException("ERROR: '/to' time for an 'event' task is empty. Please include it.");
        }
    }

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

    public static void handleDeadlineTaskValidation(String userInput) throws TaskHandlerException{
        if (userInput.length() >= STARTING_INDEX_OF_DEADLINE_TASK_DESCRIPTION &&
                (userInput.charAt(STARTING_INDEX_OF_DEADLINE_TASK_DESCRIPTION - 1) != ' ')) {
            throw new TaskHandlerException("ERROR: Unknown task type. Valid tasks are 'todo', 'event', 'deadline'");
        }
        if (userInput.trim().length() < STARTING_INDEX_OF_DEADLINE_TASK_DESCRIPTION) {
            throw new TaskHandlerException("ERROR: Description and /by date of a 'deadline' task are empty. Please include them.");
        }

        if (userInput.indexOf("/by") == STARTING_INDEX_OF_DEADLINE_TASK_DESCRIPTION) {
            throw new TaskHandlerException("ERROR: Description of a 'deadline' task is empty. Please include it.");
        }

        if (!userInput.contains(" /by")) {
            throw new TaskHandlerException("ERROR: '/by' date of a 'deadline' task is empty. Please include it");
        }
    }

    public static Deadline getDeadlineInstance(String userInput) {
        int endIndexOfDeadlineDescription = userInput.indexOf(" /by");
        String deadlineTaskDescription = userInput.substring(STARTING_INDEX_OF_DEADLINE_TASK_DESCRIPTION,
                endIndexOfDeadlineDescription);

        int deadlineDateIndex = userInput.indexOf("/by") + ("/by").length();
        String deadlineDate = userInput.substring(deadlineDateIndex);

        return new Deadline(deadlineTaskDescription, deadlineDate);
    }
}
