import java.io.*;
import java.util.Scanner;

public class NoteMgmt {
    private static final String FILE_NAME = "notes.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\n--- Notes Manager ---");
            System.out.println("1. Add a new note");
            System.out.println("2. View all notes");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addNote(scanner);
                    break;
                case 2:
                    viewNotes();
                    break;
                case 3:
                    System.out.println("Exiting Notes Manager. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addNote(Scanner scanner) {
        System.out.print("Enter your note: ");
        String note = scanner.nextLine();

        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            writer.write(note + System.lineSeparator());
            System.out.println("Note added successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing the note.");
            e.printStackTrace();
        }
    }

    private static void viewNotes() {
        System.out.println("\n--- All Notes ---");

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            boolean empty = true;

            while ((line = reader.readLine()) != null) {
                empty = false;
                System.out.println("- " + line);
            }

            if (empty) {
                System.out.println("(No notes found)");
            }

        } catch (FileNotFoundException e) {
            System.out.println("No notes file found. Add a note first.");
        } catch (IOException e) {
            System.out.println("An error occurred while reading notes.");
            e.printStackTrace();
        }
    }
}
