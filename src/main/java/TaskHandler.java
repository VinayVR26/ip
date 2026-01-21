public class TaskHandler {

    public static void horizontalLines() {
        for (int i = 0; i < 60; i += 1){
            System.out.print("-");

            if (i == 59) {
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        horizontalLines();
        System.out.println("Hello! I'm TaskHandler");
        System.out.println("What can I do for you?");
        horizontalLines();
        System.out.println("Bye. Hope to see you again soon!");
        horizontalLines();
    }
}
