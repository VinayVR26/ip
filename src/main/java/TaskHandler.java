import java.util.Scanner;

public class TaskHandler {

    /**
     * The maximum number of tasks
     */
    private static final int MAX_NUMBER_OF_TASKS = 100;

    /**
     * The number of horizontal dashes in each horizontal line
     */
    private static final int MAX_NUMBER_OF_DASHES = 60;


    /**
     * The index position to start traversing to get the task number to mark
     */
    private static final int INDEX_TO_CHECK_TO_GET_TASK_NUMBER_TO_MARK = 5;

    /**
     * The index position to start traversing to get the task number to unmark
     */
    private static final int INDEX_TO_CHECK_TO_GET_TASK_NUMBER_TO_UNMARK = 7;

    /**
     * Starting index position of description of a Todo task
     */
    private static final int STARTING_INDEX_OF_TODO_TASK_DESCRIPTION = 5;

    /**
     * Starting index position of description of an Event task
     */
    private static final int STARTING_INDEX_OF_EVENT_TASK_DESCRIPTION = 6;

    /**
     * Number of components in an Event task which are
     * 1) Task description
     * 2) From Time
     * 3) End Time
     */
    private static final int NUMBER_OF_COMPONENTS_IN_EVENT_TASK = 3;

    /**
     * Starting index position of description of a Deadline task
     */
    private static final int STARTING_INDEX_OF_DEADLINE_TASK_DESCRIPTION = 9;

    /**
      * Main entry point of the code
      * Initialises the code and starts interaction with the user
     */
    public static void main(String[] args) {
        drawHorizontalLine("top");
        displayWelcomeMessage();
        drawHorizontalLine("bottom");
        echoUser();
    }

    private static void addIndentation(int spacingCount) {
        for (int i = 0; i < spacingCount; i++) {
            System.out.print(" ");
        }
    }

    /**
     * Displays the welcome message to the user
     */
    public static void displayWelcomeMessage() {
        addIndentation(4);
        System.out.println("Hello! I'm TaskHandler");
        addIndentation(4);
        System.out.println("Tell me what to do. I am happy to assist you.\n");
    }

    /**
     * @param position horizontal line on the top/bottom of each section of text
     */
    public static void drawHorizontalLine(String position) {
        System.out.print("    ");
        for (int i = 0; i < MAX_NUMBER_OF_DASHES; i += 1){
            System.out.print("-");

            if (i == MAX_NUMBER_OF_DASHES - 1) {
                System.out.println();
            }
        }
        if (position.equals("bottom")) {
            System.out.println();
        }
    }

    /**
     * @param userTaskArray array of Task objects of the user's tasks
     * @param taskIndex index of the task in userTaskArray to be displayed
     */
    public static void displayContentOfSpecificTask(Task[] userTaskArray, int taskIndex) {
        addIndentation(5);
        System.out.print((taskIndex + 1) + ".");
        System.out.println(userTaskArray[taskIndex]);
    }

    /**
     * @param userTaskArray array of Task objects of the user's tasks
     * @param numberOfItems number of user's tasks
     */
    public static void displayUserDataArray(Task[] userTaskArray, int numberOfItems) {
        drawHorizontalLine("top");
        addIndentation(5);
        System.out.println("Here are the tasks in your list");
        for (int taskIndex = 0; taskIndex < numberOfItems; taskIndex += 1){
            displayContentOfSpecificTask(userTaskArray,  taskIndex);
        }
        drawHorizontalLine("bottom");
    }

    /**
     * @param userTaskArray array of Task objects of the user's tasks
     * @param taskNumber task number as shown when "list" is inputted
     * @param toDo user input to mark or unmark the task
     */
    public static void updateTaskStatus(Task[] userTaskArray, int taskNumber, String toDo) {
        userTaskArray[taskNumber - 1].setTaskStatus(toDo.equals("mark"));
    }


    /**
     * @param userTaskArray array of Task objects of the user's tasks
     * @param taskNumber task number as shown when "list" is inputted
     */
    public static void displayMarkedSuccessMessage(Task[] userTaskArray, int taskNumber) {
        addIndentation(5);
        System.out.println("Nice! I've marked this task as done:");
        addIndentation(7);
        System.out.println("[X] " + userTaskArray[taskNumber - 1].taskDescription);
    }

    /**
     * @param userTaskArray array of Task objects of the user's tasks
     * @param taskNumber ask number as shown when "list" is inputted
     */
    public static void displayUnmarkedSuccessMessage(Task[] userTaskArray, int taskNumber) {
        addIndentation(5);
        System.out.println("OK, I've marked this task as not done yet:");
        addIndentation(7);
        System.out.println("[ ] " + userTaskArray[taskNumber - 1].taskDescription);
    }

    public static Todo getTodoInstance(String userInput) {
        return new Todo(userInput.substring(STARTING_INDEX_OF_TODO_TASK_DESCRIPTION));
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

    public static Deadline getDeadlineInstance(String userInput) {
        int endIndexOfDeadlineDescription = userInput.indexOf(" /by");
        String deadlineTaskDescription = userInput.substring(STARTING_INDEX_OF_DEADLINE_TASK_DESCRIPTION,
                endIndexOfDeadlineDescription);

        int deadlineDateIndex = userInput.indexOf("/by") + ("/by").length();
        String deadlineDate = userInput.substring(deadlineDateIndex);

        return new Deadline(deadlineTaskDescription, deadlineDate);
    }

    public static void displayTaskAddedMessage(Task taskInstance, int taskNumber) {
        addIndentation(5);
        System.out.println("Got it. I've added this task:");
        addIndentation(7);
        System.out.println(taskInstance);
        addIndentation(5);
        System.out.println("Now you have " + (taskNumber + 1) + " tasks in the list.");
    }

    public static void determineTaskTypeAndDisplay(Task[] userTaskArray, String userInput, int taskNumber) {
        if (userInput.startsWith("todo")) {
            Todo todoTask = getTodoInstance(userInput);
            userTaskArray[taskNumber] = todoTask;

        } else if (userInput.startsWith("event")) {
            Event eventTask = getEventInstance(userInput);
            userTaskArray[taskNumber] = eventTask;

        } else {
            Deadline deadlineTask = getDeadlineInstance(userInput);
            userTaskArray[taskNumber] = deadlineTask;
        }

        displayTaskAddedMessage(userTaskArray[taskNumber], taskNumber);
    }

    /**
     * Main logic of the task bot to handle user input.
     */
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
                drawHorizontalLine("top");
                displayMarkedSuccessMessage(userTaskArray, taskNumber);
                drawHorizontalLine("bottom");

            } else if (userInput.startsWith("unmark ")) {
                int taskNumber = Integer.parseInt(userInput.substring(INDEX_TO_CHECK_TO_GET_TASK_NUMBER_TO_UNMARK));
                updateTaskStatus(userTaskArray, taskNumber, "unmark");
                drawHorizontalLine("top");
                displayUnmarkedSuccessMessage(userTaskArray, taskNumber);
                drawHorizontalLine("bottom");

            } else {
                /*Task aTask = new Task(userInput);
                userTaskArray[numberOfTasks] = aTask;
                numberOfTasks = numberOfTasks + 1;*/
                drawHorizontalLine("top");
                determineTaskTypeAndDisplay(userTaskArray, userInput, numberOfTasks);
                numberOfTasks = numberOfTasks + 1;
                //System.out.println("     Got it. I've added this task: " + userInput);
                drawHorizontalLine("bottom");
            }
        }
        drawHorizontalLine("top");
        addIndentation(5);
        System.out.println("I hope I helped you! Bye for now");
        drawHorizontalLine("bottom");
    }
}
