import java.util.Scanner;

public class TaskHandler {

    private static final int MAX_NUMBER_OF_TASKS = 100;
    private static final int MAX_NUMBER_OF_DASHES = 60;
    private static final int NUMBER_OF_SPACES_TO_INDENT_HORIZONTAL_LINE = 4;
    private static final int NUMBER_OF_SPACES_TO_INDENT_WELCOME_MESSAGE = 5;
    private static final int NUMBER_OF_SPACES_TO_INDENT_TASK_ADDED_SUCCESS_MESSAGE = 5;
    private static final int NUMBER_OF_SPACES_TO_INDENT_ADDED_TASK_DESCRIPTION= 7;
    private static final int NUMBER_OF_SPACES_TO_INDENT_TASK_MARKED_SUCCESS_MESSAGE = 5;
    private static final int NUMBER_OF_SPACES_TO_INDENT_MARKED_TASK_DESCRIPTION= 7;
    private static final int NUMBER_OF_SPACES_TO_INDENT_TASK_UNMARKED_SUCCESS_MESSAGE = 5;
    private static final int NUMBER_OF_SPACES_TO_INDENT_UNMARKED_TASK_DESCRIPTION = 7;
    private static final int NUMBER_OF_SPACES_TO_INDENT_BYE_MESSAGE = 5;
    private static final int NUMBER_OF_SPACES_TO_INDENT_EACH_TASK_ADDED = 5;
    private static final int NUMBER_OF_SPACES_TO_INDENT_ERROR_MESSAGE = 5;

    private static final int INDEX_TO_CHECK_TO_GET_TASK_NUMBER_TO_MARK = 5;
    private static final int INDEX_TO_CHECK_TO_GET_TASK_NUMBER_TO_UNMARK = 7;
    private static final int STARTING_INDEX_OF_TODO_TASK_DESCRIPTION = 5;
    private static final int STARTING_INDEX_OF_EVENT_TASK_DESCRIPTION = 6;
    private static final int STARTING_INDEX_OF_DEADLINE_TASK_DESCRIPTION = 9;

    public enum LineLocation {
        TOP, BOTTOM;
    }

    public static void main(String[] args) {
        drawHorizontalLine(LineLocation.TOP);
        displayWelcomeMessage();
        drawHorizontalLine(LineLocation.BOTTOM);
        echoUser();
    }

    private static void addIndentation(int spacingCount) {
        for (int i = 0; i < spacingCount; i++) {
            System.out.print(" ");
        }
    }

    public static void displayWelcomeMessage() {
        addIndentation(NUMBER_OF_SPACES_TO_INDENT_WELCOME_MESSAGE);
        System.out.println("Hello! I'm TaskHandler");
        addIndentation(NUMBER_OF_SPACES_TO_INDENT_WELCOME_MESSAGE);
        System.out.println("Tell me what to do. I am happy to assist you.");
    }

    public static void drawHorizontalLine(LineLocation position) {
        addIndentation(NUMBER_OF_SPACES_TO_INDENT_HORIZONTAL_LINE);
        for (int i = 0; i < MAX_NUMBER_OF_DASHES; i += 1){
            System.out.print("-");
            if (i == MAX_NUMBER_OF_DASHES - 1) {
                System.out.println();
            }
        }
        if (position == LineLocation.BOTTOM) {
            System.out.println();
        }
    }

    public static void displayContentOfSpecificTask(Task[] userTaskArray, int taskIndex) {
        addIndentation(NUMBER_OF_SPACES_TO_INDENT_EACH_TASK_ADDED);
        System.out.print((taskIndex + 1) + ".");
        System.out.println(userTaskArray[taskIndex]);
    }

    public static void displayUserDataArray(Task[] userTaskArray, int numberOfTasks) {
        drawHorizontalLine(LineLocation.TOP);
        addIndentation(NUMBER_OF_SPACES_TO_INDENT_EACH_TASK_ADDED);
        System.out.println("Here are the tasks in your list");
        for (int taskIndex = 0; taskIndex < numberOfTasks; taskIndex += 1){
            displayContentOfSpecificTask(userTaskArray,  taskIndex);
        }
        drawHorizontalLine(LineLocation.BOTTOM);
    }

    public static void updateTaskStatus(Task[] userTaskArray, int taskNumber, String toDo) {
        userTaskArray[taskNumber - 1].setTaskStatus(toDo.equals("mark"));
    }

    public static void displayMarkedSuccessMessage(Task[] userTaskArray, int taskNumber) {
        addIndentation(NUMBER_OF_SPACES_TO_INDENT_TASK_MARKED_SUCCESS_MESSAGE);
        System.out.println("Nice! I've marked this task as done:");
        addIndentation(NUMBER_OF_SPACES_TO_INDENT_MARKED_TASK_DESCRIPTION);
        System.out.println("[X] " + userTaskArray[taskNumber - 1].taskDescription);
    }

    public static void displayUnmarkedSuccessMessage(Task[] userTaskArray, int taskNumber) {
        addIndentation(NUMBER_OF_SPACES_TO_INDENT_TASK_UNMARKED_SUCCESS_MESSAGE);
        System.out.println("OK, I've marked this task as not done yet:");
        addIndentation(NUMBER_OF_SPACES_TO_INDENT_UNMARKED_TASK_DESCRIPTION);
        System.out.println("[ ] " + userTaskArray[taskNumber - 1].taskDescription);
    }

    public static Todo getTodoInstance(String userInput) throws TaskHandlerException{
        if (userInput.trim().length() < STARTING_INDEX_OF_TODO_TASK_DESCRIPTION) {
            throw new TaskHandlerException("ERROR: Description of a 'todo' cannot be empty");
        }
        return new Todo(userInput.substring(STARTING_INDEX_OF_TODO_TASK_DESCRIPTION));
    }

    public static void handleEventTaskValidation(String userInput) throws TaskHandlerException{
        if (userInput.trim().length() < STARTING_INDEX_OF_EVENT_TASK_DESCRIPTION) {
            throw new TaskHandlerException("ERROR: Description, /from and /to timings of an 'event' task cannot be empty");
        }

        if (!userInput.contains(" /from")) {
            throw new TaskHandlerException("ERROR: No '/from' time specified for an 'event' task");
        }

        if (!userInput.contains(" /to")) {
            throw new TaskHandlerException("ERROR: No '/to' time specified for an 'event' task");
        }
    }

    public static Event getEventInstance(String userInput) throws TaskHandlerException{
        handleEventTaskValidation(userInput);

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
        if (userInput.trim().length() < STARTING_INDEX_OF_DEADLINE_TASK_DESCRIPTION) {
            throw new TaskHandlerException("ERROR: Description and /by date of a 'deadline' task cannot be empty");
        }

        if (!userInput.contains(" /by")) {
            throw new TaskHandlerException("ERROR: No '/by' date specified for a 'deadline' task");
        }
    }

    public static Deadline getDeadlineInstance(String userInput) throws TaskHandlerException{
        handleDeadlineTaskValidation(userInput);

        int endIndexOfDeadlineDescription = userInput.indexOf(" /by");
        String deadlineTaskDescription = userInput.substring(STARTING_INDEX_OF_DEADLINE_TASK_DESCRIPTION,
                endIndexOfDeadlineDescription);

        int deadlineDateIndex = userInput.indexOf("/by") + ("/by").length();
        String deadlineDate = userInput.substring(deadlineDateIndex);

        return new Deadline(deadlineTaskDescription, deadlineDate);
    }

    public static void displayTaskAddedMessage(Task taskObject, int taskIndex) {
        addIndentation(NUMBER_OF_SPACES_TO_INDENT_TASK_ADDED_SUCCESS_MESSAGE);
        System.out.println("Got it. I've added this task:");
        addIndentation(NUMBER_OF_SPACES_TO_INDENT_ADDED_TASK_DESCRIPTION);
        System.out.println(taskObject);
        addIndentation(NUMBER_OF_SPACES_TO_INDENT_TASK_ADDED_SUCCESS_MESSAGE);
        System.out.println("Now you have " + (taskIndex + 1) + " tasks in the list.");
    }

    public static void determineTaskTypeAndDisplay(Task[] userTaskArray,
            String userInput, int taskIndex) throws TaskHandlerException {

        if (userInput.startsWith("todo ")) {
            Todo todoTask = getTodoInstance(userInput);
            userTaskArray[taskIndex] = todoTask;

        } else if (userInput.startsWith("event ")) {
            Event eventTask = getEventInstance(userInput);
            userTaskArray[taskIndex] = eventTask;

        } else if (userInput.startsWith("deadline ")) {
            Deadline deadlineTask = getDeadlineInstance(userInput);
            userTaskArray[taskIndex] = deadlineTask;

        } else {
            throw new TaskHandlerException("ERROR: Unknown task type. Valid tasks are 'todo', 'event', 'deadline'");
        }
        displayTaskAddedMessage(userTaskArray[taskIndex], taskIndex);
    }

    public static void echoUser() {
        String userInput;
        Task[] userTaskArray = new Task[MAX_NUMBER_OF_TASKS];
        int numberOfTasks = 0;
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.print("");
            userInput = in.nextLine();
            if (userInput.equals("bye")) {
                break;

            } else if (userInput.equals("list")) {
                displayUserDataArray(userTaskArray, numberOfTasks);

            } else if (userInput.startsWith("mark ")) {
                int taskNumber = Integer.parseInt(userInput.substring(INDEX_TO_CHECK_TO_GET_TASK_NUMBER_TO_MARK));
                updateTaskStatus(userTaskArray, taskNumber, "mark");
                drawHorizontalLine(LineLocation.TOP);
                displayMarkedSuccessMessage(userTaskArray, taskNumber);
                drawHorizontalLine(LineLocation.BOTTOM);

            } else if (userInput.startsWith("unmark ")) {
                int taskNumber = Integer.parseInt(userInput.substring(INDEX_TO_CHECK_TO_GET_TASK_NUMBER_TO_UNMARK));
                updateTaskStatus(userTaskArray, taskNumber, "unmark");
                drawHorizontalLine(LineLocation.TOP);
                displayUnmarkedSuccessMessage(userTaskArray, taskNumber);
                drawHorizontalLine(LineLocation.BOTTOM);

            } else {
                drawHorizontalLine(LineLocation.TOP);
                try {
                    determineTaskTypeAndDisplay(userTaskArray, userInput, numberOfTasks);
                    numberOfTasks = numberOfTasks + 1;
                } catch (Exception e) {
                    addIndentation(NUMBER_OF_SPACES_TO_INDENT_ERROR_MESSAGE);
                    System.out.println(e.getMessage());
                }
                drawHorizontalLine(LineLocation.BOTTOM);
            }
        }
        drawHorizontalLine(LineLocation.TOP);
        addIndentation(NUMBER_OF_SPACES_TO_INDENT_BYE_MESSAGE);
        System.out.println("I hope I helped you! Bye for now");
        drawHorizontalLine(LineLocation.BOTTOM);
    }
}
