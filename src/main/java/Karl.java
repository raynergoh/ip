import java.util.Scanner;

public class Karl {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // chatbot intro
        printLine();
        System.out.println(" Hello! I'm Karl 🤖");
        System.out.println(" What can I do for you?");
        printLine();
        System.out.println();

        while (true) {
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("bye")) {
                printLine();
                System.out.println(" Bye. Karl hopes to see you again soon!");
                printLine();
                break;
            }

            // echo user input
            printLine();
            System.out.println(" " + input);
            printLine();
            System.out.println();
        }

        sc.close();
    }

    // Helper function for formatting
    private static void printLine() {
        System.out.println("____________________________________________________________");
    }
}