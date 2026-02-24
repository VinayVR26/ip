import java.util.Scanner;

public class TaskHandler {
    private static final int MAX_NUMBER_OF_TASKS = 100;
    private static final int MAX_NUMBER_OF_DASHES = 60;
    private static final int NUMBER_OF_SPACES_TO_INDENT_HORIZONTAL_LINE = 4;
    private static final int NUMBER_OF_SPACES_TO_INDENT_WELCOME_MESSAGE = 5;
    private static final int NUMBER_OF_SPACES_TO_INDENT_TASK_ADDED_SUCCESS_MESSAGE = 5;
    private static final int NUMBER_OF_SPACES_TO_INDENT_ADDED_TASK_DESCRIPTION= 7;
    private static final int NUMBER_OF_SPACES_TO_INDENT_TASK_MARKED_SUCCESS_MESSAGE = 5;
    private static final int NUMBER_OF_SPACES_TO_INDENT_TASK_DETAILS= 7;
    private static final int NUMBER_OF_SPACES_TO_INDENT_TASK_UNMARKED_SUCCESS_MESSAGE = 5;
    private static final int NUMBER_OF_SPACES_TO_INDENT_TASK_DELETE_SUCCESS_MESSAGE= 5;
    private static final int NUMBER_OF_SPACES_TO_INDENT_BYE_MESSAGE = 5;
    private static final int NUMBER_OF_SPACES_TO_INDENT_EACH_TASK_ADDED = 5;
    private static final int NUMBER_OF_SPACES_TO_INDENT_ERROR_MESSAGE = 5;

    private static final int INDEX_TO_CHECK_TO_GET_TASK_NUMBER_TO_MARK = 5;
    private static final int INDEX_TO_CHECK_TO_GET_TASK_NUMBER_TO_UNMARK = 7;

    private static final String FILE_PATH = "./data/task_handler.txt";

    private static final TaskList userTaskArrayList = new TaskList(MAX_NUMBER_OF_TASKS);
    private static final Storage dataStorage = new Storage(FILE_PATH);

    public enum LineLocation {
        TOP, BOTTOM
    }

    public static void main(String[] args) {
        dataStorage.ensureDataFileExists();
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

    public static void displayContentOfSpecificTask(int taskIndex) {
        addIndentation(NUMBER_OF_SPACES_TO_INDENT_EACH_TASK_ADDED);
        System.out.print((taskIndex + 1) + ".");
        System.out.println(userTaskArrayList.getTask(taskIndex));
    }

    public static void displayUserDataArray(int numberOfTasks) {
        drawHorizontalLine(LineLocation.TOP);
        addIndentation(NUMBER_OF_SPACES_TO_INDENT_EACH_TASK_ADDED);
        System.out.println("Here are the tasks in your list");
        for (int taskIndex = 0; taskIndex < numberOfTasks; taskIndex += 1){
            displayContentOfSpecificTask(taskIndex);
        }
        drawHorizontalLine(LineLocation.BOTTOM);
    }

    public static void displayMarkedSuccessMessage(int taskNumber) {
        addIndentation(NUMBER_OF_SPACES_TO_INDENT_TASK_MARKED_SUCCESS_MESSAGE);
        System.out.println("Nice! I've marked this task as done:");
        addIndentation(NUMBER_OF_SPACES_TO_INDENT_TASK_DETAILS);
        System.out.println("[X] " + userTaskArrayList.getTask(taskNumber - 1).taskDescription);
    }

    public static void displayUnmarkedSuccessMessage(int taskNumber) {
        addIndentation(NUMBER_OF_SPACES_TO_INDENT_TASK_UNMARKED_SUCCESS_MESSAGE);
        System.out.println("OK, I've marked this task as not done yet:");
        addIndentation(NUMBER_OF_SPACES_TO_INDENT_TASK_DETAILS);
        System.out.println("[ ] " + userTaskArrayList.getTask(taskNumber - 1).taskDescription);
    }

    public static void displayDeleteSuccessMessage(int taskNumber) {
        addIndentation(NUMBER_OF_SPACES_TO_INDENT_TASK_DELETE_SUCCESS_MESSAGE);
        System.out.println("Noted. I've removed this task:");
        addIndentation(NUMBER_OF_SPACES_TO_INDENT_TASK_DETAILS);
        System.out.println(userTaskArrayList.getTask(taskNumber - 1));
        addIndentation(NUMBER_OF_SPACES_TO_INDENT_TASK_DELETE_SUCCESS_MESSAGE);
        System.out.println("Now you have " + (userTaskArrayList.getSize() - 1) + " tasks in the list.");
    }

    public static void determineTaskTypeAndDisplay(String userInput, int taskNumber) throws TaskHandlerException {
        if (taskNumber == MAX_NUMBER_OF_TASKS) {
            throw new TaskHandlerException("ERROR: Unable to add task. Maximum number of tasks of - " +
                    MAX_NUMBER_OF_TASKS + " reached");
        }

        Task newTask = Parser.determineTaskType(userInput);
        userTaskArrayList.addTask(newTask);
        displayTaskAddedMessage(newTask, taskNumber);
    }

    public static void displayTaskAddedMessage(Task taskObject, int taskIndex) {
        addIndentation(NUMBER_OF_SPACES_TO_INDENT_TASK_ADDED_SUCCESS_MESSAGE);
        System.out.println("Got it. I've added this task:");
        addIndentation(NUMBER_OF_SPACES_TO_INDENT_ADDED_TASK_DESCRIPTION);
        System.out.println(taskObject);
        addIndentation(NUMBER_OF_SPACES_TO_INDENT_TASK_ADDED_SUCCESS_MESSAGE);
        System.out.println("Now you have " + (taskIndex + 1) + " tasks in the list.");
    }

    public static void echoUser() {
        String userInput;
        int numberOfTasks = dataStorage.loadData(userTaskArrayList.getTaskArrayList());
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.print("");
            userInput = in.nextLine();
            if (userInput.equals("bye")) {
                break;

            } else if (userInput.equals("list")) {
                displayUserDataArray(numberOfTasks);

            } else if (userInput.startsWith("mark ")) {
                int taskNumber = Integer.parseInt(userInput.substring(INDEX_TO_CHECK_TO_GET_TASK_NUMBER_TO_MARK));
                userTaskArrayList.markTask(taskNumber);
                drawHorizontalLine(LineLocation.TOP);
                displayMarkedSuccessMessage(taskNumber);
                drawHorizontalLine(LineLocation.BOTTOM);

            } else if (userInput.startsWith("unmark ")) {
                int taskNumber = Integer.parseInt(userInput.substring(INDEX_TO_CHECK_TO_GET_TASK_NUMBER_TO_UNMARK));
                userTaskArrayList.unMarkTask(taskNumber);
                drawHorizontalLine(LineLocation.TOP);
                displayUnmarkedSuccessMessage(taskNumber);
                drawHorizontalLine(LineLocation.BOTTOM);

            } else if (userInput.startsWith("delete ")) {
                int taskNumber = Integer.parseInt(userInput.substring(INDEX_TO_CHECK_TO_GET_TASK_NUMBER_TO_UNMARK));
                drawHorizontalLine(LineLocation.TOP);
                displayDeleteSuccessMessage(taskNumber);
                drawHorizontalLine(LineLocation.BOTTOM);
                userTaskArrayList.deleteTask(taskNumber);
                numberOfTasks = numberOfTasks - 1;

            } else {
                drawHorizontalLine(LineLocation.TOP);
                try {
                    determineTaskTypeAndDisplay(userInput, numberOfTasks);
                    numberOfTasks = numberOfTasks + 1;
                } catch (Exception e) {
                    addIndentation(NUMBER_OF_SPACES_TO_INDENT_ERROR_MESSAGE);
                    System.out.println(e.getMessage());
                }
                drawHorizontalLine(LineLocation.BOTTOM);
            }
        }

        dataStorage.saveData(userTaskArrayList.getTaskArrayList());
        drawHorizontalLine(LineLocation.TOP);
        addIndentation(NUMBER_OF_SPACES_TO_INDENT_BYE_MESSAGE);
        System.out.println("I hope I helped you! Bye for now");
        drawHorizontalLine(LineLocation.BOTTOM);
    }
}
