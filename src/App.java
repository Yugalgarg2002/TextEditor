import java.util.Scanner;

import notepad.Notepad;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);

        Notepad n = new Notepad(100);

        do {
            System.out.println("Choose your option");
            System.out.println("1. Display the text");
            System.out.println("2. Display the text from given lines");
            System.out.println("3. Insret the text");
            System.out.println("4. Delete the text");
            System.out.println("5. Delete the text from given lines");
            System.out.println("6. Copy the text");
            System.out.println("7. Paste the text");
            System.out.println("8. Undo your action");
            System.out.println("9. Redo your action");
            System.out.println("10. Exit");

            System.out.println("Enter the choice:");
            int choice = scan.nextInt();
            int lineNumber;
            switch (choice) {
                case 1:
                    n.display();
                    break;
                case 2:
                    System.out.println("Enter the starting line and the ending line value: ");
                    int startLine = scan.nextInt();
                    int endLine = scan.nextInt();
                    n.display(startLine, endLine);
                    break;
                case 3:
                    System.out.println("Enter the text: ");
                    String text = scan.next();
                    System.out.println("Enter the line number: ");
                    lineNumber = scan.nextInt();
                    n.insertLine(lineNumber, text);
                    break;
                case 4:
                    System.out.println("Enter the line number: ");
                    lineNumber = scan.nextInt();
                    n.delete(lineNumber);
                    break;
                case 5:
                    System.out.println("Enter the starting line and the ending line value: ");
                    startLine = scan.nextInt();
                    endLine = scan.nextInt();
                    n.delete(startLine, endLine);
                    break;
                case 6:
                    System.out.println("Enter the starting line and the ending line value: ");
                    startLine = scan.nextInt();
                    endLine = scan.nextInt();
                    n.copy(startLine, endLine);
                    break;
                case 7:
                    System.out.println("Enter the line number: ");
                    lineNumber = scan.nextInt();
                    n.paste(lineNumber);
                    break;
                case 8:
                    n.undo();
                    break;
                case 9:
                    n.redo();
                    break;
                case 10:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Choice");
            }

        } while (true);
    }
}
