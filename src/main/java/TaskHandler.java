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

    public static void displayUserDataArray(String[] userDataArray, int numberOfItems) {
        drawHorizontalLine();
        for (int itemIndex = 0; itemIndex < numberOfItems; itemIndex += 1){
            System.out.println("     " + (itemIndex + 1) + ". " + userDataArray[itemIndex]);
        }
        drawHorizontalLine();
        System.out.println();
    }

    public static void echoUser() {
        String userInput;
        String[] userDataArray = new String[100];
        int numberOfItems = 0;
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.print("");
            userInput = in.nextLine();
            if (userInput.equals("bye")) {
                break;
            } else if (userInput.equals("list")) {
                displayUserDataArray(userDataArray, numberOfItems);
            } else {
                userDataArray[numberOfItems] = userInput;
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
