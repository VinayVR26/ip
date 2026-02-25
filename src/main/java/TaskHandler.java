import java.util.ArrayList;

/**
 * This class is used to maintain a list of three types of tasks which are
 * saved in a text file.
 */
public class TaskHandler {

    /**
     * Maximum number of tasks that can be stored in the list
     */
    private static final int MAX_NUMBER_OF_TASKS = 100;

    /**
     * The index position of the user input to obtain the task number to mark.
     */
    private static final int INDEX_TO_CHECK_TO_GET_TASK_NUMBER_TO_MARK = 5;

    /**
     * The index position of the user input to obtain the task number to unmark.
     */
    private static final int INDEX_TO_CHECK_TO_GET_TASK_NUMBER_TO_UNMARK = 7;

    /**
     * The index position of the user input to obtain the keyword to check for matching tasks.
     */
    private static final int INDEX_TO_CHECK_TO_GET_KEYWORD = 5;


    /**
     * The file path for the tasks that are saved
     */
    private static final String FILE_PATH = "./data/task_handler.txt";

    /**
     * The list that is used to manage all the user's tasks
     */
    private static final TaskList userTaskArrayList = new TaskList(MAX_NUMBER_OF_TASKS);

    /**
     * The {@code Storage} instance is used to load data from the disk and
     * save data to the disk.
     */
    private static final Storage dataStorage = new Storage(FILE_PATH);

    /**
     * The {@code Ui} instance is used to display the different messages depending on user's input
     */
    private static final Ui ui = new Ui();


    /**
     * Main entry point of the application
     * Initializes the application and starts the interaction with the user.
     */
    public static void main(String[] args) {
        dataStorage.ensureDataFileExists();
        ui.displayWelcomeMessage();
        echoUser();
    }

    /**
     * Determines the task type among Todo, Deadline, Event, validates to ensure
     * all the components are specified for that task and
     * adds the task to {@code userTaskArrayList}
     *
     * @param userInput the text that is entered by the user
     * @throws TaskHandlerException error messages due failing validation checks
     */
    public static void determineTaskTypeAndDisplay(String userInput) throws TaskHandlerException {
        if (userTaskArrayList.getSize() == MAX_NUMBER_OF_TASKS) {
            throw new TaskHandlerException("ERROR: Unable to add task. Maximum number of tasks of - " +
                    MAX_NUMBER_OF_TASKS + " reached");
        }
        Task newTask = Parser.determineTaskType(userInput);
        userTaskArrayList.addTask(newTask);
        ui.displayTaskAddedMessage(newTask, userTaskArrayList.getSize());
    }

    /**
     * Decides what action to take based on the first few characters of the user input
     */
    public static void echoUser() {
        dataStorage.loadData(userTaskArrayList.getTaskArrayList());
        boolean userSaidBye = false;

        while (!userSaidBye) {
            String userInput = ui.readUserInput();

            if (userInput.equals("bye")) {
                userSaidBye = true;

            } else if (userInput.equals("list")) {
                ui.displayUserDataArray(userTaskArrayList);

            } else if (userInput.startsWith("mark ")) {
                int taskNumber = Integer.parseInt(userInput.substring(INDEX_TO_CHECK_TO_GET_TASK_NUMBER_TO_MARK));
                userTaskArrayList.markTask(taskNumber);
                ui.displayMarkedSuccessMessage(userTaskArrayList.getTask(taskNumber));

            } else if (userInput.startsWith("unmark ")) {
                int taskNumber = Integer.parseInt(userInput.substring(INDEX_TO_CHECK_TO_GET_TASK_NUMBER_TO_UNMARK));
                userTaskArrayList.unMarkTask(taskNumber);
                ui.displayUnmarkedSuccessMessage(userTaskArrayList.getTask(taskNumber));

            } else if (userInput.startsWith("delete ")) {
                int taskNumber = Integer.parseInt(userInput.substring(INDEX_TO_CHECK_TO_GET_TASK_NUMBER_TO_UNMARK));
                Task deletedTask = userTaskArrayList.deleteTask(taskNumber);
                ui.displayDeleteSuccessMessage(deletedTask, userTaskArrayList.getSize());

            } else if (userInput.startsWith("find ")) {
                String keyword = userInput.substring(INDEX_TO_CHECK_TO_GET_KEYWORD);
                ArrayList<Task> matchingTasksArrayList = userTaskArrayList.findTasks(keyword);
                ui.displayMatchignTasksMessage(matchingTasksArrayList);

            } else {
                try {
                    determineTaskTypeAndDisplay(userInput);
                } catch (Exception e) {
                    ui.drawHorizontalLine(Ui.LineLocation.TOP);
                    ui.displayErrorMessage(e.getMessage());
                    ui.drawHorizontalLine(Ui.LineLocation.BOTTOM);
                }
            }
        }

        dataStorage.saveData(userTaskArrayList.getTaskArrayList());
        ui.displayByeMessage();
    }
}
