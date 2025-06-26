import java.util.*;

public class LibrarySystem {

    // Book class
    static class Book {
        private int bookId;
        private String title;
        private String author;
        private boolean isIssued;

        public Book(int bookId, String title, String author) {
            this.bookId = bookId;
            this.title = title;
            this.author = author;
            this.isIssued = false;
        }

        public int getBookId() { return bookId; }
        public String getTitle() { return title; }
        public String getAuthor() { return author; }
        public boolean isIssued() { return isIssued; }

        public void setIssued(boolean issued) {
            isIssued = issued;
        }

        @Override
        public String toString() {
            String status = isIssued ? "Issued" : "Available";
            return bookId + ": '" + title + "' by " + author + " [" + status + "]";
        }
    }

    // User class
    static class User {
        private int userId;
        private String name;
        private List<Book> borrowedBooks;

        public User(int userId, String name) {
            this.userId = userId;
            this.name = name;
            this.borrowedBooks = new ArrayList<>();
        }

        public int getUserId() { return userId; }
        public String getName() { return name; }
        public List<Book> getBorrowedBooks() { return borrowedBooks; }

        public void borrowBook(Book book) {
            borrowedBooks.add(book);
        }

        public void returnBook(Book book) {
            borrowedBooks.remove(book);
        }

        @Override
        public String toString() {
            StringBuilder books = new StringBuilder();
            for (Book b : borrowedBooks) {
                books.append(b.getTitle()).append(", ");
            }
            String borrowed = books.length() > 0 ? books.substring(0, books.length() - 2) : "None";
            return "User " + userId + " - " + name + " | Borrowed Books: " + borrowed;
        }
    }

    // Library class
    static class Library {
        private Map<Integer, Book> books = new HashMap<>();
        private Map<Integer, User> users = new HashMap<>();

        public void addBook(Book book) {
            books.put(book.getBookId(), book);
        }

        public void addUser(User user) {
            users.put(user.getUserId(), user);
        }

        public void issueBook(int bookId, int userId) {
            Book book = books.get(bookId);
            User user = users.get(userId);

            if (book == null) {
                System.out.println("Book not found.");
                return;
            }
            if (user == null) {
                System.out.println("User not found.");
                return;
            }

            if (book.isIssued()) {
                System.out.println("The book '" + book.getTitle() + "' is already issued.");
            } else {
                book.setIssued(true);
                user.borrowBook(book);
                System.out.println("Issued '" + book.getTitle() + "' to " + user.getName() + ".");
            }
        }

        public void returnBook(int bookId, int userId) {
            Book book = books.get(bookId);
            User user = users.get(userId);

            if (book == null || user == null) {
                System.out.println("Book or user not found.");
                return;
            }

            if (user.getBorrowedBooks().contains(book)) {
                user.returnBook(book);
                book.setIssued(false);
                System.out.println(user.getName() + " returned '" + book.getTitle() + "'.");
            } else {
                System.out.println(user.getName() + " does not have '" + book.getTitle() + "' issued.");
            }
        }

        public void displayBooks() {
            System.out.println("\n--- Library Books ---");
            for (Book book : books.values()) {
                System.out.println(book);
            }
        }

        public void displayUsers() {
            System.out.println("\n--- Library Users ---");
            for (User user : users.values()) {
                System.out.println(user);
            }
        }
    }

    // Main method
    public static void main(String[] args) {
        Library library = new Library();

        // Add books
        library.addBook(new Book(1, "1984", "George Orwell"));
        library.addBook(new Book(2, "To Kill a Mockingbird", "Harper Lee"));
        library.addBook(new Book(3, "The Great Gatsby", "F. Scott Fitzgerald"));

        // Add users
        library.addUser(new User(101, "Alice"));
        library.addUser(new User(102, "Bob"));

        library.displayBooks();
        library.displayUsers();

        // Issue books
        library.issueBook(1, 101); // Alice borrows 1984
        library.issueBook(2, 102); // Bob borrows To Kill a Mockingbird
        library.issueBook(1, 102); // Try to re-issue 1984

        library.displayBooks();
        library.displayUsers();

        // Return and re-issue
        library.returnBook(1, 101); // Alice returns 1984
        library.issueBook(1, 102);  // Bob borrows 1984

        library.displayBooks();
        library.displayUsers();
    }
}
