import java.util.Scanner;

public class TaskHandler {

    public static void drawHorizontalLine() {
        System.out.print("    ");
        for (int i = 0; i < 60; i += 1){
            System.out.print("-");

            if (i == 59) {
                System.out.println();
            }
        }
    }

    public static int getTaskMarkNumber(String userInput) {
        String number = "0";
        int userInputNumber = userInput.length();
        if (userInput.startsWith("mark ") && userInputNumber <= 8) {
            number = userInput.substring(4).trim();
        } else if (userInput.startsWith("unmark ") && userInputNumber <= 10) {
            number = userInput.substring(6).trim();
        }

        return Integer.parseInt(number);

    }

    public static void displayUserDataArray(Task[] userDataArray, int numberOfItems) {
        drawHorizontalLine();
        System.out.println("Here are the tasks in your list");
        for (int itemIndex = 0; itemIndex < numberOfItems; itemIndex += 1){
            System.out.print("     " + (itemIndex + 1) + ".");
            System.out.print("[" + userDataArray[itemIndex].getTaskStatusSymbol() + "] ");
            System.out.println(userDataArray[itemIndex].taskName);
        }
        drawHorizontalLine();
        System.out.println();
    }

    public static void updateTaskStatus(Task[] userDataArray, int taskNumber, String toDo) {
        userDataArray[taskNumber - 1].setTaskStatus(toDo.equals("mark"));
    }

    public static void echoUser() {
        String userInput;
        Task[] userDataArray = new Task[100];
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
                int number = Integer.parseInt(userInput.substring(5));
                updateTaskStatus(userDataArray, number, "mark");
                drawHorizontalLine();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  [X] " + userDataArray[number - 1].taskName);
                drawHorizontalLine();

            } else if (userInput.startsWith("unmark ")) {
                int number = Integer.parseInt(userInput.substring(7));
                updateTaskStatus(userDataArray, number, "unmark");
                drawHorizontalLine();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  [ ] " + userDataArray[number - 1].taskName);
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

    public static void main(String[] args) {
        drawHorizontalLine();
        System.out.println("    Hello! I'm TaskHandler");
        System.out.println("    Tell me what to do. I am happy to assist you.\n");
        drawHorizontalLine();
        System.out.println();

        echoUser();
    }
}
