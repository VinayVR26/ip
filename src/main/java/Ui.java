import java.util.Scanner;
import java.util.ArrayList;

public class Ui {
    private static final int MAX_NUMBER_OF_DASHES = 60;
    private static final int NUMBER_OF_SPACES_TO_INDENT_HORIZONTAL_LINE = 4;
    private static final int NUMBER_OF_SPACES_TO_INDENT_WELCOME_MESSAGE = 5;
    private static final int NUMBER_OF_SPACES_TO_INDENT_TASK_ADDED_SUCCESS_MESSAGE = 5;
    private static final int NUMBER_OF_SPACES_TO_INDENT_ADDED_TASK_DESCRIPTION= 7;
    private static final int NUMBER_OF_SPACES_TO_INDENT_TASK_MARKED_SUCCESS_MESSAGE = 5;
    private static final int NUMBER_OF_SPACES_TO_INDENT_TASK_DETAILS= 7;
    private static final int NUMBER_OF_SPACES_TO_INDENT_TASK_UNMARKED_SUCCESS_MESSAGE = 5;
    private static final int NUMBER_OF_SPACES_TO_INDENT_TASK_DELETE_SUCCESS_MESSAGE= 5;
    private static final int NUMBER_OF_SPACES_TO_INDENT_MATCHING_TASK = 5;
    private static final int NUMBER_OF_SPACES_TO_INDENT_BYE_MESSAGE = 5;
    private static final int NUMBER_OF_SPACES_TO_INDENT_EACH_TASK_ADDED = 5;
    private static final int NUMBER_OF_SPACES_TO_INDENT_ERROR_MESSAGE = 5;
    private Scanner in;

    public enum LineLocation {
        TOP, BOTTOM
    }

    public Ui() {
        this.in = new Scanner(System.in);
    }

    public String readUserInput() {
        System.out.print("");
        return in.nextLine();
    }

    private static void addIndentation(int spacingCount) {
        for (int i = 0; i < spacingCount; i++) {
            System.out.print(" ");
        }
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

    public static void displayWelcomeMessage() {
        drawHorizontalLine(LineLocation.TOP);
        addIndentation(NUMBER_OF_SPACES_TO_INDENT_WELCOME_MESSAGE);
        System.out.println("Hello! I'm TaskHandler");
        addIndentation(NUMBER_OF_SPACES_TO_INDENT_WELCOME_MESSAGE);
        System.out.println("Tell me what to do. I am happy to assist you.");
        drawHorizontalLine(LineLocation.BOTTOM);
    }

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

    public static void displayMarkedSuccessMessage(Task taskMarked) {
        drawHorizontalLine(LineLocation.TOP);
        addIndentation(NUMBER_OF_SPACES_TO_INDENT_TASK_MARKED_SUCCESS_MESSAGE);
        System.out.println("Nice! I've marked this task as done:");
        addIndentation(NUMBER_OF_SPACES_TO_INDENT_TASK_DETAILS);
        System.out.println("[X] " + taskMarked.taskDescription);
        drawHorizontalLine(LineLocation.BOTTOM);
    }

    public static void displayUnmarkedSuccessMessage(Task taskUnmarked) {
        drawHorizontalLine(LineLocation.TOP);
        addIndentation(NUMBER_OF_SPACES_TO_INDENT_TASK_UNMARKED_SUCCESS_MESSAGE);
        System.out.println("OK, I've marked this task as not done yet:");
        addIndentation(NUMBER_OF_SPACES_TO_INDENT_TASK_DETAILS);
        System.out.println("[ ] " + taskUnmarked.taskDescription);
        drawHorizontalLine(LineLocation.BOTTOM);
    }

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

    public static void displayByeMessage() {
        drawHorizontalLine(LineLocation.TOP);
        addIndentation(NUMBER_OF_SPACES_TO_INDENT_BYE_MESSAGE);
        System.out.println("I hope I helped you! Bye for now");
        drawHorizontalLine(LineLocation.BOTTOM);
    }

    public static void displayErrorMessage(String errorMessage) {
        addIndentation(NUMBER_OF_SPACES_TO_INDENT_ERROR_MESSAGE);
        System.out.println(errorMessage);

    }
}
