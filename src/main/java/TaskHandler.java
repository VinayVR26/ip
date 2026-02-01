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
      * Main entry point of the code
      * Initialises the code and starts interaction with the user
     */
    public static void main(String[] args) {
        drawHorizontalLine("top");
        displayWelcomeMessage();
        drawHorizontalLine("bottom");
        echoUser();
    }

    /**
     * Displays the welcome message to the user
     */
    public static void displayWelcomeMessage() {
        System.out.println("    Hello! I'm TaskHandler");
        System.out.println("    Tell me what to do. I am happy to assist you.\n");
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
        System.out.print("    " + (taskIndex + 1) + ".");
        System.out.print("[" + userTaskArray[taskIndex].getTaskStatusSymbol() + "] ");
        System.out.println(userTaskArray[taskIndex].taskName);
    }

    /**
     * @param userTaskArray array of Task objects of the user's tasks
     * @param numberOfItems number of user's tasks
     */
    public static void displayUserDataArray(Task[] userTaskArray, int numberOfItems) {
        drawHorizontalLine("top");
        System.out.println("    Here are the tasks in your list");
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
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("      [X] " + userTaskArray[taskNumber - 1].taskName);
    }

    /**
     * @param userTaskArray array of Task objects of the user's tasks
     * @param taskNumber ask number as shown when "list" is inputted
     */
    public static void displayUnmarkedSuccessMessage(Task[] userTaskArray, int taskNumber) {
        System.out.println("    OK, I've marked this task as not done yet:");
        System.out.println("      [ ] " + userTaskArray[taskNumber - 1].taskName);
    }

    /**
     * logic of the task bot to handle user input
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
                Task aTask = new Task(userInput);
                userTaskArray[numberOfTasks] = aTask;
                numberOfTasks = numberOfTasks + 1;
                drawHorizontalLine("top");
                System.out.println("     added: " + userInput);
                drawHorizontalLine("bottom");
            }
        }
        drawHorizontalLine("top");
        System.out.println("    I hope I helped you! Bye for now");
        drawHorizontalLine("bottom");
    }
}
