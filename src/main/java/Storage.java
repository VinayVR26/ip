import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private static final int INDEX_OF_TASK_TYPE_IN_FILE = 0;
    private static final int INDEX_OF_TASK_COMPLETION_IN_FILE = 1;
    private static final int INDEX_OF_TASK_DESCRIPTION_IN_FILE = 2;
    private static final int INDEX_OF_DEADLINE_TASK_BY_DATE_IN_FILE = 3;
    private static final int INDEX_OF_EVENT_TASK_FROM_TIME_IN_FILE = 3;
    private static final int INDEX_OF_EVENT_TASK_TO_TIME_IN_FILE = 4;
    private static final int INDEX_OF_DATA_TO_SAVE_PAST_THE_FIRST_SPACE = 1;

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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

    public int loadData(ArrayList<Task> userTaskArray) {
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

                } else if (taskType.equals("D")) {
                    String deadlineTaskDate = " " + tokens[INDEX_OF_DEADLINE_TASK_BY_DATE_IN_FILE];
                    taskToAdd = new Deadline(taskDescription, deadlineTaskDate);

                } else if (taskType.equals("E")) {
                    String eventTaskFromTime = " " + tokens[INDEX_OF_EVENT_TASK_FROM_TIME_IN_FILE];
                    String eventTaskToTime = " " + tokens[INDEX_OF_EVENT_TASK_TO_TIME_IN_FILE];
                    taskToAdd = new Event(taskDescription, eventTaskFromTime, eventTaskToTime);
                }

                taskToAdd.setTaskStatus(isTaskDone);
                userTaskArray.add(taskToAdd);
                numberOfTasks = numberOfTasks + 1;
            }
        } catch (FileNotFoundException e) {
            System.err.println("ERROR: File not found");
        }
        return numberOfTasks;
    }

    public void saveData(ArrayList<Task> userTaskArray) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task taskToSave : userTaskArray) {
                String isTaskCompleted = (taskToSave.isTaskDone ? "1" : "0");
                String lineInFile = "";

                if (taskToSave instanceof Todo) {
                    lineInFile = lineInFile + "T," + isTaskCompleted + "," + taskToSave.taskDescription;
                } else if (taskToSave instanceof Deadline) {
                    lineInFile = lineInFile + "D," + isTaskCompleted + "," + taskToSave.taskDescription + "," +
                            ((Deadline) taskToSave).endDate.substring(INDEX_OF_DATA_TO_SAVE_PAST_THE_FIRST_SPACE);
                } else {
                    lineInFile = lineInFile + "E," + isTaskCompleted + "," + taskToSave.taskDescription + "," +
                            ((Event) taskToSave).fromTime.substring(INDEX_OF_DATA_TO_SAVE_PAST_THE_FIRST_SPACE) + "," +
                            ((Event) taskToSave).toTime.substring(INDEX_OF_DATA_TO_SAVE_PAST_THE_FIRST_SPACE);
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
