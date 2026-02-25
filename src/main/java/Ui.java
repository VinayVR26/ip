import java.util.Scanner;
import java.util.ArrayList;

public class Ui {

    /**
     * Maximum number of '-' characters to form a horizontal divider line
     */
    private static final int MAX_NUMBER_OF_DASHES = 60;

    /**
     * Number of spaces ' ' to indent the horizontal divider line
     */
    private static final int NUMBER_OF_SPACES_TO_INDENT_HORIZONTAL_LINE = 4;

    /**
     * Number of spaces ' ' to indent the welcome message
     */
    private static final int NUMBER_OF_SPACES_TO_INDENT_WELCOME_MESSAGE = 5;

    /**
     * Number of spaces ' ' to indent the task added success message
     */
    private static final int NUMBER_OF_SPACES_TO_INDENT_TASK_ADDED_SUCCESS_MESSAGE = 5;

    /**
     * Number of spaces ' ' to indent the description of the task that was added
     */
    private static final int NUMBER_OF_SPACES_TO_INDENT_ADDED_TASK_DESCRIPTION= 7;

    /**
     * Number of spaces ' ' to indent the task that was marked
     */
    private static final int NUMBER_OF_SPACES_TO_INDENT_TASK_MARKED_SUCCESS_MESSAGE = 5;

    /**
     * Number of spaces ' ' to indent the task details
     */
    private static final int NUMBER_OF_SPACES_TO_INDENT_TASK_DETAILS= 7;

    /**
     * Number of spaces ' ' to indent the task unmarked message
     */
    private static final int NUMBER_OF_SPACES_TO_INDENT_TASK_UNMARKED_SUCCESS_MESSAGE = 5;

    /**
     * Number of spaces ' ' to indent the task deleted message
     */
    private static final int NUMBER_OF_SPACES_TO_INDENT_TASK_DELETE_SUCCESS_MESSAGE= 5;

    /**
     * Number of spaces ' ' to indent the task matching message
     */
    private static final int NUMBER_OF_SPACES_TO_INDENT_MATCHING_TASK = 5;

    /**
     * Number of spaces ' ' to indent the bye message
     */
    private static final int NUMBER_OF_SPACES_TO_INDENT_BYE_MESSAGE = 5;

    /**
     * Number of spaces ' ' to indent each task displayed
     */
    private static final int NUMBER_OF_SPACES_TO_INDENT_EACH_TASK_ADDED = 5;

    /**
     * Number of spaces ' ' to indent an error message
     */
    private static final int NUMBER_OF_SPACES_TO_INDENT_ERROR_MESSAGE = 5;

    /**
     * To read user input from the standard input
     */
    private Scanner in;

    /**
     * Indicates the position of the horizontal divider line relative to the message block.
     */
    public enum LineLocation {
        TOP, BOTTOM
    }

    /**
     * Creates an instance of {@code Ui} and initializes the scanner.
     */
    public Ui() {
        this.in = new Scanner(System.in);
    }

    /**
     * Reads the input provided by the user from the standard input.
     *
     * @return The string entered by the user.
     */
    public String readUserInput() {
        System.out.print("");
        return in.nextLine();
    }

    /**
     * Prints {@code spacingCount} number of space characters ' ' to the console.
     *
     * @param spacingCount The number of space charatcers ' ' to print.
     */
    private static void addIndentation(int spacingCount) {
        for (int i = 0; i < spacingCount; i++) {
            System.out.print(" ");
        }
    }

    /**
     * Draws a dashed horizontal divider line to seperate different message blocks
     * An extra newline character is added if th position is {@code LineLocation.BOTTOM}
     *
     * @param position The {@code LineLocation} indicating if this line is above or below the
     * message block.
     */
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

    /**
     * Displays the welcome message to the user when the application starts.
     */
    public static void displayWelcomeMessage() {
        drawHorizontalLine(LineLocation.TOP);
        addIndentation(NUMBER_OF_SPACES_TO_INDENT_WELCOME_MESSAGE);
        System.out.println("Hello! I'm TaskHandler");
        addIndentation(NUMBER_OF_SPACES_TO_INDENT_WELCOME_MESSAGE);
        System.out.println("Tell me what to do. I am happy to assist you.");
        drawHorizontalLine(LineLocation.BOTTOM);
    }

    /**
     * Displays all the tasks in {@code userTaskArrayList}.
     * Each task is printed after its corresponding task number
     *
     * @param userTaskArrayList The {@code TaskList} object containing the tasks to be displayed.
     */
    public static void displayUserDataArray(TaskList userTaskArrayList) {
        drawHorizontalLine(LineLocation.TOP);
        addIndentation(NUMBER_OF_SPACES_TO_INDENT_EACH_TASK_ADDED);
        System.out.println("Here are the tasks in your list");
        for (int taskIndex = 0; taskIndex < userTaskArrayList.getSize(); taskIndex += 1) {
            addIndentation(NUMBER_OF_SPACES_TO_INDENT_EACH_TASK_ADDED);
            System.out.print((taskIndex + 1) + ".");
            System.out.println(userTaskArrayList.getTask(taskIndex + 1));
        }
        drawHorizontalLine(LineLocation.BOTTOM);
    }

    /**
     * Displays a success message indicating that the task instructed by the user to be marked
     * is marked successfully.
     *
     * @param taskMarked The {@code Task} object that was marked.
     */
    public static void displayMarkedSuccessMessage(Task taskMarked) {
        drawHorizontalLine(LineLocation.TOP);
        addIndentation(NUMBER_OF_SPACES_TO_INDENT_TASK_MARKED_SUCCESS_MESSAGE);
        System.out.println("Nice! I've marked this task as done:");
        addIndentation(NUMBER_OF_SPACES_TO_INDENT_TASK_DETAILS);
        System.out.println("[X] " + taskMarked.taskDescription);
        drawHorizontalLine(LineLocation.BOTTOM);
    }

    /**
     * Displays a success message indicating that the task instructed by the user to be unmarked
     * is unmarked successfully.
     *
     * @param taskUnmarked The {@code Task} object that was unmarked.
     */
    public static void displayUnmarkedSuccessMessage(Task taskUnmarked) {
        drawHorizontalLine(LineLocation.TOP);
        addIndentation(NUMBER_OF_SPACES_TO_INDENT_TASK_UNMARKED_SUCCESS_MESSAGE);
        System.out.println("OK, I've marked this task as not done yet:");
        addIndentation(NUMBER_OF_SPACES_TO_INDENT_TASK_DETAILS);
        System.out.println("[ ] " + taskUnmarked.taskDescription);
        drawHorizontalLine(LineLocation.BOTTOM);
    }

    /**
     * Displays a success message indicting that the task instructed by the user to be deleted
     * is deleted successfully and the new total number of tasks left in the list.
     *
     * @param deletedTask The {@code Task} object that was deleted.
     * @param sizeOfArrayList The number of {@code Task} objects left in the list.
     */
    public static void displayDeleteSuccessMessage(Task deletedTask, int sizeOfArrayList) {
        drawHorizontalLine(LineLocation.TOP);
        addIndentation(NUMBER_OF_SPACES_TO_INDENT_TASK_DELETE_SUCCESS_MESSAGE);
        System.out.println("Noted. I've removed this task:");
        addIndentation(NUMBER_OF_SPACES_TO_INDENT_TASK_DETAILS);
        System.out.println(deletedTask);
        addIndentation(NUMBER_OF_SPACES_TO_INDENT_TASK_DELETE_SUCCESS_MESSAGE);
        System.out.println("Now you have " + sizeOfArrayList+ " tasks in the list.");
        drawHorizontalLine(LineLocation.BOTTOM);
    }

    /**
     * Displays a success message indicating that the task instructed by the user to be added is
     * added successfully and the new total number of tasks in the list.
     *
     * @param taskObject The {@code Task} object that was added.
     * @param userArrayListSize The number of {@code Task} objects in the list.
     */
    public static void displayTaskAddedMessage(Task taskObject, int userArrayListSize) {
        drawHorizontalLine(LineLocation.TOP);
        addIndentation(NUMBER_OF_SPACES_TO_INDENT_TASK_ADDED_SUCCESS_MESSAGE);
        System.out.println("Got it. I've added this task:");
        addIndentation(NUMBER_OF_SPACES_TO_INDENT_ADDED_TASK_DESCRIPTION);
        System.out.println(taskObject);
        addIndentation(NUMBER_OF_SPACES_TO_INDENT_TASK_ADDED_SUCCESS_MESSAGE);
        System.out.println("Now you have " + userArrayListSize + " tasks in the list.");
        drawHorizontalLine(LineLocation.BOTTOM);
    }

    /**
     * Displays the task's type, completion status, description and dates (if any) for tasks
     * whose description contains the keyword entered by the user.
     *
     * @param matchingTasksArrayList The object containing {@code Task} objects whose description contains
     *                               the keyword entered by the user.
     */
    public static void displayMatchignTasksMessage(ArrayList<Task> matchingTasksArrayList) {
        drawHorizontalLine(LineLocation.TOP);
        if (matchingTasksArrayList.isEmpty()) {
            addIndentation(NUMBER_OF_SPACES_TO_INDENT_MATCHING_TASK);
            System.out.println("There are no matching tasks in the list.");
        } else {
            int taskNumber = 1;
            for (Task task : matchingTasksArrayList) {
                addIndentation(NUMBER_OF_SPACES_TO_INDENT_MATCHING_TASK);
                System.out.print(taskNumber + ".");
                System.out.println(task);
                taskNumber = taskNumber + 1;
            }
        }
        drawHorizontalLine(LineLocation.BOTTOM);
    }

    /**
     * Displays the bye message to the user.
     */
    public static void displayByeMessage() {
        drawHorizontalLine(LineLocation.TOP);
        addIndentation(NUMBER_OF_SPACES_TO_INDENT_BYE_MESSAGE);
        System.out.println("I hope I helped you! Bye for now");
        drawHorizontalLine(LineLocation.BOTTOM);
    }

    /**
     * Displays any error message that has been generated.
     *
     * @param errorMessage The actual error message to be displayed.
     */
    public static void displayErrorMessage(String errorMessage) {
        addIndentation(NUMBER_OF_SPACES_TO_INDENT_ERROR_MESSAGE);
        System.out.println(errorMessage);

    }
}