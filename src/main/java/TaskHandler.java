public class TaskHandler {
    private static final int MAX_NUMBER_OF_TASKS = 100;
    private static final int INDEX_TO_CHECK_TO_GET_TASK_NUMBER_TO_MARK = 5;
    private static final int INDEX_TO_CHECK_TO_GET_TASK_NUMBER_TO_UNMARK = 7;

    private static final String FILE_PATH = "./data/task_handler.txt";
    private static final TaskList userTaskArrayList = new TaskList(MAX_NUMBER_OF_TASKS);
    private static final Storage dataStorage = new Storage(FILE_PATH);
    private static final Ui ui = new Ui();



    public static void main(String[] args) {
        dataStorage.ensureDataFileExists();
        ui.displayWelcomeMessage();
        echoUser();
    }

    public static void determineTaskTypeAndDisplay(String userInput) throws TaskHandlerException {
        if (userTaskArrayList.getSize() == MAX_NUMBER_OF_TASKS) {
            throw new TaskHandlerException("ERROR: Unable to add task. Maximum number of tasks of - " +
                    MAX_NUMBER_OF_TASKS + " reached");
        }
        Task newTask = Parser.determineTaskType(userInput);
        userTaskArrayList.addTask(newTask);
        ui.displayTaskAddedMessage(newTask, userTaskArrayList.getSize());
    }

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
