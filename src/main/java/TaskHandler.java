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

    public static void echoUser() {
        String userInput;
        Scanner in = new Scanner(System.in);

        System.out.print("");
        userInput = in.nextLine();

        while (!(userInput.equals("bye"))) {
            drawHorizontalLine();
            System.out.println("    " + userInput);
            drawHorizontalLine();
            System.out.print("");
            userInput = in.nextLine();
        }

        drawHorizontalLine();
        System.out.println("    I hope I helped you! Bye for now");
        drawHorizontalLine();
    }

    public static void main(String[] args) {
        drawHorizontalLine();
        System.out.println("    Hello! I'm TaskHandler");
        System.out.println("    Tell me what to do. I am happy to assist you.");
        drawHorizontalLine();

        echoUser();
    }
}
