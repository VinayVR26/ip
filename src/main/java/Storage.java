import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class is used to manage the loading and saving of the current status of all tasks to a text file.
 * The tasks are read from the text file on application startup and saved to the same text file on
 * application termination.
 */
public class Storage {

    /**
     * Index of the task type letter in the {@code tokens}.
     */
    private static final int INDEX_OF_TASK_TYPE_IN_FILE = 0;

    /**
     * Index of the task completion status in the {@code tokens}.
     */
    private static final int INDEX_OF_TASK_COMPLETION_IN_FILE = 1;

    /**
     * Starting index of the task description in the {@code tokens}.
     */
    private static final int INDEX_OF_TASK_DESCRIPTION_IN_FILE = 2;

    /**
     * Starting index of the /by date for a Deadline task in the {@code tokens}.
     */
    private static final int INDEX_OF_DEADLINE_TASK_BY_DATE_IN_FILE = 3;

    /**
     * Starting index of the /from time for an Event task in the {@code tokens}.
     */
    private static final int INDEX_OF_EVENT_TASK_FROM_TIME_IN_FILE = 3;

    /**
     * Starting index of the /to time for an Event task in the {@code tokens}.
     */
    private static final int INDEX_OF_EVENT_TASK_TO_TIME_IN_FILE = 4;

    /**
     * Starting index to skip past the leading space charatcer when extracting the date and time fields
     * {@code Deadline} and {@code Event} tasks.
     */
    private static final int INDEX_OF_DATA_TO_SAVE_PAST_THE_FIRST_SPACE = 1;

    /**
     * The string containing the file path to load and save the data to.
     */
    private String filePath;

    /**
     * Constructor to create an instance of {@code Storage}.
     *
     * @param filePath The file path to load and save the data to.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Checks if the folder path and text file exists in {@code filePath} location.
     * If the folder path or file does not exist, they are created to prevent errors
     * during read and write operations.
     */
    public void ensureDataFileExists() {
        File f = new File(filePath);
        File directory = f.getParentFile();
        if (directory != null && !directory.exists()) {
            boolean isDirectoryExist = directory.mkdirs();
            if (!isDirectoryExist) {
                System.err.println("Error: Unable to create directory.");
            }
        }

        if (!f.exists()) {
            try {
                boolean isFileCreated = f.createNewFile();
            } catch (IOException e) {
                System.err.println("Error: Unable to create file.");
            }
        }
    }

    /**
     * Reads the file containing task details from {@code filePath} and adds the tasks to the list.
     * Each line in the file is extracted and is parsed into either a {@code Todo}, {@code Deadline} or
     * {@code Event} object.
     *
     * @param userTaskArrayList The {@code ArrayList} where the {@code Task} objects that were loaded from
     *                          the file, are added to.
     */
    public void loadData(ArrayList<Task> userTaskArrayList) {
        int numberOfTasks = 0;
        File f = new File(filePath);
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] tokens = line.split(",");
                String taskType = tokens[INDEX_OF_TASK_TYPE_IN_FILE];
                boolean isTaskDone = tokens[INDEX_OF_TASK_COMPLETION_IN_FILE].equals("1");
                String taskDescription = tokens[INDEX_OF_TASK_DESCRIPTION_IN_FILE];

                Task taskToAdd = null;
                if (taskType.equals("T")) {
                    taskToAdd = new Todo(taskDescription);
                    taskToAdd.setTaskStatus(isTaskDone);

                } else if (taskType.equals("D")) {
                    String deadlineTaskDate = " " + tokens[INDEX_OF_DEADLINE_TASK_BY_DATE_IN_FILE];
                    taskToAdd = new Deadline(taskDescription, deadlineTaskDate);
                    taskToAdd.setTaskStatus(isTaskDone);

                } else if (taskType.equals("E")) {
                    String eventTaskFromTime = " " + tokens[INDEX_OF_EVENT_TASK_FROM_TIME_IN_FILE];
                    String eventTaskToTime = " " + tokens[INDEX_OF_EVENT_TASK_TO_TIME_IN_FILE];
                    taskToAdd = new Event(taskDescription, eventTaskFromTime, eventTaskToTime);
                    taskToAdd.setTaskStatus(isTaskDone);
                }

                userTaskArrayList.add(taskToAdd);
                numberOfTasks = numberOfTasks + 1;
            }
        } catch (FileNotFoundException e) {
            System.err.println("ERROR: File not found");
        }
    }

    /**
     * Saves the tasks in {@code userTaskArrayList} to the file.
     * Each {@code Task} object is formatted into a comma-seperated line and then
     * written to the disk.
     * Existing file content in the file is overwritten.
     *
     * @param userTaskArrayList The {@code ArrayList} of {@code Task} objects to be saved.
     */
    public void saveData(ArrayList<Task> userTaskArrayList) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task taskToSave : userTaskArrayList) {
                String isTaskCompleted = (taskToSave.isTaskDone ? "1" : "0");
                String lineInFile = "";

                if (taskToSave instanceof Todo) {
                    lineInFile = lineInFile + "T," + isTaskCompleted + "," + taskToSave.taskDescription;
                } else if (taskToSave instanceof Deadline) {
                    lineInFile = lineInFile + "D," + isTaskCompleted + "," + taskToSave.taskDescription + "," +
                            ((Deadline) taskToSave).endDate.substring(INDEX_OF_DATA_TO_SAVE_PAST_THE_FIRST_SPACE);
                } else {
                    lineInFile = lineInFile + "E," + isTaskCompleted + "," + taskToSave.taskDescription + "," +
                            ((Event) taskToSave).fromPeriod.substring(INDEX_OF_DATA_TO_SAVE_PAST_THE_FIRST_SPACE) + "," +
                            ((Event) taskToSave).toPeriod.substring(INDEX_OF_DATA_TO_SAVE_PAST_THE_FIRST_SPACE);
                }
                lineInFile = lineInFile + System.lineSeparator();
                fw.write(lineInFile);
            }
            fw.close();
        } catch (IOException e) {
            System.err.println("Error: Unable to save file.");
        }
    }
}