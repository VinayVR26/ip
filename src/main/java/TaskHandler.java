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
    private static final int INDEX_TO_CHECK_TO_GET_TASK_TO_MARK = 5;

    /**
     * The index position to start traversing to get the task number to unmark
     */
    private static final int INDEX_TO_CHECK_TO_GET_TASK_TO_UNMARK = 7;

    /**
      * Main entry point of the code
      * Initialises the code and starts interaction with the user
     */
    public static void main(String[] args) {
        drawHorizontalLine();
        displayWelcomeMessage();
        drawHorizontalLine();
        System.out.println();
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
     * Displays a horizontal line
     */
    public static void drawHorizontalLine() {
        System.out.print("    ");
        for (int i = 0; i < MAX_NUMBER_OF_DASHES; i += 1){
            System.out.print("-");

            if (i == MAX_NUMBER_OF_DASHES - 1) {
                System.out.println();
            }
        }
    }

    /**
     *
     * @param userDataArray array of Task objects of the user's tasks
     * @param taskIndex index of the task in userDataArray to be displayed
     */
    public static void displayContentOfSpecificTask(Task[] userDataArray, int taskIndex) {
        System.out.print("     " + (taskIndex + 1) + ".");
        System.out.print("[" + userDataArray[taskIndex].getTaskStatusSymbol() + "] ");
        System.out.println(userDataArray[taskIndex].taskName);
    }

    /**
     *
     * @param userDataArray array of Task objects of the user's tasks
     * @param numberOfItems number of user's tasks
     */
    public static void displayUserDataArray(Task[] userDataArray, int numberOfItems) {
        drawHorizontalLine();
        System.out.println("Here are the tasks in your list");
        for (int taskIndex = 0; taskIndex < numberOfItems; taskIndex += 1){
            displayContentOfSpecificTask(userDataArray,  taskIndex);
        }
        drawHorizontalLine();
        System.out.println();
    }

    /**
     *
     * @param userDataArray array of Task objects of the user's tasks
     * @param taskNumber task number as shown when "list" is inputted
     * @param toDo user input to mark or unmark the task
     */
    public static void updateTaskStatus(Task[] userDataArray, int taskNumber, String toDo) {
        userDataArray[taskNumber - 1].setTaskStatus(toDo.equals("mark"));
    }


    /**
     *
     * @param userDataArray array of Task objects of the user's tasks
     * @param taskNumber task number as shown when "list" is inputted
     */
    public static void displayMarkedSuccessMessage(Task[] userDataArray, int taskNumber) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  [X] " + userDataArray[taskNumber - 1].taskName);
    }

    /**
     *
     * @param userDataArray array of Task objects of the user's tasks
     * @param taskNumber ask number as shown when "list" is inputted
     */
    public static void displayUnmarkedSuccessMessage(Task[] userDataArray, int taskNumber) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  [ ] " + userDataArray[taskNumber - 1].taskName);
    }

    /**
     * logic of the task bot to handle user input
     */
    public static void echoUser() {
        String userInput;
        Task[] userDataArray = new Task[MAX_NUMBER_OF_TASKS];
        int numberOfItems = 0;
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.print("");
            userInput = in.nextLine();
            if (userInput.equals("bye")) {
                break;

            } else if (userInput.equals("list")) {
                displayUserDataArray(userDataArray, numberOfItems);

            } else if (userInput.startsWith("mark ")) {
                int taskNumber = Integer.parseInt(userInput.substring(INDEX_TO_CHECK_TO_GET_TASK_TO_MARK));
                updateTaskStatus(userDataArray, taskNumber, "mark");
                drawHorizontalLine();
                displayMarkedSuccessMessage(userDataArray, taskNumber);
                drawHorizontalLine();

            } else if (userInput.startsWith("unmark ")) {
                int taskNumber = Integer.parseInt(userInput.substring(INDEX_TO_CHECK_TO_GET_TASK_TO_UNMARK));
                updateTaskStatus(userDataArray, taskNumber, "unmark");
                drawHorizontalLine();
                displayUnmarkedSuccessMessage(userDataArray, taskNumber);
                drawHorizontalLine();

            } else {
                Task aTask = new Task(userInput);
                userDataArray[numberOfItems] = aTask;
                numberOfItems = numberOfItems + 1;
                drawHorizontalLine();
                System.out.println("     added: " + userInput);
                drawHorizontalLine();
                System.out.println();
            }
        }
        drawHorizontalLine();
        System.out.println("    I hope I helped you! Bye for now");
        drawHorizontalLine();
    }

}
